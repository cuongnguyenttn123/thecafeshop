package thecafeshop.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.ImageDAOImp;
import thecafeshop.entity.Image;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class ImageDAO implements ImageDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public List<Image> getListImageOfProduct(String PId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Image> images = entityManager
					.createQuery("FROM Image p WHERE p.PId = :PId", Image.class)
					.setParameter("PId", PId).setParameter("is_delete", this.IS_NOT_DELETE).getResultList();
			
			return images;
		} catch (Exception e) {

			return null;
		}
	}

}
