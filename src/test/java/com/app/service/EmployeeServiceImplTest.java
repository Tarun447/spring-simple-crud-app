package com.app.service;

import com.app.entity.Address;
import com.app.entity.Employee;
import com.app.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl impl;

    private Employee mockrequestEmployee;
    private  Employee mockresponseEmployee;

    @BeforeEach
    public  void startUp() {
        mockrequestEmployee =
                new Employee("Tarun Kumar Nanda", "tkumarna98@gmail.com", "9876785431",
                        new Address("bdk", "odisha", "756133"));


        mockresponseEmployee =
                new Employee(1, "Tarun Kumar Nanda", "tkumarna98@gmail.com", "9876785431",
                        new Address("bdk", "odisha", "756133"));
    }


    @Test
    public void saveEmployeeTest()
    {

      when(repository.save(mockrequestEmployee)).thenReturn(mockresponseEmployee);

      Employee employee = impl.saveEmployee(mockrequestEmployee);

      assertEquals(1,employee.getId());

    }

    @Test
    public void getAllEmployeeTest()
    {
        List<Employee> emplist = new ArrayList();
        emplist.add(mockresponseEmployee);
        when(repository.findAll()).thenReturn(emplist);

        List emp = impl.getAllEmployee();
        assertEquals(emp.size(),emplist.size());
        verify(repository).findAll();
    }

    @Test
    public void getByIdWithoutExceptionTest()
    {
        when(repository.findById(1)).thenReturn(Optional.of(mockresponseEmployee));
        Employee emp = impl.getById(1);
        assertEquals(mockresponseEmployee.getEmpName(),emp.getEmpName());
        verify(repository).findById(1);
    }

    @Test
    public void getByIdWithExceptionTest()
    {
        when(repository.findById(2)).thenReturn(Optional.ofNullable(null));
       Throwable exception =  assertThrows(RuntimeException.class,()->impl.getById(2));
       assertEquals("Employee data is not found with id : 2",exception.getMessage());
       verify(repository).findById(2);
    }

    @Test
    public void updateEmployeeWithoutExceptionTest()
    {
        when(repository.findById(1)).thenReturn(Optional.of(mockresponseEmployee));
        when(repository.save(mockresponseEmployee)).thenReturn(mockresponseEmployee);
        Employee emp1 = impl.updateEmployee(mockresponseEmployee);
        assertEquals("tkumarna98@gmail.com",emp1.getEmpEmail());
        verify(repository).findById(1);
        verify(repository).save(mockresponseEmployee);
    }

    @Test
  public void deleteByIdTest()
  {
      when(repository.findById(1)).thenReturn(Optional.of(mockresponseEmployee));
      doNothing().when(repository).delete(mockresponseEmployee);
      String str = impl.deleteById(1);
      assertEquals("Employee record has been deleted successfully from the database with id : 1",str);
      verify(repository).delete(mockresponseEmployee);
  }


}