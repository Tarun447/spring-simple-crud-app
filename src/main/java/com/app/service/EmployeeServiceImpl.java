package com.app.service;

import com.app.entity.Address;
import com.app.entity.Employee;
import com.app.exception.EmployeeNotFoundException;
import com.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public Employee saveEmployee(Employee emp) {
        return repo.save(emp);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return repo.findAll();
    }

    @Override
    public Employee getById(int id) throws EmployeeNotFoundException {
        return repo.findById(id).orElseThrow(()->new RuntimeException("Employee data is not found with id : "+id));
    }

    @Override
    public Employee updateEmployee(Employee emp) throws EmployeeNotFoundException {
       Employee e=  repo.findById(emp.getId()).orElseThrow(()->new RuntimeException("Employee data is not found with id : "+emp.getId()));

        e.setEmpName(emp.getEmpName());
       e.setEmpEmail(emp.getEmpEmail());
       e.setEmpPhone(emp.getEmpPhone());
       e.setEmpAddress(emp.getEmpAddress());

        return repo.save(e);
    }

    @Override
    public String deleteById(int id) throws EmployeeNotFoundException {
       Employee emp =  repo.findById(id).orElseThrow(()->new RuntimeException("Employee data is not found with id : "+id));
       repo.delete(emp);
       return "Employee record has been deleted successfully from the database with id : "+id;
    }
}
