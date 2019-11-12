package thecafeshop.DTO;


import thecafeshop.entity.Dinnertable;

public class indexAdminDTO {

	private Dinnertable dinnertable;
	
	private int totalBill;
	
	private String statusTable;

	public Dinnertable getDinnertable() {
		return dinnertable;
	}

	public void setDinnertable(Dinnertable dinnertable) {
		this.dinnertable = dinnertable;
	}

	public int getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(int totalBill) {
		this.totalBill = totalBill;
	}

	public String getStatusTable() {
		return statusTable;
	}

	public void setStatusTable(String statusTable) {
		this.statusTable = statusTable;
	}
	
}
