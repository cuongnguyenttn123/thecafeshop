package thecafeshop.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.VoucherDAOImp;
import thecafeshop.entity.Voucher;
import thecafeshop.reponsitory.VoucherRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;



@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class VoucherDAO implements VoucherDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	VoucherRepository voucherRepository;

	@Override
	public Boolean addVoucher(Voucher voucher) {

		try {
			voucherRepository.save(voucher);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Voucher> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Voucher> vouchers = entityManager.createQuery("FROM Voucher v WHERE  v.isdelete =:isdelete", Voucher.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return vouchers;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Voucher findById(int voucherid) {
		try {
			Voucher voucher = voucherRepository.findById(voucherid).get();
			return voucher;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Voucher findByName(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Voucher voucher = entityManager
					.createQuery("FROM Voucher v WHERE v.name =:name AND v.isdelete =:isdelete", Voucher.class)
					.setParameter("name", name).setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();

			return voucher;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Boolean checkVoucher(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Voucher voucher = entityManager
					.createQuery("FROM Voucher v WHERE v.name =:name AND v.isdelete =:eisdelete", Voucher.class)
					.setParameter("name", name).setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
			if(voucher.getCount() == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	@Override
	public Boolean deleteVoucher(int voucherid) {

		try {
			voucherRepository.deleteById(voucherid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editVoucher(Voucher voucher) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(voucher);
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
	public List<Voucher> findLimit(int startPosition) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Voucher> vouchers = entityManager.createQuery("FROM Voucher v WHERE  v.isdelete =:isdelete", Voucher.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).setFirstResult(startPosition * MAX_RESULTS)
					.setMaxResults(MAX_RESULTS).getResultList();
			return vouchers;
		} catch (Exception e) {

			return null;
		}
	}
}
