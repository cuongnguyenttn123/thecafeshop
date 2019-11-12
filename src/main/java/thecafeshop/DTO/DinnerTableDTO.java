package thecafeshop.DTO;


import thecafeshop.entity.Dinnertable;

public class DinnerTableDTO {

	private Dinnertable dinnertable;
	
	private Boolean canDelete;

	public Dinnertable getDinnertable() {
		return dinnertable;
	}

	public void setDinnertable(Dinnertable dinnertable) {
		this.dinnertable = dinnertable;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}
	
	
}
