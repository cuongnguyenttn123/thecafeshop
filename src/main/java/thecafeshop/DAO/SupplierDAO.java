package thecafeshop.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.SupplierDAOImp;
import thecafeshop.entity.Supplier;
import thecafeshop.reponsitory.SupplierRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;



@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class SupplierDAO implements SupplierDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	SupplierRepository supplierRepository;

	@Override
	public Boolean addSupplier(Supplier supplier) {

		try {
			supplierRepository.save(supplier);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Supplier> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Supplier> suppliers = entityManager
					.createQuery("FROM Supplier s WHERE s.isdelete =:isdelete", Supplier.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return suppliers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Supplier getInfoById(int supplierid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Supplier supplier = entityManager
					.createQuery("FROM Supplier s WHERE s.supplierid =:supplierid AND s.isdelete =:isdelete",
							Supplier.class)
					.setParameter("supplierid", supplierid).setParameter("isdelete", this.IS_NOT_DELETE)
					.getSingleResult();
			return supplier;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean deleteSupplier(int supplierid) {

		try {
			supplierRepository.deleteById(supplierid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editSupplier(Supplier supplier) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(supplier);
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
	public List<Supplier> findLimit(int startPosition) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Supplier> suppliers = entityManager
					.createQuery("FROM Supplier s WHERE s.isdelete =:isdelete", Supplier.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).setFirstResult(startPosition * MAX_RESULTS)
					.setMaxResults(MAX_RESULTS).getResultList();
			return suppliers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean checkExistByName(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Supplier supplier = entityManager
					.createQuery("FROM Supplier s WHERE s.name =:name AND s.isdelete =:isdelete",
							Supplier.class)
					.setParameter("name", name).setParameter("isdelete", this.IS_NOT_DELETE)
					.getSingleResult();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}