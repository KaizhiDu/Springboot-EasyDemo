package com.kevin.demo.controller;

import com.kevin.demo.dao.DepartmentDao;
import com.kevin.demo.dao.EmployeeDao;
import com.kevin.demo.entity.Department;
import com.kevin.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
public class empController {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @GetMapping(value = "/emps")
    public String emps(HttpServletResponse response, HttpServletRequest request, Model model){
        Collection<Employee> emps = employeeDao.getAll();
        model.addAttribute("emps",emps);
        return "emp/list";
    }

    @GetMapping(value = "/emp")
    public String emp(HttpServletResponse response, HttpServletRequest request, Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("dep", departments);
        return "emp/create";
    }

    @PostMapping(value = "/emp")
    public String saveEmp(Employee employee ,HttpServletRequest request, HttpServletResponse response, Model model){
        //这里employee会和前台name相关联, 只要javabean和name对应，就会自动提取
        employeeDao.save(employee);
        //直接回传/emps 会直接返回html，所以我们需要用redirect，重定向
        return "redirect:/emps";
    }

    @GetMapping(value = "/empModify")
    public String modifyEmpPage(Integer id ,HttpServletRequest request, HttpServletResponse response, Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("dep", departments);
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        return "/emp/modify";
    }

    @PutMapping(value = "/empModify")
    public String moidfyEmp(@Valid Employee employee, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request, Model model){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping(value = "/delete")
    public String delete(Integer id, HttpServletResponse response, HttpServletRequest request, Model model){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
