package thecafeshop.controller;

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
import thecafeshop.DTO.DinnerTableDTO;
import thecafeshop.entity.Dinnertable;
import thecafeshop.service.BillService;
import thecafeshop.service.BooktableService;
import thecafeshop.common.Common;
import thecafeshop.service.DinnertableService;


@Controller
@RequestMapping("")
public class DinnerTableController extends Common {

	@Autowired
	DinnertableService dinnertableService;
	@Autowired
	BooktableService booktableService;
	@Autowired
	BillService billService;

	@GetMapping(value = "/admin/dinner-table")
	public String index(ModelMap modelMap, HttpSession httpSession) {

		int totalPage = dinnertableService.findAll().size() / super.MAX_RESULTS;
		if ((dinnertableService.findAll().size() % super.MAX_RESULTS) > 0) {
			totalPage++;
		}
		modelMap.addAttribute("totalPage", totalPage);
		return "/admin/management-system/dinner-table";

	}

	@GetMapping(value = "/admin/dinner-table/table")
	public String tbody(ModelMap modelMap, HttpSession httpSession, @RequestParam String startPosition) {

		List<Dinnertable> dinnertables = dinnertableService.findLimit(Integer.valueOf(startPosition.trim()) - 1);

		List<DinnerTableDTO> dtos = new ArrayList<DinnerTableDTO>();
		for (Dinnertable dinnertable : dinnertables) {
			DinnerTableDTO dinnerTableDTO = new DinnerTableDTO();
			dinnerTableDTO.setDinnertable(dinnertable);

			dinnerTableDTO.setCanDelete(false);
			if (booktableService.checkExistDinnerTable(dinnertable.getDinnertableid())) {
				dinnerTableDTO.setCanDelete(true);
			}
			if (billService.checkExistDinnerTable(dinnertable.getDinnertableid())) {
				dinnerTableDTO.setCanDelete(true);
			}

			dtos.add(dinnerTableDTO);
		}

		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-system/content/dinner-table/tBody";
	}

	@PostMapping(value = "/admin/dinner-table/insert")
	public String insert(ModelMap modelMap, HttpSession httpSession, @RequestParam String name,
			@RequestParam String countchair) {

		/* check */
		List<String> results = checkForm(name, countchair);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";//
		}
		/* check[END] */

		if (dinnertableService.checkExistDinnerTable(name.trim())) {

			modelMap.addAttribute("results", "Mã đã tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		Dinnertable dinnertable = new Dinnertable();
		dinnertable.setName(name.trim());
		dinnertable.setCountchair(Integer.valueOf(countchair.trim()));
		dinnertableService.addDinnertable(dinnertable);

		modelMap.addAttribute("result", "Thêm thành công!");
		return "/admin/public/Success"; // thành công
	}

	@PostMapping(value = "/admin/dinner-table/remove")
	public String remove(ModelMap modelMap, HttpSession httpSession, @RequestParam String dinnertableid) {

		Dinnertable dinnertable = dinnertableService.getInfoById(Integer.valueOf(dinnertableid.trim()));
		if (dinnertable == null) {
			modelMap.addAttribute("results", "Bàn không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}
		dinnertable.setIsdelete(IS_DELETE);
		dinnertable.setUpdateat(new Date());
		dinnertableService.editDinnertable(dinnertable);

		modelMap.addAttribute("result", "Xóa thành công!");
		return "/admin/public/Success";// đã tồn tại
	}

	@GetMapping(value = "/admin/dinner-table/edit")
	public String view(ModelMap modelMap, HttpSession httpSession, @RequestParam String dinnertableid) {

		Dinnertable dinnertable = dinnertableService.getInfoById(Integer.valueOf(dinnertableid.trim()));
		if (dinnertable == null) {
			modelMap.addAttribute("results", "Bàn không tồn tại!");
			return "/admin/public/Danger";//
		}

		modelMap.addAttribute("dinnertable", dinnertable);
		return "/admin/management-system/content/dinner-table/form";
	}

	@PostMapping(value = "/admin/dinner-table/edit")
	public String edit(ModelMap modelMap, HttpSession httpSession, @RequestParam String dinnertableid,
			@RequestParam String name, @RequestParam String countchair) {

		/* check */
		List<String> results = checkForm(name, countchair);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";
		}
		/* check[END] */

		Dinnertable dinnertable = dinnertableService.getInfoById(Integer.valueOf(dinnertableid.trim()));
		if (dinnertable == null) {
			modelMap.addAttribute("results", "Bàn không tồn tại!");
			return "/admin/public/Danger";
		}

		dinnertable.setName(name.trim());
		dinnertable.setCountchair(Integer.valueOf(countchair.trim()));
//		dinnertable.setUpdateby(updateby);
		dinnertable.setUpdateat(new Date());
		dinnertableService.editDinnertable(dinnertable);

		modelMap.addAttribute("result", "Cập nhật thành công!");
		return "/admin/public/Success";
	}

	public List<String> checkForm(String name, String countchair) {

		List<String> results = new ArrayList<String>();
		if (name.trim().length() <= 0 || name.trim().length() > 255) {
			results.add("Tên không thể để trống và tối đa 255 ký tự!");
		}
		if (countchair.trim().length() <= 0 || Integer.valueOf(countchair.trim()) <= 0) {
			results.add("Số ghế không thể để trống và phải lớn hơn 0!");
		}
		return results;

	}
}
