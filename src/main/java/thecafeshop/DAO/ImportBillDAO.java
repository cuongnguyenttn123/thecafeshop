package thecafeshop.DAO;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.ImportBillDAOImp;
import thecafeshop.entity.Importbill;
import thecafeshop.entity.Importbilldetail;
import thecafeshop.reponsitory.ImportbillRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class ImportBillDAO implements ImportBillDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	ImportbillRepository importbillRepository;

	@Override
	public int addImportBill(Importbill importbill) {

		try {
			importbillRepository.save(importbill);
			return importbill.getImportbillid();
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public List<Importbill> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Importbill> importbills = entityManager
					.createQuery("FROM Importbill i WHERE i.isdelete =:isdelete", Importbill.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return importbills;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Importbill> findLimit(int startPosition) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Importbill> importbills = entityManager
					.createQuery("FROM Importbill i WHERE i.isdelete =:isdelete", Importbill.class)
					.setFirstResult(startPosition * MAX_RESULTS).setMaxResults(MAX_RESULTS)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return importbills;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Importbill getInfoById(int importbillid) {

		try {
			Importbill importbill = importbillRepository.findById(importbillid).get();
			return importbill;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean deleteImportBill(int importbillid) {

		try {
			importbillRepository.deleteById(importbillid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean editImportBill(Importbill importbill) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(importbill);
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
	public int tongtienImportBill(Date date) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Importbill> importbills = entityManager
					.createQuery("FROM Importbill i WHERE  DATE(i.updateat) = DATE(:date) AND i.isdelete =:isdelete",
							Importbill.class)
					.setParameter("date", date).setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			int total = 0;
			for (Importbill importbill : importbills) {
				Set<Importbilldetail> importbilldetails = importbill.getImportbilldetails();
				for (Importbilldetail importbilldetail : importbilldetails) {
					total += importbilldetail.getMaterialdetail().getQuantity()
							* importbilldetail.getMaterialdetail().getPrice();
				}
			}
			return total;
		} catch (Exception e) {

			return 0;
		}
	}

	@Override
	public int soluongImportBill(Date date) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Importbill> importbills = entityManager
					.createQuery("FROM Importbill i WHERE  DATE(i.updateat) = DATE(:date) AND i.isdelete =:isdelete",
							Importbill.class)
					.setParameter("date", date).setParameter("isdelete", this.IS_NOT_DELETE).getResultList();

			return importbills.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int tongtienImportBillTrongTuan(int tuan) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Importbill> importbills = entityManager
					.createQuery("FROM Importbill i WHERE WEEK(i.updateat) =:tuan AND i.isdelete =:isdelete",
							Importbill.class)
					.setParameter("tuan", tuan).setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			int total = 0;
			for (Importbill importbill : importbills) {
				Set<Importbilldetail> importbilldetails = importbill.getImportbilldetails();
				for (Importbilldetail importbilldetail : importbilldetails) {
					total += importbilldetail.getMaterialdetail().getQuantity()
							* importbilldetail.getMaterialdetail().getPrice();
				}
			}
			return total;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int soluongImportBillTrongTuan(int tuan) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Importbill> importbills = entityManager
					.createQuery("FROM Importbill i WHERE WEEK(i.updateat) =:tuan AND i.isdelete =:isdelete",
							Importbill.class)
					.setParameter("tuan", tuan).setParameter("isdelete", this.IS_NOT_DELETE).getResultList();

			return importbills.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int tongtienImportBillTrongThang(int thang) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Importbill> importbills = entityManager
					.createQuery("FROM Importbill i WHERE MONTH(i.updateat) =:thang AND i.isdelete =:isdelete",
							Importbill.class)
					.setParameter("thang", thang).setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			int total = 0;
			for (Importbill importbill : importbills) {
				Set<Importbilldetail> importbilldetails = importbill.getImportbilldetails();
				for (Importbilldetail importbilldetail : importbilldetails) {
					total += importbilldetail.getMaterialdetail().getQuantity()
							* importbilldetail.getMaterialdetail().getPrice();
				}
			}
			return total; 
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int soluongImportBillTrongThang(int thang) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Importbill> importbills = entityManager
					.createQuery("FROM Importbill i WHERE MONTH(i.updateat) =:thang AND i.isdelete =:isdelete",
							Importbill.class)
					.setParameter("thang", thang).setParameter("isdelete", this.IS_NOT_DELETE).getResultList();

			return importbills.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
