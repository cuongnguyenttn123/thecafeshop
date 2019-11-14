package thecafeshop.common;

import thecafeshop.DAOImp.CommonDAOImp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;



public class Common implements CommonDAOImp {

	public static String HOME_REDIRECT = "redirect:/admin/login";

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static SimpleDateFormat sdfDateField = new SimpleDateFormat("yyyy-MM-dd");
	
	public static SimpleDateFormat sdfDateTimeField = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static SimpleDateFormat sdfTimeField = new SimpleDateFormat("hh:mm");

	/**
	 * check logined
	 * 
	 * @param
	 */
	public Boolean checkLogIn(HttpSession httpSession) {
		if (httpSession.getAttribute("emId") == null) {
			// has a session emId
			return true;
		} else {
			return false;
		}
	}

	public int rateOldAndNewPrice(float oldPrice, float newPrice) {

		/* if old price = 0 -> product is new realease and dont start */
		if ((newPrice < oldPrice) && (oldPrice > 0)) {

			float result = (oldPrice - newPrice) / oldPrice * 100;

			return (int) result;
		}

		return 0;
	}

	public List<String> listProductCart(String listCart) {

		List<String> list = new ArrayList<String>();
		String listPId = listCart;
		while ((listPId.indexOf("~") != -1)) {
			String PId = listPId.substring(0, listPId.indexOf("~"));

			list.add(PId);

			listPId = listPId.substring(listPId.indexOf("~") + 1, listPId.length());
		}

		return list;
	}
	
	public Boolean checkNumberPhone(String number) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(number);
        if (!matcher.matches()) {
            return false;
        } else if (number.length() == 10 || number.length() == 11) {
            if (number.length() == 10) {
                if (number.substring(0, 2).equals("09") || number.substring(0, 2).equals("01")) {
                    return true;
                } else {
                    return false;
                }
            } else if (number.substring(0, 2).equals("09") || number.substring(0, 2).equals("01")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


	public List<Integer> listNumberCart(String listNumberProduct) {

		List<Integer> list = new ArrayList<Integer>();
		while ((listNumberProduct.indexOf("~") != -1)) {
			Integer number = Integer.valueOf(listNumberProduct.substring(0, listNumberProduct.indexOf("~")));

			list.add(number);
			
			listNumberProduct = listNumberProduct.substring(listNumberProduct.indexOf("~") + 1,
					listNumberProduct.length());
		}
		
		return list;
	}
}
