package com.ss.leave.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date leaveDate;
	//勤怠連絡
	@NotEmpty
	private LeaveType leaveType;	
	//種類
	@NotEmpty
	private Type type;
	//現場連絡
	private Confirm approvedSiteContact;
	//理由
	@NotEmpty
	private String reason;
	//承認
	private Confirm approved;
	
	public enum Type{
		有給連, 欠勤連絡, 代休連絡, 休日出勤, シフト出勤, 遅刻, 早退, 現場出勤, テレワーク
	}
	
	public enum LeaveType{
		全休, 午前半休, 午後半休, 全日出勤, 午前出勤, 午後出勤, 離席
	}
	public enum Confirm{
		済み, 未済み
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
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

	public Confirm getApprovedSiteContact() {
		return approvedSiteContact;
	}

	public void setApprovedSiteContact(Confirm approvedSiteContact) {
		this.approvedSiteContact = approvedSiteContact;
	}

	public EmployeeDetail getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDetail optional) {
		this.employee = optional;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Confirm getApproved() {
		return approved;
	}

	public void setApproved(Confirm approved) {
		this.approved = approved;
	}

	public LeaveDetail() {
		super();
	}

	@Override
	public String toString() {
		return "LeaveDetail [leaveId=" + leaveId + ",  leaveDate=" + leaveDate
				+ ", leaveType=" + leaveType + ", type=" + type + ", approvedSiteContact=" + approvedSiteContact + "]";
	}

	public LeaveDetail(@NotEmpty Date leaveDate, @NotEmpty LeaveType leaveType, @NotEmpty Type type,
			Confirm approvedSiteContact, @NotEmpty String reason, Confirm approved) {
		super();
		this.leaveDate = leaveDate;
		this.leaveType = leaveType;
		this.type = type;
		this.approvedSiteContact = approvedSiteContact;
		this.reason = reason;
		this.approved = approved;
	}

	public LeaveDetail(EmployeeDetail employee, @NotEmpty Date leaveDate, @NotEmpty LeaveType leaveType,
			@NotEmpty Type type, Confirm approvedSiteContact, @NotEmpty String reason, Confirm approved) {
		super();
		this.employee = employee;
		this.leaveDate = leaveDate;
		this.leaveType = leaveType;
		this.type = type;
		this.approvedSiteContact = approvedSiteContact;
		this.reason = reason;
		this.approved = approved;
	}

}