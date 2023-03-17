package controllers;

import DAL.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.CategoryDAO;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Category> list = new CategoryDAO().getCategory();
        req.setAttribute("list", list);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        

    }
    
}
