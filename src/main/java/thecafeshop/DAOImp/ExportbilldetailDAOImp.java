package thecafeshop.DAOImp;

import thecafeshop.entity.Exportbilldetail;
import thecafeshop.entity.ExportbilldetailId;

import java.util.List;



public interface ExportbilldetailDAOImp extends CommonDAOImp{

	public Boolean addExportbilldetail(Exportbilldetail exportbilldetail);

	public List<Exportbilldetail> getInfoByExportbillId(int exportbillId);

	public Exportbilldetail getInfoByExportbilldetail(ExportbilldetailId id);

	public Boolean deleteExportbilldetail(Exportbilldetail exportbilldetail);

	public Boolean editExportbilldetail(Exportbilldetail exportbilldetail);
	
	public int getNumberExportbilldetail(int exportbillId);
	
	public Boolean checkExistMaterialDetail(int materialdetailid);
	
}
