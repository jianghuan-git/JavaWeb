package org.example.CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeacherUpdate {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=GMT&characterEncoding=UTF-8";
        String user = "root";
        String password = "123456";
        // 修改数据 id =1, name=宝儿姐
        String sql = "UPDATE teacher SET name = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql);) {
                // 设置参数
                ps.setString(1, "宝儿姐");
                ps.setInt(2, 1);
                // 执行插入或更新
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
