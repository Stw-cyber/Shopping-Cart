package controllers;

import DAL.Account;
import DAL.Category;
import DAL.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.CategoryDAO;

public class ProductControl extends HttpServlet {
private final int itemsPerPage = 10;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") != null && ((Account) req.getSession().getAttribute("AccSession")).getRole() == 1) {
//            ArrayList<Product> products = new CategoryDAO().getPro(4);
//            req.setAttribute("products", products);
            
            Integer pageQuantity = (Integer) req.getServletContext().getAttribute("pageQuantity");
        System.out.println(pageQuantity);
        if (pageQuantity == null) {
            pageQuantity = (int) Math.ceil(new CategoryDAO().getPro(4).size() / ((double) itemsPerPage));
            req.getServletContext().setAttribute("pageQuantity", pageQuantity);
        }
        
        int pageNumber = 1;
        try {
            pageNumber = Integer.parseInt(req.getParameter("page"));
        } catch (Exception e) {
        }
        if (pageNumber < 1 || pageNumber > pageQuantity) {
            resp.sendRedirect("product-list");
            return;
        }
        ArrayList<Category> categories = new CategoryDAO().getCategory();
        ArrayList<Product> products = new CategoryDAO().getProductsAtPage(pageNumber, itemsPerPage,1,1);
//        ArrayList<Category> categories = new CategoryDAO().getCategories();
//        Map<Integer, String> cateMap = new HashMap<>();
//        for (Category category : categories) {
//            cateMap.put(category.getCategoryID(), category.getCategoryName());
//        }
        
        req.setAttribute("products", products);
        req.setAttribute("categories", categories);
        req.setAttribute("cat", new CategoryDAO());
//        req.setAttribute("cateMap", cateMap);
        req.setAttribute("pageNumber", pageNumber);
            req.getRequestDispatcher("product.jsp").forward(req, resp);
        } else {
            resp.getWriter().print("Not allowed to access");
        }
    }
}
