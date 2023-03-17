package controllers;

import DAL.Account;
import DAL.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import models.AccountDAO;

public class SignupControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") == null) {
            req.getRequestDispatcher("../signup.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("../index.jsp");
        }
        //req.getRequestDispatcher("../signup.jsp").forward(req, resp);
    }

    private String randomString(int length) {
        String s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String str = "";
        Random rd = new Random();
        for (int i = 0; i < length; i++) {
            int j = rd.nextInt(s.length() - 1);
            str += s.charAt(j);
        }

        return str;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String CustomerID = randomString(5);
        String CompanyName = req.getParameter("txtCompany");
        String Email = req.getParameter("txtEmail");
        String Password = req.getParameter("txtPass");

        String ContactName = req.getParameter("txtContactN");
        String ContactTitle = req.getParameter("txtContactT");
        String Address = req.getParameter("txtAddress");
        String RePassword = req.getParameter("txtRePass");

        String msgCompanyName = "";
        String msgEmail = "";
        String msgPassword = "";
        String msgContactName = "";
        String msgContactTitle = "";
        String msgAddress = "";
        String msgRePassword = "";
        String msgEmailForm="";
        
        if (CompanyName.equals("")) {
            msgCompanyName = "Company name is required";
            req.setAttribute("msgCompanyName", msgCompanyName);
        }
        if (Email.equals("")) {
            msgEmail = "Email is required";
            req.setAttribute("msgEmail", msgEmail);
        }
        if (!Email.equals("")&&!Email.matches("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]+(.[a-zA-Z]+){1,3}$")) {
            msgEmailForm = "Enter an email please.";
            req.setAttribute("msgEmailForm", msgEmailForm);
        }
        if (Password.equals("")) {
            msgPassword = "Password is required";
            req.setAttribute("msgPassword", msgPassword);
        }
        if (ContactName.equals("")) {
            msgContactName = "ContactName is required";
            req.setAttribute("msgContactName", msgContactName);
        }
        if (ContactTitle.equals("")) {
            msgContactTitle = "ContactTitle is required";
            req.setAttribute("msgContactTitle", msgContactTitle);
        }
        if (Address.equals("")) {
            msgAddress = "Address is required";
            req.setAttribute("msgAddress", msgAddress);
        }
        if (RePassword.equals("")) {
            msgRePassword = "RePassword is required";
            req.setAttribute("msgRePassword", msgRePassword);
        }
        if (!msgCompanyName.equals("") || !msgEmail.equals("") || !msgPassword.equals("") || !msgContactName.equals("") || !msgContactTitle.equals("") || !msgAddress.equals("") || !msgRePassword.equals("")||!msgEmailForm.equals("")) {
            //
            req.setAttribute("CompanyName", CompanyName);
            req.setAttribute("Email", Email);
            req.setAttribute("Password", Password);
            req.setAttribute("ContactName", ContactName);
            req.setAttribute("ContactTitle", ContactTitle);
            req.setAttribute("Address", Address);
            req.setAttribute("RePassword", RePassword);
            //
            req.getRequestDispatcher("../signup.jsp").forward(req, resp);
        } else {
            Customer cust = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address);
            //(CustomerID, CompanyName, "", "", "")
            Account acc = new Account(0, Email, Password, CustomerID, 0, 2);
            //(0, Email, Password, CustomerID, 0, 0)

            if (!Password.equals(RePassword)) {
                req.setAttribute("msg2", "Password and RePassword does not match. Try again!");
                req.setAttribute("CompanyName", CompanyName);
            req.setAttribute("Email", Email);
            req.setAttribute("Password", Password);
            req.setAttribute("ContactName", ContactName);
            req.setAttribute("ContactTitle", ContactTitle);
            req.setAttribute("Address", Address);
                req.getRequestDispatcher("../signup.jsp").forward(req, resp);
            } else {
//            Account account=new AccountDAO().addAccount(CompanyName, ContactName, ContactTitle, Address, Email, Password);

                //dieu huong toi index.jsp
//            req.getRequestDispatcher("../signin.jsp").forward(req, resp);
                if (new AccountDAO().AddAccount(acc, cust) > 0) {
                    resp.sendRedirect("../account/signin");
                    req.getSession().setAttribute("CusSession", cust);
                } else {
                    req.getRequestDispatcher("../signup.jsp").forward(req, resp);
//resp.getWriter().println("adsfdg");
                }
            }
        }

    }

}
