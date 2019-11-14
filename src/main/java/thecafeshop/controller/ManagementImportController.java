package thecafeshop.controller;

import java.io.IOException;
import java.text.ParseException;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import thecafeshop.DTO.ImportBillDTO;
import thecafeshop.common.Common;
import thecafeshop.entity.*;
import thecafeshop.service.*;


@Controller
@RequestMapping("")
public class ManagementImportController extends Common {

	@Autowired
	ImportBillService importBillService;
	@Autowired
	ImportBillDetailService importBillDetailService;
	@Autowired
	SupplierService supplierService;
	@Autowired
	MaterialService materialService;
	@Autowired
	MaterialdetailService materialdetailService;

	@GetMapping(value = "/admin/warehouse-import-material")
	public String index(ModelMap modelMap) {

		int totalPage = importBillService.findAll().size() / super.MAX_RESULTS;
		if ((importBillService.findAll().size() % super.MAX_RESULTS) > 0) {
			totalPage++;
		}
		modelMap.addAttribute("totalPage", totalPage);

		List<Supplier> suppliers;
		suppliers = supplierService.findAll();
		modelMap.addAttribute("suppliers", suppliers);

		List<Material> materials = materialService.findAll();
		modelMap.addAttribute("materials", materials);

		return "/admin/management-warehouse/import-material";
	}

	@GetMapping(value = "/admin/warehouse-import-material/table")
	public String tbody(ModelMap modelMap, HttpSession httpSession, @RequestParam String startPosition) {

		List<Importbill> importbills = importBillService.findLimit(Integer.valueOf(startPosition.trim()) - 1);
		List<ImportBillDTO> dtos = new ArrayList<ImportBillDTO>();
		for (Importbill importbill : importbills) {
			ImportBillDTO importBillDTO = new ImportBillDTO();

			importBillDTO.setImportbill(importbill);
			importBillDTO.setEmployee(importbill.getEmployee());
			importBillDTO.setSupplier(importbill.getSupplier());
			importBillDTO.setCountBillDetail(
					importBillDetailService.getNumberImportbilldetail(importbill.getImportbillid()));
			importBillDTO.setCanDelete(false);

			dtos.add(importBillDTO);
		}
		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-warehouse/content/import-material/tBody";
	}

