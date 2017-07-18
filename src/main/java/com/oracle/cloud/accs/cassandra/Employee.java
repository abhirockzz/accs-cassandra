package com.oracle.cloud.accs.cassandra;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;

@Table(keyspace = "test", name = "employee",
       readConsistency = "QUORUM",
       writeConsistency = "QUORUM",
       caseSensitiveKeyspace = false,
       caseSensitiveTable = false)

@XmlRootElement
public class Employee {
    @PartitionKey
    @Column(name = "emp_id")
    private UUID empId;
    private String name;

    public Employee() {
    }

    public Employee(UUID empId, String name) {
        this.empId = empId;
        this.name = name;
    }

    public UUID getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{" + "empId=" + empId + ", name=" + name + '}';
    }

    public void setEmpId(UUID empId) {
        this.empId = empId;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
