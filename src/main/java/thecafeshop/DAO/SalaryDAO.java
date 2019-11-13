package thecafeshop.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.SalaryDAOImp;
import thecafeshop.entity.Employee;
import thecafeshop.entity.Salary;
import thecafeshop.reponsitory.SalaryRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class SalaryDAO implements SalaryDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	SalaryRepository salaryRepository;

	@Override
	public Boolean addSalary(Salary salary) {

		try {
			salaryRepository.save(salary);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Salary getInfoById(int employeeid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Salary salary = entityManager
					.createQuery("FROM Salary s WHERE s.employeeid = :employeeid and s.isdelete =:isdelete ", Salary.class)
					.setParameter("employeeid", employeeid).setParameter("isdelete", this.IS_NOT_DELETE)
					.getSingleResult();
			return salary;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Boolean deleteSalary(Salary salary) {

		try {
			salaryRepository.delete(salary);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editSalary(Salary salary) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(salary);
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
	public int getSalaryByEmployeeid(String employeeid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Salary salary = entityManager.createQuery(
					"FROM Salary s WHERE s.employee =:employee AND s.startdate <= now() AND s.enddate is null AND s.isdelete =: isdelete ORDER BY s.startdate DESC",
					Salary.class).setParameter("employee", new Employee(employeeid))
					.setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
			return salary.getSalaryonhour();
		} catch (Exception e) {

			return 0;
		}
	}

}