package controllers;

import DAL.Account;
import DAL.Order;
import DAL.OrderDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.CategoryDAO;

public class DashboardControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") != null && ((Account) req.getSession().getAttribute("AccSession")).getRole() == 1) {
            
            
            double weeklySale=new CategoryDAO().getweeklySale();
            double totalOrder=new CategoryDAO().getTotalOrder();
            int cus=new CategoryDAO().getTotalCus();
            int cusNew=new CategoryDAO().getCountNewCus();
            int guest=new CategoryDAO().getTotalGuest();
            ArrayList<Integer> year=new CategoryDAO().getYear();
            
                req.setAttribute("weeklySale", (double) Math.round(weeklySale/1000 * 100) / 100);
                req.setAttribute("totalOrder",(double) Math.round(totalOrder/1000 * 100) / 100);
                req.setAttribute("cus",cus);
                req.setAttribute("cusNew",cusNew);
                req.setAttribute("guest",guest);
                req.setAttribute("year",year);
                if (req.getParameter("Year")!=null) {
                req.setAttribute("Year",req.getParameter("Year"));
            }
                
                req.setAttribute("dao",new CategoryDAO());
            
            
            req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
        } else {
            resp.getWriter().print("Not allowed to access");
        }

    }

}
