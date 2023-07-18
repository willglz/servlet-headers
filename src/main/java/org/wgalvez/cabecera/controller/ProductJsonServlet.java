package org.wgalvez.cabecera.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
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

@WebServlet("/products.json")
public class ProductJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductServiceImpl();
        List<Product> products = productService.showProducts();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(products);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(jsonStream, Product.class);
        resp.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = resp.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Products details from JSON</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Products details from JSON</h1>");
            out.println("<ul>");
            out.println("<li>" + product.getId() + "</li>");
            out.println("<li>" + product.getName() + "</li>");
            out.println("<li>" + product.getBrand() + "</li>");
            out.println("<li>" + product.getPrice() + "</li>");
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
