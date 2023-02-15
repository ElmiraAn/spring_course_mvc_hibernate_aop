package com.elmiraantipina.spring.mvc_hibernate_aop.service;

import com.elmiraantipina.spring.mvc_hibernate_aop.dao.EmployeeDAO;
import com.elmiraantipina.spring.mvc_hibernate_aop.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service//означает, что этот класс соединительное звено между Controller-ом и DAO(на тот случай, если ДАО будет несколько)
public class EmployeeServiceImpl implements EmployeeService{


    @Autowired
    private EmployeeDAO employeeDAO;//прописываем зависмость, чтобы вызвать метод getAllEmployees() из EmployeeDAOImpl


    @Override
    @Transactional//чтобы весь цикл транзакции выполнял Spring, открывал и закрывал, перенесли из ДАО
    public List<Employee> getAllEmployees() {
        //будем вызывать getAllEmployees() из EmployeeDAOImpl

        return employeeDAO.getAllEmployees();
    }

    @Override
    @Transactional//чтобы весь цикл транзакции выполнял Spring, открывал и закрывал
    public void saveEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee); //будем вызывать saveEmployee(employee) из EmployeeDAOImpl,
        // в котором прописано реальная работа этого метода
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }


    @Override
    @Transactional
    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);

    }
}
