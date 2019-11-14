package thecafeshop.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import thecafeshop.DTO.EmployeeDTO;
import thecafeshop.common.Common;
import thecafeshop.entity.Atposition;
import thecafeshop.entity.Employee;
import thecafeshop.entity.Position;
import thecafeshop.entity.Salary;
import thecafeshop.service.*;


@Controller
@RequestMapping("")
public class EmployeeController extends Common {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	PositionService positionService;
	@Autowired
	AtpositionService atpositionService;
	@Autowired
	SalaryService salaryService;

	@GetMapping(value = "/admin/employee")
	public String index(ModelMap modelMap, HttpSession httpSession) {

		int totalPage = employeeService.findAll().size() / super.MAX_RESULTS;
		if ((employeeService.findAll().size() % super.MAX_RESULTS) > 0) {
			totalPage++;
		}
		modelMap.addAttribute("totalPage", totalPage);

		List<Position> positions = positionService.findAll();
		modelMap.addAttribute("positions", positions);

		return "/admin/management-employee/employee";
	}

	@GetMapping(value = "/admin/employee/table")
	public String tbody(ModelMap modelMap, HttpSession httpSession, @RequestParam String startPosition) {

		List<Employee> employees = employeeService.findLimit(Integer.valueOf(startPosition.trim()) - 1);
		
		List<EmployeeDTO> dtos = new ArrayList<EmployeeDTO>();
		for (Employee employee : employees) {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployee(employee);
			employeeDTO.setSalary(salaryService.getSalaryByEmployeeid(employee.getEmployeeid()));
			
			dtos.add(employeeDTO);
		}
		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-employee/content/employee/tBody";
	}

	@PostMapping(value = "/admin/employee/insert")
	public String insert(ModelMap modelMap, HttpSession httpSession, @RequestParam String employeeid,
			@RequestParam String name, @RequestParam String sex, @RequestParam String phone,
			@RequestParam String address, @RequestParam String usename, @RequestParam String password,
			@RequestParam String position, @RequestParam String salaryonhour, @RequestParam String startdate)
			throws ParseException {

		/* check */
		List<String> results = checkForm(employeeid, name, phone, address, usename, password, position, salaryonhour,
				startdate);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";// đã tồn tại
		}
		/* check[END] */

