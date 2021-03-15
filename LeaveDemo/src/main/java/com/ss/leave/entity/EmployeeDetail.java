package com.ss.leave.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class EmployeeDetail implements Serializable{
	/** serialVersionUID **/
	private static final long serialVersionUID = 5984801165236077923L;

	//メール
	@Id
	@Email(message = "メールアドレス")
    @Size(max = 100)
	@NotEmpty
	@Column(unique = true)
    private String mail;
	
	//名前
	@NotEmpty
    @Size(max = 100)
	private String name;
    
    //パスワード
	@NotEmpty
    private String password;
    
    //電話番号
    @Pattern(regexp ="^\\+?[0-9. ()-]{7,25}$", message = "Phone number")
    @Size(max = 25)
    private String phoneNo;
    
    //入社日
    private Date hireDate;
    
    //住所
    private String address;

	//グループ
	private Groups groupNo;
	
	//位置
	private Position position;
    
    public enum Position {
    	Leader,  Member
    }
    
    public enum Groups{
    	グルプ１,グルプ２,グルプ3,グルプ4,ルプ5
    }
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    List<LeaveDetail> leaveDetail = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Groups getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(Groups groupNo) {
		this.groupNo = groupNo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public EmployeeDetail() {
		super();
	}

	public EmployeeDetail(String mail, String name, String password,
			String phoneNo, Date hireDate, String address, Groups groupNo, Position position) {
		this.mail = mail;
		this.name = name;
		this.password = password;
		this.phoneNo = phoneNo;
		this.hireDate = hireDate;
		this.address = address;
		this.groupNo = groupNo;
		this.position = position;
	}

	public EmployeeDetail(@Email(message = "メールアドレス") @Size(max = 100) @NotEmpty String mail,
			@NotEmpty @Size(max = 100) String name, @NotEmpty String password,
			@Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Phone number") @Size(max = 25) String phoneNo,
			Date hireDate, String address, Groups groupNo, Position position, List<LeaveDetail> leaveDetail) {
		this.mail = mail;
		this.name = name;
		this.password = password;
		this.phoneNo = phoneNo;
		this.hireDate = hireDate;
		this.address = address;
		this.groupNo = groupNo;
		this.position = position;
		this.leaveDetail = leaveDetail;
	}

	@Override
	public String toString() {
		return "EmployeeDetail [mail=" + mail + ", name=" + name + ", password=" + password + ", phoneNo=" + phoneNo
				+ ", hireDate=" + hireDate + ", address=" + address + ", groupNo=" + groupNo + ", position=" + position
				+ ", leaveDetail=" + leaveDetail + "]";
	}

}
