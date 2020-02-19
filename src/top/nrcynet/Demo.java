package top.nrcynet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {
	public static void main(String[] args) {
		
		String url = "";
		String user = "";
		String password = "";
		
		String sql = "select * from test.employee";
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			//注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			
			//获取数据库连接对象
			conn = DriverManager.getConnection(url, user, password);
			
			//获取数据库操作对象
			stat = conn.createStatement();
			
			//执行sql语句
			rs = stat.executeQuery(sql);
			
			//处理查询结果集
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				int departmentId = rs.getInt("department_id");
				System.out.println(id + "\t" + name + "\t" + address + "\t" + departmentId);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			//关闭资源
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
}
