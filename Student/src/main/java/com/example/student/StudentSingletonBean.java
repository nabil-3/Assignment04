package com.example.student;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/students")
public class StudentSingletonBean {

    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Path("/highest-cgpa")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudentWithHighestCgpa() {
        String queryString = "SELECT s FROM Student s ORDER BY s.cgpa DESC";
        Query query = entityManager.createQuery(queryString);
        query.setMaxResults(1);
        Student student = (Student) query.getSingleResult();
        return student;
    }
}
