package com.itheima.transfer.service;

import java.sql.SQLException;

import com.itheima.transfer.dao.TransferDao;
import com.itheima.utils.MyDataSourceUtils;

public class TransferService {
    public boolean transfer(String out,String in,Double money){
    	TransferDao dao=new TransferDao();
    	boolean isTranferSuccess=true;
    	//Connection conn=null;
    	try {
    		//conn = DataSourceUtils.getConnection();
			//conn.setAutoCommit(false);
    		
    		//开启事务
    		MyDataSourceUtils.startTransaction();
			//转出钱的方法
			dao.out(out,money);
			int i=1/0;
			//转入钱的方法
	    	dao.in(in,money);
	    	
		} catch (Exception e1) {
			isTranferSuccess=false;
			try {
				MyDataSourceUtils.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//conn.rollback();
			/*try {
				isTranferSuccess=false;
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			e1.printStackTrace();
		}finally{
			try {
				MyDataSourceUtils.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    	
    	
		return isTranferSuccess;
    	
    }
}
