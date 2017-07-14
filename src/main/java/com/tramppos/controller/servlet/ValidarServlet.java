/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tramppos.controller.servlet;

import com.tramppos.domain.Pessoa;
import com.tramppos.service.PessoaService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author matheus
 */
public class ValidarServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String hash = request.getParameter("hash");
        
        Pessoa pessoa = new Pessoa();
        PessoaService pessoaService = new PessoaService();
        
        ///
        PrintWriter out = response.getWriter();
        
        if(email != null && hash != null){
            
            boolean resultado = pessoaService.validaCadastro(email, hash);
            
            if(resultado){
                
                pessoa = pessoaService.consult(email);
                
                 /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ValidarServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> Ola " + pessoa.getNome() + "</h1>");
                out.println("<h2> Agora voce esta pronto para acessar o tramppos "
                        + "e começar a procurar Profissionais para sua obra!! </h2>");
                out.println("</body>");
                out.println("</html>"); 
            }else{
                 /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ValidarServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> INVALIDO </h1>");
                out.println("</body>");
                out.println("</html>"); 
            }
                     
            
        }else{
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ValidarServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Email ou hash estao nulos</h1>");
            out.println("</body>");
            out.println("</html>"); 
        }     
        
//        response.setContentType("text/html;charset=UTF-8");
//        
//        
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ValidarServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ValidarServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
