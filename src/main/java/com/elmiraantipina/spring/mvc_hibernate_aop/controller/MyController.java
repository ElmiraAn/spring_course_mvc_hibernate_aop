package com.elmiraantipina.spring.mvc_hibernate_aop.controller;


import com.elmiraantipina.spring.mvc_hibernate_aop.dao.EmployeeDAO;
import com.elmiraantipina.spring.mvc_hibernate_aop.entity.Employee;
import com.elmiraantipina.spring.mvc_hibernate_aop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {
    //@Autowired
    //private EmployeeDAO employeeDAO;
    //теперь используем вместо DAO, посредние Service
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String showAllEmployees(Model model) {

        //List<Employee> allEmployees = employeeDAO.getAllEmployees();
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("allEmps", allEmployees);
        return "all-employees";
    }

    @RequestMapping("/addNewEmployee")
    public String addNewEmployee(Model model) {

        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-info";
    }

    @RequestMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        //@ModelAttribute("employee") - получаем его из view, это работник с уже заполненными данными
        employeeService.saveEmployee(employee);//вызываем метод, используя сервис
        //сервис вызывает saveEmployee(employee) из EmployeeDAOImpl,
        // в котором прописано реальная работа этого метода

        return "redirect:/";//перенаправялем на "/"(showAllEmployees)
    }

    @RequestMapping("/updateInfo")
    public String updateEmployee(@RequestParam("empId") int id, Model model){

        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        return "employee-info";
    }

    @RequestMapping("/deleteEmployee")
    public  String deleteEmployee(@RequestParam("empId") int id){
        employeeService.deleteEmployee(id);
        return "redirect:/";//перенаправялем на "/"(showAllEmployees)
    }

}
