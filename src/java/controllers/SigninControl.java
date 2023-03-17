package controllers;

import DAL.Account;
import DAL.Category;
import DAL.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.AccountDAO;
import models.CategoryDAO;

public class SigninControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        if (req.getSession().getAttribute("AccSession") != null) {
            req.getSession().removeAttribute("listCartProSession");
            req.getSession().removeAttribute("AccSession");
            resp.sendRedirect("../index.jsp");
        } else {
            
            req.getRequestDispatcher("../signin.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("listCartProSession") != null) {
            req.getSession().removeAttribute("listCartProSession");
        }
        String email = req.getParameter("txtEmail");
        String pass = req.getParameter("txtPass");

        String msgEmail = "";
        String msgPass = "";

        if (email.equals("")) {
            msgEmail = "Email is required";
            req.setAttribute("msgEmail", msgEmail);
        }
        if (pass.equals("")) {
            msgPass = "Password is required";
            req.setAttribute("msgPass", msgPass);
        }
        if (!msgEmail.equals("") || !msgPass.equals("")) {
            req.setAttribute("email", email);
                req.setAttribute("pass", pass);
            req.getRequestDispatcher("../signin.jsp").forward(req, resp);
        } else {
            Account acc = new AccountDAO().getAccount(email, pass);
            
            if (acc != null) {
                //cap session
                req.getSession().setAttribute("AccSession", acc);
                
                if (acc.getRole()==1) {
                    resp.sendRedirect("../manage-dashboard");
                }else{
                    resp.sendRedirect("../index.jsp");
                }
//                req.getSession().setAttribute("AccSession", acc);
                
                //dieu huong toi index.jsp
                
            } else {
                //dong goi thong diep loioxx vè doGet (login.jsp)
                req.setAttribute("msg", "This account does not exist");
                
                
                req.getRequestDispatcher("../signin.jsp").forward(req, resp);
            }
        }
//        resp.getWriter().print(email+", "+pass);

    }

}
