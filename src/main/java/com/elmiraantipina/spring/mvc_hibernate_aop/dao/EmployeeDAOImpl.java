package com.elmiraantipina.spring.mvc_hibernate_aop.dao;

import com.elmiraantipina.spring.mvc_hibernate_aop.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @Autowired// Dependency Injection для поля, зависимости с sessionFactory бина
    private SessionFactory sessionFactory;

    @Override
    // Убираем @Transactional и переносим ее в Service
    //@Transactional//чтобы весь цикл транзакции выполнял Spring, открывал и закрывал
    public List<Employee> getAllEmployees() {

        Session session = sessionFactory.getCurrentSession();
        //List<Employee> allEmployees = session.createQuery("from Employee", Employee.class)
        //        .getResultList();//получаем из БД
        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        List<Employee> allEmployees = query.getResultList();


        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();


        session.saveOrUpdate(employee);//сохраняем данные о новом работнике

    }

    @Override
    public Employee getEmployee(int id) {

        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);//получаем работника из БД по id
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("delete from Employee "+
                "where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
