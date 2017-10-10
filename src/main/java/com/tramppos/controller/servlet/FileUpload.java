/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.servlet;

import com.tramppos.domain.Cliente;
import com.tramppos.domain.Foto;
import com.tramppos.service.FotoService;
import com.tramppos.util.jsf.SessionUtil;
import com.tramppos.util.upload.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(urlPatterns = { "/paginas/cliente/FileUpload" })
public class FileUpload extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
//    private Image image;
//    private Cliente cliente;
//    private Foto foto;
//    private FotoService fotoService;
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        
        
//        this.cliente =  (Cliente) SessionUtil.getParam("logCliente");
//        this.foto = new Foto();
//        this.fotoService = new FotoService();
//        this.foto.setPessoa(cliente); 
//        System.out.println("ENTROU NO FILEEUPLOAD");
        
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Foto foto = new Foto();
        
//        Cliente cliente = (Cliente) SessionUtil.getParam("logCliente");
////        Cliente cliente = new Cliente();
////        cliente.setId(23);
//        foto.setPessoa(cliente); 
        
        FotoService fotoService = new FotoService();        
        
        Part file = request.getPart("file");
        String filename = getFilename(file);
        
//        fotoService.upImagemTmp(file, foto);

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