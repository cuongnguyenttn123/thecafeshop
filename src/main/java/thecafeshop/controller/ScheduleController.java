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

import thecafeshop.DTO.ScheduleDTO;
import thecafeshop.entity.Schedule;
import thecafeshop.common.Common;
import thecafeshop.service.ScheduleService;


@Controller
@RequestMapping("")
public class ScheduleController extends Common {

	@Autowired
	ScheduleService scheduleService;

	@GetMapping(value = "/admin/schedule")
	public String index(ModelMap modelMap, HttpSession httpSession) {

		int totalPage = scheduleService.findAll().size() / super.MAX_RESULTS;
		if ((scheduleService.findAll().size() % super.MAX_RESULTS) > 0) {
			totalPage++;
		}
		modelMap.addAttribute("totalPage", totalPage);

		return "/admin/management-system/schedule";
	}

	@GetMapping(value = "/admin/schedule/table")
	public String tbody(ModelMap modelMap, HttpSession httpSession, @RequestParam String startPosition) {

		List<Schedule> schedules = scheduleService.findLimit(Integer.valueOf(startPosition.trim()) - 1);
		List<ScheduleDTO> dtos = new ArrayList<ScheduleDTO>();
		for (Schedule schedule : schedules) {
			ScheduleDTO scheduleDTO = new ScheduleDTO();
			
			scheduleDTO.setScheduleid(schedule.getScheduleid());
			scheduleDTO.setStarttime(schedule.getStarttime().toString());
			scheduleDTO.setEndtime(schedule.getEndtime().toString());
			scheduleDTO.setPayrate(schedule.getPayrate());
			scheduleDTO.setUpdateat(schedule.getUpdateat());

			dtos.add(scheduleDTO);
		}
		modelMap.addAttribute("dtos", dtos);

		return "/admin/management-system/content/schedule/tBody";
	}

	@PostMapping(value = "/admin/schedule/insert")
	public String insert(ModelMap modelMap, HttpSession httpSession, @RequestParam String scheduleid,
			@RequestParam String starttime, @RequestParam String endtime, @RequestParam String payrate)
			throws ParseException {

		List<String> results = checkForm(scheduleid, starttime, endtime, payrate);
		if (results.size() > 0) {
			modelMap.addAllAttributes(results);
			return "/admin/public/Danger";// đã tồn tại
		}

		if (scheduleService.getInfoById(scheduleid.trim()) != null) {

			modelMap.addAttribute("results", "Mã đã tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		Schedule schedule = new Schedule();
		schedule.setScheduleid(scheduleid.trim());
		schedule.setStarttime(super.sdfTimeField.parse(starttime.trim()));
		schedule.setEndtime(super.sdfTimeField.parse(endtime.trim()));
		schedule.setPayrate(Float.valueOf(payrate.trim()));
		schedule.setUpdateat(new Date());
		schedule.setIsdelete(super.IS_NOT_DELETE);
		scheduleService.addSchedule(schedule);

		List<Schedule> schedules = scheduleService.findAll();
		modelMap.addAttribute("schedules", schedules);

		modelMap.addAttribute("result", "Thêm thành công!");
		return "/admin/public/Success"; // thành công
	}

	@PostMapping(value = "/admin/schedule/remove")
	public String remove(ModelMap modelMap, HttpSession httpSession, @RequestParam String scheduleid) {

		Schedule schedule = scheduleService.getInfoById(scheduleid);
		if (schedule == null) {
			modelMap.addAttribute("results", "Ca làm không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}
		schedule.setIsdelete(this.IS_DELETE);
		scheduleService.editSchedule(schedule);

		modelMap.addAttribute("result", "Xóa thành công!");
		return "/admin/public/Success";// đã tồn tại
	}

	@GetMapping(value = "/admin/schedule/edit")
	public String view(ModelMap modelMap, HttpSession httpSession, @RequestParam String scheduleid) {

		Schedule schedule = scheduleService.getInfoById(scheduleid);
		if (schedule == null) {
			modelMap.addAttribute("results", "Ca làm không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		modelMap.addAttribute("schedule", schedule);
		return "/admin/management-system/content/schedule/form";
	}

	@PostMapping(value = "/admin/schedule/edit")
	public String edit(ModelMap modelMap, HttpSession httpSession, @RequestParam String scheduleid,
			@RequestParam String starttime, @RequestParam String endtime, @RequestParam String payrate)
			throws ParseException {
		
		List<String> results = checkForm(scheduleid, starttime, endtime, payrate);
		if (results.size() > 0) {
			modelMap.addAllAttributes(results);
			return "/admin/public/Danger";// đã tồn tại
		}

		Schedule schedule = scheduleService.getInfoById(scheduleid);
		if (schedule == null) {
			modelMap.addAttribute("results", "Ca làm không tồn tại!");
			return "/admin/public/Danger";// đã tồn tại
		}

		schedule.setStarttime(super.sdfTimeField.parse(starttime.trim()));
		schedule.setEndtime(super.sdfTimeField.parse(endtime.trim()));
		schedule.setPayrate(Float.valueOf(payrate.trim()));
//		schedule.setUpdateby(updateby);
		schedule.setUpdateat(new Date());
		scheduleService.editSchedule(schedule);

		modelMap.addAttribute("result", "Cập nhật thành công!");
		return "/admin/public/Success";
	}

	public List<String> checkForm(String scheduleid, String starttime, String endtime, String payrate) {

		List<String> results = new ArrayList<String>();
		if (scheduleid.trim().length() <= 0 || scheduleid.trim().length() > 8) {
			results.add("Mã không thể để trống và tối đa 7 ký tự!");
		}
		if (starttime.trim().length() <= 0) {
			results.add("Giờ bắt đầu không thể để trống!");
		}
		if (endtime.trim().length() <= 0) {
			results.add("Giờ kết thúc không thể để trống!");
		}
		if (payrate.trim().length() <= 0) {
			results.add("Giờ bắt đầu không thể để trống!");
		}
		return results;
	}
}
