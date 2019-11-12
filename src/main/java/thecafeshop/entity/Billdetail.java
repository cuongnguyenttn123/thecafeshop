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
 * Billdetail generated by hbm2java
 */
@Entity
@Table(name = "billdetail", catalog = "theshopcoffee")
public class Billdetail implements java.io.Serializable {

	private BilldetailId id;
	private Bill bill;
	private Product product;
	private Integer quantity;
	private Date updateat;
	private Boolean isdelete;

	public Billdetail() {
	}

	public Billdetail(BilldetailId id, Bill bill, Product product) {
		this.id = id;
		this.bill = bill;
		this.product = product;
	}

	public Billdetail(BilldetailId id, Bill bill, Product product, Integer quantity, Date updateat, Boolean isdelete) {
		this.id = id;
		this.bill = bill;
		this.product = product;
		this.quantity = quantity;
		this.updateat = updateat;
		this.isdelete = isdelete;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "productid", column = @Column(name = "PRODUCTID", nullable = false, length = 7)),
			@AttributeOverride(name = "billid", column = @Column(name = "BILLID", nullable = false)) })
	public BilldetailId getId() {
		return this.id;
	}

	public void setId(BilldetailId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BILLID", nullable = false, insertable = false, updatable = false)
	public Bill getBill() {
		return this.bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCTID", nullable = false, insertable = false, updatable = false)
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