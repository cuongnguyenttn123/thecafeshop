package thecafeshop.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import thecafeshop.DTO.MaterialDetailDTO;
import thecafeshop.entity.Materialdetail;
import thecafeshop.common.Common;
import thecafeshop.service.MaterialService;
import thecafeshop.service.MaterialdetailService;


@Controller
@RequestMapping("")
public class ManagementMaterialController extends Common {

	@Autowired
	MaterialdetailService materialdetailService;
	@Autowired
	MaterialService materialService;

	@GetMapping(value = "/admin/warehouse-material")
	public String index(ModelMap modelMap) {

		List<Materialdetail> list = materialdetailService.findAll();

		List<MaterialDetailDTO> dtos = new ArrayList<MaterialDetailDTO>();
		for (Materialdetail materialdetail : list) {
			MaterialDetailDTO dto = new MaterialDetailDTO();
			dto.setMaterialdetailid(materialdetail.getMaterialdetailid());
			dto.setName(materialdetail.getMaterial().getName());
			dto.setQuantity(materialdetail.getQuantity());
			dto.setDateofmanufacture(materialdetail.getDateofmanufacture());
			dto.setExpirationdate(materialdetail.getExpirationdate());
			dto.setPrice(materialdetail.getPrice());
			dto.setUpdateat(materialdetail.getUpdateat());

			dtos.add(dto);
		}

		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-warehouse/material";
	}

	@PostMapping(value = "/admin/warehouse-material")
	public String search(ModelMap modelMap, @RequestParam String materialdetailid, @RequestParam String name) {
		
		List<Materialdetail> list = materialdetailService.search(materialdetailid.trim(), name.trim());

		List<MaterialDetailDTO> dtos = new ArrayList<MaterialDetailDTO>();
		for (Materialdetail materialdetail : list) {
			MaterialDetailDTO dto = new MaterialDetailDTO();
			dto.setMaterialdetailid(materialdetail.getMaterialdetailid());
			dto.setName(materialdetail.getMaterial().getName());
			dto.setQuantity(materialdetail.getQuantity());
			dto.setDateofmanufacture(materialdetail.getDateofmanufacture());
			dto.setExpirationdate(materialdetail.getExpirationdate());
			dto.setPrice(materialdetail.getPrice());
			dto.setUpdateat(materialdetail.getUpdateat());

			dtos.add(dto);
		}

		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-warehouse/content/material/content";
	}

//
//	@PostMapping(value = "/admin/warehouse-material/update")
//	public @ResponseBody String updateQuantity(ModelMap modelMap, @RequestParam String productid,
//			@RequestParam String quantity) {
//
//		if (quantity.trim().length() <= 0 || Integer.valueOf(quantity.trim()) <= 0) {
//			return "{\"id\":\"0\",\"mes\":\"Số lượng để trống và nhỏ hơn không!\"}";
//		}
//
//		Product product = productService.getInfoById(productid.trim());
//		if (product == null) {
//			return "{\"id\":\"0\",\"mes\":\"Sản phẩm không tồn tại!\"}";
//		}
//		if (Integer.valueOf(quantity.trim()) > exportbillService.totalQuantityProduct(productid.trim())) {
//			return "{\"id\":\"0\",\"mes\":\"Số lượng bỏ phải <= số lượng tồn!\"}";
//		}
//
//		int intQuality = Integer.valueOf(quantity.trim());
//		List<Exportbill> exportbills = exportbillService.getListExportBillbyProduct(productid);
//		for (Exportbill exportbill : exportbills) {
//
//			// số nhập nhỏ hơn số lượng đang có
//			if ((exportbill.getQuantityInventory() - intQuality) >= 0) {
//				exportbill.setQuantityInventory(exportbill.getQuantityInventory() - intQuality);
//				exportbill.setQuantityThrow(exportbill.getQuantityThrow() + intQuality);
//				exportbillService.editExportbill(exportbill);
//			}
//			// số nhập lớn hơn số lượng đang có
//			if ((exportbill.getQuantityInventory() - intQuality) < 0) {
//				intQuality = intQuality - exportbill.getQuantityInventory();
//				exportbill.setQuantityInventory(0);
//				exportbill.setQuantityThrow(exportbill.getQuantityThrow() + exportbill.getQuantityInventory());
//				exportbillService.editExportbill(exportbill);
//			}
//		}
//
//		int totalQuantity = exportbillService.totalQuantityProduct(product.getProductid());
//		return "{\"id\":\"1\",\"mes\":\"Cập nhật thành công!\",\"quantity\":\"" + totalQuantity + "\"}";
//	}
}
