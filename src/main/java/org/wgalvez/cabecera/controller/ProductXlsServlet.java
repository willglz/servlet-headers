package org.wgalvez.cabecera.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.wgalvez.cabecera.model.Product;
import org.wgalvez.cabecera.service.ProductService;
import org.wgalvez.cabecera.service.ProductServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/products.xls", "/products.html"})
public class ProductXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductServiceImpl();
        List<Product> productList = productService.showProducts();
        resp.setContentType("text/html;charset=UTF-8");
        String servletPath = req.getServletPath();
        boolean isXls = servletPath.endsWith(".xls");
        if (isXls){
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=products.xls");
        }
        try(PrintWriter out = resp.getWriter()){
            if (!isXls){
                out.println("<!DOCTYPE html>");
                out.println("<html lang='en'>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                out.println("<title>Product List</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Product List</h1>");
                out.println("<p><a href='" + req.getContextPath() + "/products.xls'>Export to Excel</a></p>");
            }

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Brand</th>");
            out.println("<th>Price</th>");
            out.println("</tr>");
            productList.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() +"</td>");
                out.println("<td>" + p.getName() +"</td>");
                out.println("<td>" + p.getBrand() +"</td>");
                out.println("<td>" + p.getPrice() +"</td>");
                out.println("</tr>");
            });
            out.println("</table>");
            if (!isXls){
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}
