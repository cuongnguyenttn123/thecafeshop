package thecafeshop.DAOImp;

import java.util.Date;
import java.util.List;

import thecafeshop.entity.Bill;

public interface BillDAOImp extends CommonDAOImp {

	public int addBill(Bill bill);

	public List<Bill> findAll();

	public List<Bill> findLimit(int startPosition);

	public Bill getInfoById(int billid);

	public Boolean deleteBill(int billid);

	public Boolean editBill(Bill bill);

	public Boolean checkExistBillStatus(String billstatusid);

	public Boolean checkExistVoucher(int voucherid);

	public Boolean checkExistDinnerTable(int dinnertableid);

	public int getTotalPriceOfBill(int billid);

	public Bill getInfoLastBill(int dinnertableid);

	// thống kê tổng tiền thu vào trong ngày, tổng hóa đơn trong ngày
	public int thongkeTongTienTrongNgay(Date date);

	public int thongkeSoHoaDonTrongNgay(Date date);

	// thống kê tổng tiền thu vào trong tuần, tổng hóa đơn trong tuần
	public int thongkeTongTienTrongTuan(int tuan);

	public int thongkeSoHoaDonTrongTuan(int tuan);

	// thống kê tổng tiền thu vào trong tuần, tổng hóa đơn trong năm
	public int thongkeTongTienTrongThang(int thang);

	public int thongkeSoHoaDonTrongThang(int thang);
}
