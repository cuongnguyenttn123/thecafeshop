package thecafeshop.DAOImp;

import thecafeshop.entity.Billdetail;
import thecafeshop.entity.BilldetailId;

import java.util.Date;
import java.util.List;



public interface BilldetailDAOImp extends CommonDAOImp {

	public Boolean addBilldetail(Billdetail billdetail);

	public List<Billdetail> getInfoBilldetailByBillId(int billid);

	public Billdetail getInfoBilldetailByBilldetailId(BilldetailId billdetailId);

	public Boolean deleteBilldetail(BilldetailId billdetailId);

	public Boolean editBilldetail(Billdetail billdetail);
	
	// lấy tổng của một sản phẩm trong billdetail(số lượng * giá sản phẩm)
	public int getPriceOfBillDetail(BilldetailId billdetailId);
	
	// lấy đơn giá của sản phẩm trong billdetail
	public int getSinglePriceOfBillDetail(String PId, Date startdatetime);
	
}
