package thecafeshop.DAOImp;

import thecafeshop.entity.Billstatus;

import java.util.List;



public interface BillstatusDAOImp extends CommonDAOImp{ 

	public Boolean addBillstatus(Billstatus billstatus);

	public List<Billstatus> findAll();

	public List<Billstatus> findLimit(int startPosition);
	
	public Billstatus getInfoById(String billstatusid);
	
	public Boolean deleteBillstatus(String billstatusid);

	public Boolean editBilldetail(Billstatus billstatus);
}
