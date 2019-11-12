package thecafeshop.entity;
// Generated Nov 20, 2018 8:44:18 AM by Hibernate Tools 5.1.7.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Register generated by hbm2java
 */
@Entity
@Table(name = "register", catalog = "theshopcoffee")
public class Register implements java.io.Serializable {

	private int registerid;
	private Employee employee;
	private Schedule schedule;
	private Date date;
	private int result;
	private Date updateat;
	private Boolean isdelete;

	public Register() {
	}

	public Register(int registerid) {
		this.registerid = registerid;
	}

	public Register(int registerid, Employee employee, Schedule schedule, Date date, int result, Date updateat,
			Boolean isdelete) {
		this.registerid = registerid;
		this.employee = employee;
		this.schedule = schedule;
		this.date = date;
		this.result = result;
		this.updateat = updateat;
		this.isdelete = isdelete;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "REGISTERID", unique = true, nullable = false)
	public int getRegisterid() {
		return this.registerid;
	}

	public void setRegisterid(int registerid) {
		this.registerid = registerid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMPLOYEEID")
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SCHEDULEID")
	public Schedule getSchedule() {
		return this.schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE", length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "RESULT")
	public int getResult() {
		return this.result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATEAT", length = 19)
	public Date getUpdateat() {
		return this.updateat;
	}

	public void setUpdateat(Date updateat) {
		this.updateat = updateat;
	}

	@Column(name = "ISDELETE")
	public Boolean getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(Boolean isdelete) {
		this.isdelete = isdelete;
	}

}