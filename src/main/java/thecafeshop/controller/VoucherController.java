package thecafeshop.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import thecafeshop.DTO.VoucherDTO;
import thecafeshop.entity.Voucher;
import thecafeshop.service.BillService;
import thecafeshop.common.Common;
import thecafeshop.service.VoucherService;


@Controller
@RequestMapping("")
public class VoucherController extends Common {

	@Autowired
	VoucherService voucherService;
	@Autowired
	BillService billService;

	@GetMapping(value = "/admin/voucher")
	public String index(ModelMap modelMap, HttpSession httpSession) {

		int totalPage = voucherService.findAll().size() / super.MAX_RESULTS;
		if ((voucherService.findAll().size() % super.MAX_RESULTS) > 0) {
			totalPage++;
		}
		modelMap.addAttribute("totalPage", totalPage);

		return "/admin/management-system/voucher";
	}

	@GetMapping(value = "/admin/voucher/table")
	public String tbody(ModelMap modelMap, HttpSession httpSession, @RequestParam String startPosition) {

		List<Voucher> vouchers = voucherService.findLimit(Integer.valueOf(startPosition.trim()) - 1);
		List<VoucherDTO> dtos = new ArrayList<VoucherDTO>();
		for (Voucher voucher : vouchers) {
			VoucherDTO voucherDTO = new VoucherDTO();
			voucherDTO.setVoucher(voucher);

			voucherDTO.setCanDelete(false);
			if (billService.checkExistVoucher(voucher.getVoucherid())) {
				voucherDTO.setCanDelete(true);
			}

			dtos.add(voucherDTO);
		}
		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-system/content/voucher/tBody";
	}

	@PostMapping(value = "/admin/voucher/insert")
	public String insert(ModelMap modelMap, HttpSession httpSession, @RequestParam String name,
			@RequestParam String startdatetime, @RequestParam String enddate, @RequestParam String number,
			@RequestParam String discount) throws ParseException {
		
		/* check */
		List<String> results = checkForm(name, startdatetime, enddate, number, discount);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";// đã tồn tại
		}
		/* check[END] */
		
		if (voucherService.findByName(name.trim()) != null) {

			modelMap.addAttribute("results", "Voucher đã tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		Voucher voucher = new Voucher();
		voucher.setName(name.trim());
		voucher.setStartdatetime(super.sdfDateField.parse(startdatetime));
		voucher.setEnddate(super.sdfDateField.parse(enddate));
		voucher.setNumber(Integer.valueOf(number));
		voucher.setCount(Integer.valueOf(number));
		voucher.setDiscount(Float.valueOf(discount));
		voucher.setUpdateat(new Date());
		voucher.setIsdelete(super.IS_NOT_DELETE);
		voucherService.addVoucher(voucher);

		List<Voucher> vouchers = voucherService.findAll();
		modelMap.addAttribute("vouchers", vouchers);

		modelMap.addAttribute("result", "Thêm thành công!");
		return "/admin/public/Success"; // thành công
	}

	@PostMapping(value = "/admin/voucher/remove")
	public String remove(ModelMap modelMap, HttpSession httpSession, @RequestParam String voucherid) {

		Voucher voucher = voucherService.findById(Integer.valueOf(voucherid.trim()));
		if (voucher == null) {
			modelMap.addAttribute("results", "Voucher không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}
		voucher.setIsdelete(this.IS_DELETE);
		voucherService.editVoucher(voucher);

		modelMap.addAttribute("result", "Xóa thành công!");
		return "/admin/public/Success";// đã tồn tại
	}

	@GetMapping(value = "/admin/voucher/edit")
	public String view(ModelMap modelMap, HttpSession httpSession, @RequestParam String voucherid) {

		Voucher voucher = voucherService.findById(Integer.valueOf(voucherid.trim()));
		if (voucher == null) {
			modelMap.addAttribute("results", "Voucher không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		/* start rồi: không cho sửa saleof, không cho sửa ngày start. */
		if (voucher.getStartdatetime().before(new Date())) {
			modelMap.addAttribute("temb", true);
		}

		modelMap.addAttribute("voucher", voucher);
		return "/admin/management-system/content/voucher/form";
	}

	@PostMapping(value = "/admin/voucher/edit")
	public String edit(ModelMap modelMap, HttpSession httpSession, @RequestParam String voucherid,
			@RequestParam String name, @RequestParam String startdatetime, @RequestParam String enddate,
			@RequestParam String number, @RequestParam String discount) throws ParseException {

		/* check */
		List<String> results = checkForm(name, startdatetime, enddate, number, discount);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";// đã tồn tại
		}
		/* check[END] */
		
		Voucher voucher = voucherService.findById(Integer.valueOf(voucherid.trim()));
		if (voucher == null) {
			modelMap.addAttribute("results", "Voucher không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}
		voucher.setName(name.trim());
		voucher.setStartdatetime(super.sdfDateField.parse(startdatetime));
		voucher.setUpdateat(new Date());
		voucher.setIsdelete(super.IS_NOT_DELETE);

		/* nếu mà voucher chưa start thì sửa thoải mái */
		if (voucher.getStartdatetime().after(new Date())) {
			voucher.setEnddate(super.sdfDateField.parse(enddate));
			voucher.setNumber(Integer.valueOf(number));
			voucher.setDiscount(Float.valueOf(discount));
		}
		/*
		 * start rồi: không cho sửa saleof, không cho sửa ngày start, nếu số voucher nhỏ
		 * hơn voucher tồn thì lỗi.
		 */
		else {
			if (Integer.valueOf(number) < voucher.getCount()) {
				modelMap.addAttribute("results", "Số không thể nhỏ hơn voucher tồn " + voucher.getCount() + "!");
				return "/admin/public/Danger";// đã tồn tại
			}
			voucher.setNumber(Integer.valueOf(number));
		}

		voucherService.editVoucher(voucher);

		modelMap.addAttribute("result", "Cập nhật thành công!");
		return "/admin/public/Success";
	}

	public List<String> checkForm(String name, String startdatetime, String enddate, String number, String discount) {
		List<String> results = new ArrayList<String>();
		if (name.trim().length() <= 0 || name.trim().length() > 255) {
			results.add("Tên không thể để trống và tối đa 255 ký tự!");
		}
		if (startdatetime.trim().length() <= 0) {
			results.add("Ngày bắt đầu không thể để trống!");
		}
		if (enddate.trim().length() <= 0) {
			results.add("Ngày kết thúc không thể để trống!");
		}
		if (number.trim().length() <= 0) {
			results.add("Số voucher không thể để trống!");
		}
		float temp = 0.0f;
		if (discount.trim().length() <= 0 /*|| Float.valueOf(discount) > temp*/) {
			results.add("Giảm giá không thể để trống và phải lớn hơn 0!");
		}
		return results;
	}
}
