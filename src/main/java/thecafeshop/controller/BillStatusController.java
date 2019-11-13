package thecafeshop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import thecafeshop.DTO.BillStatusDTO;
import thecafeshop.entity.Billstatus;
import thecafeshop.service.BillstatusService;
import thecafeshop.service.Common;


@Controller
@RequestMapping("")
public class BillStatusController extends Common {

	@Autowired
	BillstatusService billstatusService;



	@GetMapping(value = "/admin/bill-status")
	public String index(ModelMap modelMap, HttpSession httpSession) {

		int totalPage = billstatusService.findAll().size() / super.MAX_RESULTS;
		if ((billstatusService.findAll().size() % super.MAX_RESULTS) > 0) {
			totalPage++;
		}
		modelMap.addAttribute("totalPage", totalPage);
		return "/admin/management-system/bill-status";
	}

	@GetMapping(value = "/admin/bill-status/table")
	public String tbody(ModelMap modelMap, HttpSession httpSession, @RequestParam String startPosition) {

		List<Billstatus> ListBillstatus = billstatusService.findLimit(Integer.valueOf(startPosition.trim()) - 1);

		List<BillStatusDTO> dtos = new ArrayList<BillStatusDTO>();
		for (Billstatus billstatus : ListBillstatus) {
			BillStatusDTO billStatusDTO = new BillStatusDTO();
			billStatusDTO.setBillstatus(billstatus);

			billStatusDTO.setCanDelete(false);
//			if (billService.checkExistBillStatus(billstatus.getBillstatusid())) {
//				billStatusDTO.setCanDelete(true);
//			}
			dtos.add(billStatusDTO);
		}

		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-system/content/bill-status/tBody";
	}

	@PostMapping(value = "/admin/bill-status/insert")
	public String insert(ModelMap modelMap, HttpSession httpSession, @RequestParam String billstatusid,
			@RequestParam String name) {

		/* check */
		List<String> results = checkForm(billstatusid, name);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";// đã tồn tại
		}
		/* check[END] */

		if (billstatusService.getInfoById(billstatusid.trim()) != null) {

			modelMap.addAttribute("results", "Mã đã tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		Billstatus billstatus = new Billstatus();
		billstatus.setBillstatusid(billstatusid);
		billstatus.setName(name.trim());
		billstatus.setUpdateat(new Date());
		billstatus.setIsdelete(this.IS_NOT_DELETE);
		billstatusService.addBillstatus(billstatus);

		List<Billstatus> ListBillstatus = billstatusService.findAll();
		modelMap.addAttribute("ListBillstatus", ListBillstatus);

		modelMap.addAttribute("result", "Thêm thành công!");
		return "/admin/public/Success"; // thành công
	}

	@PostMapping(value = "/admin/bill-status/remove")
	public String remove(ModelMap modelMap, HttpSession httpSession, @RequestParam String billstatusid) {

		Billstatus billstatus = billstatusService.getInfoById(billstatusid.trim());
		if (billstatus == null) {
			modelMap.addAttribute("results", "Trạng thái hóa đơn không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}
		billstatus.setIsdelete(this.IS_DELETE);
		billstatusService.editBilldetail(billstatus);

		modelMap.addAttribute("result", "Xóa thành công!");
		return "/admin/public/Success";// đã tồn tại
	}

	@GetMapping(value = "/admin/bill-status/edit")
	public String view(ModelMap modelMap, HttpSession httpSession, @RequestParam String billstatusid) {

		Billstatus billstatus = billstatusService.getInfoById(billstatusid.trim());
		if (billstatus == null) {
			modelMap.addAttribute("results", "Trạng thái hóa đơn không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		modelMap.addAttribute("billstatus", billstatus);
		return "/admin/management-system/content/bill-status/form";
	}

	@PostMapping(value = "/admin/bill-status/edit")
	public String edit(ModelMap modelMap, HttpSession httpSession, @RequestParam String billstatusid,
			@RequestParam String name) {

		/* check */
		List<String> results = checkForm(billstatusid, name);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";// đã tồn tại
		}
		/* check[END] */

		Billstatus billstatus = billstatusService.getInfoById(billstatusid.trim());
		if (billstatus == null) {
			modelMap.addAttribute("results", "Trạng thái hóa đơn không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		billstatus.setName(name);
		billstatusService.editBilldetail(billstatus);

		modelMap.addAttribute("result", "Cập nhật thành công!");
		return "/admin/public/Success";
	}

	public List<String> checkForm(String billstatusid, String name) {
		List<String> results = new ArrayList<String>();
		if (billstatusid.length() == 0 || billstatusid.length() > 8) {

			results.add("Mã phải không được để trống và tối đa 7 kí tự!");
		}
		if (name.length() == 0) {

			results.add("Tên không được để trống!");
		}
		return results;
	}
}
