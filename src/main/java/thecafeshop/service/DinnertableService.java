package thecafeshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAO.DinnertableDAO;
import thecafeshop.DAOImp.DinnertableDAOImp;
import thecafeshop.entity.Dinnertable;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class DinnertableService implements DinnertableDAOImp {

	@Autowired
	DinnertableDAO dinnertableDAO;

	@Override
	public Boolean addDinnertable(Dinnertable dinnertable) {
		// TODO Auto-generated method stub
		return dinnertableDAO.addDinnertable(dinnertable);
	}

	@Override
	public List<Dinnertable> findAll() {
		// TODO Auto-generated method stub
		return dinnertableDAO.findAll();
	}

	@Override
	public Dinnertable getInfoById(int dinnertableid) {
		// TODO Auto-generated method stub
		return dinnertableDAO.getInfoById(dinnertableid);
	}

	@Override
	public Boolean deleteDinnertable(int dinnertableid) {
		// TODO Auto-generated method stub
		return dinnertableDAO.deleteDinnertable(dinnertableid);
	}

	@Override
	public Boolean editDinnertable(Dinnertable dinnertable) {
		// TODO Auto-generated method stub
		return dinnertableDAO.editDinnertable(dinnertable);
	}

	@Override
	public List<Dinnertable> findLimit(int startPosition) {
		// TODO Auto-generated method stub
		return dinnertableDAO.findLimit(startPosition);
	}

	@Override
	public Boolean checkExistDinnerTable(String name) {
		// TODO Auto-generated method stub
		return dinnertableDAO.checkExistDinnerTable(name);
	}

	@Override
	public List<Integer> getListCountChair() {
		// TODO Auto-generated method stub
		return dinnertableDAO.getListCountChair();
	}

	@Override
	public List<Dinnertable> dsBanTrong() {
		// TODO Auto-generated method stub
		return dinnertableDAO.dsBanTrong();
	}

}