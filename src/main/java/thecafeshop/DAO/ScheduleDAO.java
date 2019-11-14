package thecafeshop.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.ScheduleDAOImp;
import thecafeshop.entity.Schedule;
import thecafeshop.reponsitory.ScheduleRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class ScheduleDAO implements ScheduleDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	ScheduleRepository scheduleRepository;
	@Override
	public Boolean addSchedule(Schedule schedule) {

		try {
			scheduleRepository.save(schedule);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Schedule> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Schedule> schedules = entityManager
					.createQuery("FROM Schedule s WHERE s.isdelete =:isdelete", Schedule.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return schedules;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Schedule getInfoById(String scheduleid) {

		try {
			Schedule schedule = scheduleRepository.findById(scheduleid).get();
			return schedule;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean deleteSchedule(String scheduleid) {

		try {
			scheduleRepository.deleteById(scheduleid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean editSchedule(Schedule schedule) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(schedule);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<Schedule> findLimit(int startPosition) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Schedule> schedules = entityManager
					.createQuery("FROM Schedule s WHERE s.isdelete =:isdelete", Schedule.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).setFirstResult(startPosition * MAX_RESULTS)
					.setMaxResults(MAX_RESULTS).getResultList();
			return schedules;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}