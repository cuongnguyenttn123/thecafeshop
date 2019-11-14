package thecafeshop.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.BooktableDAOImp;
import thecafeshop.entity.Booktable;
import thecafeshop.entity.BooktableId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class BooktableDAO implements BooktableDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Override
	public Boolean addBooktable(Booktable booktable) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.persist(booktable);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Booktable> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Booktable> booktables = entityManager
					.createQuery("FROM Booktable b WHERE b.isdelete =:isdelete", Booktable.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return booktables;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Booktable getInfoByBooktableId(BooktableId booktableId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Booktable booktable = entityManager
					.createQuery("FROM Booktable b WHERE BooktableId =:booktableId AND b.isdelete =:isdelete",
							Booktable.class)
					.setParameter("booktableId", booktableId).setParameter("isdelete", this.IS_NOT_DELETE)
					.getSingleResult();
			return booktable;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Boolean deleteBooktable(BooktableId booktableId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Booktable booktable = this.getInfoByBooktableId(booktableId);
			entityManager.remove(booktable);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editBooktable(Booktable booktable) {
		return null;
	}

	@Override
	public Boolean checkExistDinnerTable(int dinnertableid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Booktable> booktables = entityManager
					.createQuery(
							"FROM Booktable b WHERE b.id.dinnertableid =:dinnertableid AND b.isdelete =:isdelete",
							Booktable.class)
					.setParameter("dinnertableid", dinnertableid).setParameter("isdelete", this.IS_NOT_DELETE)
					.getResultList();
			if (booktables.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

}