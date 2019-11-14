package thecafeshop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import thecafeshop.DTO.TableStatusDTO;
import thecafeshop.entity.Tablestatus;
import thecafeshop.common.Common;
import thecafeshop.service.TablestatusService;


@Controller
@RequestMapping("")
public class TableStatusController extends Common {

	@Autowired
	TablestatusService tablestatusService;

	@GetMapping(value = "/admin/table-status")
	public String index(ModelMap modelMap, HttpSession httpSession) {

		int totalPage = tablestatusService.findAll().size() / super.MAX_RESULTS;
		if ((tablestatusService.findAll().size() % super.MAX_RESULTS) > 0) {
			totalPage++;
		}
		modelMap.addAttribute("totalPage", totalPage);
		
		return "/admin/management-system/table-status";
	}

	@GetMapping(value = "/admin/table-status/table")
	public String tbody(ModelMap modelMap, HttpSession httpSession, @RequestParam String startPosition) {

		List<Tablestatus> tablestatuses= tablestatusService.findLimit(Integer.valueOf(startPosition.trim()) - 1);

		List<TableStatusDTO> dtos = new ArrayList<TableStatusDTO>();
		for (Tablestatus tablestatus : tablestatuses) {
			TableStatusDTO dto = new TableStatusDTO();
			dto.setTablestatusid(tablestatus.getTablestatusid());
			dto.setName(tablestatus.getName());
			dto.setUpdateat(tablestatus.getUpdateat());

			dto.setCanDelete(false);
//			if (tablestatusService.checkExistBillStatus(tablestatus.getTablestatusid())) {
//				tableStatusDTO.setCanDelete(true);
//			}
			dtos.add(dto);
		}
		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-system/content/table-status/tBody";
	}

	@PostMapping(value = "/admin/table-status/insert")
	public String insert(ModelMap modelMap, HttpSession httpSession, @RequestParam String name) {

		if (name.trim().length() <= 0 || name.trim().length() >255 ) {

			modelMap.addAttribute("results", "Tên không được để trống và tối đa 255 ký tự!");
			return "/admin/public/Danger";// đã tồn tại
		}
		if (tablestatusService.checkExist(name.trim())) {

			modelMap.addAttribute("results", "Trạng thái bàn đã tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		Tablestatus tablestatus = new Tablestatus();
		tablestatus.setName(name.trim());
		tablestatus.setUpdateat(new Date());
		tablestatus.setIsdelete(this.IS_NOT_DELETE);
		tablestatusService.addTablestatus(tablestatus);

		List<Tablestatus> listTablestatus = tablestatusService.findAll();
		modelMap.addAttribute("listTablestatus", listTablestatus);

		modelMap.addAttribute("result", "Thêm thành công!");
		return "/admin/public/Success"; // thành công
	}

	@PostMapping(value = "/admin/table-status/remove")
	public String remove(ModelMap modelMap, HttpSession httpSession, @RequestParam String tablestatusid) {

		Tablestatus tablestatus = tablestatusService.getInfoById(Integer.valueOf(tablestatusid.trim()));
		if (tablestatus == null) {
			modelMap.addAttribute("results", "Trạng thái bàn không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		tablestatus.setIsdelete(IS_DELETE);
		tablestatusService.editTablestatus(tablestatus);

		modelMap.addAttribute("results", "Xóa thành công!");
		return "/admin/public/Success";// đã tồn tại
	}

	@GetMapping(value = "/admin/table-status/edit")
	public String view(ModelMap modelMap, HttpSession httpSession, @RequestParam String tablestatusid) {

		Tablestatus tablestatus = tablestatusService.getInfoById(Integer.valueOf(tablestatusid.trim()));
		if (tablestatus == null) {
			modelMap.addAttribute("results", "Trạng thái bàn không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		modelMap.addAttribute("tablestatus", tablestatus);
		return "/admin/management-system/content/table-status/form";
	}

	@PostMapping(value = "/admin/table-status/edit")
	public String edit(ModelMap modelMap, HttpSession httpSession, @RequestParam String tablestatusid,
			@RequestParam String name) {
		
		if (name.trim().length() > 0 || name.trim().length() >255 ) {

			modelMap.addAttribute("resulst", "Tên không được để trống và tối đa 255 ký tự!");
			return "/admin/public/Danger";// đã tồn tại
		}

		Tablestatus tablestatus = tablestatusService.getInfoById(Integer.valueOf(tablestatusid.trim()));
		if (tablestatus == null) {
			modelMap.addAttribute("results", "Trạng thái bàn không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		tablestatus.setName(name);
		tablestatusService.editTablestatus(tablestatus);

		modelMap.addAttribute("result", "Cập nhật thành công!");
		return "/admin/public/Success";
	}
}
