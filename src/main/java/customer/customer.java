package customer;

import java.io.Serializable;
import java.util.Date;

public class customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String custNRIC;
	private String custEmail;
	private String custProfilePic;
	private int age;
	private Date DOB;
	private String custPassword;
	private String custUsername;
	private String custPhoneNo;
	private String custName;
	
	public String getCustNRIC() {
		return custNRIC;
	}
	public void setCustNRIC(String custNRIC) {
		this.custNRIC = custNRIC;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCustProfilePic() {
		return custProfilePic;
	}
	public void setCustProfilePic(String custProfilePic) {
		this.custProfilePic = custProfilePic;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public String getCustPassword() {
		return custPassword;
	}
	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}
	public String getCustUsername() {
		return custUsername;
	}
	public void setCustUsername(String custUsername) {
		this.custUsername = custUsername;
	}
	public String getCustPhoneNo() {
		return custPhoneNo;
	}
	public void setCustPhoneNo(String custPhoneNo) {
		this.custPhoneNo = custPhoneNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
}
