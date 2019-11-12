package thecafeshop.DAOImp;

import thecafeshop.entity.Importbilldetail;
import thecafeshop.entity.ImportbilldetailId;

import java.util.List;



public interface ImportbilldetailDAOImp extends CommonDAOImp{

	public Boolean addImportbilldetail(Importbilldetail importbilldetail);

	public List<Importbilldetail> getInfoByImportbillid(int importbillid);

	public Importbilldetail getInfoByImportbilldetailId(ImportbilldetailId id);

	public Boolean deleteImportbilldetail(Importbilldetail importbilldetail);

	public Boolean editImportbilldetail(Importbilldetail importbilldetail);
	
	public int getNumberImportbilldetail(int importbillid);
	
	public Boolean checkExistMaterialDetail(int materialdetailid);
	
}
