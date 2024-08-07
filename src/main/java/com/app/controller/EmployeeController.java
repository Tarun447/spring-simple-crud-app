package com.app.controller;


import com.app.entity.Employee;
import com.app.exception.EmployeeNotFoundException;
import com.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {


    private EmployeeService service;


    public EmployeeController(EmployeeService service) {

        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee emp)
    {
        return new ResponseEntity<>(service.saveEmployee(emp), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee()
    {
        return  ResponseEntity.ok(service.getAllEmployee());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Employee> getbyId(@PathVariable int id) throws EmployeeNotFoundException
    {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp) throws EmployeeNotFoundException
    {
        return new ResponseEntity<>(service.updateEmployee(emp), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable int id)
    {
        return ResponseEntity.ok(service.deleteById(id));
    }

}
