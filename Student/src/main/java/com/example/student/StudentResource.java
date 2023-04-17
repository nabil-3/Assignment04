package com.example.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/students")
public class StudentResource {

    @PersistenceContext(unitName = "studentPU")
    private EntityManager em;

    private Connection conn = null;
    private final String url = "jdbc:mysql://localhost:3307/student";
    private final String user = "root";
    private final String password = "admin123";

    public Connection connect() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStudent(Student student) {
        em.persist(student);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    @GET
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudentById(@PathParam("studentId") int studentId) {
        Student student = em.find(Student.class, studentId);
        if (student == null) {
            throw new NotFoundException("Student with ID " + studentId + " not found");
        }
        return student;
    }

    @GET
    @Path("/highest_cgpa")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudentWithHighestCgpa() {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s ORDER BY s.cgpa DESC", Student.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    @PUT
    @Path("/{studentId}")
    public void updateStudent(@PathParam("studentId") int studentId, Student updatedStudent) {
        Student student = em.find(Student.class, studentId);
        if (student == null) {
            throw new NotFoundException("Student with ID " + studentId + " not found");
        }
        student.setName(updatedStudent.getName());
        student.setSemester(updatedStudent.getSemester());
        student.setCgpa(updatedStudent.getCgpa());
        em.merge(student);
    }

    @DELETE
    @Path("/{studentId}")
    public void deleteStudent(@PathParam("studentId") int studentId) {
        Student student = em.find(Student.class, studentId);
        if (student == null) {
            throw new NotFoundException("Student with ID " + studentId + " not found");
        }
        em.remove(student);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents() {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
        return query.getResultList();
    }
}
