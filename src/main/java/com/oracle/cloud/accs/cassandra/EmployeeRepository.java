package com.oracle.cloud.accs.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmployeeRepository {

    private static Session session = null;
    private static Cluster cluster = null;
    private static MappingManager manager = null;

    static {
        String username = System.getenv().getOrDefault("CASSANDRA_USERNAME", "cassandra");
        String password = System.getenv().getOrDefault("CASSANDRA_PASSWORD", "cassandra");
        String cassandraHost = System.getenv().getOrDefault("CASSANDRA_HOST", "140.86.34.91");
        String cassandraPort = System.getenv().getOrDefault("CASSANDRA_PORT", "9042");
        
        cluster = Cluster.builder()
                .addContactPoint(cassandraHost)
                .withPort(Integer.valueOf(cassandraPort))
                .withCredentials(username, password).build();
        session = cluster.connect();

        manager = new MappingManager(session);
        System.out.println("Connected to Cassandra....");
    }
    
    static void close(){
        if(session!=null){
            session.close();
            System.out.println("Cassandra connection closed");
        }
    }

    public String create(String name) {

        Mapper<Employee> mapper = manager.mapper(Employee.class);

        UUID empid = UUID.randomUUID();
        mapper.save(new Employee(empid, name));
        System.out.println("Employee saved");

        return empid.toString();
    }

    public Employee get(String empid) {

        Mapper<Employee> mapper = manager.mapper(Employee.class);

        Employee emp = mapper.get(UUID.fromString(empid));
        System.out.println("Found empployee " + emp);

        return emp;
    }

    public List<Employee> all() {
        List<Employee> employees = new ArrayList<>();

        Mapper<Employee> mapper = manager.mapper(Employee.class);
        ResultSet rawRSet = session.execute("select * from test.employee");
        Result<Employee> empsRSet = mapper.map(rawRSet);

        for (Employee employee : empsRSet) {
            employees.add(employee);
            System.out.println("Found employee " + employee);

        }

        return employees;
    }

}
