package thecafeshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAO.CategoryProductDAO;
import thecafeshop.DAOImp.CategoryProductDAOImp;
import thecafeshop.entity.Categoryproduct;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CategoryProductService implements CategoryProductDAOImp {

	@Autowired
	private CategoryProductDAO categoryProductDAO;

	@Override
	public Boolean addCategoryProduct(Categoryproduct categoryproduct) {
		// TODO Auto-generated method stub
		return categoryProductDAO.addCategoryProduct(categoryproduct);
	}

	@Override
	public List<Categoryproduct> findAll() {

		return categoryProductDAO.findAll();
	}

	@Override
	public Categoryproduct getInfoById(String categoryproductid) {

		return categoryProductDAO.getInfoById(categoryproductid);
	}

	@Override
	public Boolean deleteCategoryproduct(String categoryproductid) {
		// TODO Auto-generated method stub
		return categoryProductDAO.deleteCategoryproduct(categoryproductid);
	}

	@Override
	public Boolean editCategoryproduct(Categoryproduct categoryproduct) {
		// TODO Auto-generated method stub
		return categoryProductDAO.editCategoryproduct(categoryproduct);
	}

	@Override
	public List<Categoryproduct> findLimit(int startPosition) {
		// TODO Auto-generated method stub
		return categoryProductDAO.findLimit(startPosition);
	}

}
