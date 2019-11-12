package thecafeshop.controller;

import java.awt.dnd.DnDConstants;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import thecafeshop.DTO.ProductDTO;
import thecafeshop.DTO.TableStatusDTO;
import thecafeshop.DTO.indexAdminDTO;
import thecafeshop.entity.*;
import thecafeshop.reponsitory.DinnertableRepository;
import thecafeshop.reponsitory.EmployeeRepository;


@Controller
@RequestMapping("")
public class IndexAdminController {
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DinnertableRepository dinnertableRepository;
	@GetMapping(value = { "/admin/index" })
	public String index(ModelMap modelMap, HttpSession httpSession) {

		// check logined
		if (httpSession.getAttribute("emId") == null) {
			return "redirect:/admin/login";
		}
		String emId = httpSession.getAttribute("emId").toString();
		Employee employee = employeeRepository.findById(emId).get();
		modelMap.addAttribute(employee);

		// danh sách bàn

		List<indexAdminDTO> dtos = new ArrayList<indexAdminDTO>();
		List<Dinnertable> dinnertables = dinnertableRepository.findAll();
		for (Dinnertable dinnertable : dinnertables) {
			indexAdminDTO indexAdminDTO = new indexAdminDTO();
			indexAdminDTO.setDinnertable(dinnertable);

			dtos.add(indexAdminDTO);
		}
		modelMap.addAttribute("dtos", dtos);

		return "/admin/index";
	}





}
