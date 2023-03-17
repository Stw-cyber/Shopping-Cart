package controllers;

import DAL.Category;
import DAL.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import models.CategoryDAO;

public class CRUDControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            if (action.equals("edit")) {
                exeEdit(req, resp);
            }
            if (action.equals("create")) {
                exeCreate(req, resp);
            }
            if (action.equals("delete")) {
//                resp.getWriter().print("kkk");
                exeDelete(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("edit")) {
            edit(req, resp);
        }
        if (action.equals("delete")) {
            delete(req, resp);
        }
        if (action.equals("create")) {
            create(req, resp);
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Product product = new CategoryDAO().get1Pro(id);
        ArrayList<Category> categories = new CategoryDAO().getCategory();
        if (product != null) {
            req.setAttribute("product", product);
            req.setAttribute("categories", categories);
            req.setAttribute("option", "edit");
            req.setAttribute("id", id);
            req.getRequestDispatcher("create-edit-product.jsp").forward(req, resp);
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Category> categories = new CategoryDAO().getCategory();
//        int id=Integer.parseInt(req.getParameter("id")) ;
        req.setAttribute("categories", categories);
        req.setAttribute("option", "create");
//        req.setAttribute("id", "id");
        req.getRequestDispatcher("create-edit-product.jsp").forward(req, resp);
    }

    private void exeEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int ProductID = Integer.parseInt(req.getParameter("ProductID"));
            String ProductName = req.getParameter("ProductName");
            int CategoryID = Integer.parseInt(req.getParameter("CategoryID"));
            String QuantityPerUnit = req.getParameter("QuantityPerUnit");
            double UnitPrice = Double.parseDouble(req.getParameter("UnitPrice"));
            int UnitsInStock = Integer.parseInt(req.getParameter("UnitsInStock"));
            int UnitsOnOrder = Integer.parseInt(req.getParameter("UnitsOnOrder"));
            int ReorderLevel = Integer.parseInt(req.getParameter("ReorderLevel"));
            boolean Discontinued = "on".equalsIgnoreCase(req.getParameter("Discontinued"));

            if (ProductName.isEmpty() || QuantityPerUnit.isEmpty() || UnitPrice < 0 || UnitsInStock < 0 || UnitsOnOrder < 0 || ReorderLevel < 0) {
                throw new ServletException();
            }
            if (!ProductName.isEmpty() && !QuantityPerUnit.isEmpty() && req.getParameter("UnitPrice").matches("[0-9]+(\\.){0,1}[0-9]*") && req.getParameter("UnitsInStock").matches("[0-9]+") && req.getParameter("ReorderLevel").matches("[0-9]+")) {

            } else {
                throw new ServletException();
            }

            Product product = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
            int result = new CategoryDAO().updateProduct(product);
            if (result > 0) {
                resp.sendRedirect("manage-product");
            } else {
                throw new SQLException();
            }
        } catch (Exception e) {
            String msgProductName = "";
            String msgQuantityPerUnit = "";
            String msgUnitPrice = "";
            String msgUnitsInStock = "";
            String msgUnitsOnOrder = "";
            String msgReorderLevel = "";

            if (req.getParameter("ProductName").equals("")) {
                msgProductName = "ProductName is required";
                req.setAttribute("msgProductName", msgProductName);
            }
            if (req.getParameter("QuantityPerUnit").equals("")) {
                msgQuantityPerUnit = "QuantityPerUnit is required";
                req.setAttribute("msgQuantityPerUnit", msgQuantityPerUnit);
            }
            if (req.getParameter("UnitPrice").equals("")) {
                msgUnitPrice = "UnitPrice is required";
                req.setAttribute("msgUnitPrice", msgUnitPrice);
            }
            if (req.getParameter("UnitsInStock").equals("")) {
                msgUnitsInStock = "UnitsInStock is required";
                req.setAttribute("msgUnitsInStock", msgUnitsInStock);
            }
            if (req.getParameter("UnitsOnOrder").equals("")) {
                msgUnitsOnOrder = "UnitsOnOrder is required";
                req.setAttribute("msgUnitsOnOrder", msgUnitsOnOrder);
            }
            if (req.getParameter("ReorderLevel").equals("")) {
                msgReorderLevel = "ReorderLevel is required";
                req.setAttribute("msgReorderLevel", msgReorderLevel);
            }
            int ProductID = Integer.parseInt(req.getParameter("ProductID"));
            Product productOld = new CategoryDAO().get1Pro(ProductID);
            ArrayList<Category> categories = new CategoryDAO().getCategory();
            req.setAttribute("product", productOld);
            req.setAttribute("categories", categories);
            req.setAttribute("option", "edit");
//            req.setAttribute("errMsg", "Edit product failed!");
            req.getRequestDispatcher("create-edit-product.jsp").forward(req, resp);
        }
    }

    private void exeCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String ProductName = req.getParameter("ProductName");
            int CategoryID = Integer.parseInt(req.getParameter("CategoryID"));
            String QuantityPerUnit = req.getParameter("QuantityPerUnit");
            double UnitPrice = Double.parseDouble(req.getParameter("UnitPrice"));
            int UnitsInStock = Integer.parseInt(req.getParameter("UnitsInStock"));
            int UnitsOnOrder = Integer.parseInt(req.getParameter("UnitsOnOrder"));
            int ReorderLevel = Integer.parseInt(req.getParameter("ReorderLevel"));
            boolean Discontinued = "on".equalsIgnoreCase(req.getParameter("Discontinued"));

            if (ProductName.isEmpty() || QuantityPerUnit.isEmpty() || UnitPrice < 0 || UnitsInStock < 0 || UnitsOnOrder < 0 || ReorderLevel < 0) {
                throw new ServletException();
            }

            if (!ProductName.isEmpty() && !QuantityPerUnit.isEmpty() && req.getParameter("UnitPrice").matches("[0-9]+(\\.){0,1}[0-9]*") && req.getParameter("UnitsInStock").matches("[0-9]+") && req.getParameter("ReorderLevel").matches("[0-9]+")) {

            } else {
                throw new ServletException();
            }
            Product product = new Product(0, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
            int result = new CategoryDAO().createProduct(product);
            if (result > 0) {
                req.getServletContext().setAttribute("pageQuantity", (int) Math.ceil(new CategoryDAO().getPro(4).size() / ((double) 10)));
                resp.sendRedirect("manage-product");
            } else {
                throw new SQLException();
            }
        } catch (Exception e) {
            String msgProductName = "";
            String msgQuantityPerUnit = "";
            String msgUnitPrice = "";
            String msgUnitsInStock = "";
            String msgUnitsOnOrder = "";
            String msgReorderLevel = "";

            if (req.getParameter("ProductName").equals("")) {
                msgProductName = "ProductName is required";
                req.setAttribute("msgProductName", msgProductName);
            }
            if (req.getParameter("QuantityPerUnit").equals("")) {
                msgQuantityPerUnit = "QuantityPerUnit is required";
                req.setAttribute("msgQuantityPerUnit", msgQuantityPerUnit);
            }
            if (req.getParameter("UnitPrice").equals("")) {
                msgUnitPrice = "UnitPrice is required";
                req.setAttribute("msgUnitPrice", msgUnitPrice);
            }
            if (req.getParameter("UnitsInStock").equals("")) {
                msgUnitsInStock = "UnitsInStock is required";
                req.setAttribute("msgUnitsInStock", msgUnitsInStock);
            }
            if (req.getParameter("UnitsOnOrder").equals("")) {
                msgUnitsOnOrder = "UnitsOnOrder is required";
                req.setAttribute("msgUnitsOnOrder", msgUnitsOnOrder);
            }
            if (req.getParameter("ReorderLevel").equals("")) {
                msgReorderLevel = "ReorderLevel is required";
                req.setAttribute("msgReorderLevel", msgReorderLevel);
            }
            ArrayList<Category> categories = new CategoryDAO().getCategory();
            req.setAttribute("categories", categories);
            req.setAttribute("option", "create");

//            if (!msgProductName.equals("")||!msgQuantityPerUnit.equals("")||!msgUnitPrice.equals("")||!msgUnitsInStock.equals("")||!msgUnitsOnOrder.equals("")||!msgReorderLevel.equals("")) {
//                req.setAttribute("ProductName", req.getParameter("ProductName"));
//                req.setAttribute("CategoryID", Integer.parseInt(req.getParameter("CategoryID")));
//                req.setAttribute("QuantityPerUnit",req.getParameter("QuantityPerUnit"));
//                if (msgUnitPrice.equals("")) {
//                    req.setAttribute("UnitPrice",Double.parseDouble(req.getParameter("UnitPrice")));
//                }
//                if (msgUnitsInStock.equals("")) {
//                    req.setAttribute("UnitsInStock",Integer.parseInt(req.getParameter("UnitsInStock")));
//                }
//                
//                if (msgUnitsOnOrder.equals("")) {
//                    req.setAttribute("UnitsOnOrder",Integer.parseInt(req.getParameter("UnitsOnOrder")));
//                }
//                if (msgReorderLevel.equals("")) {
//                    req.setAttribute("ReorderLevel",Integer.parseInt(req.getParameter("ReorderLevel")));
//                }
//                resp.getWriter().print("aaa");
//            }
//            req.setAttribute("errMsg", "Create product failed!");
            req.getRequestDispatcher("create-edit-product.jsp").forward(req, resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        try {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("productID", id);

        if (new CategoryDAO().get1Pro(id) != null) {
            req.getRequestDispatcher("delete.jsp").forward(req, resp);
        }
    }

    private void exeDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pid = Integer.parseInt(req.getParameter("id"));
//        resp.getWriter().print("ooo");
        int result = new CategoryDAO().deleteProduct(new CategoryDAO().get1Pro(pid));

        if (result != 0) {
            req.setAttribute("success-delete-msg", "Successfully deleted!");
            req.removeAttribute("deleteID");
//            req.getRequestDispatcher("manage-product").forward(req, resp);
            resp.sendRedirect("manage-product");
        }
    }

}
