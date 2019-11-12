package thecafeshop.DAOImp;

import thecafeshop.entity.Materialdetail;

import java.util.List;


public interface MaterialdetailDAOImp extends CommonDAOImp{
	
	public int addMaterialdetail(Materialdetail materialdetail);

	public List<Materialdetail> findAll();

	public List<Materialdetail> search(String materialdetailid, String name);

	public Materialdetail getInfoById(int materialdetailid);

	public Boolean deleteMaterialdetail(int materialdetailid);

	public Boolean editMaterialdetail(Materialdetail materialdetail);
	
	public Boolean checkExistMaterial(int materialid);
	
	//lấy nguyen lieu tồn kho
	public List<Materialdetail>layNguyenLieuTonKho(int materialid);
	
	//lấy số nguyen lieu tồn kho
	public int laySoNguyenLieuTonKho(int materialid);

}
