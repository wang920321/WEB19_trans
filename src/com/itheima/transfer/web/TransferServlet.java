package com.itheima.transfer.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.transfer.service.TransferService;

public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String out = req.getParameter("out");
		String in = req.getParameter("in");
		String moneyStr = req.getParameter("money");
		TransferService service=new TransferService();
		boolean transferIsSeccess = service.transfer(out, in, Double.parseDouble(moneyStr));
		res.setContentType("text/html;charset=UTF-8");
		if(transferIsSeccess){
			res.getWriter().write("转账成功");
		}else{
			res.getWriter().write("转账失败");
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}