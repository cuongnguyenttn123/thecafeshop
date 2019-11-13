package thecafeshop.DAO;

import java.util.List;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.MaterialdetailDAOImp;
import thecafeshop.entity.Materialdetail;
import thecafeshop.reponsitory.MaterialdetailRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class MaterialdetailDAO implements MaterialdetailDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	MaterialdetailRepository materialdetailRepository;

	@Override
	public int addMaterialdetail(Materialdetail materialdetail) {

		try {
			materialdetailRepository.save(materialdetail);
			return materialdetail.getMaterialdetailid();
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public List<Materialdetail> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			List<Materialdetail> materialdetails = entityManager
					.createQuery("FROM Materialdetail m WHERE m.quantity > 0 AND m.isdelete =:isdelete",
							Materialdetail.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return materialdetails;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Materialdetail getInfoById(int materialdetailid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			Materialdetail materialdetail = entityManager.createQuery(
					"FROM Materialdetail m WHERE m.materialdetailid =:materialdetailid AND m.isdelete =:isdelete",
					Materialdetail.class).setParameter("materialdetailid", materialdetailid)
					.setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
			return materialdetail;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public Boolean deleteMaterialdetail(int materialdetailid) {

		try {
			materialdetailRepository.deleteById(materialdetailid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean editMaterialdetail(Materialdetail materialdetail) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(materialdetail);
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
	public Boolean checkExistMaterial(int materialid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			List<Materialdetail> materialdetails = entityManager.createQuery(
					"FROM Materialdetail m WHERE m.material.materialid =:materialid AND m.isdelete =:isdelete",
					Materialdetail.class).setParameter("materialid", materialid)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			if (materialdetails.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {

			return false;
		}
	}

	@Override
	public List<Materialdetail> layNguyenLieuTonKho(int materialid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			List<Materialdetail> materialdetails = entityManager.createQuery(
					"FROM Materialdetail m WHERE m.quantity > 0 AND m.material.materialid =: materialid AND m.isdelete =: isdelete ORDER BY m.materialdetailid ASC",
					Materialdetail.class).setParameter("materialid", materialid)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return materialdetails;
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public int laySoNguyenLieuTonKho(int materialid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			List<Materialdetail> materialdetails = entityManager.createQuery(
					"FROM Materialdetail m WHERE m.quantity > 0 AND m.material.materialid =: materialid AND m.isdelete =: isdelete ORDER BY m.materialdetailid ASC",
					Materialdetail.class).setParameter("materialid", materialid)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			if (materialdetails.size() <= 0) {
				return 0;
			}
			int total = 0;
			for (Materialdetail materialdetail : materialdetails) {
				total += materialdetail.getQuantity();
			}
			return total;
		} catch (Exception e) {

			return 0;
		}
	}

	@Override
	public List<Materialdetail> search(String materialdetailid, String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			String hql = "FROM Materialdetail m WHERE m.quantity > 0 AND m.isdelete =: isdelete";
			if (materialdetailid != "") {
				hql = hql + " AND m.materialdetailid =: materialdetailid ";
			}
			if (name != "") {
				hql = hql + " AND m.material.name =: name ";
			}
			Query query = (Query) entityManager.createQuery(hql, Materialdetail.class);
			query.setParameter("isdelete", this.IS_NOT_DELETE);
			if (materialdetailid != "") {
				query.setParameter("materialdetailid", Integer.valueOf(materialdetailid));
			}
			if (name != "") {
				query.setParameter("name", name);
			}
			List<Materialdetail> materialdetails = query.getResultList();
			return materialdetails;
			
		} catch (Exception e) {

			return null;
		}

	}

}