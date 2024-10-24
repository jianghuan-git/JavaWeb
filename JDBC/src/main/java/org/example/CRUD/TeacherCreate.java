package org.example.CRUD;

import java.sql.*;

public class TeacherCreate {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=GMT&characterEncoding=UTF-8";
        String user = "root";
        String password = "123456";
        // 插入数据 name=诸葛清, 年龄=22, 性别=男
        String sql = "INSERT INTO teacher (id,name,course,birthday) VALUES (?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql);) {
                // 设置参数
                ps.setInt(1,2);
                ps.setString(2, "王老师");
                ps.setString(3, "高级数据库");
                ps.setString(4, "1998-9-24");
                // 执行插入
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