		if (employeeService.getInfoById(employeeid.trim()) != null) {

			modelMap.addAttribute("results", "Mã đã tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}
		if (employeeService.checkExistUseName(usename.trim())) {

			modelMap.addAttribute("results", "Tài khoản đã tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		Employee employee = new Employee();
		employee.setEmployeeid(employeeid.trim());
		employee.setName(name);
		if (sex != "Male") {
			employee.setSex(true);
		}
		if (sex != "FeMale") {
			employee.setSex(false);
		}
		employee.setPhone(phone);
		employee.setAddress(address);
		employee.setUsename(usename);
		employee.setPassword(password);
//		employee.setCreateat(createat);
		employee.setUpdateat(new Date());
		employee.setIsdelete(IS_NOT_DELETE);
		employeeService.addEmployee(employee);

		Atposition atposition = new Atposition();
		atposition.setEmployee(employee);
		atposition.setUpdateat(new Date());
//		atposition.setCreateby(createby);
		atposition.setUpdateat(new Date());
		atposition.setStartdate(super.sdfDateField.parse(startdate));
		atposition.setIsdelete(super.IS_NOT_DELETE);
		atpositionService.addAtposition(atposition);

		Salary salary = new Salary();
		salary.setSalaryonhour(Integer.valueOf(salaryonhour.trim()));
		salary.setUpdateat(new Date());
		salary.setStartdate(super.sdfDateField.parse(startdate));
		salary.setUpdateat(new Date());
		salary.setIsdelete(super.IS_NOT_DELETE);
		salaryService.addSalary(salary);

		modelMap.addAttribute("result", "Thêm thành công!");
		return "/admin/public/Success"; // thành công
	}

	@PostMapping(value = "/admin/employee/remove")
	public String remove(ModelMap modelMap, HttpSession httpSession, @RequestParam String employeeid) {

		Employee employee = employeeService.getInfoById(employeeid.trim());
		if (employee == null) {
			modelMap.addAttribute("results", "Nhân viên không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}
		employee.setIsdelete(this.IS_DELETE);
		employeeService.editEmployee(employee);

		modelMap.addAttribute("result", "Xóa thành công!");
		return "/admin/public/Success";// đã tồn tại
	}

	@GetMapping(value = "/admin/employee/edit")
	public String view(ModelMap modelMap, HttpSession httpSession, @RequestParam String employeeid) {

		List<Position> positions = positionService.findAll();
		modelMap.addAttribute("positions", positions);

		Employee employee = employeeService.getInfoById(employeeid.trim());
		if (employee == null) {
			modelMap.addAttribute("results", "Nhân viên không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		modelMap.addAttribute("employee", employee);

		return "/admin/management-employee/content/employee/form";
	}

	@PostMapping(value = "/admin/employee/edit")
	public String edit(ModelMap modelMap, HttpSession httpSession, @RequestParam String employeeid,
			@RequestParam String name, @RequestParam String sex, @RequestParam String phone,
			@RequestParam String address, @RequestParam String position, @RequestParam String salaryonhour,
			@RequestParam String startdate) throws ParseException {

		/* check */
		List<String> results = checkForm(employeeid, name, phone, address, null, null, position, salaryonhour, startdate);
		if (results.size() > 0) {
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";// đã tồn tại
		}
		/* check[END] */

		Employee employee = employeeService.getInfoById(employeeid.trim());
		if (employee == null) {
			results.add("Nhân viên không tồn tại!");
			modelMap.addAttribute("results", results);
			return "/admin/public/Danger";// đã tồn tại
		}

		employee.setName(name);
		if (sex != "Male") {
			employee.setSex(true);
		}
		if (sex != "FeMale") {
			employee.setSex(false);
		}
		employee.setPhone(phone);
		employee.setAddress(address);
		employee.setUpdateat(new Date());
		employee.setIsdelete(IS_NOT_DELETE);
		employeeService.editEmployee(employee);

		Atposition atposition = new Atposition();
		atposition.setEmployee(employee);
		atposition.setUpdateat(new Date());
		atposition.setStartdate(super.sdfDateField.parse(startdate));
		atposition.setIsdelete(super.IS_NOT_DELETE);
		atpositionService.addAtposition(atposition);

		Salary salary = new Salary();
		salary.setSalaryonhour(Integer.valueOf(salaryonhour.trim()));
		salary.setStartdate(super.sdfDateField.parse(startdate));
		salary.setUpdateat(new Date());
		salary.setIsdelete(super.IS_NOT_DELETE);
		salaryService.addSalary(salary);

		modelMap.addAttribute("result", "Cập nhật thành công!");
		return "/admin/public/Success";
	}

	public List<String> checkForm(String employeeid, String name, String phone, String address, String usename,
			String password, String position, String salaryonhour, String startdate) {
		
		List<String> results = new ArrayList<String>();
		if (employeeid.length() <= 0 || employeeid.length() > 7) {
			results.add("Mã không được để trống và tối đa 7 kí tự!");
		}
		if (name.length() <= 0) {
			results.add("Tên không được để trống!");
		}
		if (!this.checkNumberPhone(phone)) {
			results.add("Số điện thoại không đúng!");
		}
		if (address.length() <= 0) {
			results.add("Địa chỉ không được để trống!");
		}
		if (usename != null && usename.length() <= 0) {
			results.add("Tài khoản không được để trống!");
		}
		if (password != null && password.length() <= 0) {
			results.add("Mật khẩu không được để trống!");
		}
		if (position == "-1") {
			results.add("Vui lòng chọn chức vụ!");
		}
		if (salaryonhour.length() <= 0) {
			results.add("Lương trên giờ không được để trống!");
		}
		if (startdate.length() <= 0) {
			results.add("Ngày bắt đầu làm việc không được để trống!");
		}
		return results;
	}
}
