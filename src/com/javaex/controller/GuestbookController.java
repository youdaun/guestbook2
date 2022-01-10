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
import com.javaex.vo.GuestbookVo;

@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String act = request.getParameter("action");

		if ("addlist".equals(act)) {
			System.out.println("action=addlist");
			GuestbookDao gDao = new GuestbookDao();
			List<GuestbookVo> gList = gDao.getList();

			request.setAttribute("gList", gList);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);
			
		} else if ("add".equals(act)) {
			System.out.println("action=add");
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestbookDao gDao = new GuestbookDao();
			GuestbookVo gvo = new GuestbookVo(name, password, content);
			gDao.guestInsert(gvo);
			
			response.sendRedirect("/guestbook2/gbc?action=addlist");


		} else if ("deleteform".equals(act)) {
			System.out.println("action=deleteform");
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			rd.forward(request, response); 	
			
		} else if ("delete".equals(act)) {
			System.out.println("action=delete");
			
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");

			GuestbookDao gbDao = new GuestbookDao();

			gbDao.guestDelete(no, password);
			response.sendRedirect("/guestbook2/gbc?action=addlist");
			
		} else {
			System.out.println("파라미터값 없음");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
