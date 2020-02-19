package top.nrcynet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo02 {
public static void main(String[] args) {
		
		String url = "";
		String user = "";
		String password = "";
		
		String sql = "insert into test.employee(name,address,department_id) values(?,?,?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			//注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			
			//获取数据库连接对象
			conn = DriverManager.getConnection(url, user, password);
			//设置不自动提交事务
			conn.setAutoCommit(false);
			
			//获取数据库预处理操作对象
			ps = conn.prepareStatement(sql);
			
			
			ps.setString(1, "张伟");
			ps.setString(2, "北京市");
			ps.setInt(3, 2);
			
			ps.execute();
			
			//手动提交事务
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			try {
				//程序报错，事务回滚
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			//关闭资源
			
			if(ps != null) {
				try {
					ps.close();
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
