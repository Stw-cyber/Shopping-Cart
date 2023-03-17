/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAL.Account;
import DAL.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.AccountDAO;
import models.CategoryDAO;

/**
 *
 * @author Admin
 */
public class EditProfileControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer cus = new AccountDAO().getCustomer(((Account) req.getSession().getAttribute("AccSession")).getCustomerID());
        req.setAttribute("cus", cus);
        req.getRequestDispatcher("editProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyName = req.getParameter("txtCompanyName");
        String contactName = req.getParameter("txtContact");
        String contactTitle = req.getParameter("txtComTitle");
        String address = req.getParameter("txtAddress");
        Customer c = new Customer(((Account) req.getSession().getAttribute("AccSession")).getCustomerID(), companyName, contactName, contactTitle, address);
        if (new CategoryDAO().updateCustomer(c) != 0) {
            resp.sendRedirect(req.getContextPath() + "/account/profile");
        } else {
//            resp.getWriter().write("Fail to update!");
        }
    }
}
