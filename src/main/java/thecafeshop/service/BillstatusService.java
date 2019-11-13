package thecafeshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAO.BillstatusDAO;
import thecafeshop.DAOImp.BillstatusDAOImp;
import thecafeshop.entity.Billstatus;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class BillstatusService implements BillstatusDAOImp {

	@Autowired
	BillstatusDAO billstatusDAO;

	@Override
	public Boolean addBillstatus(Billstatus billstatus) {
		// TODO Auto-generated method stub
		return billstatusDAO.addBillstatus(billstatus);
	}

	@Override
	public List<Billstatus> findAll() {
		// TODO Auto-generated method stub
		return billstatusDAO.findAll();
	}

	@Override
	public Billstatus getInfoById(String billstatusid) {
		// TODO Auto-generated method stub
		return billstatusDAO.getInfoById(billstatusid);
	}

	@Override
	public Boolean deleteBillstatus(String billstatusid) {
		// TODO Auto-generated method stub
		return billstatusDAO.deleteBillstatus(billstatusid);
	}

	@Override
	public Boolean editBilldetail(Billstatus billstatus) {
		// TODO Auto-generated method stub
		return billstatusDAO.editBilldetail(billstatus);
	}

	@Override
	public List<Billstatus> findLimit(int startPosition) {
		// TODO Auto-generated method stub
		return billstatusDAO.findLimit(startPosition);
	}
}