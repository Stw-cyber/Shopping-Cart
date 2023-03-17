
package controllers;

import DAL.Account;
import DAL.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.AccountDAO;

public class ProfileControl extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc2=(Account) req.getSession().getAttribute("AccSession");
        Customer cus=new AccountDAO().getCustomer(acc2.getCustomerID());
        req.getSession().setAttribute("cus", cus);
        req.getRequestDispatcher("../profile.jsp").forward(req, resp);
    }
}
