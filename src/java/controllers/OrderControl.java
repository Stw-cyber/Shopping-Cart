package controllers;

import DAL.Account;
import DAL.Customer;
import DAL.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import models.AccountDAO;
import models.CategoryDAO;
import models.MyGeneric;

public class OrderControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") != null && ((Account) req.getSession().getAttribute("AccSession")).getRole() == 1) {
            String fromDate = req.getParameter("txtStartOrderDate");
            String toDate = req.getParameter("txtEndOrderDate");
            String sql;
            if (toDate == null) {
                toDate = Date.valueOf(LocalDate.now()).toString();
            }
//            int pageTh;
//            try {
//                pageTh = Integer.parseInt(req.getParameter("page"));
//            } catch (Exception e) {
//                pageTh = 1;
//            }

            if (fromDate != null) {
                if (!fromDate.isEmpty()) {
                    sql = "select * from Orders\n"
                            + "where OrderDate between '" + fromDate + "' and '" + toDate + "' ";
                } else {
                    sql = "select * from Orders order by OrderDate desc";
                }
            } else {
                sql = "select * from Orders order by OrderDate desc";
            }

            ArrayList<Order> orderList = new CategoryDAO().getOrdersBySqlQurey(sql);

            int pageQuantity = new CategoryItemController().getpageQuantity(req, resp, orderList.size());
            int pageNumber = 1;
            try {
                pageNumber = Integer.parseInt(req.getParameter("page"));
            } catch (Exception e) {
            }
            if (pageNumber < 1 || pageNumber > pageQuantity) {
                resp.sendRedirect("manage-order");
//response.getWriter().print(pageNumber);
                return;
            }
            req.setAttribute("pageNumber", pageNumber);
            ArrayList<Order> Order = new MyGeneric<Order>(orderList).dataPaging(pageNumber, 8);
//            ArrayList<Product> products = new CategoryDAO().getProductsAtPage(pageNumber, 8,1,1);
            req.setAttribute("Orders", Order);

            req.setAttribute("fromDate", fromDate);
            req.setAttribute("toDate", toDate);
            req.setAttribute("orderList", orderList);
            req.setAttribute("dao", new AccountDAO());

            req.getRequestDispatcher("order.jsp").forward(req, resp);
        } else {
            resp.getWriter().print("Not allowed to access");
        }
    }
}
