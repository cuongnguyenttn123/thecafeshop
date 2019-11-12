package thecafeshop.DTO;

import thecafeshop.entity.Supplier;

public class SupplierDTO {

	private Supplier supplier;

	private Boolean canDelete;

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

}
