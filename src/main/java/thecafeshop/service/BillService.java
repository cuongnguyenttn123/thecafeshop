package thecafeshop.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAO.BillDao;
import thecafeshop.DAOImp.BillDAOImp;
import thecafeshop.entity.Bill;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class BillService implements BillDAOImp {

	@Autowired
	BillDao billDAO;

	@Override
	public int addBill(Bill bill) {
		bill.setIsdelete(this.IS_NOT_DELETE);
		return billDAO.addBill(bill);
	}

	@Override
	public List<Bill> findAll() {
		// TODO Auto-generated method stub
		return billDAO.findAll();
	}

	@Override
	public Bill getInfoById(int billid) {
		// TODO Auto-generated method stub
		return billDAO.getInfoById(billid);
	}

	@Override
	public Boolean deleteBill(int billid) {
		// TODO Auto-generated method stub
		return billDAO.deleteBill(billid);
	}

	@Override
	public Boolean editBill(Bill bill) {
		// TODO Auto-generated method stub
		return billDAO.editBill(bill);
	}

	@Override
	public Boolean checkExistBillStatus(String billstatusid) {
		// TODO Auto-generated method stub
		return billDAO.checkExistBillStatus(billstatusid);
	}

	@Override
	public Boolean checkExistVoucher(int voucherid) {
		// TODO Auto-generated method stub
		return billDAO.checkExistVoucher(voucherid);
	}

	@Override
	public Boolean checkExistDinnerTable(int dinnertableid) {
		// TODO Auto-generated method stub
		return billDAO.checkExistDinnerTable(dinnertableid);
	}

	@Override
	public List<Bill> findLimit(int startPosition) {
		// TODO Auto-generated method stub
		return billDAO.findLimit(startPosition);
	}

	@Override
	public int getTotalPriceOfBill(int billid) {
		// TODO Auto-generated method stub
		return billDAO.getTotalPriceOfBill(billid);
	}

	@Override
	public Bill getInfoLastBill(int dinnertableid) {
		// TODO Auto-generated method stub
		return billDAO.getInfoLastBill(dinnertableid);
	}

	@Override
	public int thongkeTongTienTrongNgay(Date date) {
		// TODO Auto-generated method stub
		return billDAO.thongkeTongTienTrongNgay(date);
	}

	@Override
	public int thongkeSoHoaDonTrongNgay(Date date) {
		// TODO Auto-generated method stub
		return billDAO.thongkeSoHoaDonTrongNgay(date);
	}

	@Override
	public int thongkeTongTienTrongTuan(int tuan) {
		// TODO Auto-generated method stub
		return billDAO.thongkeTongTienTrongTuan(tuan);
	}

	@Override
	public int thongkeSoHoaDonTrongTuan(int tuan) {
		// TODO Auto-generated method stub
		return billDAO.thongkeSoHoaDonTrongTuan(tuan);
	}

	@Override
	public int thongkeTongTienTrongThang(int thang) {
		// TODO Auto-generated method stub
		return billDAO.thongkeTongTienTrongThang(thang);
	}

	@Override
	public int thongkeSoHoaDonTrongThang(int thang) {
		// TODO Auto-generated method stub
		return billDAO.thongkeSoHoaDonTrongThang(thang);
	}

}
