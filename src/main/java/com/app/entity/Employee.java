package com.app.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="Emp_Db")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String empName;
    private String empEmail;
    private String empPhone;

    @JdbcTypeCode(SqlTypes.JSON)
    private Address empAddress;

    public Employee(String empName, String empEmail, String empPhone, Address empAddress) {
        this.empName = empName;
        this.empEmail = empEmail;
        this.empPhone = empPhone;
        this.empAddress = empAddress;
    }
}
