package com.springtutorial.restcruddemo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service //service layer
//@Repository - simple
public class EmployeeDAOImpl implements EmployeeDAO{
    //define entity manager
    private EntityManager entityManager;
    @Autowired
    public  EmployeeDAOImpl(EntityManager theEntityManager){
        entityManager=theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee",Employee.class);
        List<Employee>employees=theQuery.getResultList();
        //exec query

        return employees;
    }

    @Override
    public Employee findById(int theId) {
        Employee theEmployee=entityManager.find(Employee.class,theId);

        return theEmployee;
    }

    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        Employee dbEmployee = entityManager.merge(theEmployee);
        return dbEmployee;
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
        Employee theEmployee = entityManager.find(Employee.class,theId);
        entityManager.remove(theEmployee);
    }
}
