package thecafeshop.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.CustomerDAOImp;
import thecafeshop.entity.Customer;
import thecafeshop.reponsitory.CustomerRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;



@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class CustomerDAO implements CustomerDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public int addCustomer(Customer customer) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.persist(customer);

			return customer.getCustomerid();
		} catch (Exception e) {

			return -1;
		}
	}

	@Override
	public List<Customer> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Customer> customers = entityManager
					.createQuery("FROM Customer c WHERE c.isdelete =: isdelete", Customer.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return customers;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Customer getInfoById(int customerid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Customer customer = entityManager
					.createQuery("FROM Customer c WHERE c.customerid = :customerid AND c.isdelete =: isdelete",
							Customer.class)
					.setParameter("customerid", customerid).setParameter("isdelete", this.IS_NOT_DELETE)
					.getSingleResult();
			return customer;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Boolean deleteCustomer(int customerid) {

		try {
			customerRepository.deleteById(customerid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editCustomer(Customer customer) {
		return null;
	}

}
