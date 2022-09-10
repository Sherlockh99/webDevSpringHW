import products.Product;
import products.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
        this.productRepository.insert(new Product("Orange"));
        this.productRepository.insert(new Product("Apple"));
        this.productRepository.insert(new Product("Lemon"));
        this.productRepository.insert(new Product("Melon"));
        this.productRepository.insert(new Product("Water melon"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

/*
        String contextPath = request.getContextPath();
        response.getWriter().println("<p>request.getPathInfo(): " + pathInfo + "</p>");
        response.getWriter().println("<p>request.getContextPath(): " + contextPath + "</p>");
*/

        PrintWriter writer = response.getWriter();

        if(request.getPathInfo() == null) {
            writer.println("<table>");
            writer.println("<tr>");
            writer.println("<th>Id</th>");
            writer.println("<th>Name product</th>");
            writer.println("</tr>");

            for (Product product : productRepository.findAll()) {
                writer.println("<tr>");
                //writer.println("<td>" + product.getId() + "</td>");
                writer.println("<td><a href='" + getServletContext().getContextPath() + "/product/" + product.getId() + "'>" + product.getId() + "</a></td>");
                writer.println("<td>" + product.getNameProduct() + "</td>");
                writer.println("</tr>");
            }
        } else {

            try {
                int idProduct = Integer.parseInt(pathInfo.substring(1));
                Product product = productRepository.findById(idProduct);
                writer.println("<p>Product id: " + idProduct + "</p>");
                writer.println("<p>Product name: " + product.getNameProduct()+"</p");

            } catch (NumberFormatException n){
                writer.println("<p>Error product id: " + pathInfo.substring(1) + "</p>");
            }

        }
    }
}
