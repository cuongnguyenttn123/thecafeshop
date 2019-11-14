package thecafeshop.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.RegisterDAOImp;
import thecafeshop.entity.Register;
import thecafeshop.reponsitory.RegisterRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class RegisterDAO implements RegisterDAOImp {

	@Autowired
	EntityManagerFactory entityManagerFactory;


	@Autowired
	RegisterRepository registerRepository;

	@Override
	public Boolean addRegister(Register register) {

		try {
			registerRepository.save(register);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Register getInfoById(int registerid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Register register = entityManager
					.createQuery("FROM Register r WHERE r.registerid =:registerid and r.isdelete =:isdelete",
							Register.class)
					.setParameter("registerid", registerid).setParameter("isdelete", this.IS_NOT_DELETE)
					.getSingleResult();
			return register;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Register> getListRegisterOnWeek(Date from, Date to) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Register> registers = entityManager
					.createQuery("FROM Register r WHERE r.date >= :from AND r.date <= :to AND r.isdelete =:isdelete",
							Register.class)
					.setParameter("from", from).setParameter("to", to).setParameter("isdelete", this.IS_NOT_DELETE)
					.getResultList();
			return registers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean deleteRegister(Register register) {

		try {
			registerRepository.delete(register);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editRegister(Register register) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(register);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			return false;
		}
	}

	@Override
	public Boolean checkExistSchedule(String scheduleid) {

		try {
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Register> listByDateScheduleid(Date date, String scheduleid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Register> registers = entityManager.createQuery(
					"FROM Register r WHERE r.date = :date AND r.schedule.scheduleid =:scheduleid AND r.isdelete =:isdelete",
					Register.class).setParameter("date", date).setParameter("scheduleid", scheduleid)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return registers;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
