package thecafeshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import thecafeshop.DTO.ProductDTO;
import thecafeshop.entity.Categoryproduct;
import thecafeshop.entity.Image;
import thecafeshop.entity.Price;
import thecafeshop.entity.Product;
import thecafeshop.reponsitory.CategoryproductRepository;
import thecafeshop.reponsitory.PriceRepository;
import thecafeshop.reponsitory.ProductRepository;
import thecafeshop.service.Common;

@Controller
@RequestMapping("")
public class IndexUserController extends Common {

	@Autowired
	private CategoryproductRepository categoryProductService;
	@Autowired
	private ProductRepository productService;
	@Autowired
	private PriceRepository priceService;

	@GetMapping(value = "/")
	public String index(ModelMap modelMap, HttpSession httpSession) {

		pagination(modelMap, httpSession, 0, null, null, null, null, null);

		return "/user/index";

	}

	@PostMapping(value = "/index/search")
	public String search(ModelMap modelMap, HttpSession httpSession, @RequestParam String page,
			@RequestParam String cgPrdId, @RequestParam String strSearch, @RequestParam String isHotDeal,
			@RequestParam String priceAZ, @RequestParam String priceZA) {

		int startPosition = Integer.valueOf(page.trim());
		// pagination(modelMap, httpSession, startPosition, cgPrdId, strSearch,
		// isHotDeal, priceAZ, priceZA);
		pagination(modelMap, httpSession, startPosition, cgPrdId, strSearch.trim(), null, null, null);
		return "user/content/content-index";

	}

	/* get info of product */
	@PostMapping(value = "/infoProduct")
	public String infoProduct(@RequestParam String productid, ModelMap modelMap) {

		Product product = productService.findById(productid).get();

		if (product == null) {
			return "Sản phẩm không tồn tại";
		}

		modelMap.addAttribute("product", product);
		modelMap.addAttribute("old_prPrice", product);
		Price new_Price = priceService.findById(3).get();
		if (new_Price != null) {
			modelMap.addAttribute("new_Price", new_Price);
		}
		Set<Image> images = product.getImages();
		modelMap.addAttribute("images", images);

		return "/user/content/infoProduct";
	}

	public void pagination(ModelMap modelMap, HttpSession httpSession, int startPosition, String cgPrdId,
			String strSearch, String isHotDeal, String priceAZ, String priceZA) {

		modelMap.addAttribute("startPosition", startPosition + 1);
		modelMap.addAttribute("cgPrdId", cgPrdId);
		modelMap.addAttribute("strSearch", strSearch);
		modelMap.addAttribute("isHotDeal", isHotDeal);
		modelMap.addAttribute("priceAZ", priceAZ);
		modelMap.addAttribute("priceZA", priceZA);

		/* display Categoryproduct on combobox */
		List<Categoryproduct> categoryProducts = categoryProductService.findAll();
		modelMap.addAttribute("categoryProducts", categoryProducts);

		/* display list product on page */
		if (cgPrdId != null && cgPrdId.equals("-1")) {
			cgPrdId = null;
		}
		if(strSearch == "") {
			strSearch = null;
		}

		List<Product> products = productService.findAll();

		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		/* display price product and sale price and new product release */
		for (Product product : products) {
			ProductDTO dto = new ProductDTO();

			/* image */
			Set<Image> setImages = product.getImages();
			List<Image> images = new ArrayList<Image>();
			int size = setImages.size() - 1;
			for (Image image : setImages) {
				if (size <= 3) {
					images.add(image);
				}
				size--;
			}
			dto.setImages(images);

			/* find old price */
			dto.setProductid(product.getProductid());
			dto.setCategoryproductNAME(product.getCategoryproduct().getName());

			float oldPrice = priceService.findById(3).get().getPrice();
			if (oldPrice > 0) {
				dto.setPrice((int) oldPrice);
			}

			/* find new price */
			Price newPrice = priceService.findById(3).get();
			if (newPrice != null) {

				/* new price */
				dto.setNewPrice(newPrice);
				/* rate price between new price to old price */
				float newPriceValue = newPrice.getPrice();

				int rate = this.rateOldAndNewPrice(oldPrice, newPriceValue);
				dto.setRateOldAndNewPrice(rate);
			}

			/* check is new product */
			dto.setCheckIsNew(false);
			if (productService.existsById(product.getProductid())) {
				dto.setCheckIsNew(true);
			}

			productDTOs.add(dto);
		}

		modelMap.addAttribute("productDTOs", productDTOs);
	}
}
