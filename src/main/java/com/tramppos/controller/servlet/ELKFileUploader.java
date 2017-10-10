/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ELKFileUploader
 */
@MultipartConfig
@WebServlet(urlPatterns = { "/paginas/cliente/ELKFileUploader" })

public class ELKFileUploader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ELKFileUploader() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Part file = request.getPart("file");
            String filename = getFilename(file);
            InputStream filecontent = file.getInputStream();        

            FileOutputStream outputStream =  new FileOutputStream(new File("/home/matheus/uploadTramppos/" + filename));

            int read = 0;
                    byte[] bytes = new byte[1024];

                    while ((read = filecontent.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, read);
                    }

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("File " + filename + " successfully uploaded");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}