	@PostMapping(value = "/admin/warehouse-import-material/insert")
	public String insert(ModelMap modelMap, HttpSession httpSession, @RequestParam String supplierid,
			@RequestParam String listMaterialDetail) throws ParseException {
		
		/* check */
		if (supplierid.trim().length() == 0 || supplierid.trim().equals("-1")) {

			modelMap.addAttribute("results", "Mã nhà cung cấp phải không được để trống!");
			return "/admin/public/Danger";
		}
		/* check[END] */
// thêm hóa đơn nhập
		Importbill importbill = new Importbill();
		importbill.setSupplier(new Supplier(Integer.valueOf(supplierid.trim())));
//		importbill.setEmployee(employee);
		importbill.setUpdateat(new Date());
		importbill.setIsdelete(IS_NOT_DELETE);
		int lastId = importBillService.addImportBill(importbill);

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonObject = objectMapper.readTree(listMaterialDetail);
			JsonNode jsonArray = jsonObject.get("listMaterialDetail");

			int materialid, quantity, price;
			String dateofmanufacture, expirationdate;
			for (JsonNode materialDetail : jsonArray) {

				materialid = materialDetail.get("materialid").asInt();
				dateofmanufacture = materialDetail.get("dateofmanufacture").asText();
				expirationdate = materialDetail.get("expirationdate").asText();
				quantity = materialDetail.get("quantity").asInt();
				price = materialDetail.get("price").asInt();

				/* check */
				List<String> results = checkForm(materialid, quantity, dateofmanufacture, expirationdate, price);
				if (results.size() > 0) {
					modelMap.addAttribute("results", results);
					return "/admin/public/Danger";
				}
				/* check[END] */
			}
			for (JsonNode materialDetail : jsonArray) {

				materialid = materialDetail.get("materialid").asInt();
				dateofmanufacture = materialDetail.get("dateofmanufacture").asText();
				expirationdate = materialDetail.get("expirationdate").asText();
				quantity = materialDetail.get("quantity").asInt();
				price = materialDetail.get("price").asInt();

				// thêm nguyên liệu chi tiết
				Materialdetail materialdetail = new Materialdetail();
				materialdetail.setMaterial(new Material(materialid));
				materialdetail.setQuantity(Integer.valueOf(quantity));
				materialdetail.setDateofmanufacture(super.sdfDateField.parse(dateofmanufacture));
				materialdetail.setExpirationdate(super.sdfDateField.parse(expirationdate));
				materialdetail.setPrice(price);
				materialdetail.setUpdateat(new Date());
				materialdetail.setIsdelete(IS_NOT_DELETE);
				int materialDetailId = materialdetailService.addMaterialdetail(materialdetail);

				// thêm hóa đơn nhập chi tiết
				Importbilldetail importbilldetail = new Importbilldetail();
				importbilldetail.setId(new ImportbilldetailId(materialDetailId, lastId));
				importbilldetail.setUpdateat(new Date());
				importbilldetail.setIsdelete(IS_NOT_DELETE);
				importBillDetailService.addImportbilldetail(importbilldetail);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelMap.addAttribute("result", "Thêm thành công!");
		return "/admin/public/Success"; // thành công
	}

//
//	@PostMapping(value = "/admin/bill-status/remove")
//	public String remove(ModelMap modelMap, HttpSession httpSession, @RequestParam String billstatusid) {
//
//		Billstatus billstatus = billstatusService.getInfoById(billstatusid.trim());
//		if (billstatus == null) {
//			modelMap.addAttribute("results", "Trạng thái hóa đơn không tồn tại!");
//			return "/admin/public/Danger";// đã tồn tại
//		}
//		billstatus.setIsdelete(this.IS_DELETE);
//		billstatusService.editBilldetail(billstatus);
//
//		modelMap.addAttribute("result", "Xóa thành công!");
//		return "/admin/public/Success";// đã tồn tại
//	}
//
	@GetMapping(value = "/admin/warehouse-import-material/edit")
	public String view(ModelMap modelMap, HttpSession httpSession, @RequestParam String importbillid) {

		List<Supplier> suppliers = supplierService.findAll();
		modelMap.addAttribute("suppliers", suppliers);

		List<Material> materials = materialService.findAll();
		modelMap.addAttribute("materials", materials);

		Importbill importbill = importBillService.getInfoById(Integer.valueOf(importbillid.trim()));
		if (importbill == null) {
			modelMap.addAttribute("results", "Trạng thái hóa đơn không tồn tại!");
			return "/admin/public/Danger";
		}
		modelMap.addAttribute("importbill", importbill);

		List<Importbilldetail> importbilldetails = importBillDetailService
				.getInfoByImportbillid(Integer.valueOf(importbillid.trim()));
		modelMap.addAttribute("importbilldetails", importbilldetails);

		return "/admin/management-warehouse/content/import-material/form";
	}
	@PostMapping(value = "/admin/warehouse-import-material/edit")
	public String edit(ModelMap modelMap, HttpSession httpSession, @RequestParam String importbillid,
			@RequestParam String listMaterialDetail) throws ParseException {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonObject = objectMapper.readTree(listMaterialDetail);
			JsonNode jsonArray = jsonObject.get("listMaterialDetail");

			int materialid, quantity, materialdetailid = -1, price;
			String dateofmanufacture, expirationdate;
			for (JsonNode materialDetail : jsonArray) {
				materialid = materialDetail.get("materialid").asInt();
				materialdetailid = materialDetail.get("materialdetailid").asInt();
				dateofmanufacture = materialDetail.get("dateofmanufacture").asText();
				expirationdate = materialDetail.get("expirationdate").asText();
				quantity = materialDetail.get("quantity").asInt();
				price = materialDetail.get("price").asInt();
				/* check */
				List<String> results = checkForm(materialid, quantity, dateofmanufacture, expirationdate, price);
				if (results.size() > 0) {
					modelMap.addAttribute("results", results);
					return "/admin/public/Danger";
				}
				/* check[END] */
			}

			for (JsonNode materialDetail : jsonArray) {

				materialid = materialDetail.get("materialid").asInt();
				dateofmanufacture = materialDetail.get("dateofmanufacture").asText();
				expirationdate = materialDetail.get("expirationdate").asText();
				quantity = materialDetail.get("quantity").asInt();
				materialdetailid = materialDetail.get("materialdetailid").asInt();
				price = materialDetail.get("price").asInt();

				// kiểm tra NL chi tiết đã tồn tại trong phiếu nhập chưa - chưa thì thêm
				if (!importBillDetailService.checkExistMaterialDetail(materialdetailid)) {
					Materialdetail materialdetail = new Materialdetail();
					materialdetail.setQuantity(quantity);
					materialdetail.setUpdateat(new Date());
					materialdetail.setMaterial(new Material(materialid));
					materialdetail.setDateofmanufacture(super.sdfDateField.parse(dateofmanufacture));
					materialdetail.setExpirationdate(super.sdfDateField.parse(expirationdate));
					materialdetail.setIsdelete(IS_NOT_DELETE);
					materialdetailid = materialdetailService.addMaterialdetail(materialdetail);

					// thêm hóa đơn nhập chi tiết
					Importbilldetail importbilldetail = new Importbilldetail();
					importbilldetail
							.setId(new ImportbilldetailId(materialdetailid, Integer.valueOf(importbillid.trim())));
					importbilldetail.setUpdateat(new Date());
					importbilldetail.setIsdelete(IS_NOT_DELETE);
					importBillDetailService.addImportbilldetail(importbilldetail);
				} else {
					Materialdetail materialdetail2 = materialdetailService.getInfoById(materialdetailid);
					materialdetail2.setQuantity(quantity);
					materialdetail2.setPrice(price);
					materialdetail2.setUpdateat(new Date());
					materialdetailService.editMaterialdetail(materialdetail2);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelMap.addAttribute("result", "Cập nhật thành công!");
		return "/admin/public/Success"; // thành công
	}

	public List<String> checkForm(int materialid, int quantity, String dateofmanufacture, String expirationdate, int price) throws ParseException {
		List<String> results = new ArrayList<String>();

		if (materialid == -1) {

			results.add("Mã nguyên liệu phải không được để trống!");
		}
		if (quantity <= 0) {

			results.add("Số lượng phải lớn hơn 0!");
		}
		if (price <= 0) {

			results.add("Giá phải lớn hơn 0!");
		}
		if (dateofmanufacture.length() <= 0) {
			results.add("Ngày sản xuất không được để trống!");
		}
		if (expirationdate.length() <= 0) {
			results.add("Ngày hết hạn không được để trống!");
		}
		Date SX = super.sdfDateField.parse(dateofmanufacture);
		Date HH = super.sdfDateField.parse(expirationdate);
		if(!SX.before(HH)) {
			results.add("Ngày hết hạn phải lớn hơn ngày sản xuất!");
		}
		return results;
	}

}
