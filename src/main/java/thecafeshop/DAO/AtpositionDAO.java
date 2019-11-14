package thecafeshop.DAO;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.AtpositionDAOImp;
import thecafeshop.entity.Atposition;
import thecafeshop.entity.AtpositionId;
import thecafeshop.reponsitory.AtpositionRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class AtpositionDAO implements AtpositionDAOImp {

	@Autowired
	private EntityManagerFactory  entityManagerFactory;

	@Autowired
	AtpositionRepository atpositionRepository;
	@Override
	public Boolean addAtposition(Atposition atposition) {

		try {
			atpositionRepository.save(atposition);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Atposition getInfoById(AtpositionId atpositionId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			Atposition atposition = entityManager
					.createQuery("FROM Atposition a WHERE a.AtpositionId =:atpositionId AND e.isdelete =:isdelete",
							Atposition.class)
					.setParameter("atpositionId", atpositionId).setParameter("isdelete", this.IS_NOT_DELETE)
					.getSingleResult();
			return atposition;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean deleteAtposition(AtpositionId atpositionId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Atposition atposition = this.getInfoById(atpositionId);
			entityManager.remove(atposition);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editAtposition(Atposition atposition) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.persist(atposition);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean checkExistPosition(String positionid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			List<Atposition> atpositions = entityManager
					.createQuery("FROM Atposition a WHERE a.id =:id AND e.isdelete =:isdelete",
							Atposition.class)
					.setParameter("id", positionid).setParameter("isdelete", this.IS_NOT_DELETE)
					.getResultList();
			if(atpositions.size()>0) {
				return true;
			}
			return false;
		} catch (Exception e) {

			return false;
		}
	}

//	@Override
//	public Atposition getInfoByEmployeeId(String employeeid) {
//
//		Session session = this.sessionFactory.getCurrentSession();
//		try {
//
//			Atposition atposition = session.createQuery(
//					"FROM Atposition a WHERE p.startdate > date() AND a.AtpositionId = :atpositionId AND a.isdelete =: isdelete ORDER BY a.startdate DESC",
//					Atposition.class).setParameter("atpositionId", new AtpositionId(employeeid, null))
//					.setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
//			return atposition;
//		} catch (Exception e) {
//
//			return null;
//		}
//	}

}
