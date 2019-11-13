package thecafeshop.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.ExportbilldetailDAOImp;
import thecafeshop.entity.Exportbilldetail;
import thecafeshop.entity.ExportbilldetailId;
import thecafeshop.reponsitory.ExportbilldetailRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class ExportbilldetailDAO implements ExportbilldetailDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	ExportbilldetailRepository exportbilldetailRepository;

	@Override
	public Boolean addExportbilldetail(Exportbilldetail exportbilldetail) {

		try {
			exportbilldetailRepository.save(exportbilldetail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Exportbilldetail> getInfoByExportbillId(int exportbillid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Exportbilldetail> exportbilldetails = entityManager
					.createQuery(
							"FROM Exportbilldetail e WHERE e.id.exportbillid =:exportbillid AND e.isdelete =:isdelete",
							Exportbilldetail.class)
					.setParameter("exportbillid", exportbillid).setParameter("isdelete", this.IS_NOT_DELETE)
					.getResultList();
			return exportbilldetails;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Exportbilldetail getInfoByExportbilldetail(ExportbilldetailId id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Exportbilldetail exportbilldetail = entityManager
					.createQuery("FROM Exportbilldetail e WHERE e.id =:id AND e.isdelete =:isdelete",
							Exportbilldetail.class)
					.setParameter("id", id).setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
			return exportbilldetail;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Boolean deleteExportbilldetail(Exportbilldetail exportbilldetail) {
		try {
			exportbilldetailRepository.delete(exportbilldetail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editExportbilldetail(Exportbilldetail exportbilldetail) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.merge(exportbilldetail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int getNumberExportbilldetail(int exportbillid) {

		List<Exportbilldetail> exportbilldetails = getInfoByExportbillId(exportbillid);
		if (exportbilldetails == null) {
			return 0;
		} else {
			return exportbilldetails.size();
		}
	}

	@Override
	public Boolean checkExistMaterialDetail(int materialdetailid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Exportbilldetail exportbilldetail = entityManager.createQuery(
					"FROM Exportbilldetail e WHERE e.id.materialdetailid =:materialdetailid AND e.isdelete =: isdelete",
					Exportbilldetail.class).setParameter("materialdetailid", materialdetailid)
					.setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
			return true;
		} catch (Exception e) {

			return false;
		}
	}

}
