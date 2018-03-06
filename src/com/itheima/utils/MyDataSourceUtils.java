package com.itheima.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MyDataSourceUtils {
	//获得Connection---从连接池获取
	private static ComboPooledDataSource dataSource=new ComboPooledDataSource();
	//创建ThreadLocal
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	
	/**
	 * 开启事务
	 * @throws SQLException 
	 */
	public static void startTransaction() throws SQLException{
		Connection conn = getCurrentConnection();
		conn.setAutoCommit(false);
	}
	public static void rollback() throws SQLException{
		getCurrentConnection().rollback();
	}
	public static void commit() throws SQLException{
		Connection conn = getCurrentConnection();
		conn.commit();
		//将connection从TreadLocal中移除
		tl.remove();
		conn.close();
		
	}
	/**
	 * 获得当前线程上绑定的connection
	 * @return
	 * @throws SQLException
	 */
	public static Connection getCurrentConnection() throws SQLException{
		//先从ThreadLocal寻找当前线程是否有对应Connection
		Connection conn = tl.get();
		if(conn==null){
			//获得新的connection
			conn=getConnection();
			//将conn资源绑定到ThreadLocal（map）上
			tl.set(conn);
		}
		return conn;
	}
}
