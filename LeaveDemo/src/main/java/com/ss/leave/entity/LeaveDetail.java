package com.ss.leave.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "leave_contact")
public class LeaveDetail implements Serializable{
	/** serialVersionUID **/
	private static final long serialVersionUID = 6067992868469352830L;

	//勤怠連絡ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int leaveId;

	//社員ID
	@ManyToOne
	@JoinColumn(name="mail")
	private EmployeeDetail employee;

	//日付
	@NotEmpty
	private String leaveDate;
	
	//勤怠連絡
	@NotEmpty
	private LeaveType leaveType;
	
	//種類
	@NotEmpty
	private Type type;
	
	//現場連絡
	private Boolean approvedSiteContact;
	
	public enum Type{
		有給連, 欠勤連絡, 代休連絡, 休日出勤, シフト出勤, 遅刻, 早退, 現場出勤, テレワーク
	}
	
	public enum LeaveType{
		全休, 午前半休, 午後半休, 全日出勤, 午前出勤, 午後出勤, 離席
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public String getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public Boolean getApprovedSiteContact() {
		return approvedSiteContact;
	}

	public void setApprovedSiteContact(Boolean approvedSiteContact) {
		this.approvedSiteContact = approvedSiteContact;
	}

	public EmployeeDetail getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDetail optional) {
		this.employee = optional;
	}

	public LeaveDetail() {
		super();
	}

	public LeaveDetail(String leaveDate, LeaveType leaveType, Type type,
			Boolean approvedSiteContact) {
		this.leaveDate = leaveDate;
		this.leaveType = leaveType;
		this.type = type;
		this.approvedSiteContact = approvedSiteContact;
	}

	public LeaveDetail(EmployeeDetail employee, String leaveDate, LeaveType leaveType, Type type,
			Boolean approvedSiteContact) {
		this.employee = employee;
		this.leaveDate = leaveDate;
		this.leaveType = leaveType;
		this.type = type;
		this.approvedSiteContact = approvedSiteContact;
	}

	@Override
	public String toString() {
		return "LeaveDetail [leaveId=" + leaveId + ",  leaveDate=" + leaveDate
				+ ", leaveType=" + leaveType + ", type=" + type + ", approvedSiteContact=" + approvedSiteContact + "]";
	}
}