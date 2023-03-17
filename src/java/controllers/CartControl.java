package controllers;

import DAL.Account;
import DAL.CartItem;
import DAL.Customer;
import DAL.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.AccountDAO;
import models.CategoryDAO;

public class CartControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ///
        ArrayList<CartItem> listCartPro = (ArrayList<CartItem>) req.getSession().getAttribute("listCartProSession");
        int pid = Integer.parseInt(req.getParameter("pid"));
        Product p = new CategoryDAO().get1Pro(pid);

       
        if (listCartPro == null) {
            listCartPro = new ArrayList<CartItem>();
            req.getSession().setAttribute("listCartProSession", listCartPro);
        } else {
            listCartPro = (ArrayList<CartItem>) req.getSession().getAttribute("listCartProSession");
        }

        CartItem cartItem = new CategoryDAO().getCartItem(p, 1);
        int c = 0;
//        double totalMoney=0;
        for (CartItem item : listCartPro) { 
            if (cartItem.getP().getProductID().equals(item.getP().getProductID())) {
                item.setQuantity(item.getQuantity() + 1);
                c++;
            }
        }
        if (c == 0) {
            listCartPro.add(cartItem);
        }
        if (req.getParameter("action")!=null&&req.getParameter("action").equals("addCart")) {
            req.getRequestDispatcher("/detail.jsp?detailId="+pid).forward(req, resp);
        }else{
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
        }
    }
}

