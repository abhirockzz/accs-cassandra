package com.oracle.cloud.accs.cassandra;

import java.net.URI;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

@Path("employees")
public class EmployeesResource {
    
    
    @POST
    public Response create(String name) {

        System.out.println("Creating employee with name " + name);
        Response resp = null;
        try {

            String empID = new EmployeeRepository().create(name);
            
            String appURL = System.getenv().getOrDefault("ORA_APP_PUBLIC_URL", 
                        "http://localhost:8080");
                
                URI empURI = new URI(appURL+"/employees/"+empID);
                resp = Response.created(empURI).build();
        } catch (Throwable e) {
            resp = Response.serverError().entity("Could not create employee due to "+e.getMessage()).build();
        } 

        return resp;
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") String id) {

        System.out.println("Searching for employee with ID " + id);
        Response resp = null;
        Employee emp = null;
        try {

            emp = new EmployeeRepository().get(id);
            if (emp == null) {
                System.out.println("Could not find employee with id " + id);
                resp = Response.status(Response.Status.NOT_FOUND).entity("Could not find employee with id " + id).build();
            } else {
                resp = Response.ok().entity(emp).build();
            }
        } catch (Throwable e) {
            resp = Response.serverError().entity(e.getMessage()).build();
        }

        return resp;
    }

    @GET
    public Response all() {

        System.out.println("Listing all employees......");
        Response resp = null;

        List<Employee> employees = null;
        try {
            employees = new EmployeeRepository().all();
            if (employees == null | employees.isEmpty()) {
                System.out.println("Could not find employees");
                resp = Response.status(Response.Status.NOT_FOUND).entity("Could not find employees").build();
            } else {
                GenericEntity<List<Employee>> list = new GenericEntity<List<Employee>>(employees) {
                };
                resp = Response.ok(list).build();
            }
        } catch (Throwable e) {
            resp = Response.serverError().entity(e.getMessage()).build();
            e.printStackTrace();
        } 

        return resp;
    }

}
