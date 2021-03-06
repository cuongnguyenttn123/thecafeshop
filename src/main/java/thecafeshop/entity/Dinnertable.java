package thecafeshop.entity;
// Generated Nov 20, 2018 8:44:18 AM by Hibernate Tools 5.1.7.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Dinnertable generated by hbm2java
 */
@Entity
@Table(name = "dinnertable", catalog = "theshopcoffee")
public class Dinnertable implements java.io.Serializable {

	private int dinnertableid;
	private Tablestatus tablestatus;
	private String name;
	private Integer countchair;
	private Date updateat;
	private Boolean isdelete;
	private Set<Booktable> booktables = new HashSet<Booktable>(0);
	private Set<Bill> bills = new HashSet<Bill>(0);

	public Dinnertable() {
	}

	public Dinnertable(int dinnertableid) {
		this.dinnertableid = dinnertableid;
	}

	public Dinnertable(int dinnertableid, Tablestatus tablestatus, String name, Integer countchair, Date updateat,
			Boolean isdelete, Set<Booktable> booktables, Set<Bill> bills) {
		this.dinnertableid = dinnertableid;
		this.tablestatus = tablestatus;
		this.name = name;
		this.countchair = countchair;
		this.updateat = updateat;
		this.isdelete = isdelete;
		this.booktables = booktables;
		this.bills = bills;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "DINNERTABLEID", unique = true, nullable = false)
	public int getDinnertableid() {
		return this.dinnertableid;
	}

	public void setDinnertableid(int dinnertableid) {
		this.dinnertableid = dinnertableid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TABLESTATUSID")
	public Tablestatus getTablestatus() {
		return this.tablestatus;
	}

	public void setTablestatus(Tablestatus tablestatus) {
		this.tablestatus = tablestatus;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "COUNTCHAIR")
	public Integer getCountchair() {
		return this.countchair;
	}

	public void setCountchair(Integer countchair) {
		this.countchair = countchair;
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "dinnertable")
	public Set<Booktable> getBooktables() {
		return this.booktables;
	}

	public void setBooktables(Set<Booktable> booktables) {
		this.booktables = booktables;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "dinnertable")
	public Set<Bill> getBills() {
		return this.bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

}
