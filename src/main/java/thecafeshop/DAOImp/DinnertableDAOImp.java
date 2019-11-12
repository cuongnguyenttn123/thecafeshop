package thecafeshop.DAOImp;

import thecafeshop.entity.Dinnertable;

import java.util.List;



public interface DinnertableDAOImp extends CommonDAOImp {

	public Boolean addDinnertable(Dinnertable dinnertable);

	public List<Dinnertable> findAll();

	public List<Dinnertable> dsBanTrong();

	public List<Dinnertable> findLimit(int startPosition);

	public Dinnertable getInfoById(int dinnertableid);

	public List<Integer> getListCountChair();
	
	public Boolean checkExistDinnerTable(String name);

	public Boolean deleteDinnertable(int dinnertableid);

	public Boolean editDinnertable(Dinnertable dinnertable);
}
