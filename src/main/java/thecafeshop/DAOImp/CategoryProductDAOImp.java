package thecafeshop.DAOImp;


import thecafeshop.entity.Categoryproduct;

import java.util.List;

public interface CategoryProductDAOImp extends CommonDAOImp {


	public Boolean addCategoryProduct(Categoryproduct categoryproduct);

	public List<Categoryproduct> findAll();
	
	public List<Categoryproduct> findLimit(int startPosition);
	
	public Categoryproduct getInfoById(String categoryproductid);

	public Boolean deleteCategoryproduct(String categoryproductid);

	public Boolean editCategoryproduct(Categoryproduct categoryproduct);
}
