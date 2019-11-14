package thecafeshop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import thecafeshop.DTO.PositionDTO;
import thecafeshop.entity.Position;
import thecafeshop.service.AtpositionService;
import thecafeshop.common.Common;
import thecafeshop.service.PositionService;


@Controller
@RequestMapping("")
public class PositionController extends Common {

	@Autowired
	PositionService positionService;
	@Autowired
	AtpositionService atpositionService;

	@GetMapping(value = "/admin/position")
	public String index(ModelMap modelMap, HttpSession httpSession) {

		int totalPage = positionService.findAll().size() / super.MAX_RESULTS;
		if ((positionService.findAll().size() % super.MAX_RESULTS) > 0) {
			totalPage++;
		}
		modelMap.addAttribute("totalPage", totalPage);
		return "/admin/management-system/position";
	}

	@GetMapping(value = "/admin/position/table")
	public String tbody(ModelMap modelMap, HttpSession httpSession, @Autowired String startPosition) {

		List<Position> positions = positionService.findLimit(Integer.valueOf(startPosition.trim()) - 1);
		List<PositionDTO> dtos = new ArrayList<PositionDTO>();
		for (Position position : positions) {
			PositionDTO positionDTO = new PositionDTO();
			positionDTO.setPosition(position);

			positionDTO.setCanDelete(false);
			if (atpositionService.checkExistPosition(position.getPositionid())) {
				positionDTO.setCanDelete(true);
			}
			dtos.add(positionDTO);
		}
		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-system/content/position/tBody";
	}

	@PostMapping(value = "/admin/position/insert")
	public String insert(ModelMap modelMap, HttpSession httpSession, @RequestParam String positionid,
			@RequestParam String name) {
		
		List<String> results = checkForm(positionid, name);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";// đã tồn tại
		}

		Position position = new Position();
		position.setPositionid(positionid.trim());
		position.setName(name.trim());
		position.setUpdateat(new Date());
		position.setIsdelete(super.IS_NOT_DELETE);
		positionService.addPosition(position);

		List<Position> positions = positionService.findAll();
		modelMap.addAttribute("positions", positions);

		modelMap.addAttribute("result", "Thêm thành công!");
		return "/admin/public/Success"; // thành công
	}

	@PostMapping(value = "/admin/position/remove")
	public String remove(ModelMap modelMap, HttpSession httpSession, @RequestParam String positionid) {

		Position position = positionService.getInfoById(positionid);
		if (position == null) {
			modelMap.addAttribute("results", "Chức vụ không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}
		position.setIsdelete(super.IS_DELETE);
//		position.setUpdateby(updateby);
		position.setUpdateat(new Date());
		positionService.editPosition(position);

		modelMap.addAttribute("result", "Xóa thành công!");
		return "/admin/public/Success";// đã tồn tại
	}

	@GetMapping(value = "/admin/position/edit")
	public String view(ModelMap modelMap, HttpSession httpSession, @RequestParam String positionid) {

		Position position = positionService.getInfoById(positionid);
		if (position == null) {
			modelMap.addAttribute("results", "Chức vụ không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		modelMap.addAttribute("position", position);
		return "/admin/management-system/content/position/form";
	}

	@PostMapping(value = "/admin/position/edit")
	public String edit(ModelMap modelMap, HttpSession httpSession, @RequestParam String positionid,
			@RequestParam String name) {

		List<String> results = checkForm(positionid, name);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";// đã tồn tại
		}
		
		Position position = positionService.getInfoById(positionid);
		if (position == null) {
			modelMap.addAttribute("results", "Chức vụ không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}
		if (positionService.getInfoByName(name.trim()) != null) {

			modelMap.addAttribute("results", "Lỗi trùng tên!");
			return "/admin/public/Danger";// đã tồn tại
		}

		position.setName(name.trim());
//		position.setUpdateby(updateby);
		position.setUpdateat(new Date());
		positionService.editPosition(position);

		modelMap.addAttribute("result", "Cập nhật thành công!");
		return "/admin/public/Success";
	}

	public List<String> checkForm(String positionid, String name) {
		List<String> results = new ArrayList<String>();
		if (positionid.length() <= 0 || positionid.length() > 255) {
			results.add("Mã không được để trống và tối đa 255 ký tự!");
		}

		if (name.length() <= 0 || name.length() > 255) {
			results.add("Tên không được để trống và tối đa 255 ký tự!");
		}

		if (positionService.getInfoById(positionid.trim()) != null) {

			results.add("Mã đã tồn tại!");
		}
		if (positionService.getInfoByName(name.trim()) != null) {

			results.add("Lỗi trùng tên!");
		}
		return results;
	}
}
