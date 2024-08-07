package com.app.service;

import com.app.entity.Employee;
import com.app.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {

    public Employee saveEmployee(Employee emp);
    public List<Employee> getAllEmployee();
    public Employee getById(int id) throws EmployeeNotFoundException;
    public Employee updateEmployee(Employee emp) throws EmployeeNotFoundException;
    public String deleteById(int id) throws EmployeeNotFoundException;
}
