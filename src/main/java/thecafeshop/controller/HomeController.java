package thecafeshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import thecafeshop.entity.Dinnertable;
import thecafeshop.entity.Employee;
import thecafeshop.entity.Tablestatus;
import thecafeshop.reponsitory.DinnertableRepository;
import thecafeshop.reponsitory.EmployeeRepository;
import thecafeshop.service.TablestatusService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
@Transactional
public class HomeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DinnertableRepository dinnertableRepository;

    @Autowired
    TablestatusService tablestatusService;
    @GetMapping
    public String getHome(){
        Tablestatus tablestatus = tablestatusService.getInfoById(2);
        List<Tablestatus> dinnertables = tablestatusService.findAll();
        return "home";
    }
}
