# Assignment04

In my application, I created a Student entity class which maps to a database table using JPA. The class has four fields: studentId, name, semester, and cgpa. I also created a DBConnection class which handles the database connection using JDBC.

In the StudentBean class, I used the @Stateless annotation to mark the class as a stateless session bean. This class has four methods: addStudent(), getStudentName(), getStudentWithHighestCgpa(), and updateStudentName().

The addStudent() method is a POST method that takes in four query parameters: studentId, name, semester, and cgpa. It creates a new Student object with these parameters and persists it to the database using EntityManager.

The getStudentName() method is a GET method that takes in a path parameter: studentId. It uses EntityManager to find the Student object with the given ID and returns its name.

The getStudentWithHighestCgpa() method is also a GET method that returns the Student object with the highest CGPA in the database. It does this by creating a JPA query that orders the results by CGPA in descending order and returns the first result.

The updateStudentName() method is a PUT method that takes in a path parameter: studentId, and a query parameter: name. It uses EntityManager to find the Student object with the given ID, updates its name field with the new name, and merges it back into the database.

Regarding the stateless session bean, I could replace it with a singleton session bean. A singleton bean is a bean that has only one instance per application and can be used to maintain shared application state. However, in this case, it is not necessary since the StudentBean does not have any shared state that needs to be maintained. Therefore, a stateless session bean is sufficient for this application.
