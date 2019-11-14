package thecafeshop.DAO;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.MaterialDAOImp;
import thecafeshop.entity.Material;
import thecafeshop.reponsitory.MaterialRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class MaterialDAO implements MaterialDAOImp {
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	MaterialRepository materialRepository;

	@Override
	public Boolean addMaterial(Material material) {

		try {
			materialRepository.save(material);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Material> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			List<Material> materials = entityManager
					.createQuery("FROM Material m WHERE m.isdelete =:isdelete", Material.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return materials;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Material getInfoById(int materialid) {

		try {
			Material material = materialRepository.findById(materialid).get();
			return material;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean deleteAtposition(int materialid) {

		try {
			materialRepository.deleteById(materialid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean editMaterial(Material material) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(material);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			return false;
		}
	}

	@Override
	public List<Material> findLimit(int startPosition) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			List<Material> materials = entityManager
					.createQuery("FROM Material m WHERE m.isdelete =:isdelete", Material.class)
					.setParameter("isdelete", this.IS_NOT_DELETE).setFirstResult(startPosition * MAX_RESULTS)
					.setMaxResults(MAX_RESULTS).getResultList();
			return materials;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean checkExistNameMaterial(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	try {
		Material material = entityManager
				.createQuery("FROM Material m WHERE m.name=:name AND m.isdelete =:isdelete",
						Material.class)
				.setParameter("name", name).setParameter("isdelete", this.IS_NOT_DELETE)
				.getSingleResult();
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	}

}