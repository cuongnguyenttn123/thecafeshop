package thecafeshop.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.ImportbilldetailDAOImp;
import thecafeshop.entity.Importbilldetail;
import thecafeshop.entity.ImportbilldetailId;
import thecafeshop.reponsitory.ImportbilldetailRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class ImportbilldetailDAO implements ImportbilldetailDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	ImportbilldetailRepository importbilldetailRepository;

	@Override
	public Boolean addImportbilldetail(Importbilldetail importbilldetail) {

		try {
			importbilldetailRepository.save(importbilldetail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Importbilldetail> getInfoByImportbillid(int importbillid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Importbilldetail> importbilldetails = entityManager
					.createQuery(
							"FROM Importbilldetail i WHERE i.id.importbillid =:importbillid AND i.isdelete =:isdelete",
							Importbilldetail.class)
					.setParameter("importbillid", importbillid).setParameter("isdelete", this.IS_NOT_DELETE)
					.getResultList();
			return importbilldetails;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Importbilldetail getInfoByImportbilldetailId(ImportbilldetailId id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Importbilldetail importbilldetail = entityManager
					.createQuery("FROM Importbilldetail i WHERE i.id =:id AND i.isdelete =:isdelete",
							Importbilldetail.class)
					.setParameter("id", id).setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
			return importbilldetail;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Boolean deleteImportbilldetail(Importbilldetail importbilldetail) {

		try {
			importbilldetailRepository.delete(importbilldetail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editImportbilldetail(Importbilldetail importbilldetail) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.merge(importbilldetail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int getNumberImportbilldetail(int importbillid) {

		List<Importbilldetail> importbilldetails = getInfoByImportbillid(importbillid);
		if (importbilldetails == null) {
			return 0;
		} else {
			return importbilldetails.size();
		}
	}

	@Override
	public Boolean checkExistMaterialDetail(int materialdetailid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Importbilldetail importbilldetail = entityManager.createQuery(
					"FROM Importbilldetail i WHERE i.id.materialdetailid =:materialdetailid AND i.isdelete =:isdelete",
					Importbilldetail.class).setParameter("materialdetailid", materialdetailid)
					.setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
			return true;
		} catch (Exception e) {

			return false;
		}
	}

}
