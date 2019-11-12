package thecafeshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAO.TablestatusDAO;
import thecafeshop.DAOImp.TablestatusDAOImp;
import thecafeshop.entity.Tablestatus;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(rollbackFor = Exception.class)
public class TablestatusService implements TablestatusDAOImp {

	@Autowired
	TablestatusDAO tablestatusDAO;

	@Override
	public Boolean addTablestatus(Tablestatus tablestatus) {
		// TODO Auto-generated method stub
		return tablestatusDAO.addTablestatus(tablestatus);
	}

	@Override
	public List<Tablestatus> findAll() {
		// TODO Auto-generated method stub
		return tablestatusDAO.findAll();
	}

	@Override
	public Tablestatus getInfoById(int tablestatusid) {
		// TODO Auto-generated method stub
		return tablestatusDAO.getInfoById(tablestatusid);
	}

	@Override
	public Boolean deleteTablestatus(int tablestatusid) {
		// TODO Auto-generated method stub
		return tablestatusDAO.deleteTablestatus(tablestatusid);
	}

	@Override
	public Boolean editTablestatus(Tablestatus tablestatus) {
		// TODO Auto-generated method stub
		return tablestatusDAO.editTablestatus(tablestatus);
	}

	@Override
	public Boolean checkExist(String name) {
		// TODO Auto-generated method stub
		return tablestatusDAO.checkExist(name);
	}

	@Override
	public List<Tablestatus> findLimit(int startPosition) {
		// TODO Auto-generated method stub
		return tablestatusDAO.findLimit(startPosition);
	}
}