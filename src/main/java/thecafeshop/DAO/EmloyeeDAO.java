package thecafeshop.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.EmployeeDAOImp;
import thecafeshop.entity.Employee;
import thecafeshop.reponsitory.EmployeeRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class EmloyeeDAO implements EmployeeDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Boolean addEmployee(Employee employee) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.persist(employee);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String logIn(String username, String password) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Employee employee = entityManager.createQuery(
					"FROM Employee e WHERE e.usename = :username AND e.password =:password AND e.isdelete =:isdelete",
					Employee.class).setParameter("username", username).setParameter("password", password)
					.setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
			return employee.getEmployeeid();
		} catch (Exception e) {

			return null;
		}

	}

	@Override
	public Employee getInfoById(String emId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			Employee employee = entityManager
					.createQuery("FROM Employee e WHERE e.employeeid =:employeeid and e.isdelete =:isdelete",
							Employee.class)
					.setParameter("employeeid", emId).setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
			return employee;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Boolean deleteEmployee(String employeeid) {

		try {
			employeeRepository.deleteById(employeeid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editEmployee(Employee employee) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.remove(employee);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean checkExistUseName(String usename) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Employee employee = entityManager
					.createQuery("FROM Employee e WHERE e.usename = :usename AND e.isdelete =: isdelete",
							Employee.class)
					.setParameter("usename", usename).setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	@Override
	public List<Employee> findLimit(int startPosition) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Employee> employees =  entityManager.createQuery("FROM Employee e WHERE e.isdelete =: isdelete", Employee.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).setFirstResult(startPosition*this.MAX_RESULTS).setMaxResults(this.MAX_RESULTS).getResultList();
			return employees;
		} catch (Exception e) {
			return null;
		}
	}

//public void save(Employee employee) {
//	Session session = this.sessionFactory.getCurrentSession();
//	session.save(employee);
//}
//
//public void update(Customer customer) {
//	Session session = this.sessionFactory.getCurrentSession();
//	session.update(customer);
//}
//
//public Customer findById(final int id) {
//	Session session = this.sessionFactory.getCurrentSession();
//	return session.get(Customer.class, id);
//}
//
//public void delete(final Customer customer) {
//	Session session = this.sessionFactory.getCurrentSession();
//	session.remove(customer);
//}
//
}
