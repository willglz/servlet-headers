package org.wgalvez.cabecera.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabecera-request")
public class CabeceraServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String methodHttp = req.getMethod();
        String requestUri = req.getRequestURI();
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ip = req.getLocalAddr();
        String port = String.valueOf(req.getLocalPort());
        String scheme = req.getScheme();
        String host = req.getHeader("host");
        String url2 = scheme + "://" + host + contextPath + servletPath;
        String url3 = scheme + "://" + ip + ":" + port + contextPath + servletPath;
        String ipClient = req.getRemoteAddr();

        try (PrintWriter out = resp.getWriter();) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Cabeceras HTTP Request</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Form Result</h1>");
            out.println("<ul>");
            out.println("<li>Method HTTP: " + methodHttp + "</li>");
            out.println("<li>Request URI: " + requestUri + "</li>");
            out.println("<li>Request URL" + requestUrl + "</li>");
            out.println("<li>Context Path" + contextPath + "</li>");
            out.println("<li>Servlet Path: " + servletPath + "</li>");
            out.println("<li>Server IP: " + ip + "</li>");
            out.println("<li>Server Port: " + port+ "</li>");
            out.println("<li>Scheme: " + scheme + "</li>");
            out.println("<li>URL 2: " + url2 + "</li>");
            out.println("<li>URL 3: " + url3 + "</li>");
            out.println("<li>IP Cliente: " + ipClient + "</li>");

            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()){
                String cabecera = headerNames.nextElement();
                out.println("<li>" + cabecera + ": " + req.getHeader(cabecera) + "</li>");
            }
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
