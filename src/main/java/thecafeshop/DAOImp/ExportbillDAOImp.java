package thecafeshop.DAOImp;

import thecafeshop.entity.Exportbill;

import java.util.List;



public interface ExportbillDAOImp extends CommonDAOImp{

    public int addExportbill(Exportbill exportbill);
    
    public List<Exportbill> findAll();
    
    public List<Exportbill> findLimit(int startPosition);

	public Exportbill getInfoById(int exportbillid);

	public Boolean deleteExportbill(int exportbillid);

	public Boolean editExportbill(Exportbill exportbill);

	// lấy số lượng tồn kho sp
	public int totalQuantityProduct(String productid);
	
	public List<Exportbill> getListExportBillbyProduct(String productid);
	
}
