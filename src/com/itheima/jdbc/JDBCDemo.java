package com.itheima.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Administrator
 *不规范的代码
 */
public class JDBCDemo {
     public static void main(String[] args)  {
    	 Connection conn=null;
		//通过jdbc去控制事务
    	 //1注册驱动
    	 try {
			Class.forName("com.mysql.jdbc.Driver");
			 //2获得connection
	    	  conn= DriverManager.getConnection("jdbc:mysql:///web19", "root", "root");
	    	 
	    	 //手动开启事务
	    	 conn.setAutoCommit(false);
	    	 //3获得执行平台
	    	 Statement stmt = conn.createStatement();
	    	 //4操作sql
	    	 stmt.executeUpdate("update account set money=5000 where name='tommm'");
	    	 stmt.executeUpdate("insert into account values(null,'wang9',3000)");
	    	 conn.commit();
	    	 stmt.close();
	    	 conn.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
    	
	}
}
