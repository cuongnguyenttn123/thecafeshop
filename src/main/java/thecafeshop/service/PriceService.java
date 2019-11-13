package thecafeshop.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAO.PriceDAO;
import thecafeshop.DAOImp.PriceDAOImp;
import thecafeshop.entity.Price;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class PriceService implements PriceDAOImp {

	@Autowired
	private PriceDAO priceDAO;

	@Override
	public Boolean addPrice(Price price) {
		return priceDAO.addPrice(price);
	}

	@Override
	public Price getInfoById(String prId) {
		return priceDAO.getInfoById(prId);
	}

	@Override
	public Price getInfoByProduct(String PId) {

		return priceDAO.getInfoByProduct(PId);
	}

	@Override
	public Price getNewPrice(String PId) {

		return priceDAO.getNewPrice(PId);
	}

	@Override
	public int getOldPrice(String PId) {

		return priceDAO.getOldPrice(PId);
	}

	@Override
	public Boolean editPrice(Price price) {
		// TODO Auto-generated method stub
		return priceDAO.editPrice(price);
	}
}
