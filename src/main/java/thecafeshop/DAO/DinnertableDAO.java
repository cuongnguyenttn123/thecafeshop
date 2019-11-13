package thecafeshop.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.DinnertableDAOImp;
import thecafeshop.entity.Dinnertable;
import thecafeshop.reponsitory.DinnertableRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;



@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class DinnertableDAO implements DinnertableDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	DinnertableRepository dinnertableRepository;

	@Override
	public Boolean addDinnertable(Dinnertable dinnertable) {
		try {
			dinnertableRepository.save(dinnertable);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Dinnertable> findAll() {

		try {
			List<Dinnertable> dinnertables = dinnertableRepository.findAll();
			return dinnertables;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Dinnertable getInfoById(int dinnertableid) {
		try {
			Dinnertable dinnertable = dinnertableRepository.findById(dinnertableid).get();
			return dinnertable;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Boolean deleteDinnertable(int dinnertableid) {

		try {
			dinnertableRepository.deleteById(dinnertableid);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editDinnertable(Dinnertable dinnertable) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.remove(dinnertable);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Dinnertable> findLimit(int startPosition) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Dinnertable> dinnertables = entityManager
					.createQuery("FROM Dinnertable d WHERE d.isdelete =:isdelete", Dinnertable.class)
					.setFirstResult(startPosition * MAX_RESULTS).setMaxResults(MAX_RESULTS)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return dinnertables;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Boolean checkExistDinnerTable(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Dinnertable dinnertable = entityManager
					.createQuery("FROM Dinnertable d WHERE d.name LIKE: name AND d.isdelete =: isdelete",
							Dinnertable.class)
					.setParameter("name", name).setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	@Override
	public List<Integer> getListCountChair() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Dinnertable> dinnertables = entityManager
					.createQuery("FROM Dinnertable d WHERE d.isdelete =:isdelete GROUP BY d.countchair", Dinnertable.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			
			List<Integer> integers = new ArrayList<Integer>();
			for (Dinnertable dinnertable : dinnertables) {
				integers.add(dinnertable.getCountchair());
			}
			
			return integers;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public List<Dinnertable> dsBanTrong() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Dinnertable> dinnertables = entityManager
					.createQuery("FROM Dinnertable d WHERE d.tablestatus.tablestatusid = 5 AND d.isdelete =: isdelete", Dinnertable.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return dinnertables;
		} catch (Exception e) {

			return null;
		}
	}
}