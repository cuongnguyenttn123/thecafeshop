package thecafeshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAO.ExportbilldetailDAO;
import thecafeshop.DAOImp.ExportbilldetailDAOImp;
import thecafeshop.entity.Exportbilldetail;
import thecafeshop.entity.ExportbilldetailId;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ExportbilldetailService implements ExportbilldetailDAOImp {

	@Autowired
	ExportbilldetailDAO exportbilldetailDAO;

	@Override
	public Boolean addExportbilldetail(Exportbilldetail exportbilldetail) {
		// TODO Auto-generated method stub
		return exportbilldetailDAO.addExportbilldetail(exportbilldetail);
	}

	@Override
	public List<Exportbilldetail> getInfoByExportbillId(int exportbillid) {
		// TODO Auto-generated method stub
		return exportbilldetailDAO.getInfoByExportbillId(exportbillid);
	}

	@Override
	public Exportbilldetail getInfoByExportbilldetail(ExportbilldetailId id) {
		// TODO Auto-generated method stub
		return exportbilldetailDAO.getInfoByExportbilldetail(id);
	}

	@Override
	public Boolean deleteExportbilldetail(Exportbilldetail exportbilldetail) {
		// TODO Auto-generated method stub
		return exportbilldetailDAO.deleteExportbilldetail(exportbilldetail);
	}

	@Override
	public Boolean editExportbilldetail(Exportbilldetail exportbilldetail) {
		// TODO Auto-generated method stub
		return exportbilldetailDAO.editExportbilldetail(exportbilldetail);
	}

	@Override
	public int getNumberExportbilldetail(int exportbillid) {
		// TODO Auto-generated method stub
		return exportbilldetailDAO.getNumberExportbilldetail(exportbillid);
	}

	@Override
	public Boolean checkExistMaterialDetail(int materialdetailid) {
		// TODO Auto-generated method stub
		return exportbilldetailDAO.checkExistMaterialDetail(materialdetailid);
	}

}
