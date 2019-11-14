package thecafeshop.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.BillstatusDAOImp;
import thecafeshop.entity.Billstatus;
import thecafeshop.reponsitory.BillstatusRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class BillstatusDAO implements BillstatusDAOImp {

	@Autowired
	BillstatusRepository billstatusRepository;

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	public Boolean addBillstatus(Billstatus billstatus) {

		try {
			billstatusRepository.save(billstatus);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Billstatus> findAll() {
		try {
			List<Billstatus> billstatus = billstatusRepository.findAll();
			return billstatus;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Billstatus getInfoById(String billstatusid) {
		try {
			Billstatus billstatus = billstatusRepository.findById(billstatusid).get();
			return billstatus;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean deleteBillstatus(String billstatusid) {
		try {
			billstatusRepository.deleteById(billstatusid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editBilldetail(Billstatus billstatus) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(billstatus);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			return false;
		}
	}

	@Override
	public List<Billstatus> findLimit(int startPosition) {
		EntityManager entityManager  = entityManagerFactory.createEntityManager();
		try {
			List<Billstatus> billstatus = entityManager
					.createQuery("from Billstatus b WHERE b.isdelete =?1", Billstatus.class)
					.setParameter(1, this.IS_NOT_DELETE)
					.setFirstResult(startPosition*this.MAX_RESULTS)
					.setMaxResults(this.MAX_RESULTS).getResultList();
			return billstatus;
		} catch (Exception e) {
			return null;
		}
	}
}