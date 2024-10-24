package org.example;

import java.sql.*;

public class ScrollResult {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=GMT&characterEncoding=UTF-8";
        String user = "root";
        String password = "123456";
        String sql = "SELECT * FROM teacher WHERE id > ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ) {
            // 设置参数
            ps.setInt(1, 20);
            // 执行查询
            try (ResultSet rs = ps.executeQuery()) {
                // 移动到最后一行
                rs.last();
//                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("course") + " " + rs.getString("birthday"));

                // 向前移动1行
                rs.relative(-1);
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("course") + " " + rs.getString("birthday"));
            }catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}