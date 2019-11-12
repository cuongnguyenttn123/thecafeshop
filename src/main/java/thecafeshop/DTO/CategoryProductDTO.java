package thecafeshop.DTO;


import thecafeshop.entity.Categoryproduct;

public class CategoryProductDTO {
	
	private Categoryproduct categoryproduct;
	
	public Categoryproduct getCategoryproduct() {
		return categoryproduct;
	}

	public void setCategoryproduct(Categoryproduct categoryproduct) {
		this.categoryproduct = categoryproduct;
	}

	public Boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

	private Boolean canDelete;

}
