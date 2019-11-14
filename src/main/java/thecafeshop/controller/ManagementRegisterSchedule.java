package thecafeshop.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import thecafeshop.DTO.ScheduleDTO;
import thecafeshop.entity.Employee;
import thecafeshop.entity.Register;
import thecafeshop.entity.Schedule;
import thecafeshop.common.Common;
import thecafeshop.service.RegisterService;
import thecafeshop.service.ScheduleService;


@Controller
@RequestMapping("")
public class ManagementRegisterSchedule extends Common {

	@Autowired
	ScheduleService scheduleService;
	@Autowired
	RegisterService registerService;

	@GetMapping(value = "/admin/register-schedule")
	public String index(ModelMap modelMap) {

		Calendar c = Calendar.getInstance();
		int now = c.get(Calendar.DAY_OF_WEEK);
//		if (now == 6) {// chỉ mở đăng kí vào T6
			List<ScheduleDTO> dtos = new ArrayList<ScheduleDTO>();
			List<Schedule> schedules = scheduleService.findAll();
			for (Schedule schedule : schedules) {
				ScheduleDTO dto = new ScheduleDTO();
				String StartTime = schedule.getStarttime().toString().substring(0, 5);
				String EndTime = schedule.getEndtime().toString().substring(0, 5);
				dto.setScheduleid(schedule.getScheduleid());
				dto.setStarttime(StartTime);
				dto.setEndtime(EndTime);
				dtos.add(dto);
			}

			modelMap.addAttribute("dtos", dtos);

//		}

		return "/admin/management-employee/register-schedule";
	}

	@PostMapping(value = "/admin/register-schedule")
	public @ResponseBody String register(HttpSession httpSession, @RequestParam String listRegister) {
		String emId = httpSession.getAttribute("emId").toString();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonObject = objectMapper.readTree(listRegister);
			JsonNode jsonArray = jsonObject.get("listRegister");
			Calendar c = Calendar.getInstance();
			int now = c.get(Calendar.DAY_OF_WEEK);
			c.roll(c.DAY_OF_MONTH, 8 - now);
			for (JsonNode register : jsonArray) {

				int day = register.get("day").asInt();
				String scheduleid = register.get("scheduleid").asText();
				Register register2 = new Register();
				register2.setEmployee(new Employee(emId));
				register2.setSchedule(new Schedule(scheduleid));
				register2.setDate(c.getTime());
				register2.setIsdelete(IS_NOT_DELETE);
				register2.setUpdateat(new Date());
				register2.setResult(Short.valueOf("0"));
				registerService.addRegister(register2);
				c.roll(c.DAY_OF_MONTH,  1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Đăng ký thành công! Vui lòng chờ xét duyệt";
	}
}
