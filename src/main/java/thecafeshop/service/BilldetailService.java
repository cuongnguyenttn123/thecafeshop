package thecafeshop.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAO.BilldetailDAO;
import thecafeshop.DAOImp.BilldetailDAOImp;
import thecafeshop.entity.Billdetail;
import thecafeshop.entity.BilldetailId;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class BilldetailService implements BilldetailDAOImp {

	@Autowired
	BilldetailDAO billdetailDAO;

	@Override
	public Boolean addBilldetail(Billdetail billdetail) {
		billdetail.setIsdelete(this.IS_NOT_DELETE);
		return billdetailDAO.addBilldetail(billdetail);
	}

	@Override
	public List<Billdetail> getInfoBilldetailByBillId(int billid) {
		// TODO Auto-generated method stub
		return billdetailDAO.getInfoBilldetailByBillId(billid);
	}

	@Override
	public Billdetail getInfoBilldetailByBilldetailId(BilldetailId billdetailId) {
		// TODO Auto-generated method stub
		return billdetailDAO.getInfoBilldetailByBilldetailId(billdetailId);
	}

	@Override
	public Boolean deleteBilldetail(BilldetailId billdetailId) {
		// TODO Auto-generated method stub
		return billdetailDAO.deleteBilldetail(billdetailId);
	}

	@Override
	public Boolean editBilldetail(Billdetail billdetail) {
		// TODO Auto-generated method stub
		return billdetailDAO.editBilldetail(billdetail);
	}

	@Override
	public int getPriceOfBillDetail(BilldetailId billdetailId) {
		// TODO Auto-generated method stub
		return billdetailDAO.getPriceOfBillDetail(billdetailId);
	}

	@Override
	public int getSinglePriceOfBillDetail(String productid, Date startdatetime) {
		// TODO Auto-generated method stub
		return billdetailDAO.getSinglePriceOfBillDetail(productid, startdatetime);
	}


}