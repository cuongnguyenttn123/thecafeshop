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
import thecafeshop.DTO.SupplierDTO;
import thecafeshop.entity.Supplier;
import thecafeshop.common.Common;
import thecafeshop.service.SupplierService;


@Controller
@RequestMapping("")
public class SupplierController extends Common {

	@Autowired
	SupplierService supplierService;

	@GetMapping(value = "/admin/warehouse-supplier")
	public String index(ModelMap modelMap, HttpSession httpSession) {

		int totalPage = supplierService.findAll().size() / super.MAX_RESULTS;
		if ((supplierService.findAll().size() % super.MAX_RESULTS) > 0) {
			totalPage++;
		}
		modelMap.addAttribute("totalPage", totalPage);

		return "/admin/management-warehouse/supplier";
	}

	@GetMapping(value = "/admin/warehouse-supplier/table")
	public String tbody(ModelMap modelMap, HttpSession httpSession, @RequestParam String startPosition) {

		List<Supplier> suppliers = supplierService.findLimit(Integer.valueOf(startPosition.trim()) - 1);

		List<SupplierDTO> dtos = new ArrayList<SupplierDTO>();
		for (Supplier supplier : suppliers) {
			SupplierDTO supplierDTO = new SupplierDTO();

			supplierDTO.setSupplier(supplier);
			supplierDTO.setCanDelete(false);

			dtos.add(supplierDTO);
		}

		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-warehouse/content/supplier/tBody";
	}

	@PostMapping(value = "/admin/warehouse-supplier/insert")
	public String insert(ModelMap modelMap, HttpSession httpSession, @RequestParam String name,
			@RequestParam String phone, @RequestParam String address) {

		/* check */
		List<String> results = checkForm(name, phone, address);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";
		}
		/* check[END] */

		if (supplierService.checkExistByName(name.trim())) {

			modelMap.addAttribute("results", "Tên nhà cung cấp đã tồn tại!");
			return "/admin/public/Danger";
		}

		Supplier supplier = new Supplier();
		supplier.setName(name);
		supplier.setPhone(phone);
		supplier.setAddress(address);
		supplier.setUpdateat(new Date());
		supplier.setIsdelete(IS_NOT_DELETE);
		supplierService.addSupplier(supplier);

		modelMap.addAttribute("result", "Thêm thành công!");
		return "/admin/public/Success"; // thành công
	}

	@PostMapping(value = "/admin/warehouse-supplier/remove")
	public String remove(ModelMap modelMap, HttpSession httpSession, @RequestParam String supplierid) {

		Supplier supplier = supplierService.getInfoById(Integer.valueOf(supplierid));
		if (supplier == null) {
			modelMap.addAttribute("results", "Nhà cung cấp không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}
		supplier.setIsdelete(this.IS_DELETE);
		supplierService.editSupplier(supplier);

		modelMap.addAttribute("result", "Xóa thành công!");
		return "/admin/public/Success";// đã tồn tại
	}

	@GetMapping(value = "/admin/warehouse-supplier/edit")
	public String view(ModelMap modelMap, HttpSession httpSession, @RequestParam String supplierid) {

		Supplier supplier = supplierService.getInfoById(Integer.valueOf(supplierid));
		if (supplier == null) {
			modelMap.addAttribute("results", "Nhà cung cấp không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		modelMap.addAttribute("supplier", supplier);
		return "/admin/management-warehouse/content/supplier/form";
	}

	@PostMapping(value = "/admin/warehouse-supplier/edit")
	public String edit(ModelMap modelMap, HttpSession httpSession, @RequestParam String supplierid,
			@RequestParam String name, @RequestParam String phone, @RequestParam String address) {

		/* check */
		List<String> results = checkForm(name, phone, address);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";
		}
		/* check[END] */

		Supplier supplier = supplierService.getInfoById(Integer.valueOf(supplierid));
		if (supplier == null) {
			modelMap.addAttribute("results", "Nhà cung cấp không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		supplier.setName(name);
		supplier.setPhone(phone);
		supplier.setAddress(address);
		supplier.setUpdateat(new Date());
		supplierService.editSupplier(supplier);

		modelMap.addAttribute("result", "Cập nhật thành công!");
		return "/admin/public/Success";
	}

	public List<String> checkForm(String name, String phone, String address) {
		List<String> results = new ArrayList<String>();
		if (name.length() == 0 || name.length() > 255) {

			results.add("Tên không được để trống và tối đa 255 kí tự!");
		}
		if (!this.checkNumberPhone(phone)) {
			results.add("Số điện thoại không đúng!");
		}
		if (address.length() <= 0) {
			results.add("Địa chỉ không được để trống!");
		}
		return results;
	}

}
