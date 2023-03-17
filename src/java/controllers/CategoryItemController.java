
package controllers;

import DAL.Category;
import DAL.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import models.CategoryDAO;

public class CategoryItemController extends HttpServlet {
    private final int itemsPerPage = 8;
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //processRequest(request, response);
        int catID = Integer.parseInt(request.getParameter("cid"));
//        CategoryDAO cdb = new CategoryDAO();
        Category category = new CategoryDAO().get(catID);
        ArrayList<Product> productList=new CategoryDAO().getProByCatID(catID);
        ArrayList<Category> listCat=new CategoryDAO().getCategory();
        request.setAttribute("category", category);
        request.setAttribute("productList", productList);
        request.setAttribute("listCat", listCat);
        
        ////////////////////
        int pageQuantity=getpageQuantity(request, response,new CategoryDAO().getProByCatID(catID).size());
        ///
        
        int pageNumber = 1;
        try {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
        }
        if (pageNumber < 1 || pageNumber > pageQuantity) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        ArrayList<Product> products = new CategoryDAO().getProductsAtPage(pageNumber, itemsPerPage,catID,2);
//        ArrayList<Category> categories = new CategoryDAO().getCategory();
//        Map<Integer, String> cateMap = new HashMap<>();
//        for (Category cat : categories) {
//            cateMap.put(cat.getCategoryID(), cat.getCategoryName());
//        }
        request.setAttribute("products", products);
        request.setAttribute("pageNumber", pageNumber);
//        request.setAttribute("cateMap", cateMap);
        //
        request.getRequestDispatcher("category.jsp").forward(request, response);
    } 


    public int getpageQuantity(HttpServletRequest request, HttpServletResponse response, int size) {
        Integer pageQuantity = (Integer) request.getAttribute("pageQuantity");
//        System.out.println(pageQuantity);
        if (pageQuantity == null) {
            pageQuantity = (int) Math.ceil(size / ((double) itemsPerPage));
            request.setAttribute("pageQuantity", pageQuantity);
        }
        return pageQuantity;
    }
}
