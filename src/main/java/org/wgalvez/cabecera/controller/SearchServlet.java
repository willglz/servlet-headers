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
import java.util.Optional;

@WebServlet("/search-product")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductServiceImpl();
        String product = req.getParameter("product");
        Optional<Product> find = productService.getProduct(product);
        if (find.isPresent()){
            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html lang='en'>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                out.println("<title>Product </title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Product find it</h1>");
                out.println("<h3>Name: " + find.get().getName() + "</h3>");
                out.println("<h3>Price: " + find.get().getPrice() + "</h3>");
                out.println("</body>");
                out.println("</html>");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "product not found");
        }
    }
}
