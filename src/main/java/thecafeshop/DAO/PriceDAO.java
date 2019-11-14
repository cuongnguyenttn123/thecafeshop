package thecafeshop.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.PriceDAOImp;
import thecafeshop.entity.Price;
import thecafeshop.entity.Product;
import thecafeshop.reponsitory.PriceRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class PriceDAO implements PriceDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	PriceRepository priceRepository;

	@Override
	public Boolean addPrice(Price price) {

		try {
			priceRepository.save(price);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Price getInfoById(String priceid) {
		try {
			Price price = priceRepository.findById(Integer.parseInt(priceid)).get();
			return price;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Price getInfoByProduct(String PId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Price Price = entityManager.createQuery(
					"FROM Price p WHERE p.product = :product AND p.isdelete =:isdelete ORDER BY p.startdatetime > now()",
					Price.class).setParameter("product", new Product(PId)).setParameter("isdelete", this.IS_NOT_DELETE)
					.getSingleResult();
			return Price;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Price getNewPrice(String productid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Price price = entityManager.createQuery(
					"FROM Price p WHERE p.product = :product AND p.isdelete =:isdelete AND p.startdatetime > now()",
					Price.class).setParameter("product", new Product(productid))
					.setParameter("isdelete", this.IS_NOT_DELETE).setMaxResults(1).getSingleResult();
			return price;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getOldPrice(String productid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Price price = entityManager.createQuery(
					"FROM Price p WHERE p.product = :product AND p.isdelete =:isdelete AND p.startdatetime <= now() ORDER BY p.startdatetime DESC",
					Price.class).setParameter("product", new Product(productid))
					.setParameter("isdelete", this.IS_NOT_DELETE).setMaxResults(1).getSingleResult();
			return price.getPrice();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Boolean editPrice(Price price) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(price);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			return false;
		}finally {
			entityManager.close();
		}
	}
}
