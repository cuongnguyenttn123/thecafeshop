package thecafeshop.entity;
// Generated Nov 20, 2018 8:44:18 AM by Hibernate Tools 5.1.7.Final

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Atposition generated by hbm2java
 */
@Entity
@Table(name = "atposition", catalog = "theshopcoffee")
public class Atposition implements java.io.Serializable {

	private AtpositionId id;
	private Employee employee;
	private Position position;
	private Date startdate;
	private Date enddate;
	private Date updateat;
	private Boolean isdelete;

	public Atposition() {
	}

	public Atposition(AtpositionId id, Employee employee, Position position) {
		this.id = id;
		this.employee = employee;
		this.position = position;
	}

	public Atposition(AtpositionId id, Employee employee, Position position, Date startdate, Date enddate,
			Date updateat, Boolean isdelete) {
		this.id = id;
		this.employee = employee;
		this.position = position;
		this.startdate = startdate;
		this.enddate = enddate;
		this.updateat = updateat;
		this.isdelete = isdelete;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "employeeid", column = @Column(name = "EMPLOYEEID", nullable = false, length = 7)),
			@AttributeOverride(name = "positionid", column = @Column(name = "POSITIONID", nullable = false)) })
	public AtpositionId getId() {
		return this.id;
	}

	public void setId(AtpositionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMPLOYEEID", nullable = false, insertable = false, updatable = false)
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "POSITIONID", nullable = false, insertable = false, updatable = false)
	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STARTDATE", length = 10)
	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDATE", length = 10)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
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