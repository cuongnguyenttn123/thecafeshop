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
 * Bill generated by hbm2java
 */
@Entity
@Table(name = "bill", catalog = "theshopcoffee")
public class Bill implements java.io.Serializable {

	private int billid;
	private Billstatus billstatus;
	private Customer customer;
	private Dinnertable dinnertable;
	private Employee employee;
	private Voucher voucher;
	private Date startdatetime;
	private Date enddate;
	private String notice;
	private Date updateat;
	private Boolean isdelete;
	private Set<Billdetail> billdetails = new HashSet<Billdetail>(0);

	public Bill() {
	}

	public Bill(int billid) {
		this.billid = billid;
	}

	public Bill(int billid, Billstatus billstatus, Customer customer, Dinnertable dinnertable, Employee employee,
			Voucher voucher, Date startdatetime, Date enddate, String notice, Date updateat, Boolean isdelete,
			Set<Billdetail> billdetails) {
		this.billid = billid;
		this.billstatus = billstatus;
		this.customer = customer;
		this.dinnertable = dinnertable;
		this.employee = employee;
		this.voucher = voucher;
		this.startdatetime = startdatetime;
		this.enddate = enddate;
		this.notice = notice;
		this.updateat = updateat;
		this.isdelete = isdelete;
		this.billdetails = billdetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "BILLID", unique = true, nullable = false)
	public int getBillid() {
		return this.billid;
	}

	public void setBillid(int billid) {
		this.billid = billid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BILLSTATUSID")
	public Billstatus getBillstatus() {
		return this.billstatus;
	}

	public void setBillstatus(Billstatus billstatus) {
		this.billstatus = billstatus;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUSTOMERID")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DINNERTABLEID")
	public Dinnertable getDinnertable() {
		return this.dinnertable;
	}

	public void setDinnertable(Dinnertable dinnertable) {
		this.dinnertable = dinnertable;
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
	@JoinColumn(name = "VOUCHERID")
	public Voucher getVoucher() {
		return this.voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STARTDATETIME", length = 19)
	public Date getStartdatetime() {
		return this.startdatetime;
	}

	public void setStartdatetime(Date startdatetime) {
		this.startdatetime = startdatetime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDATE", length = 10)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Column(name = "NOTICE")
	public String getNotice() {
		return this.notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "bill")
	public Set<Billdetail> getBilldetails() {
		return this.billdetails;
	}

	public void setBilldetails(Set<Billdetail> billdetails) {
		this.billdetails = billdetails;
	}

}
