package thecafeshop.DTO;

import java.util.Date;

public class MaterialDetailDTO {
	private int materialdetailid;
	private String name;
	private Date dateofmanufacture;
	private Date expirationdate;
	private Integer quantity;
	private Integer price;
	private Date updateat;

	public int getMaterialdetailid() {
		return materialdetailid;
	}

	public void setMaterialdetailid(int materialdetailid) {
		this.materialdetailid = materialdetailid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateofmanufacture() {
		return dateofmanufacture;
	}

	public void setDateofmanufacture(Date dateofmanufacture) {
		this.dateofmanufacture = dateofmanufacture;
	}

	public Date getExpirationdate() {
		return expirationdate;
	}

	public void setExpirationdate(Date expirationdate) {
		this.expirationdate = expirationdate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getUpdateat() {
		return updateat;
	}

	public void setUpdateat(Date updateat) {
		this.updateat = updateat;
	}

}
