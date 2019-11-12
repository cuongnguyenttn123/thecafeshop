package thecafeshop.DAOImp;

import thecafeshop.entity.Product;

import java.util.List;


public interface ProductDAOImp extends CommonDAOImp {

	public Boolean addProduct(Product product);

	public List<Product> findAll();

	public List<Product> findLimit(int startPosition);
	
	public List<Product> getListProductLimit(int startPosition, String cgPrdId, String strSearch, String isHotDeal,
                                             String priceAZ, String priceZA, String productid);

	public Boolean checkExistCategoryProduct(String categoryproductid);

	public Product getInfoById(String productid);

	public Boolean checkExistNameProduct(String name);

	public Boolean checkIsNewProduct(String productid);

	public Boolean deleteProduct(String productid);

	public Boolean editProduct(Product product);
}
