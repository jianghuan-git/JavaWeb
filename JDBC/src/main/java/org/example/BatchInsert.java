package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchInsert {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=GMT&characterEncoding=UTF-8";
        String user = "root";
        String password = "123456";
        // 批量插入数据
        String sql = "INSERT INTO teacher(id, name, course ,birthday) VALUES(?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql);) {
                // 设置参数
                for (int i = 0; i < 100; i++) {
                    ps.setInt(1, i+1);
                    ps.setString(2,"name"+i);
                    ps.setString(3, "课程"+i);
                    ps.setString(4, i % 2 == 0 ? "1998-01-01" : "1995-12-12");

                    // 添加到批处理
                    ps.addBatch();
                    if (i % 10 == 0) { // 每5条记录执行一次批处理
                        ps.executeBatch();
                        ps.clearBatch();
                    }
                }
                ps.executeBatch();
                conn.commit();
                System.out.println("完成批量插入数据");
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
