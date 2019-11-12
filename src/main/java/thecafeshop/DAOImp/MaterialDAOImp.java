package thecafeshop.DAOImp;

import thecafeshop.entity.Material;

import java.util.List;


public interface MaterialDAOImp extends CommonDAOImp{

    public Boolean addMaterial(Material material);
    
    public List<Material> findAll();
    
    public List<Material> findLimit(int startPosition);

	public Material getInfoById(int materialid);
	
	public Boolean checkExistNameMaterial(String name);

	public Boolean deleteAtposition(int materialid);

	public Boolean editMaterial(Material material);
	
}
