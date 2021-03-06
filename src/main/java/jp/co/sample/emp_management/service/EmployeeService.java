package jp.co.sample.emp_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.emp_management.domain.Employee;
import jp.co.sample.emp_management.repository.EmployeeRepository;

/**
 * 従業員情報を操作するサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 従業員情報を全件取得します.
	 * 
	 * @return　従業員情報一覧
	 */
	public List<Employee> showList(String fuzzyName) {
		if(fuzzyName == null) {
			List<Employee> employeeList = employeeRepository.findAll();			
			return employeeList;
		}
		List<Employee> employeeList = employeeRepository.findByName(fuzzyName);
		return employeeList;
	}
	
	/**
	 * 従業員情報を取得します.
	 * 
	 * @param id ID
	 * @return 従業員情報
	 * @throws 検索されない場合は例外が発生します
	 */
	public Employee showDetail(Integer id) {
		Employee employee = employeeRepository.load(id);
		return employee;
	}
	
	/**
	 * 従業員情報を更新します.
	 * 
	 * @param employee　更新した従業員情報
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}
	
	public List<String> showAllName(){
		return employeeRepository.findAllName();
	}
	
	/**
	 * 従業員情報を登録します.
	 * 
	 * @param employee 新規従業員情報
	 */
	public void insert(Employee employee) {
		int insertId = employeeRepository.findMaxId() + 1;
		employee.setId(insertId);
		employeeRepository.insertEmployee(employee);
	}
	
	/**
	 * メールアドレスから従業員情報を検索します.
	 * @param mailAddress　メールアドレス
	 * @return　管理者情報　存在しない場合はnullが返ります
	 */
	public Employee findByMailAddress(String mailAddress) {
		return employeeRepository.findByMailAddress(mailAddress);
	}
}
