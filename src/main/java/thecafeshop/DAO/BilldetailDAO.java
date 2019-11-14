package thecafeshop.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAOImp.BilldetailDAOImp;
import thecafeshop.entity.Billdetail;
import thecafeshop.entity.BilldetailId;
import thecafeshop.entity.Price;
import thecafeshop.entity.Product;
import thecafeshop.reponsitory.BilldetailRepository;
import thecafeshop.service.BillService;
import thecafeshop.service.PriceService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.List;



@Repository()
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class BilldetailDAO implements BilldetailDAOImp {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	private PriceService priceService;
	@Autowired
	private BillService billService;
	@Autowired
	private VoucherDAO voucherDAO;

	@Autowired
	BilldetailRepository billdetailRepository;

	@Override
	public Boolean addBilldetail(Billdetail billdetail) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			billdetailRepository.save(billdetail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Billdetail> getInfoBilldetailByBillId(int billid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			List<Billdetail> billdetails = entityManager
					.createQuery("FROM Billdetail b WHERE b.id.billid =:billid AND b.isdelete =:isdelete",
							Billdetail.class)
					.setParameter("billid", billid).setParameter("isdelete", this.IS_NOT_DELETE).getResultList();
			return billdetails;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Billdetail getInfoBilldetailByBilldetailId(BilldetailId billdetailId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Billdetail billdetail = entityManager
					.createQuery("FROM Billdetail b WHERE b.id =:id AND b.isdelete =:isdelete", Billdetail.class)
					.setParameter("id", billdetailId).setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();
			return billdetail;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean deleteBilldetail(BilldetailId billdetailId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Billdetail billdetail = this.getInfoBilldetailByBilldetailId(billdetailId);
			entityManager.remove(billdetail);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean editBilldetail(Billdetail billdetail) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.merge(billdetail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int getPriceOfBillDetail(BilldetailId billdetailId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			int billId = billdetailId.getBillid();
			Date startdatetime = billService.getInfoById(billId).getStartdatetime();

			// lấy số lượng của sản phẩm
			Billdetail billdetail = entityManager
					.createQuery("FROM Billdetail b WHERE b.id =:id AND  b.isdelete =:isdelete", Billdetail.class)
					.setParameter("id", billdetailId).setParameter("isdelete", this.IS_NOT_DELETE).getSingleResult();

			// giá = số lượng * giá của sản phẩm
			int price = billdetail.getQuantity()
					* getSinglePriceOfBillDetail(billdetailId.getProductid(), startdatetime);

			return price;

		} catch (Exception e) {

			return 0;
		}
	}

	@Override
	public int getSinglePriceOfBillDetail(String productid, Date startdatetime) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		// lấy giá có ngày áp dụng <= startdatetime, sắp xếp giảm theo ngày và lấy dòng
		// đầu trong table

		try {
			Price price = entityManager.createQuery(
					"FROM Price p WHERE p.product =:product AND p.isdelete =:isdelete AND p.startdatetime <=:startdatetime ORDER BY p.startdatetime DESC",
					Price.class).setParameter("product", new Product(productid))
					.setParameter("startdatetime", startdatetime).setParameter("isdelete", this.IS_NOT_DELETE)
					.setMaxResults(1).getSingleResult();
			return price.getPrice();
		} catch (Exception e) {
			return 0;
		}
	}

}