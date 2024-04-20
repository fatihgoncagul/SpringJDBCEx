package com.example.springjdbcex.repository;

import com.example.springjdbcex.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepo {


    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Student s) {
        String sql = "insert into student (rollNo, name, marks) values (?, ?, ?)";
        int rows = jdbc.update(sql, s.getRollNo(), s.getName(), s.getMarks()); // we tried to insert the data but we did not create the table with H2
        // we can do that by creating schema.sql (creating table) and data.sql file to insert rows

        System.out.println(rows + " rows affected");

    }

    public List<Student> findAll() {
        String sql = "select * from student";
        RowMapper<Student> mapper = new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

                Student s = new Student();
                s.setRollNo(rs.getInt("rollNo"));
                s.setName(rs.getString("name"));
                s.setMarks(rs.getInt("marks"));



                return s;
            }
        };

        return jdbc.query(sql,mapper);
    }
}
