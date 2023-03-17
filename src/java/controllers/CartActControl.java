package controllers;

import DAL.Account;
import DAL.CartItem;
import DAL.Customer;
import DAL.Order;
import DAL.OrderDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import models.AccountDAO;
import models.CategoryDAO;

public class CartActControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<CartItem> listCartPro = (ArrayList<CartItem>) req.getSession().getAttribute("listCartProSession");
        int id = Integer.parseInt(req.getParameter("id"));

        for (CartItem item : listCartPro) {
            if (item.getP().getProductID() == id) {
                if (req.getParameter("action").equals("plus")) {
                    item.setQuantity(item.getQuantity() + 1);
                }
                if (req.getParameter("action").equals("minus")) {
                    item.setQuantity(item.getQuantity() - 1);
                    if (item.getQuantity() == 0) {
                        listCartPro.remove(listCartPro.indexOf(item));
                        break;
                    }
                }
                if (req.getParameter("action").equals("remove")) {
                    listCartPro.remove(listCartPro.indexOf(item));
                    break;
                }
            }
        }
        req.getSession().setAttribute("listCartProSession", listCartPro);
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("listCartProSession") != null) {
            Order(req, resp);
        } else {
            resp.sendRedirect("../index.jsp");
        }
    }

    private void Order(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateNow=new java.util.Date();
        String CompanyName = req.getParameter("compName");
        String ContactName = req.getParameter("contName");
        String ContactTitle = req.getParameter("titName");
        String Address = req.getParameter("addName");
        String RequiredDate = req.getParameter("txtDate");
        
        String msgCompanyName = "";
        String msgContactName = "";
        String msgContactTitle = "";
        String msgAddress = "";
        String msgRequiredDate = "";
        String msgCompareDate = "";

        if (CompanyName.equals("")) {
            msgCompanyName = "CompanyName is required";
            req.setAttribute("msgCompanyName", msgCompanyName);
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
        if (RequiredDate.equals("")) {
            msgRequiredDate = "RequiredDate is required";
            req.setAttribute("msgRequiredDate", msgRequiredDate);
        }
        if (!RequiredDate.equals("")&&Date.valueOf(RequiredDate).compareTo(dateNow)<0) {
            msgCompareDate = "RequiredDate had to > time now";
            req.setAttribute("msgCompareDate", msgCompareDate);
        }
        if (!msgCompareDate.equals("")||!msgCompanyName.equals("") || !msgContactName.equals("") || !msgContactTitle.equals("") || !msgAddress.equals("") || !msgRequiredDate.equals("")) {
            req.setAttribute("CompanyName", CompanyName);
            req.setAttribute("ContactName", ContactName);
            req.setAttribute("ContactTitle", ContactTitle);
            req.setAttribute("Address", Address);
//            Date d = Date.valueOf(RequiredDate);
            if (!RequiredDate.equals("")) {
                req.setAttribute("RequiredDate", Date.valueOf(RequiredDate));
            }
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
        } else {
            ////
            Order o = null;
            Customer c = null;
            ArrayList<CartItem> listCartPro = (ArrayList<CartItem>) req.getSession().getAttribute("listCartProSession");
            Account AccSession = (Account) req.getSession().getAttribute("AccSession");

            if (AccSession != null) {
                o = new Order(0, AccSession.getCustomerID(), Date.valueOf(LocalDate.now()), Date.valueOf(RequiredDate), Address);
            } else {
                do {
                    c = new Customer(randomID(5), CompanyName, ContactName, ContactTitle, Address);
                } while (new CategoryDAO().addCustomer(c) == 0);
                o = new Order(0, c.getCustomerID(), Date.valueOf(LocalDate.now()), Date.valueOf(RequiredDate), Address);
            }

            o = new CategoryDAO().addOrder(o);
//            boolean success = false;
            ArrayList<OrderDetail> OrderDetailList = new ArrayList<OrderDetail>();
            for (CartItem item : listCartPro) {
                OrderDetail od = new OrderDetail(o.getOrderID(), item.getP().getProductID(), item.getP().getUnitPrice(), item.getQuantity(), 0);
                item.getP().setUnitPrice(od.getQuantity() * od.getUnitPrice() - od.getQuantity() * od.getUnitPrice() * od.getDiscount());
                OrderDetailList.add(od);
            }
            int k=0;
            for (OrderDetail item : OrderDetailList) {
                k = new CategoryDAO().addOrderDetail(item);
            }

            if (k!=0) {
                req.getSession().removeAttribute("listCartProSession");
                if (AccSession != null) {
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                } else {
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
//resp.getWriter().print("sss");
                }
            } else {
                req.getRequestDispatcher("cart.jsp").forward(req, resp);
            }
        }

    }

    private String randomID(int length) {
        String s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String str = "";
        Random rd = new Random();
        for (int i = 0; i < length; i++) {
            int j = rd.nextInt(s.length() - 1);
            str += s.charAt(j);
        }

        return str;
    }

}
