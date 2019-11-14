package thecafeshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import thecafeshop.DTO.CategoryProductDTO;
import thecafeshop.entity.Categoryproduct;
import thecafeshop.service.CategoryProductService;
import thecafeshop.common.Common;
import thecafeshop.service.ProductService;


@Controller
@RequestMapping("")
public class CategoryProductController extends Common {

	@Autowired
	CategoryProductService categoryProductService;
	@Autowired
	ProductService productService;

	@GetMapping(value = "/admin/category-product")
	public String index(ModelMap modelMap, HttpSession httpSession) {

		int totalPage = categoryProductService.findAll().size() / super.MAX_RESULTS;
		if ((categoryProductService.findAll().size() % super.MAX_RESULTS) > 0) {
			totalPage++;
		}
		modelMap.addAttribute("totalPage", totalPage);

		return "/admin/management-system/category-product";
	}

	@GetMapping(value = "/admin/category-product/table")
	public String tbody(ModelMap modelMap, HttpSession httpSession, @RequestParam String startPosition) {

		List<Categoryproduct> categoryproducts = categoryProductService.findLimit(Integer.valueOf(startPosition) - 1);

		List<CategoryProductDTO> dtos = new ArrayList<CategoryProductDTO>();
		for (Categoryproduct categoryproduct : categoryproducts) {
			CategoryProductDTO categoryProductDTO = new CategoryProductDTO();
			categoryProductDTO.setCategoryproduct(categoryproduct);

			categoryProductDTO.setCanDelete(false);
			if (productService.checkExistCategoryProduct(categoryproduct.getCategoryproductid())) {
				categoryProductDTO.setCanDelete(true);
			}
			dtos.add(categoryProductDTO);
		}
		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-system/content/category-product/tBody";
	}

	@PostMapping(value = "/admin/category-product/insert")
	public String insert(ModelMap modelMap, HttpSession httpSession, @RequestParam String categoryproductid,
			@RequestParam String name) {

		/* check */
		List<String> results = checkForm(categoryproductid, name);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";// đã tồn tại
		}
		/* check[END] */

		if (categoryProductService.getInfoById(categoryproductid.trim()) != null) {

			modelMap.addAttribute("results", "Mã đã tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		Categoryproduct categoryproduct = new Categoryproduct();
		categoryproduct.setCategoryproductid(categoryproductid);
		categoryproduct.setName(name);
		categoryproduct.setIsdelete(super.IS_NOT_DELETE);
		categoryProductService.addCategoryProduct(categoryproduct);

		List<Categoryproduct> categoryproducts = categoryProductService.findAll();
		modelMap.addAttribute("categoryproducts", categoryproducts);

		modelMap.addAttribute("result", "Thêm thành công!");
		return "/admin/public/Success"; // thành công
	}

	@PostMapping(value = "/admin/category-product/remove")
	public String remove(ModelMap modelMap, HttpSession httpSession, @RequestParam String categoryproductid) {

		Categoryproduct categoryproduct = categoryProductService.getInfoById(categoryproductid.trim());
		if (categoryproduct == null) {
			modelMap.addAttribute("results", "Loại sản phẩm không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}
		categoryproduct.setIsdelete(this.IS_DELETE);
		categoryProductService.editCategoryproduct(categoryproduct);

		modelMap.addAttribute("result", "Xóa thành công!");
		return "/admin/public/Success";// đã tồn tại
	}

	@GetMapping(value = "/admin/category-product/edit")
	public String view(ModelMap modelMap, HttpSession httpSession, @RequestParam String categoryproductid) {

		Categoryproduct categoryproduct = categoryProductService.getInfoById(categoryproductid.trim());
		if (categoryproduct == null) {
			modelMap.addAttribute("results", "Loại sản phẩm không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		modelMap.addAttribute("categoryproduct", categoryproduct);
		return "/admin/management-system/content/category-product/form";
	}

	@PostMapping(value = "/admin/category-product/edit")
	public String edit(ModelMap modelMap, HttpSession httpSession, @RequestParam String categoryproductid,
			@RequestParam String name) {

		/* check */
		List<String> results = checkForm(categoryproductid, name);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";// đã tồn tại
		}
		/* check[END] */
		
		Categoryproduct categoryproduct = categoryProductService.getInfoById(categoryproductid.trim());
		if (categoryproduct == null) {
			modelMap.addAttribute("results", "Trạng thái hóa đơn không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		categoryproduct.setName(name);
		categoryProductService.editCategoryproduct(categoryproduct);

		modelMap.addAttribute("result", "Cập nhật thành công!");
		return "/admin/public/Success";
	}

	public List<String> checkForm(String categoryproductid, String name) {
		List<String> results = new ArrayList<String>();
		if (categoryproductid.length() == 0 || categoryproductid.length() > 8) {

			results.add("Mã phải không được để trống và tối đa 7 kí tự!");
		}
		if (name.length() == 0) {

			results.add("Tên không được để trống!");
		}
		return results;
	}
}
