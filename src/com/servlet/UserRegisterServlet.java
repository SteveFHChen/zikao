package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/service/registeruser")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name; 
		String photoFileName="xxx.jpg";
		
		req.setCharacterEncoding("utf-8");
		
		//parse request body with this format: enctype="multipart/form-data"
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List items = null;
		try {
			items = upload.parseRequest(req);
		}catch(Exception e) {
			e.printStackTrace();
		}
		Iterator it = items.iterator();
		while(it.hasNext()) {
			FileItem item = (FileItem)it.next();
			if(item.isFormField()) {//parameter
				String fieldName = item.getFieldName();
				String value = item.getString();
				System.out.println("Parameter: " + item.getFieldName() +" - "+ item.getString());
				req.setAttribute(fieldName, value);
				
				if("photoFileName".equals(fieldName)) {photoFileName = value;}
			}else {//File format
				String fileName = item.getName();
				String basePath = req.getSession().getServletContext().getRealPath("/class");
				System.out.println("Parameter: " + fileName +" - "+ basePath);
				File file = new File(basePath, photoFileName);
				try {
					item.write(file);//If file exists, it will be covered directly. Be careful!
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		//parse request body with key-value format: 
		System.out.println("Output parameters: ");
		Enumeration<String> names = req.getParameterNames();
		while(names.hasMoreElements()) {
			name = names.nextElement();
			System.out.println(name + ": " + req.getParameter(name));
		}
		
		System.out.println("Output attributes: ");
		names = req.getAttributeNames();
		while(names.hasMoreElements()) {
			name = names.nextElement();
			System.out.println(name + ": " + req.getAttribute(name));
		}
		
		res.setCharacterEncoding("utf-8");
		res.getWriter().append("Received request. Pending implemented");
	}

}
