package thecafeshop.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.PositionDAOImp;
import thecafeshop.entity.Position;
import thecafeshop.reponsitory.PositionRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;



@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class PositionDAO implements PositionDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	PositionRepository positionRepository;

	@Override
	public Boolean addPosition(Position position) {
		try {
			positionRepository.save(position);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Position> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			List<Position> positions = entityManager
					.createQuery("FROM Position p WHERE p.isdelete =:isdelete", Position.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return positions;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Position getInfoById(String positionid) {
		try {

			Position position = positionRepository.findById(positionid).get();
			return position;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Boolean deletePosition(String positionid) {
		try {
			positionRepository.deleteById(positionid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editPosition(Position position) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(position);
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
	public Position getInfoByName(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			Position position = entityManager
					.createQuery("FROM Position p WHERE p.name =:name AND p.isdelete =:isdelete", Position.class)
					.setParameter("name", name)
					.setParameter("isdelete", this.IS_NOT_DELETE)
					.getSingleResult();
			return position;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public List<Position> findLimit(int startPosition) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			List<Position> positions = entityManager
					.createQuery("FROM Position p WHERE p.isdelete =:isdelete", Position.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).setFirstResult(startPosition * MAX_RESULTS)
					.setMaxResults(MAX_RESULTS).getResultList();
			return positions;
		} catch (Exception e) {

			return null;
		}
	}

}