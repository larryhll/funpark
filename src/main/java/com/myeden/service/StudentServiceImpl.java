package com.myeden.service;

import com.myeden.dao.StudentDao;
import com.myeden.entity.EntityTest;
import com.myeden.entity.Student;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;


/**
 * Created by felhan on 11/12/2016.
 */

@Produces({ "application/json", "application/xml" })
@Consumes({ "application/json", "application/xml" })
public class StudentServiceImpl {

    @Autowired
    private StudentDao studentDao;

    private ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @GET
    @Path("/stu")
    public Response getStuInfo() {
        EntityTest student=new EntityTest();
        student.setId(123);
        student.setName("Felix");
        return Response.ok(student).build();
    }

    @POST
    @Path("/saves/{name}")
    public Response regisStu(@PathParam("name") String name) {
        System.out.println("Requset name: " + name);
        Student student=new Student();
        //student.setId(123);
        student.setName("Felix");
        studentDao.saveStu(student);
        return Response.ok(student).build();
    }

    @POST
    @Path("/save")
    public Response saveStu(String request) {
        OBJECT_MAPPER.configure(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, false);
        OBJECT_MAPPER.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Student student=null;
        try {
            student= OBJECT_MAPPER.readValue(request, Student.class);
            studentDao.saveStu(student);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.ok(student).build();
    }

}
