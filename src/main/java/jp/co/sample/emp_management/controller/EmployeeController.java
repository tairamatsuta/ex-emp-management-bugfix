package jp.co.sample.emp_management.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jp.co.sample.emp_management.domain.Employee;
import jp.co.sample.emp_management.form.InsertEmployeeForm;
import jp.co.sample.emp_management.form.UpdateEmployeeForm;
import jp.co.sample.emp_management.service.EmployeeService;
import net.arnx.jsonic.JSON;

/**
 * 従業員情報を操作するコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpForm() {
		return new UpdateEmployeeForm();
	}
	
	@ModelAttribute
	public InsertEmployeeForm setUpInsertEmployeeForm() {
		return new InsertEmployeeForm();
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員一覧を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員一覧画面を出力します.
	 * 
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(String fuzzyName, Model model) {
		List<Employee> employeeList = employeeService.showList(fuzzyName);
		if(employeeList.size() == 0) {
			model.addAttribute("message", "1件もありませんでした。");
			employeeList = employeeService.showList("");
		}
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	
	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細画面を出力します.
	 * 
	 * @param id リクエストパラメータで送られてくる従業員ID
	 * @param model モデル
	 * @return 従業員詳細画面
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}
	
	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を更新する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細(ここでは扶養人数のみ)を更新します.
	 * 
	 * @param form
	 *            従業員情報用フォーム
	 * @return 従業員一覧画面へリダクレクト
	 */
	@RequestMapping("/update")
	public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return showDetail(form.getId(), model);
		}
		Employee employee = new Employee();
		employee.setId(form.getIntId());
		employee.setDependentsCount(form.getIntDependentsCount());
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}
	
	/////////////////////////////////////////////////////
	// ユースケース：従業員情報を登録する
	/////////////////////////////////////////////////////
	/**
	 * 従業員登録画面を出力します.
	 * 
	 * @return 従業員登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "employee/insert";
	}
	
	/**
	 * 従業員情報を登録します.
	 * 
	 * @param form 従業員情報用フォーム
	 * @return 従業員一覧へリダイレクト
	 * @throws IOException 
	 */
	@RequestMapping("/insert")
	synchronized public String insert(
			@Validated InsertEmployeeForm form
			, BindingResult result
			, Model model) throws IOException {
		
		MultipartFile image = form.getImage();
		String fileExtension = null;
		try {
			fileExtension = getFileExtension(image.getOriginalFilename());
			if(!"jpg".equals(fileExtension) && !"png".equals(fileExtension)) {
				result.rejectValue("image", "", "拡張子は.jpgか.pngにのみ対応しています");
			}
		} catch (Exception e) {
			System.out.println("catch中");
			result.rejectValue("image", "", "拡張子は.jpgか.pngにのみ対応しています");
		}
//		画像をStringで保存していたバージョン
//		System.out.println(form.toString());
//		if(form.getImage().equals("")) {
//			FieldError imageError = new FieldError(result.getObjectName(), "image", "画像をアップロードしてください");
//			result.addError(imageError);
//		}
		Employee existEmployee = employeeService.findByMailAddress(form.getMailAddress());
		if(existEmployee != null) {
			FieldError employeeMailAddressError = new FieldError(result.getObjectName(), "mailAddress", "このメールアドレスは既に登録されています");
			result.addError(employeeMailAddressError);
		}
		if(result.hasErrors()) {
			return toInsert();
		}
		Employee employee = new Employee();
		// フォームからドメインにプロパティ値をコピー
		BeanUtils.copyProperties(form, employee);
		// コピーできなかったプロパティ値を手動でコピー
		String base64FileString = Base64.getEncoder().encodeToString(image.getBytes());
		if("jpg".equals(fileExtension)) {
			base64FileString = "data:image/jpeg;base64," + base64FileString;
		} else if("png".equals(fileExtension)) {
			base64FileString = "data:image/png;base64," + base64FileString;
		}
		employee.setImage(base64FileString);
		employee.setHireDate(Date.valueOf(form.getHireDate()));
		employee.setSalary(Integer.parseInt(form.getSalary()));
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		employeeService.insert(employee);
		return "redirect:/employee/showList";
	}
	
	/**
	 * 画像ファイル名から拡張子の名前を取り出して返す.
	 * 
	 * @param originalFileName 画像ファイル名
	 * @return　拡張子名
	 * @throws Exception
	 */
	private String getFileExtension(String originalFileName) throws Exception {
		if(originalFileName == null) {
			throw new FileNotFoundException();
		}
		int point = originalFileName.lastIndexOf(".");
		if(point == -1) {
			throw new FileNotFoundException();
		}
		return originalFileName.substring(point + 1);
	}
	
	
	/**
	 * オートコンプリート用のnameListを生成する.
	 * @return　JSON形式でnameListを返す
	 */
	@ResponseBody
	@RequestMapping(value = "/autoComplete", method = RequestMethod.GET)
	public String getAutoComplete() {
		List<String> nameList = employeeService.showAllName();
		return JSON.encode(nameList);
	}
}
