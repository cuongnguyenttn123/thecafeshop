package thecafeshop.controller;

import java.io.IOException;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import thecafeshop.DTO.ExportBillDTO;
import thecafeshop.common.Common;
import thecafeshop.entity.*;
import thecafeshop.service.*;


@Controller
@RequestMapping("")
public class ManagementExportController extends Common {

	@Autowired
	ExportbillService exportbillService;
	@Autowired
	ExportbilldetailService exportbilldetailService;
	@Autowired
	SupplierService supplierService;
	@Autowired
	MaterialService materialService;
	@Autowired
	MaterialdetailService materialdetailService;
	@Autowired
	ProductService productService;

	@GetMapping(value = "/admin/warehouse-export-material")
	public String index(ModelMap modelMap) {

		int totalPage = exportbillService.findAll().size() / super.MAX_RESULTS;
		if ((exportbillService.findAll().size() % super.MAX_RESULTS) > 0) {
			totalPage++;
		}
		modelMap.addAttribute("totalPage", totalPage);

		List<Product> products = productService.findAll();
		modelMap.addAttribute("products", products);

		List<Material> materials = materialService.findAll();
		modelMap.addAttribute("materials", materials);

		return "/admin/management-warehouse/export-material";
	}

	@GetMapping(value = "/admin/warehouse-export-material/table")
	public String tbody(ModelMap modelMap, HttpSession httpSession, @RequestParam String startPosition) {

		List<Exportbill> exportbills = exportbillService.findLimit(Integer.valueOf(startPosition.trim()) - 1);
		List<ExportBillDTO> dtos = new ArrayList<ExportBillDTO>();
		for (Exportbill exportbill : exportbills) {
			ExportBillDTO exportBillDTO = new ExportBillDTO();
			exportBillDTO.setExportbill(exportbill);
			exportBillDTO.setEmployee(exportbill.getEmployee());
			exportBillDTO.setCountBillDetail(
					exportbilldetailService.getNumberExportbilldetail(exportbill.getExportbillid()));
			exportBillDTO.setCanDelete(false);

			dtos.add(exportBillDTO);
		}
		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-warehouse/content/export-material/tBody";
	}

