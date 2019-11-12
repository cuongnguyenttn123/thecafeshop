package thecafeshop.DTO;


import thecafeshop.entity.Bill;
import thecafeshop.entity.Billstatus;

public class BillDTO {

	private Bill bill;

	private Boolean canDelete;

	private String tableName;

	private int totalPrice;

	private Billstatus billstatus;

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Billstatus getBillstatus() {
		return billstatus;
	}

	public void setBillstatus(Billstatus billstatus) {
		this.billstatus = billstatus;
	}

}
