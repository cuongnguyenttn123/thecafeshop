package thecafeshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAO.ExportbillDAO;
import thecafeshop.DAOImp.ExportbillDAOImp;
import thecafeshop.entity.Exportbill;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ExportbillService implements ExportbillDAOImp {

	@Autowired
	ExportbillDAO exportbillDAO;

	@Override
	public int addExportbill(Exportbill exportbill) {
		// TODO Auto-generated method stub
		return exportbillDAO.addExportbill(exportbill);
	}

	@Override
	public List<Exportbill> findAll() {
		// TODO Auto-generated method stub
		return exportbillDAO.findAll();
	}
	
	@Override
	public List<Exportbill> findLimit(int startPosition) {
		// TODO Auto-generated method stub
		return exportbillDAO.findLimit(startPosition);
	}

	@Override
	public Exportbill getInfoById(int exportbillid) {
		// TODO Auto-generated method stub
		return exportbillDAO.getInfoById(exportbillid);
	}

	@Override
	public Boolean deleteExportbill(int exportbillid) {
		// TODO Auto-generated method stub
		return exportbillDAO.deleteExportbill(exportbillid);
	}

	@Override
	public Boolean editExportbill(Exportbill exportbill) {
		// TODO Auto-generated method stub
		return exportbillDAO.editExportbill(exportbill);
	}

	@Override
	public int totalQuantityProduct(String productid) {
		// TODO Auto-generated method stub
		return exportbillDAO.totalQuantityProduct(productid);
	}

	@Override
	public List<Exportbill> getListExportBillbyProduct(String productid) {
		// TODO Auto-generated method stub
		return exportbillDAO.getListExportBillbyProduct(productid);
	}
}