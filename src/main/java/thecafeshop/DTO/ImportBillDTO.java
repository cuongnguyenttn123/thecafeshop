package thecafeshop.DTO;


import thecafeshop.entity.Employee;
import thecafeshop.entity.Importbill;
import thecafeshop.entity.Supplier;

public class ImportBillDTO {

	private Importbill importbill;

	private Boolean canDelete;

	private Employee employee;

	private Supplier supplier;

	private int countBillDetail;

	public int getCountBillDetail() {
		return countBillDetail;
	}

	public void setCountBillDetail(int countBillDetail) {
		this.countBillDetail = countBillDetail;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Importbill getImportbill() {
		return importbill;
	}

	public void setImportbill(Importbill importbill) {
		this.importbill = importbill;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

}
