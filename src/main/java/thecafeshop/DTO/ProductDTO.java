package thecafeshop.DTO;

import thecafeshop.entity.Image;
import thecafeshop.entity.Price;

import java.util.Date;
import java.util.List;


public class ProductDTO {

	private String productid;
	private String categoryproductNAME;
	private String name;
	private Date updateat;
	private Boolean canDelete;
	private List<Image> images;
	private int number;
	private int price;
	private Price newPrice;
	private int rateOldAndNewPrice;
	private boolean checkIsNew;
	private int quantityInventory;
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getCategoryproductNAME() {
		return categoryproductNAME;
	}

	public void setCategoryproductNAME(String categoryproductNAME) {
		this.categoryproductNAME = categoryproductNAME;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUpdateat() {
		return updateat;
	}

	public void setUpdateat(Date updateat) {
		this.updateat = updateat;
	}

	public int getQuantityInventory() {
		return quantityInventory;
	}

	public void setQuantityInventory(int quantityInventory) {
		this.quantityInventory = quantityInventory;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

	public boolean getCheckIsNew() {
		return checkIsNew;
	}

	public void setCheckIsNew(boolean checkIsNew) {
		this.checkIsNew = checkIsNew;
	}

	public Price getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(Price newPrice) {
		this.newPrice = newPrice;
	}

	public int getRateOldAndNewPrice() {
		return rateOldAndNewPrice;
	}

	public void setRateOldAndNewPrice(int rateOldAndNewPrice) {
		this.rateOldAndNewPrice = rateOldAndNewPrice;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
