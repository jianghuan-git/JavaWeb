package org.example.CRUD;

import java.sql.*;

public class TeacherRetrieve {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=GMT&characterEncoding=UTF-8";
        String user = "root";
        String password = "123456";
        String sql = "SELECT * FROM teacher WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            // 设置参数
            ps.setInt(1, 1);
            // 执行查询
            try (ResultSet rs = ps.executeQuery()) {
                // 输出查询结果
                while (rs.next()) {
                    System.out.println(rs.getObject(1) + " " + rs.getObject(2) + " " + rs.getObject(3) + " " + rs.getObject(4));
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
