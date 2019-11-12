package thecafeshop.DAOImp;

import thecafeshop.entity.Price;

import java.util.Date;
import java.util.List;



public interface PriceDAOImp extends CommonDAOImp {

	public Boolean addPrice(Price price);
	
	public Price getInfoById(String prId);
	
	public Price getInfoByProduct(String PId);
	
	public Boolean editPrice(Price price);
	
	/*get price will apply*/
	public Price getNewPrice(String PId);
	
	/*get price applied*/
	public int getOldPrice(String PId);
	
}
