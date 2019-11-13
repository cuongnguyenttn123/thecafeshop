package thecafeshop.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.ExportbillDAOImp;
import thecafeshop.entity.Exportbill;
import thecafeshop.reponsitory.ExportbillRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class ExportbillDAO implements ExportbillDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	ExportbillRepository exportbillRepository;

	@Override
	public int addExportbill(Exportbill exportbill) {

		try {
			exportbillRepository.save(exportbill);
			return exportbill.getExportbillid();
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public List<Exportbill> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Exportbill> exportbills = entityManager
					.createQuery("FROM Exportbill e WHERE e.isdelete =:isdelete", Exportbill.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return exportbills;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Exportbill getInfoById(int exportbillid) {

		try {

			Exportbill exportbill = exportbillRepository.findById(exportbillid).get();
			return exportbill;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Boolean deleteExportbill(int exportbillid) {
		try {
			exportbillRepository.deleteById(exportbillid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editExportbill(Exportbill exportbill) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.merge(exportbill);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int totalQuantityProduct(String productid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			int total = 0;
			List<Exportbill> exportbills = entityManager.createQuery(
					"FROM Exportbill e WHERE e.product.productid =:productid AND e.quantity > 0 AND e.isdelete =:isdelete",
					Exportbill.class)
					.setParameter("productid", productid)
					.setParameter("isdelete", this.IS_NOT_DELETE)
					.getResultList();
			for (Exportbill exportbill : exportbills) {
				total += exportbill.getQuantityInventory();
			}
			return total;
		} catch (Exception e) {

			return 0;
		}
	}

	@Override
	public List<Exportbill> getListExportBillbyProduct(String productid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Exportbill> exportbills = entityManager.createQuery(
					"FROM Exportbill e WHERE e.product.productid =:productid AND e.quantityInventory > 0 AND e.isdelete =:isdelete ORDER BY updateat ASC",
					Exportbill.class)
					.setParameter("productid", productid).setParameter("isdelete", this.IS_NOT_DELETE)
					.getResultList();

			return exportbills;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public List<Exportbill> findLimit(int startPosition) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			List<Exportbill> exportbills = entityManager
					.createQuery("FROM Exportbill e WHERE e.isdelete =:isdelete", Exportbill.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).setFirstResult(startPosition*MAX_RESULTS).setMaxResults(MAX_RESULTS).getResultList();
			return exportbills;
		} catch (Exception e) {

			return null;
		}
	}

}