package thecafeshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAO.AtpositionDAO;
import thecafeshop.DAOImp.AtpositionDAOImp;
import thecafeshop.entity.Atposition;
import thecafeshop.entity.AtpositionId;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class AtpositionService implements AtpositionDAOImp {

	@Autowired
	AtpositionDAO atpositionDAO;

	@Override
	public Boolean addAtposition(Atposition atposition) {

		return atpositionDAO.addAtposition(atposition);
	}

	@Override
	public Atposition getInfoById(AtpositionId atpositionId) {

		return atpositionDAO.getInfoById(atpositionId);
	}

	@Override
	public Boolean deleteAtposition(AtpositionId atpositionId) {

		return atpositionDAO.deleteAtposition(atpositionId);
	}

	@Override
	public Boolean editAtposition(Atposition atposition) {

		return atpositionDAO.editAtposition(atposition);
	}

	@Override
	public Boolean checkExistPosition(String positionid) {
		// TODO Auto-generated method stub
		return atpositionDAO.checkExistPosition(positionid);
	}

//	@Override
//	public Atposition getInfoByEmployeeId(String employeeid) {
//		// TODO Auto-generated method stub
//		return atpositionDAO.getInfoByEmployeeId(employeeid);
//	}

}