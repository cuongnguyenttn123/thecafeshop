package thecafeshop.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.CategoryProductDAOImp;
import thecafeshop.entity.Categoryproduct;
import thecafeshop.reponsitory.CategoryproductRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class CategoryProductDAO implements CategoryProductDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	CategoryproductRepository categoryproductRepository;

	@Override
	public Boolean addCategoryProduct(Categoryproduct categoryproduct) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.persist(categoryproduct);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Categoryproduct> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Categoryproduct> liCategoryproducts = entityManager
					.createQuery("FROM Categoryproduct c WHERE c.isdelete = :isdelete", Categoryproduct.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return liCategoryproducts;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Categoryproduct getInfoById(String categoryproductid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			Categoryproduct categoryproduct = entityManager.createQuery(
					"FROM Categoryproduct cp WHERE cp.categoryproductid = :categoryproductid and cp.isdelete =: isdelete",
					Categoryproduct.class).setParameter("categoryproductid", categoryproductid)
					.setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
			return categoryproduct;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Boolean deleteCategoryproduct(String categoryproductid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			categoryproductRepository.deleteById(categoryproductid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editCategoryproduct(Categoryproduct categoryproduct) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.merge(categoryproduct);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Categoryproduct> findLimit(int startPosition) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Categoryproduct> liCategoryproducts = entityManager
					.createQuery("FROM Categoryproduct c WHERE c.isdelete = :isdelete", Categoryproduct.class)
					.setParameter("isdelete", this.IS_NOT_DELETE)
					.setFirstResult(startPosition * this.MAX_RESULTS).setMaxResults(this.MAX_RESULTS)
					.getResultList();
			return liCategoryproducts;
		} catch (Exception e) {
			return null;
		}
	}

}
