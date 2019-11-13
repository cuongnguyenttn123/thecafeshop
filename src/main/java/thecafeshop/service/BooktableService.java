package thecafeshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import thecafeshop.DAO.BooktableDAO;
import thecafeshop.DAOImp.BooktableDAOImp;
import thecafeshop.entity.Booktable;
import thecafeshop.entity.BooktableId;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class BooktableService implements BooktableDAOImp {

	@Autowired
	BooktableDAO booktableDAO;

	@Override
	public Boolean addBooktable(Booktable booktable) {
		// TODO Auto-generated method stub
		return booktableDAO.addBooktable(booktable);
	}

	@Override
	public List<Booktable> findAll() {
		// TODO Auto-generated method stub
		return booktableDAO.findAll();
	}

	@Override
	public Booktable getInfoByBooktableId(BooktableId booktableId) {
		// TODO Auto-generated method stub
		return booktableDAO.getInfoByBooktableId(booktableId);
	}

	@Override
	public Boolean deleteBooktable(BooktableId booktableId) {
		// TODO Auto-generated method stub
		return booktableDAO.deleteBooktable(booktableId);
	}

	@Override
	public Boolean editBooktable(Booktable booktable) {
		// TODO Auto-generated method stub
		return booktableDAO.editBooktable(booktable);
	}

	@Override
	public Boolean checkExistDinnerTable(int dinnertableid) {
		// TODO Auto-generated method stub
		return booktableDAO.checkExistDinnerTable(dinnertableid);
	}
}