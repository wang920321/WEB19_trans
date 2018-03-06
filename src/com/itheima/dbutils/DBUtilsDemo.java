package com.itheima.dbutils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.itheima.utils.DataSourceUtils;


public class DBUtilsDemo {
    public static void main(String[] args) {
    	Connection conn=null;
	
	try {
		QueryRunner runner=new QueryRunner();
	
		
		//runner.update("update account set money=9000 where name='tom'");
	    //获得一个connection
		 conn = DataSourceUtils.getConnection();
			//开启事务
		 conn.setAutoCommit(false);
		runner.update(conn, "update account set money=9000 where name='tom'");
		//提交或回滚事务
		conn.commit();
	} catch (SQLException e) {
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
