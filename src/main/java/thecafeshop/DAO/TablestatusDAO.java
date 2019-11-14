package thecafeshop.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.TablestatusDAOImp;
import thecafeshop.entity.Tablestatus;
import thecafeshop.reponsitory.TablestatusRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class TablestatusDAO implements TablestatusDAOImp {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Autowired
	TablestatusRepository tablestatusRepository;

	@Override
	public Boolean addTablestatus(Tablestatus tablestatus) {
		try {
			tablestatusRepository.save(tablestatus);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Tablestatus> findAll() {
		try {
			List<Tablestatus> tablestatus = tablestatusRepository.findAll();
			return tablestatus;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Tablestatus getInfoById(int tablestatusid) {

		try {
			Tablestatus tablestatus = tablestatusRepository.findById(tablestatusid).get();
			return tablestatus;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean deleteTablestatus(int tablestatusid) {
		try {
			tablestatusRepository.deleteById(tablestatusid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean editTablestatus(Tablestatus tablestatus) {
		try {
			tablestatusRepository.save(tablestatus);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean checkExist(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Session session = entityManager.unwrap(Session.class);

		try {
			Tablestatus tablestatus = session
					.createQuery("FROM Tablestatus tb WHERE tb.name =:name AND tb.isdelete =:isdelete",
							Tablestatus.class)
					.setParameter("name", name).setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Tablestatus> findLimit(int startPosition) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Session session = entityManager.unwrap(Session.class);
		try {
			List<Tablestatus> tablestatus = session
					.createQuery("FROM Tablestatus tb WHERE tb.isdelete =:isdelete", Tablestatus.class)
					.setFirstResult(startPosition*MAX_RESULTS).setMaxResults(MAX_RESULTS).setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return tablestatus;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}