	@PostMapping(value = "/admin/warehouse-export-material/insert")
	public String insert(ModelMap modelMap, HttpSession httpSession, @RequestParam String productid,
			@RequestParam String quantityProduct, @RequestParam String listMaterialDetail) {

		/* check */
		if (productid.trim().length() == 0 || productid.trim().equals("-1")) {

			modelMap.addAttribute("results", "Mã sản phẩm không được để trống!");
			return "/admin/public/Danger";
		}
		if (quantityProduct.trim().length() <= 0) {

			modelMap.addAttribute("results", "Số lượng sản phẩm không được để trống và >0!");
			return "/admin/public/Danger";
		}
		/* check[END] */
// thêm hóa đơn xuất
		Exportbill exportbill = new Exportbill();
		exportbill.setProduct(new Product(productid));
//		exportbill.setEmployee(employee);
		exportbill.setQuantity(Integer.valueOf(quantityProduct.trim()));
		exportbill.setUpdateat(new Date());
		exportbill.setIsdelete(IS_NOT_DELETE);
		int lastId = exportbillService.addExportbill(exportbill);

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonObject = objectMapper.readTree(listMaterialDetail);
			JsonNode jsonArray = jsonObject.get("listMaterialDetail");

			int materialid, quantity, price;
			String dateofmanufacture, expirationdate;
			for (JsonNode materialDetail : jsonArray) {

				materialid = materialDetail.get("materialid").asInt();
				quantity = materialDetail.get("quantity").asInt();

				/* check */
				List<String> results = checkForm(materialid, quantity);
				if (results.size() > 0) {
					modelMap.addAttribute("results", results);
					return "/admin/public/Danger";
				}
				/* check[END] */
			}
			for (JsonNode materialDetail : jsonArray) {

				materialid = materialDetail.get("materialid").asInt();
				quantity = materialDetail.get("quantity").asInt();

				// thêm hóa đơn xuất chi tiết
				List<Materialdetail> materialdetails = materialdetailService.layNguyenLieuTonKho(materialid);
				int total = materialdetailService.laySoNguyenLieuTonKho(materialid);
				if (total < quantity) {
					modelMap.addAttribute("results",
							"Nguyên liệu " + materialdetails.get(0).getMaterial().getName() + " trong kho không đủ!");
					return "/admin/public/Danger";
				}
				for (Materialdetail materialdetail : materialdetails) {
					if (quantity <= 0) {
						break;
					}
					// cập nhật số lượng trong kho
					if (materialdetail.getQuantity() > quantity) {
						materialdetail.setQuantity(materialdetail.getQuantity() - quantity);
					} else {
						materialdetail.setQuantity(0);
						quantity = quantity - materialdetail.getQuantity();
					}
					materialdetail.setUpdateat(new Date());
					materialdetailService.editMaterialdetail(materialdetail);

					// thêm phiếu xuất chi tiết
					Exportbilldetail exportbilldetail = new Exportbilldetail();
					exportbilldetail.setId(new ExportbilldetailId(lastId, materialdetail.getMaterialdetailid()));
					// nếu nhân viên
					exportbilldetail.setQuantity(quantity);
					exportbilldetail.setUpdateat(new Date());
					exportbilldetail.setIsdelete(IS_NOT_DELETE);
					exportbilldetailService.addExportbilldetail(exportbilldetail);

					// trừ nguyên liệu trong kho

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelMap.addAttribute("result", "Thêm thành công!");
		return "/admin/public/Success"; // thành công
	}

	@PostMapping(value = "/admin/warehouse-export-material/remove")
	public String remove(ModelMap modelMap, HttpSession httpSession, @RequestParam String exportbillid) {

		int id = Integer.valueOf(exportbillid.trim());
		Exportbill exportbill = exportbillService.getInfoById(id);
		if (exportbill == null) {
			modelMap.addAttribute("results", "Hóa đơn này không tồn tại!");
			return "/admin/public/Danger";
		}
		if (!exportbill.getUpdateat().equals(exportbill.getProduct().getUpdateat())) {
			modelMap.addAttribute("results", "Sản phẩm chế biến đã đưa vào sử dụng! Không thể xóa!");
			return "/admin/public/Danger";
		}
		// Xóa phiếu xuất chi tiết
		Set<Exportbilldetail> exportbilldetails = exportbill.getExportbilldetails();
		for (Exportbilldetail exportbilldetail : exportbilldetails) {
			exportbilldetail.setIsdelete(IS_DELETE);
			exportbilldetail.setUpdateat(new Date());
			exportbilldetailService.editExportbilldetail(exportbilldetail);
		}

		// Xóa phiếu xuất
		exportbill.setIsdelete(this.IS_DELETE);
		exportbill.setUpdateat(new Date());
//		exportbill.getEmployee()
		exportbillService.editExportbill(exportbill);

		modelMap.addAttribute("result", "Xóa thành công!");
		return "/admin/public/Success";// đã tồn tại
	}

	@GetMapping(value = "/admin/warehouse-export-material/edit")
	public String view(ModelMap modelMap, HttpSession httpSession, @RequestParam String exportbillid) {

		List<Product> products = productService.findAll();
		modelMap.addAttribute("products", products);

		List<Material> materials = materialService.findAll();
		modelMap.addAttribute("materials", materials);

		Exportbill exportbill = exportbillService.getInfoById(Integer.valueOf(exportbillid.trim()));
		if (exportbill == null) {
			modelMap.addAttribute("results", "Hóa đơn này không tồn tại!");
			return "/admin/public/Danger";
		}
		modelMap.addAttribute("exportbill", exportbill);

		Set<Exportbilldetail> exportbilldetails = exportbill.getExportbilldetails();
		modelMap.addAttribute("exportbilldetails", exportbilldetails);

		return "/admin/management-warehouse/content/export-material/form";
	}

	@PostMapping(value = "/admin/warehouse-export-material/edit")
	public String edit(ModelMap modelMap, HttpSession httpSession, @RequestParam String exportbillid,
			@RequestParam String quantityProduct, @RequestParam String listMaterialDetail) {

		ObjectMapper objectMapper = new ObjectMapper();

		Exportbill exportbill = exportbillService.getInfoById(Integer.valueOf(exportbillid));
		if (exportbill == null) {
			modelMap.addAttribute("results", "Hóa đơn này không tồn tại!");
			return "/admin/public/Danger";
		}
		if (Integer.valueOf(quantityProduct) <= 0) {
			modelMap.addAttribute("results", "Số lượng sản phẩm không được > hoặc =0!");
			return "/admin/public/Danger";
		}
		exportbill.setQuantity(Integer.valueOf(quantityProduct));
		exportbillService.editExportbill(exportbill);

		try {
			JsonNode jsonObject = objectMapper.readTree(listMaterialDetail);
			JsonNode jsonArray = jsonObject.get("listMaterialDetail");

			int materialid, quantity, materialdetailid = -1;
			for (JsonNode materialDetail : jsonArray) {
				materialid = materialDetail.get("materialid").asInt();
				materialdetailid = materialDetail.get("materialdetailid").asInt();
				quantity = materialDetail.get("quantity").asInt();
				/* check */
				List<String> results = checkForm(materialid, quantity);
				if (results.size() > 0) {
					modelMap.addAttribute("results", results);
					return "/admin/public/Danger";
				}
				/* check[END] */
			}

			for (JsonNode materialDetail : jsonArray) {

				materialid = materialDetail.get("materialid").asInt();
				quantity = materialDetail.get("quantity").asInt();
				materialdetailid = materialDetail.get("materialdetailid").asInt();

				// kiểm tra NL chi tiết đã tồn tại trong phiếu xuất chưa - chưa thì thêm
				if (!exportbilldetailService.checkExistMaterialDetail(materialdetailid)) {
					Materialdetail materialdetail = materialdetailService.getInfoById(materialdetailid);
					if (materialdetail == null) {
						modelMap.addAttribute("results", "Nguyên liệu vừa thêm không tồn tại!");
						return "/admin/public/Danger";
					}

					// thêm hóa đơn nhập chi tiết
					Exportbilldetail exportbilldetail = new Exportbilldetail();
					ExportbilldetailId id = new ExportbilldetailId(Integer.valueOf(exportbillid), materialdetailid);
					exportbilldetail.setId(id);
					exportbilldetail.setQuantity(quantity);
					exportbilldetail.setUpdateat(new Date());
					exportbilldetail.setIsdelete(IS_NOT_DELETE);
					exportbilldetailService.addExportbilldetail(exportbilldetail);
				} else {
					ExportbilldetailId id = new ExportbilldetailId(Integer.valueOf(exportbillid), materialdetailid);
					Exportbilldetail exportbilldetail = exportbilldetailService.getInfoByExportbilldetail(id);
					// Kiểm tra số lượng nhập với số lượng trong hóa đơn
					Materialdetail materialdetail2;
					if (exportbilldetail.getQuantity() < quantity) {// lớn hơn thì - trong NL kho
						int temp = quantity - exportbilldetail.getQuantity();

						materialdetail2 = exportbilldetail.getMaterialdetail();
						int temp2 = materialdetail2.getQuantity() - temp;
						//nếu temp2 < 0 :  không đủ số lượng sp chi tiết
						if(temp2 < 0) {
							modelMap.addAttribute("results", "Số lượng sản phẩm không đủ!");
							return "/admin/public/Danger";
						}
						else {
							materialdetail2.setQuantity(materialdetail2.getQuantity() - temp);
						}
						
						materialdetail2.setUpdateat(new Date());
						materialdetailService.editMaterialdetail(materialdetail2);
					}
					if (exportbilldetail.getQuantity() > quantity) {// nhỏ hơn thì + trong NL kho
						int temp = exportbilldetail.getQuantity() - quantity;

						materialdetail2 = exportbilldetail.getMaterialdetail();
						materialdetail2.setQuantity(materialdetail2.getQuantity() + temp);
						materialdetail2.setUpdateat(new Date());
						materialdetailService.editMaterialdetail(materialdetail2);

					}
					exportbilldetail.setQuantity(quantity);
					exportbilldetail.setUpdateat(new Date());
					exportbilldetailService.editExportbilldetail(exportbilldetail);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelMap.addAttribute("result", "Cập nhật thành công!");
		return "/admin/public/Success"; // thành công
	}

	public List<String> checkForm(int materialid, int quantity) {
		List<String> results = new ArrayList<String>();

		if (materialid == -1) {

			results.add("Mã nguyên liệu phải không được để trống!");
		}
		if (quantity <= 0) {

			results.add("Số lượng phải lớn hơn 0!");
		}
		return results;
	}

}
