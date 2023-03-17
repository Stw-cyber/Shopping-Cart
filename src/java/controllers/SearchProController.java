package controllers;

import DAL.Category;
import DAL.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import models.CategoryDAO;

public class SearchProController extends HttpServlet {

    private final int itemsPerPage = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = "";
        if (!req.getParameter("keyword").equals(null)) {
            keyword = req.getParameter("keyword");
        }
        int catID = 0;
        if (req.getParameter("CategoryID") != null) {
            catID = Integer.parseInt(req.getParameter("CategoryID"));
        }
        Category category=null;
        ArrayList<Product> products=null;
        if (catID != -1) {
             category = new CategoryDAO().get(catID);
              products = new CategoryDAO().getProductsByKeyword2(keyword, catID);
        }else{
             products = new CategoryDAO().getProductsByKeyword(keyword);
        }

        
        int pageQuantity = (int) Math.ceil(products.size() / ((double) itemsPerPage));
        int pageNumber = 1;
        try {
            pageNumber = Integer.parseInt(req.getParameter("page"));
        } catch (Exception e) {
        }
// || keyword == null || keyword.trim().isEmpty()
//        if (pageNumber < 1 || pageNumber > pageQuantity) {
//            resp.sendRedirect("product-list");
//            resp.getWriter().print("kkk");
//            return;
//        }

        ArrayList<Category> categories = new CategoryDAO().getCategory();
//        Map<Integer, String> cateMap = new HashMap<>();
//        for (Category category : categories) {
//            cateMap.put(category.getCategoryID(), category.getCategoryName());
//        }
        ArrayList<Product> productsInPage = new ArrayList<>();
        int index = (pageNumber - 1) * itemsPerPage;
        while (index < products.size() && index < (pageNumber - 1) * itemsPerPage + 10) {
            productsInPage.add(products.get(index));
            index++;
        }
        req.setAttribute("cat", new CategoryDAO());
        req.setAttribute("category", category);
        req.setAttribute("CategoryID", catID);
        req.setAttribute("categories", categories);
        req.setAttribute("products", productsInPage);
        req.setAttribute("keyword", keyword);
//        req.setAttribute("cateMap", cateMap);
        req.setAttribute("pageQuantity", pageQuantity);
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("option", "search");
        req.getRequestDispatcher("product.jsp").forward(req, resp);
    }

}
