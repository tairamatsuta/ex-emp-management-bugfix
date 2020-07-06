package jp.co.sample.emp_management.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

public class InsertEmployeeForm {

	/** 従業員名 */
	@NotBlank(message = "従業員名を入力してください")
	private String name;
	/** 画像 */
	private MultipartFile image;
	/** 性別 */
	@NotEmpty(message = "性別を選択してください")
	private String gender;
	/** 入社年月日 */
	@NotBlank(message = "入社日を入力してください")
	private String hireDate;
	/** メールアドレス */
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "メールアドレスの形式が不正です")
	private String mailAddress;
	/** 郵便番号 */
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号の形式が不正です")
	private String zipCode;
	/** 住所 */
	//@NotBlank(message = "住所を入力してください")
	@Size(min = 1, max = 120, message = "住所は1文字以上120文字以内で入力してください")
	private String address;
	/** 電話番号 */
	@Pattern(regexp = "^0\\d{1,4}-\\d{1,4}-\\d{3,4}$", message = "電話番号の形式が不正です")
	private String telephone;
	/** 給料 */
	//@NotBlank(message = "給料を入力してください")
	@Range(max = 500000, message = "給料は50万円以下で入力してください")
	private String salary;
	/** 特性 */
	//@NotBlank(message = "特性を入力してください")
	@Size(min = 1, max = 2000, message = "特性は1文字以上2000文字以内で入力してください")
	private String characteristics;
	/** 扶養人数 */
	@Pattern(regexp = "^[0-9]+$", message = "扶養人数は数値で入力してください")
	@Range(max = 10, message = "扶養人数は10人以下で入力してください")
	private String dependentsCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public String getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

	@Override
	public String toString() {
		return "InsertEmployeeForm [name=" + name + ", image=" + image + ", gender=" + gender + ", hireDate=" + hireDate
				+ ", mailAddress=" + mailAddress + ", zipCode=" + zipCode + ", address=" + address + ", telephone="
				+ telephone + ", salary=" + salary + ", characteristics=" + characteristics + ", dependentsCount="
				+ dependentsCount + "]";
	}

}
