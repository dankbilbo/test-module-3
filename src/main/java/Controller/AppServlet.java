package Controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import Model.Category;
import Model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/app")
public class AppServlet extends HttpServlet {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    @Override
    public void init() throws ServletException {
        productDAO  = new ProductDAO();
        categoryDAO = new CategoryDAO();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "add":
                add(req,resp);
                break;
            case "edit":
                update(req,resp);
                break;
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int amount = Integer.parseInt(req.getParameter("amount"));
        String color = req.getParameter("color");
        String desc = req.getParameter("description");
        int cateId = Integer.parseInt(req.getParameter("category"));
        Product product = new Product(name,price,amount,color,desc,cateId);
        int result = productDAO.updateProduct(product);
        if (result > 0){
            req.setAttribute("message","success");
            RequestDispatcher dispatcher = req.getRequestDispatcher("app/edit.jsp");
            dispatcher.forward(req,resp);
        }else{
            req.setAttribute("message","edit not successfull");
            RequestDispatcher dispatcher = req.getRequestDispatcher("app/edit.jsp");
            dispatcher.forward(req,resp);
        }


    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int amount = Integer.parseInt(req.getParameter("amount"));
        String color = req.getParameter("color");
        String desc = req.getParameter("description");
        int cateId = Integer.parseInt(req.getParameter("category"));
        Product product = new Product(name,price,amount,color,desc,cateId);
        int result = productDAO.insertProduct(product);
        if (result > 0){
            req.setAttribute("message", "successful");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/app/add.jsp");
            dispatcher.forward(req,resp);
        }else {
            req.setAttribute("message", "not successful");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/app/add.jsp");
            dispatcher.forward(req,resp);
        }
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Product> products = productDAO.getAllProducts();
        req.setAttribute("listProduct",products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/app/products.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "add":
                showAddForm(req,resp);
                break;
            case "edit":
                showEditForm(req,resp);
                break;
            case "delete":
                    deleteProduct(req,resp);
                break;
            default:
                listProducts(req,resp);
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productDAO.deleteProduct(id);
        ArrayList<Product> products = productDAO.getAllProducts();
        req.setAttribute("listProduct",products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/app/products.jsp");
        dispatcher.forward(req,resp);

    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product existingProduct = productDAO.selectProduct(id);
        req.setAttribute("product", existingProduct);
        RequestDispatcher dispatcher = req.getRequestDispatcher("app/edit.jsp");
        req.setAttribute("user", existingProduct);
        dispatcher.forward(req, resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Category> categories = categoryDAO.selectAllCategories();
        req.setAttribute("allCategories",categories);
        RequestDispatcher dispatcher = req.getRequestDispatcher("app/add.jsp");
        dispatcher.forward(req,resp);
    }
}
