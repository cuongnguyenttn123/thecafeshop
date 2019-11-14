package thecafeshop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import thecafeshop.entity.Booktable;
import thecafeshop.entity.BooktableId;
import thecafeshop.entity.Customer;
import thecafeshop.service.BooktableService;
import thecafeshop.common.Common;
import thecafeshop.service.CustomerService;
import thecafeshop.service.DinnertableService;


@Controller
@RequestMapping("")
public class UserBookTableController extends Common {

	@Autowired
	CustomerService customerService;
	@Autowired
	BooktableService booktableService;
	@Autowired
	DinnertableService dinnertableService;

	@GetMapping(value = "/book-table")
	public String index(ModelMap modelMap) {

		List<Integer> lists = dinnertableService.getListCountChair();
		modelMap.addAttribute("lists", lists);

		return "/user/bookTable";
	}

	@PostMapping(value = "/book-table")
	public String bookTable(ModelMap modelMap, @RequestParam String name, @RequestParam String address,
			@RequestParam String phone, @RequestParam String startdatetime, @RequestParam String countpeople,
			@RequestParam String notice) {

		/* check */
		List<String> results = checkForm(name, address, phone, startdatetime, countpeople);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";
		}
		/* check[END] */

		Customer customer = new Customer();
		customer.setName(name);
		customer.setAddress(address);
		customer.setPhone(Integer.valueOf(phone));
		customer.setIsdelete(super.IS_NOT_DELETE);

		int lastCustomerID = customerService.addCustomer(customer);

		if (lastCustomerID == -1) {
			modelMap.addAttribute("results", "Lỗi bất ngờ!");
			return "/admin/public/Danger";
		}

		Booktable booktable = new Booktable();
		BooktableId id = new BooktableId(lastCustomerID, 1, "1");
		booktable.setId(id);
		Date DatetimeStart = null;
		try {
			DatetimeStart = super.sdfDateTimeField.parse(startdatetime);
		} catch (Exception e) {
		}
		booktable.setStartdatetime(DatetimeStart);
		booktable.setStatus(super.STATUS_NOT_CONFIRM);
		booktable.setCountpeople(Integer.valueOf(countpeople.trim()));
		booktable.setNotice(notice.trim());
		booktable.setUpdateat(new Date());
		booktable.setIsdelete(IS_NOT_DELETE);
		if (!booktableService.addBooktable(booktable)) {
			modelMap.addAttribute("results", "Lỗi bất ngờ!");
			return "/admin/public/Danger";// Ä‘Ã£ tá»“n táº¡i
		}

		modelMap.addAttribute("result", "Đặt bàn thành công!");
		return "/admin/public/Success"; // thÃ nh cÃ´ng
	}

	public List<String> checkForm(String name, String address, String phone, String startdatetime, String countpeople) {
		List<String> results = new ArrayList<String>();

		if (name.trim().length() <= 0 || name.trim().length() > 255) {
			results.add("Tên không thể để trống và tối đa 255 ký tự!");
		}
		if (address.trim().length() <= 0 || name.trim().length() > 255) {
			results.add("Địa chỉ không thể để trống và tối đa 255 ký tự");
		}
		if (!super.checkNumberPhone(phone.trim())) {
			results.add("Số điện thoại không đúng!");
		}
		if (startdatetime.trim().length() <= 0) {
			results.add("Ngày đến trong được để trống!");
		}
		if (countpeople.trim().length() <= 0) {
			results.add("Vui lòng chọn loại bàn hoặc nhập số người!");
		}

		return results;
	}
}
