package com.tramppos.autentica;

import java.io.IOException;
import javax.faces.application.ResourceHandler;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(servletNames = { "Faces Servlet" })
public class ControleDeAcesso implements Filter {
    
    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        
        
        //AQUI ****** :   /Web/cliente/javax.faces.resource
        //System.out.println("AQUI ****** :   " + req.getContextPath() +"/cliente"+ ResourceHandler.RESOURCE_IDENTIFIER);
        

        if ((session.getAttribute("logAdm") != null)              
                    || (req.getRequestURI().endsWith("login.xhtml"))  
                    || (req.getRequestURI().endsWith("teste.xhtml")) 
                    || (req.getRequestURI().endsWith("index.xhtml"))
                    || (req.getRequestURI().endsWith("valida.xhtml"))
                    || (req.getRequestURI().endsWith("valida"))
                    || (req.getRequestURI().endsWith("erroacesso.xhtml"))
                    || (req.getRequestURI().startsWith(req.getContextPath() +"/paginas/cadastro.xhtml"))
                    || (req.getRequestURI().contains("javax.faces.resource/"))) 
        {
                       
            //redireciona("/Logado.xhtml", response);

            chain.doFilter(request, response);
        }
        else {   
            
            if((session.getAttribute("logPessoa") != null && req.getRequestURI().startsWith(req.getContextPath() +"/paginas/pessoa"))){
                chain.doFilter(request, response);
                System.out.println("Esta como pessoa!!!!!!");
            }else{
                if((session.getAttribute("logProf") != null && req.getRequestURI().startsWith(req.getContextPath() +"/paginas/profissional"))
                    ||(session.getAttribute("logProf") != null && req.getRequestURI().startsWith(req.getContextPath() +"/paginas/pessoa"))){
                     chain.doFilter(request, response);
                     
                 }
                 else{
                     if(session.getAttribute("logCliente") != null && req.getRequestURI().startsWith(req.getContextPath() +"/paginas/cliente")
                        ||(session.getAttribute("logCliente") != null && req.getRequestURI().startsWith(req.getContextPath() +"/paginas/pessoa"))){
                         chain.doFilter(request, response);
                         System.out.println("Esta como cliente!!!!!!");
                     }else{
                         System.out.println("Esta aqui!!!!!!");
                         redireciona("/Web/paginas/index.xhtml", response);
                     }
                 }        
            }
                
        }

    }
    
    

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }

    private void redireciona(String url, ServletResponse response)
                    throws IOException {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect(url);
    }
}