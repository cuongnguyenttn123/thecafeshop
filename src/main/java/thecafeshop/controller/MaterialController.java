package thecafeshop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import thecafeshop.DTO.MaterialDTO;
import thecafeshop.entity.Material;
import thecafeshop.common.Common;
import thecafeshop.service.MaterialService;
import thecafeshop.service.MaterialdetailService;


@Controller
@RequestMapping("")
public class MaterialController extends Common {

	@Autowired
	MaterialService materialService;
	@Autowired
	MaterialdetailService materialdetailService;

	@GetMapping(value = "/admin/material")
	public String index(ModelMap modelMap, HttpSession httpSession) {

		int totalPage = materialService.findAll().size() / super.MAX_RESULTS;
		if ((materialService.findAll().size() % super.MAX_RESULTS) > 0) {
			totalPage++;
		}
		modelMap.addAttribute("totalPage", totalPage);

		return "/admin/management-system/material";
	}

	@GetMapping(value = "/admin/material/table")
	public String tbody(ModelMap modelMap, HttpSession httpSession, @RequestParam String startPosition) {

		List<Material> materials = materialService.findLimit(Integer.valueOf(startPosition.trim()) - 1);

		List<MaterialDTO> dtos = new ArrayList<MaterialDTO>();
		for (Material material : materials) {
			MaterialDTO materialDTO = new MaterialDTO();
			materialDTO.setMaterial(material);

			materialDTO.setCanDelete(false);
			if (materialdetailService.checkExistMaterial(material.getMaterialid())) {
				materialDTO.setCanDelete(true);
			}
			dtos.add(materialDTO);
		}

		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-system/content/material/tBody";
	}

	@PostMapping(value = "/admin/material/insert")
	public String insert(ModelMap modelMap, HttpSession httpSession, @RequestParam String name,
			@RequestParam String unit) {

		/* check */
		List<String> results = checkForm(name, unit);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";// đã tồn tại
		}
		/* check[END] */

		if (materialService.checkExistNameMaterial(name.trim()) == true) {

			modelMap.addAttribute("results", "Mã đã tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		Material material = new Material();
		material.setName(name.trim());
		material.setUnit(unit);
		material.setUpdateat(new Date());
		material.setIsdelete(IS_NOT_DELETE);
		materialService.addMaterial(material);

		List<Material> materials=materialService.findAll();
		modelMap.addAttribute("materials", materials);

		modelMap.addAttribute("result", "Thêm thành công!");
		return "/admin/public/Success"; // thành công
	}

	@PostMapping(value = "/admin/material/remove")
	public String remove(ModelMap modelMap, HttpSession httpSession, @RequestParam String materialid) {

		Material material= materialService.getInfoById((Integer.valueOf(materialid.trim())));
		if (material == null) {
			modelMap.addAttribute("results", "Nguyên liệu không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}
		material.setIsdelete(this.IS_DELETE);
		material.setUpdateat(new Date());
		materialService.editMaterial(material);

		modelMap.addAttribute("result", "Xóa thành công!");
		return "/admin/public/Success";// đã tồn tại
	}

	@GetMapping(value = "/admin/material/edit")
	public String view(ModelMap modelMap, HttpSession httpSession, @RequestParam String materialid) {

		Material material= materialService.getInfoById((Integer.valueOf(materialid.trim())));
		if (material == null) {
			modelMap.addAttribute("results", "Nguyên liệu không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		modelMap.addAttribute("material", material);
		return "/admin/management-system/content/material/form";
	}

	@PostMapping(value = "/admin/material/edit")
	public String edit(ModelMap modelMap, HttpSession httpSession, @RequestParam String materialid,
			@RequestParam String name, @RequestParam String unit) {

		/* check */
		List<String> results = checkForm(name, name);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";// đã tồn tại
		}
		/* check[END] */

		Material material= materialService.getInfoById((Integer.valueOf(materialid.trim())));
		if (material == null) {
			modelMap.addAttribute("results", "Nguyên liệu không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		material.setName(name);
//		material.setUnit(unit);
		material.setUpdateat(new Date());
		materialService.editMaterial(material);

		modelMap.addAttribute("result", "Cập nhật thành công!");
		return "/admin/public/Success";
	}

	public List<String> checkForm(String name, String unit) {
		List<String> results = new ArrayList<String>();
		if (name.length() == 0 || name.length() > 255) {

			results.add("Tên nguyên liệu không được để trống và tối đa 255 kí tự!");
		}
		if (unit.length() == 0) {

			results.add("Đơn vị không được để trống!");
		}
		return results;
	}
}
