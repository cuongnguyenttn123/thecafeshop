package thecafeshop.DAOImp;

import thecafeshop.entity.Importbill;

import java.util.Date;
import java.util.List;



public interface ImportBillDAOImp extends CommonDAOImp {

	public int addImportBill(Importbill importbill);

	public List<Importbill> findAll();

	public List<Importbill> findLimit(int startPosition);

	public Importbill getInfoById(int importbillid);

	public Boolean deleteImportBill(int importbillid);

	public Boolean editImportBill(Importbill importbill);

	// lấy tổng tiền, số lượng của bill trong ngày
	public int tongtienImportBill(Date date);

	public int soluongImportBill(Date date);

	// lấy tổng tiền, số lượng của bill trong tuần
	public int tongtienImportBillTrongTuan(int tuan);

	public int soluongImportBillTrongTuan(int tuan);

	// lấy tổng tiền, số lượng của bill trong tháng
	public int tongtienImportBillTrongThang(int thang);

	public int soluongImportBillTrongThang(int thang);
}
