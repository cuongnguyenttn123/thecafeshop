package thecafeshop.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import thecafeshop.DAOImp.BillDAOImp;
import thecafeshop.entity.*;
import thecafeshop.reponsitory.BillRepository;
import thecafeshop.service.BilldetailService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackOn = Exception.class)
public class BillDao implements BillDAOImp {

    @Autowired
    BillRepository billRepository;

    @Autowired
    BilldetailService billdetailService;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public int addBill(Bill bill) {
        return billRepository.save(bill).getBillid();
    }

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public List<Bill> findLimit(int startPosition) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Bill> bills = new ArrayList<Bill>();
        try {
            bills = entityManager.createQuery("select b from Bill b where b.isdetele =?1", Bill.class)
                    .setFirstResult(startPosition * MAX_RESULTS).setMaxResults(MAX_RESULTS)
                    .setParameter(1, this.IS_NOT_DELETE).getResultList();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            return null;
        }finally {
            entityManager.close();
        }
        return bills;
    }

    @Override
    public Bill getInfoById(int billid) {
        return billRepository.findById(billid).get();
    }

    @Override
    public Boolean deleteBill(int billid) {
        try {
            billRepository.deleteById(billid);
            return true;
        }catch (Exception e){

            return false;
        }

    }

    @Override
    public Boolean editBill(Bill bill) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(bill);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            return false;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public Boolean checkExistBillStatus(String billstatusid) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<Bill> bills = entityManager
                    .createQuery("FROM Bill b WHERE b.billstatus = ?1 AND b.isdelete =?2", Bill.class)
                    .setParameter(1, new Billstatus(billstatusid))
                    .setParameter(2, this.IS_NOT_DELETE).getResultList();

            if (bills.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {

            return false;
        }

    }

    @Override
    public Boolean checkExistVoucher(int voucherid) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<Bill> bills = entityManager
                    .createQuery("FROM Bill b WHERE b.voucher = :voucher AND b.isdelete =:isdelete", Bill.class)
                    .setParameter("voucher", new Voucher(voucherid)).setParameter("isdelete", this.IS_NOT_DELETE)
                    .getResultList();

            if (bills.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {

            return false;
        }
    }

    @Override
    public Boolean checkExistDinnerTable(int dinnertableid) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<Bill> bills = entityManager
                    .createQuery("FROM Bill b WHERE b.dinnertable =:dinnertable AND b.isdelete =:isdelete",
                            Bill.class)
                    .setParameter("dinnertable", new Dinnertable(dinnertableid))
                    .setParameter("isdelete", this.IS_NOT_DELETE).getResultList();

            if (bills.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {

            return false;
        }
    }

    @Override
    public int getTotalPriceOfBill(int billid) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Date startdatetime = getInfoById(billid).getStartdatetime();

            List<Billdetail> billdetails = entityManager
                    .createQuery("FROM Billdetail b WHERE b.id.billid =:billid AND b.isdelete =: isdelete",
                            Billdetail.class)
                    .setParameter("billid", billid).setParameter("isdelete", this.IS_NOT_DELETE).getResultList();

            int totalPrice = 0;
            for (Billdetail billdetail : billdetails) {
                String productId = billdetail.getProduct().getProductid();
                int price = billdetailService.getPriceOfBillDetail(new BilldetailId(productId, billid));

                totalPrice += price;
            }

            return totalPrice;

        } catch (Exception e) {

            return 0;
        }
    }

    @Override
    public Bill getInfoLastBill(int dinnertableid) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Bill bill = entityManager
                    .createQuery("FROM Bill b WHERE b.isdelete =: isdelete AND b.billstatus.billstatusid = 'CTT' AND b.dinnertable.dinnertableid =: dinnertableid AND b.startdatetime <= now() ORDER BY b.startdatetime DESC",
                    Bill.class)
                    .setParameter("dinnertableid", dinnertableid)
                    .setParameter("isdelete", this.IS_NOT_DELETE)
                    .setFirstResult(0).setMaxResults(1).getSingleResult();
            return bill;
        } catch (Exception e) {

            return null;
        }
    }

    @Override
    public int thongkeTongTienTrongNgay(Date date) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<Bill> bills = entityManager.createQuery(
                    "FROM Bill b WHERE  DATE(b.startdatetime) = DATE(:date) AND b.billstatus.billstatusid = 'DTT' AND b.isdelete =: isdelete",
                    Bill.class)
                    .setParameter("date", date)
                    .setParameter("isdelete", this.IS_NOT_DELETE)
                    .getResultList();

            int total = 0;
            for (Bill bill : bills) {
                total += getTotalPriceOfBill(bill.getBillid());
            }
            return total;
        } catch (Exception e) {

            return 0;
        }
    }

    @Override
    public int thongkeSoHoaDonTrongNgay(Date date) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<Bill> bills = entityManager.createQuery(
                    "FROM Bill b WHERE  DATE(b.startdatetime) = DATE(:date) AND b.billstatus.billstatusid = 'DTT' AND b.isdelete =: isdelete",
                    Bill.class)
                    .setParameter("date", date)
                    .setParameter("isdelete", this.IS_NOT_DELETE)
                    .getResultList();
            return bills.size();
        } catch (Exception e) {

            return 0;
        }
    }

    @Override
    public int thongkeTongTienTrongTuan(int tuan) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<Bill> bills = entityManager.createQuery(
                    "FROM Bill b WHERE  WEEK(b.enddate) =: tuan AND b.billstatus.billstatusid = 'DTT' AND b.isdelete =: isdelete",
                    Bill.class).setParameter("tuan", tuan).setParameter("isdelete", this.IS_NOT_DELETE).getResultList();

            int total = 0;
            for (Bill bill : bills) {
                total += getTotalPriceOfBill(bill.getBillid());
            }
            return total;
        } catch (Exception e) {

            return 0;
        }
    }

    @Override
    public int thongkeSoHoaDonTrongTuan(int tuan) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<Bill> bills = entityManager.createQuery(
                    "FROM Bill b WHERE  WEEK(b.enddate) =:tuan AND b.billstatus.billstatusid = 'DTT' AND b.isdelete =:isdelete",
                    Bill.class)
                    .setParameter("tuan", tuan)
                    .setParameter("isdelete", this.IS_NOT_DELETE)
                    .getResultList();
            return bills.size();
        } catch (Exception e) {

            return 0;
        }
    }

    @Override
    public int thongkeTongTienTrongThang(int thang) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<Bill> bills = entityManager.createQuery(
                    "FROM Bill b WHERE  MONTH(b.enddate) =:thang AND b.billstatus.billstatusid = 'DTT' AND b.isdelete =:isdelete",
                    Bill.class)
                    .setParameter("thang", thang)
                    .setParameter("isdelete", this.IS_NOT_DELETE)
                    .getResultList();

            int total = 0;
            for (Bill bill : bills) {
                total += getTotalPriceOfBill(bill.getBillid());
            }
            return total;
        } catch (Exception e) {

            return 0;
        }
    }

    @Override
    public int thongkeSoHoaDonTrongThang(int thang) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<Bill> bills = entityManager.createQuery(
                    "FROM Bill b WHERE  MONTH(b.enddate) =:thang AND b.billstatus.billstatusid = 'DTT' AND b.isdelete =:isdelete",
                    Bill.class)
                    .setParameter("thang", thang)
                    .setParameter("isdelete", this.IS_NOT_DELETE)
                    .getResultList();
            return bills.size();
        } catch (Exception e) {

            return 0;
        }
    }
}
