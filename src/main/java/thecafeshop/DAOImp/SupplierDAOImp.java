package thecafeshop.DAOImp;

import thecafeshop.entity.Supplier;

import java.util.List;


public interface SupplierDAOImp extends CommonDAOImp{

	public Boolean addSupplier(Supplier supplier);

	public List<Supplier> findAll();
	
	public List<Supplier> findLimit(int startPosition);

	public Supplier getInfoById(int supplierid);
	
	public Boolean checkExistByName(String name);

	public Boolean deleteSupplier(int supplierid);

	public Boolean editSupplier(Supplier supplier);
}
