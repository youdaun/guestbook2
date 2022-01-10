package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;

@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String act = request.getParameter("action");

		 if ("add".equals(act)) {
			System.out.println("action=add");
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestbookDao gDao = new GuestbookDao();
			GuestbookVo gvo = new GuestbookVo(name, password, content);
			gDao.guestInsert(gvo);
			
			WebUtil.redirect(response, "/guestbook2/gbc");

		} else if ("deleteform".equals(act)) {
			System.out.println("action=deleteform");
			
			WebUtil.forword(request, response, "/WEB-INF/deleteForm.jsp");
			
		} else if ("delete".equals(act)) {
			System.out.println("action=delete");
			
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");

			GuestbookDao gbDao = new GuestbookDao();

			gbDao.guestDelete(no, password);

			WebUtil.redirect(response, "/guestbook2/gbc");
			
		} else {
			System.out.println("action=addlist");
			GuestbookDao gDao = new GuestbookDao();
			List<GuestbookVo> gList = gDao.getList();

			request.setAttribute("gList", gList);

			WebUtil.forword(request, response, "/WEB-INF/addList.jsp");
			
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
