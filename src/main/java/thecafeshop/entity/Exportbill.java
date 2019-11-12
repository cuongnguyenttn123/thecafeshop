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
 * Exportbill generated by hbm2java
 */
@Entity
@Table(name = "exportbill", catalog = "theshopcoffee")
public class Exportbill implements java.io.Serializable {

	private int exportbillid;
	private Employee employee;
	private Product product;
	private Integer quantity;
	private Integer quantityInventory;
	private Integer quantityThrow;
	private Date updateat;
	private Boolean isdelete;
	private Set<Exportbilldetail> exportbilldetails = new HashSet<Exportbilldetail>(0);

	public Exportbill() {
	}

	public Exportbill(int exportbillid) {
		this.exportbillid = exportbillid;
	}

	public Exportbill(int exportbillid, Employee employee, Product product, Integer quantity, Integer quantityInventory,
			Integer quantityThrow, Date updateat, Boolean isdelete, Set<Exportbilldetail> exportbilldetails) {
		this.exportbillid = exportbillid;
		this.employee = employee;
		this.product = product;
		this.quantity = quantity;
		this.quantityInventory = quantityInventory;
		this.quantityThrow = quantityThrow;
		this.updateat = updateat;
		this.isdelete = isdelete;
		this.exportbilldetails = exportbilldetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "EXPORTBILLID", unique = true, nullable = false)
	public int getExportbillid() {
		return this.exportbillid;
	}

	public void setExportbillid(int exportbillid) {
		this.exportbillid = exportbillid;
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
	@JoinColumn(name = "PRODUCTID")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "QUANTITY")
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "QUANTITYINVENTORY")
	public Integer getQuantityInventory() {
		return this.quantityInventory;
	}

	public void setQuantityInventory(int quantityInventory) {
		this.quantityInventory = quantityInventory;
	}

	@Column(name = "QUANTITYTHROW")
	public Integer getQuantityThrow() {
		return this.quantityThrow;
	}

	public void setQuantityThrow(int quantityThrow) {
		this.quantityThrow = quantityThrow;
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "exportbill")
	public Set<Exportbilldetail> getExportbilldetails() {
		return this.exportbilldetails;
	}

	public void setExportbilldetails(Set<Exportbilldetail> exportbilldetails) {
		this.exportbilldetails = exportbilldetails;
	}

}
