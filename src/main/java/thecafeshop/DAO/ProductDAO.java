package thecafeshop.DAO;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.ProductDAOImp;
import thecafeshop.entity.Categoryproduct;
import thecafeshop.entity.Product;
import thecafeshop.reponsitory.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class ProductDAO implements ProductDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	ProductRepository productRepository;

	@Override
	public Boolean addProduct(Product product) {

		try {
			productRepository.save(product);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Product getInfoById(String productid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Product product = entityManager
					.createQuery("FROM Product p WHERE p.productid =:productid and p.isdelete =:isdelete",
							Product.class)
					.setParameter("productid", productid).setParameter("isdelete", this.IS_NOT_DELETE)
					.getSingleResult();
			return product;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public List<Product> getListProductLimit(int startPosition, String categoryproductid, String strSearch,
			String isHotDeal, String priceAZ, String priceZA, String productid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			String hql = "FROM Product p WHERE p.isdelete =:isdelete";
			if (categoryproductid != null) {
				hql = hql + " AND p.categoryproduct =:categoryproduct ";
			}
			if (strSearch != null) {
				hql = hql + " AND p.name =:name ";
			}
			if (productid != null) {
				hql = hql + " AND p.productid =:productid ";
			}
//			if (isHotDeal) {
//				hql = hql + " AND p.categoryproduct =: categoryproduct ";
//			}
//			if (priceAZ) {
//				hql = hql + " ORDER BY ASC ";
//			}
//			if (priceZA) {
//				hql = hql + " ORDER BY DESC ";
//			}
			Query query = (Query) entityManager.createQuery(hql, Product.class);
			query.setParameter("isdelete", this.IS_NOT_DELETE);
			if (categoryproductid != null) {
				query.setParameter("categoryproduct", new Categoryproduct(categoryproductid));
			}
			if (strSearch != null) {
				query.setParameter("name", strSearch);
			}
			if (productid != null) {
				query.setParameter("productid", productid);
			}
			query.setFirstResult(startPosition * NUM_PRODUCT_ONE_PAGE);
			query.setMaxResults(NUM_PRODUCT_ONE_PAGE);
			List<Product> products = query.getResultList();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean checkIsNewProduct(String productid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Product product = entityManager
					.createQuery("FROM Product p WHERE p.productid =:productid and p.isdelete =:isdelete",
							Product.class)
					.setParameter("productid", productid).setParameter("isdelete", this.IS_NOT_DELETE)
					.getSingleResult();

			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(product.getUpdateat());
			c2.setTime(new Date());

			long numDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
			if (numDay <= 7) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean deleteProduct(String productid) {

		try {
			productRepository.deleteById(productid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean editProduct(Product product) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(product);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			return false;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<Product> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Product> products = entityManager.createQuery("FROM Product p WHERE p.isdelete =:isdelete", Product.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean checkExistNameProduct(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Product products = entityManager
					.createQuery("FROM Product p WHERE p.name =:name AND p.isdelete =:isdelete", Product.class)
					.setParameter("name", name).setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean checkExistCategoryProduct(String categoryproductid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Product> products = entityManager
					.createQuery("FROM Product p WHERE p.categoryproduct =:categoryproduct and p.isdelete =:isdelete",
							Product.class)
					.setParameter("categoryproduct", new Categoryproduct(categoryproductid))
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			if (products.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public List<Product> findLimit(int startPosition) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Product> products = entityManager.createQuery("FROM Product p WHERE p.isdelete =:isdelete", Product.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).setFirstResult(startPosition * MAX_RESULTS)
					.setMaxResults(MAX_RESULTS).getResultList();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
