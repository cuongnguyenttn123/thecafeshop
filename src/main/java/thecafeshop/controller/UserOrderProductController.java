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

import thecafeshop.DTO.ProductDTO;
import thecafeshop.common.Common;
import thecafeshop.entity.*;
import thecafeshop.service.*;


@Controller
@RequestMapping("")
public class UserOrderProductController extends Common {

	@Autowired
	CategoryProductService categoryProductService;
	@Autowired
	ProductService productService;
	@Autowired
	PriceService priceService;
	@Autowired
	VoucherService voucherService;
	@Autowired
	CustomerService customerService;
	@Autowired
	BillService billService;
	@Autowired
	BilldetailService billdetailService;

	@GetMapping(value = "/order-product", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String index(ModelMap modelMap, HttpSession httpSession) {

		return "/user/orderProduct";
	}

	@PostMapping(value = "/order-product", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String index(ModelMap modelMap, HttpSession httpSession, @RequestParam String listCart,
			@RequestParam String listNumberProduct) {

		modelMap.addAttribute("listCart", listCart);
		modelMap.addAttribute("listNumberProduct", listNumberProduct);

		/* display Categoryproduct on combobox */
		List<Categoryproduct> categoryProducts = categoryProductService.findAll();
		modelMap.addAttribute("categoryProducts", categoryProducts);

		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		List<String> listProductId = this.listProductCart(listCart);
		List<Integer> listNumber = this.listNumberCart(listNumberProduct);

		int i = 0;
		for (String productid : listProductId) {

			Product product = productService.getInfoById(productid);
			if (product != null) {
				try {
					ProductDTO dto = new ProductDTO();
					dto.setProductid(product.getProductid());
					dto.setName(product.getName());
					dto.setCategoryproductNAME(product.getCategoryproduct().getName());
					dto.setUpdateat(product.getUpdateat());
					List<Image> images = new ArrayList<Image>();
					if (product.getImages().size() > 0) {
						Set<Image> setImages = product.getImages();
						for (Image image : setImages) {
							images.add(image);
						}
						dto.setImages(images);
					}
					dto.setNumber(listNumber.get(i));
					dto.setPrice(priceService.getOldPrice(product.getProductid()));
					productDTOs.add(dto);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			i++;
		}
		modelMap.addAttribute("productDTOs", productDTOs);

		return "/user/orderProduct";
	}

//	@PostMapping(value = "")
//	public String checkVoucher(@RequestParam String voucherName) {
//
//		JsonObject result = new JsonObject();
//
//		Voucher voucher = voucherService.findByName(voucherName);
//		if (voucher == null) {
//			result.addProperty("mes", "Voucher không tồn tại!");
//			return result.toString();
//		}
//		Date now = new Date();
//		// voucher hết hạn hoặc chưa áp dụng HOẶC đã sử dụng hết
//		if ((now.before(voucher.getStartdatetime()) && now.after(voucher.getEnddate())) || voucher.getCount() <= 0) {
//			result.addProperty("mes", "Voucher hết hạn hoặc đã sử dụng hết!");
//			return result.toString();
//		}
//		
//		result.addProperty("discount", voucher.getDiscount());
//
//		return result.toString();
//	}

	@PostMapping(value = "/pay-cart", produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public String orderProduct(ModelMap modelMap, HttpSession httpSession, @RequestParam String name,
			@RequestParam String address, @RequestParam String phone, @RequestParam String startdatetime,
			@RequestParam String notice, @RequestParam String voucherName, @RequestParam String listCart,
			@RequestParam String listNumberProduct2) {

		Customer customer = new Customer();
		customer.setName(name);
		customer.setAddress(address);
		customer.setPhone(Integer.valueOf(phone));
		customer.setIsdelete(super.IS_NOT_DELETE);

		int lastCustomerID = customerService.addCustomer(customer);
		if (lastCustomerID != -1) { /* customer has inserted */

			Bill bill = new Bill();
			Date DatetimeStart = null;
			try {
				DatetimeStart = super.sdf.parse(startdatetime);
			} catch (Exception e) {
			}
			bill.setStartdatetime(DatetimeStart);
			bill.setNotice(notice);
			bill.setBillstatus(new Billstatus("CD"));
			if (voucherName != null && voucherService.checkVoucher(voucherName.trim())) {
				Voucher voucher = voucherService.findByName(voucherName);
				bill.setVoucher(voucher);
			}

			int billid = billService.addBill(bill);
			if (billid != -1) { /* bill has inserted */

				List<String> listProductId = this.listProductCart(listCart);
				List<Integer> listNumber = this.listNumberCart(listNumberProduct2);

				int i = 0;
				for (String productid : listProductId) {

					if (productService.getInfoById(productid) != null) {// product is exist
						Billdetail billdetail = new Billdetail();

						BilldetailId billdetailId = new BilldetailId(productid, billid);
						billdetail.setId(billdetailId);
						billdetail.setBill(bill);
						billdetail.setProduct(new Product(productid));
						billdetail.setQuantity(listNumber.get(i));
						billdetail.setIsdelete(super.IS_NOT_DELETE);
						billdetail.setUpdateat(new Date());

						billdetailService.addBilldetail(billdetail);
					}
					i++;
				}

			}

		}

		return "/user/orderProduct";
	}
}
