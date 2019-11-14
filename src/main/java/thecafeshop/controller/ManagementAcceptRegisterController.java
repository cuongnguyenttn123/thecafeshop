package thecafeshop.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import thecafeshop.DTO.RegisterDTO;
import thecafeshop.DTO.ScheduleDTO;
import thecafeshop.entity.Register;
import thecafeshop.entity.Schedule;
import thecafeshop.service.RegisterService;
import thecafeshop.service.ScheduleService;


@Controller
@RequestMapping("")
public class ManagementAcceptRegisterController {

	@Autowired
	RegisterService registerService;
	@Autowired
	ScheduleService scheduleService;

	@GetMapping(value = "/admin/accept-register")
	public String index(ModelMap modelMap) {

		List<ScheduleDTO> scheduleDTOs = new ArrayList<ScheduleDTO>();
		List<Schedule> schedules = scheduleService.findAll();
		for (Schedule schedule : schedules) {
			ScheduleDTO dto = new ScheduleDTO();
			dto.setScheduleid(schedule.getScheduleid());
			scheduleDTOs.add(dto);
		}
		modelMap.addAttribute("scheduleDTOs", scheduleDTOs);

		Calendar c = Calendar.getInstance();
		int now = c.get(Calendar.DAY_OF_WEEK);
		c.roll(c.DAY_OF_MONTH, 8 - now);
		Date from = c.getTime();
		c.roll(c.DAY_OF_MONTH, 7);
		Date to = c.getTime();
		List<Register> registers = registerService.getListRegisterOnWeek(from, to);

		List<RegisterDTO> dtos = new ArrayList<RegisterDTO>();
		c.setTime(from);
		for (int i = 1; i <= 7; i++) {
			for (Schedule schedule : schedules) {
				String scheduleid = schedule.getScheduleid();
				RegisterDTO dto = new RegisterDTO();
				dto.setDay(i);
				dto.setScheduleid(scheduleid);

				List<Register> registers2 = registerService.listByDateScheduleid(c.getTime(), scheduleid);

				dto.setRegisters(registers2);

				dtos.add(dto);
			}
			c.roll(c.DAY_OF_MONTH, 1);
		}

		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-employee/accept-register";
	}

	@PostMapping(value = "/admin/accept-register")
	public @ResponseBody String accept(@RequestParam String listRegisterid) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonObject = objectMapper.readTree(listRegisterid);
			JsonNode jsonArray = jsonObject.get("listRegisterid");
			for (JsonNode registerid : jsonArray) {
				int id = registerid.get("registerid").asInt();
				int result = registerid.get("result").asInt();

				Register register = registerService.getInfoById(id);
				register.setResult(result);
				register.setUpdateat(new Date());
				registerService.editRegister(register);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Duyệt thành công!";
	}

}
