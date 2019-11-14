package thecafeshop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import thecafeshop.DTO.ProductDTO;
import thecafeshop.entity.Categoryproduct;
import thecafeshop.entity.Price;
import thecafeshop.entity.Product;
import thecafeshop.service.CategoryProductService;
import thecafeshop.common.Common;
import thecafeshop.service.PriceService;
import thecafeshop.service.ProductService;


@Controller
@RequestMapping("")
public class ProductController extends Common {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryProductService categoryProductService;
	@Autowired
	PriceService priceService;

	@GetMapping(value = "/admin/product")
	public String index(ModelMap modelMap, HttpSession httpSession) {

		List<Categoryproduct> categoryproducts = categoryProductService.findAll();
		modelMap.addAttribute("categoryproducts", categoryproducts);

		int totalPage = productService.findAll().size() / super.MAX_RESULTS;
		if ((productService.findAll().size() % super.MAX_RESULTS) > 0) {
			totalPage++;
		}
		modelMap.addAttribute("totalPage", totalPage);

		return "/admin/management-system/product";
	}

	@GetMapping(value = "/admin/product/table")
	public String tbody(ModelMap modelMap, HttpSession httpSession, @RequestParam String startPosition) {

		List<Product> products = productService.findLimit(Integer.valueOf(startPosition.trim()) - 1);

		List<ProductDTO> dtos = new ArrayList<ProductDTO>();
		for (Product product : products) {

			ProductDTO dto = new ProductDTO();
			dto.setCanDelete(true);
			if (product.getBilldetails().size() > 0) {
				dto.setCanDelete(false);
			}
			if (product.getExportbills().size() > 0) {
				dto.setCanDelete(false);
			}
			dto.setProductid(product.getProductid());
			dto.setName(product.getName());
			dto.setCategoryproductNAME(product.getCategoryproduct().getName());
			dto.setUpdateat(product.getUpdateat());

			dtos.add(dto);
		}

		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-system/content/product/tBody";
	}

	@PostMapping(value = "/admin/product/insert")
	public String insert(ModelMap modelMap, HttpSession httpSession, @RequestParam String productid,
			@RequestParam String name, @RequestParam String description, @RequestParam String categoryproductid,
			@RequestParam String price) {

		if (productService.getInfoById(productid.trim()) != null) {

			modelMap.addAttribute("results", "Mã sản phẩm bị trùng!");
			return "/admin/public/Danger";// đã tồn tại
		}
		if (productService.checkExistNameProduct(name.trim())) {

			modelMap.addAttribute("results", "Tên sản phẩm bị trùng!");
			return "/admin/public/Danger";// đã tồn tại
		}
		if (categoryproductid == "-1") {

			modelMap.addAttribute("results", "Chưa chọn loại sản phẩm!");
			return "/admin/public/Danger";// đã tồn tại
		}
		if (Integer.valueOf(price.trim()) <= 0) {
			modelMap.addAttribute("results", "Đơn giá không được để trống và > 0!");
			return "/admin/public/Danger";// đã tồn tại
		}

		Product product = new Product();
		product.setProductid(productid.trim());
		product.setName(name);
		product.setDescription(description);
		Categoryproduct categoryproduct = categoryProductService.getInfoById(categoryproductid);
		product.setCategoryproduct(categoryproduct);
		product.setUpdateat(new Date());
		product.setIsdelete(IS_NOT_DELETE);
		productService.addProduct(product);

		Price price2 = new Price();
		price2.setPrice(Integer.valueOf(price));
		price2.setStartdatetime(new Date());
		price2.setProduct(product);
		price2.setUpdateat(new Date());
		price2.setIsdelete(IS_NOT_DELETE);
		priceService.addPrice(price2);

		List<Categoryproduct> categoryproducts = categoryProductService.findAll();
		modelMap.addAttribute("categoryproducts", categoryproducts);

		modelMap.addAttribute("result", "Thêm thành công!");
		return "/admin/public/Success"; // thành công
	}

	@PostMapping(value = "/admin/product/remove")
	public String remove(ModelMap modelMap, HttpSession httpSession, @RequestParam String productid) {

		Product product = productService.getInfoById(productid.trim());
		if (product == null) {
			modelMap.addAttribute("results", "Sản phẩm không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}
		product.setIsdelete(this.IS_DELETE);
		product.setUpdateat(new Date());

		if (productService.editProduct(product)) {
			Set<Price> prices = product.getPrices();
			for (Price price : prices) {
				price.setIsdelete(IS_DELETE);
				priceService.editPrice(price);
			}
		}

		modelMap.addAttribute("result", "Xóa thành công!");
		return "/admin/public/Success";// đã tồn tại
	}

	@GetMapping(value = "/admin/product/edit")
	public String view(ModelMap modelMap, HttpSession httpSession, @RequestParam String productid) {

		List<Categoryproduct> categoryproducts = categoryProductService.findAll();
		modelMap.addAttribute("categoryproducts", categoryproducts);

		Product product = productService.getInfoById(productid.trim());
		if (product == null) {
			modelMap.addAttribute("results", "Sản phẩm không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		modelMap.addAttribute("product", product);
		
		int price = priceService.getOldPrice(productid.trim());
		modelMap.addAttribute("price", price);

		return "/admin/management-system/content/product/form";
	}

	@PostMapping(value = "/admin/product/edit")
	public String edit(ModelMap modelMap, HttpSession httpSession, @RequestParam String productid,
			@RequestParam String name, @RequestParam String description, @RequestParam String categoryproductid,
			@RequestParam String price) {

		Product product = productService.getInfoById(productid.trim());
		if (product == null) {
			modelMap.addAttribute("results", "Sản phẩm không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}
		if (productService.checkExistNameProduct(name.trim()) && !product.getName().equals(name.trim())) {

			modelMap.addAttribute("results", "Tên sản phẩm bị trùng!");
			return "/admin/public/Danger";// đã tồn tại
		}
		if (Integer.valueOf(price.trim()) <= 0) {
			modelMap.addAttribute("results", "Đơn giá không được để trống và > 0!");
			return "/admin/public/Danger";// đã tồn tại
		}

		product.setName(name);
		product.setDescription(description.trim());
		Categoryproduct categoryproduct = categoryProductService.getInfoById(categoryproductid);
		product.setCategoryproduct(categoryproduct);
//		product.setUpdateby(updateby);
		product.setUpdateat(new Date());
		if (productService.editProduct(product)) {
			if (priceService.getOldPrice(productid.trim()) != Integer.valueOf(price.trim())) {

				Set<Price> prices = product.getPrices();
				for (Price price2 : prices) {
					if (price2.getIsdelete() == IS_NOT_DELETE) {
						price2.setIsdelete(IS_DELETE);
						price2.setEnddatetime(new Date());
						priceService.editPrice(price2);
					}
				}

				Price price3 = new Price();
				price3.setPrice(Integer.valueOf(price.trim()));
				price3.setProduct(product);
				price3.setStartdatetime(new Date());
				price3.setUpdateat(new Date());
				price3.setIsdelete(IS_NOT_DELETE);
				priceService.addPrice(price3);
			}
		}

		modelMap.addAttribute("result", "Cập nhật thành công!");
		return "/admin/public/Success";
	}
}
