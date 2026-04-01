package com.project.dao;

import com.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static javax.swing.UIManager.getInt;

@Repository
public class UserDao {
@Autowired
JdbcTemplate template;

    // Insert User
    public int saveUser(User user) {
        String sql = "INSERT INTO users (name, age, email) VALUES (?, ?, ?)";
        return template.update(sql,
                user.getName(),
                user.getAge(),
                user.getEmail());
    }

    public User getUser(int id) {
        String s = "select * from users where id=?";
        List<User> list = template.query(s,(rs, rnum)->{
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setAge(rs.getInt("age"));
            u.setEmail(rs.getString("email"));
            return u;
        },id);
        return list.isEmpty()? null : list.get(0);
    }

    // Get All Users
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return template.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
            user.setEmail(rs.getString("email"));
            return user;
        });
    }

    // Delete User
    public int deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        return template.update(sql, id);
    }

    public int updateUser(User user) {
        String sql = "UPDATE users SET name = ?, age = ?, email = ? WHERE id = ?";
        return template.update(sql,
                user.getName(),
                user.getAge(),
                user.getEmail(),
                user.getId());
    }

    // Get User by ID
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return template.queryForObject(sql, userRowMapper, id);
    }

    // RowMapper (important for mapping ResultSet → User object)
    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setAge(rs.getInt("age"));
        return user;
    };
}