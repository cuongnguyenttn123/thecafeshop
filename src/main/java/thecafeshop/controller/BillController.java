package thecafeshop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import thecafeshop.DTO.BillDTO;
import thecafeshop.DTO.BillDetailDTO;
import thecafeshop.entity.Bill;
import thecafeshop.entity.Billdetail;
import thecafeshop.entity.BilldetailId;
import thecafeshop.service.BillService;
import thecafeshop.service.BilldetailService;
import thecafeshop.common.Common;
import thecafeshop.service.PriceService;

@Controller
@RequestMapping("")
public class BillController extends Common {

	@Autowired
	BillService billService;
	@Autowired
	BilldetailService billdetailService;
	@Autowired
	PriceService priceService;

	@GetMapping(value = "/admin/bill")
	public String index(ModelMap modelMap, HttpSession httpSession) {

		int totalPage = billService.findAll().size() / super.MAX_RESULTS;
		if ((billService.findAll().size() % super.MAX_RESULTS) > 0) {
			totalPage++;
		}
		modelMap.addAttribute("totalPage", totalPage);

		return "/admin/management-system/bill";
	}

	@GetMapping(value = "/admin/bill/table")
	public String tbody(ModelMap modelMap, HttpSession httpSession, @RequestParam String startPosition) {

		List<Bill> bills = billService.findLimit(Integer.valueOf(startPosition.trim()) - 1);

		List<BillDTO> dtos = new ArrayList<BillDTO>();
		for (Bill bill : bills) {
			BillDTO billDTO = new BillDTO();
			billDTO.setBill(bill);
//			billDTO.setTableName(bill.getDinnertable().getName());
			billDTO.setTotalPrice(billService.getTotalPriceOfBill(bill.getBillid()));
			billDTO.setBillstatus(bill.getBillstatus());

			dtos.add(billDTO);
		}
		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-system/content/bill/tBody";
	}

	@PostMapping(value = "/admin/bill/remove")
	public String remove(ModelMap modelMap, HttpSession httpSession, @RequestParam String billid) {

		Bill bill = billService.getInfoById(Integer.valueOf(billid.trim()));
		if (bill == null) {
			modelMap.addAttribute("results", "Hoá đơn không tồn tại!");
			return "/admin/public/Danger";
		}

		Set<Billdetail> billdetails = bill.getBilldetails();
		for (Billdetail billdetail : billdetails) {
			billdetail.setIsdelete(IS_DELETE);
			billdetailService.editBilldetail(billdetail);
		}

		bill.setIsdelete(this.IS_DELETE);
		bill.setUpdateat(new Date());
		billService.editBill(bill);

		modelMap.addAttribute("result", "Xóa thành công!");
		return "/admin/public/Success";// đã tồn tại
	}

	@GetMapping(value = "/admin/bill/edit")
	public String view(ModelMap modelMap, HttpSession httpSession, @RequestParam String billid) {

		Bill bill = billService.getInfoById(Integer.valueOf(billid.trim()));
		if (bill == null) {
			modelMap.addAttribute("results", "Hoá đơn không tồn tại!");
			return "/admin/public/Danger";
		}

		Date startdatetime = bill.getStartdatetime();

		List<Billdetail> billdetails = billdetailService.getInfoBilldetailByBillId(Integer.valueOf(billid.trim()));
		List<BillDetailDTO> dtos = new ArrayList<BillDetailDTO>();
		for (Billdetail billdetail : billdetails) {
			String productId = billdetail.getProduct().getProductid();

			BillDetailDTO dto = new BillDetailDTO();
			dto.setBillid(Integer.valueOf(billid.trim()));
			dto.setProductid(productId);
			dto.setName(billdetail.getProduct().getName());

			int quantity = billdetail.getQuantity();
			int SinglePrice = billdetailService.getSinglePriceOfBillDetail(productId, startdatetime);
			BilldetailId id = new BilldetailId(productId, Integer.valueOf(billid.trim()));
			int TotalPrice = billdetailService.getPriceOfBillDetail(id);
			dto.setQuantity(quantity);
			dto.setSinglePrice(SinglePrice);
			dto.setTotalPrice(TotalPrice);

			dtos.add(dto);
		}

		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-system/content/bill/tBodyDetail";
	}

	@PostMapping(value = "/admin/billDetail/remove")
	public String remove(ModelMap modelMap, HttpSession httpSession, @RequestParam String billid,
			@RequestParam String productid) {

		Billdetail billdetail = billdetailService
				.getInfoBilldetailByBilldetailId(new BilldetailId(productid.trim(), Integer.valueOf(billid.trim())));

		if (billdetail == null) {
			modelMap.addAttribute("results", "Chi tiết hóa đơn không tồn tại!");
			return "/admin/public/Danger";
		}

		billdetail.setIsdelete(this.IS_DELETE);
		billdetail.setUpdateat(new Date());
		billdetailService.editBilldetail(billdetail);

		modelMap.addAttribute("result", "Xóa thành công!");
		return "/admin/public/Success";// đã tồn tại
	}

	@GetMapping(value = "/admin/billDetail/edit")
	public String viewBillDetail(ModelMap modelMap, HttpSession httpSession, @RequestParam String billid,
			@RequestParam String productid) {

		Billdetail billdetail = billdetailService
				.getInfoBilldetailByBilldetailId(new BilldetailId(productid.trim(), Integer.valueOf(billid.trim())));

		if (billdetail == null) {
			modelMap.addAttribute("results", "Chi tiết hóa đơn không tồn tại!");
			return "/admin/public/Danger";
		}

		modelMap.addAttribute("billdetail", billdetail);
		return "/admin/management-system/content/bill/form";
	}

	@PostMapping(value = "/admin/billDetail/edit")
	public String editBillDetail(ModelMap modelMap, HttpSession httpSession, @RequestParam String billid,
			@RequestParam String productid, @RequestParam String quantity) {

		/* check */
		List<String> results = checkForm(quantity);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";
		}
		/* check[END] */

		Billdetail billdetail = billdetailService
				.getInfoBilldetailByBilldetailId(new BilldetailId(productid.trim(), Integer.valueOf(billid.trim())));

		if (billdetail == null) {
			modelMap.addAttribute("results", "Chi tiết hóa đơn không tồn tại!");
			return "/admin/public/Danger";
		}

		billdetail.setQuantity(Integer.valueOf(quantity.trim()));

		billdetailService.editBilldetail(billdetail);

		modelMap.addAttribute("result", "Cập nhật thành công!");
		return "/admin/public/Success";
	}

	public List<String> checkForm(String quantity) {
		List<String> results = new ArrayList<String>();
		if (quantity.trim().length() == 0 || Integer.valueOf(quantity.trim()) <= 0) {

			results.add("Số lượng không được để trống và phải lớn hơn 0!");
		}
		return results;
	}
}
