package models;

import DAL.CartItem;
import DAL.Category;
import DAL.Customer;
import DAL.DBContext;
import DAL.Order;
import DAL.OrderDetail;
import DAL.Product;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class CategoryDAO extends DBContext {

    // Action: Read all products
    public ArrayList<Category> getCategory() {
        ArrayList<Category> category = new ArrayList<Category>();
        try {
            String sql = "select * from Categories";
            //Buoc 2: tao doi tuong PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            //B3: thuc thi truy van
            ResultSet rs = ps.executeQuery();
            //B4: Xu ly ket qua tra ve
            while (rs.next()) {
                //Lay du lieu tu rs gan cho cac bien cuc bo
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                String Description = rs.getString("Description");
                String Picture = rs.getString("Picture");

                //Khoi tao doi tuong kieu Product
                Category p = new Category(CategoryID, CategoryName, Description, Picture);
                //bo sung p vao danh sach products

                category.add(p);
            }
        } catch (SQLException e) {
        }
        return category;
    }

    public ArrayList<Product> getProductsByKeyword(String keyword) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Products WHERE ProductName LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                products.add(new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued));
            }
        } catch (SQLException e) {
        }
        return products;
    }

    public ArrayList<Product> getProductsByKeyword2(String keyword, int catID) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Products WHERE ProductName LIKE ? and CategoryID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setInt(2, catID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                products.add(new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued));
            }
        } catch (SQLException e) {
        }
        return products;
    }

    public int updateCustomer(Customer c) {
        int i = 0;
        try {
            String sql = "update Customers\n"
                    + "set CompanyName =?, ContactName =?, ContactTitle =?, Address =?\n"
                    + "where CustomerID =?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getCompanyName());
            ps.setString(2, c.getContactName());
            ps.setString(3, c.getContactTitle());
            ps.setString(4, c.getAddress());
            ps.setString(5, c.getCustomerID());
            i = ps.executeUpdate();
        } catch (Exception e) {
        }
        return i;
    }

    public Category get(int ID) {
        Category c = null;
        try {
            String sql = "SELECT CategoryName, Description, Picture FROM Categories\n"
                    + "WHERE CategoryID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                c = new Category();
//                c.setCategoryID(ID);
//                c.setCategoryName(rs.getString("CategoryName"));
//                c.setDescription(rs.getString("Description"));

                String CategoryName = rs.getString("CategoryName");
                String Description = rs.getString("Description");
                String Picture = rs.getString("Picture");
                c = new Category(ID, CategoryName, Description, Picture);
            }
        } catch (SQLException e) {
        }
        return c;
    }

    public Product get1Pro(int ID) {
        Product p = null;
        try {
            String sql = "select * from Products\n"
                    + "where ProductID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //                c = new Category();
                //                c.setCategoryID(ID);
                //                c.setCategoryName(rs.getString("CategoryName"));
                //                c.setDescription(rs.getString("Description"));

                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                Double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                Boolean Discontinued = rs.getBoolean("Discontinued");
                p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
            }
        } catch (SQLException e) {
        }
        return p;
    }

    public ArrayList<Product> getProByCatID(int ID) {
        ArrayList<Product> listPro = new ArrayList<Product>();
        try {
            String sql = "select * from Products\n"
                    + "where CategoryID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //                c = new Category();
                //                c.setCategoryID(ID);
                //                c.setCategoryName(rs.getString("CategoryName"));
                //                c.setDescription(rs.getString("Description"));

                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                Double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                Boolean Discontinued = rs.getBoolean("Discontinued");
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                listPro.add(p);
            }
        } catch (SQLException e) {
        }
        return listPro;
    }

    public ArrayList<Product> getPro(int c) {
        ArrayList<Product> listPro = new ArrayList<Product>();
        try {
            String sql = "";
            if (c == 1) {
                sql = "select top 4 * from Products p\n"
                        + "inner join [Order Details] od on p.ProductID = od.ProductID\n"
                        + "order by od.Discount desc";
            }
            if (c == 2) {
                sql = "select top 4 p.ProductID, p.CategoryID, p.ProductName, p.QuantityPerUnit, p.ReorderLevel, p.UnitPrice, p.UnitsInStock, p.UnitsOnOrder, p.Discontinued\n"
                        + "from Products p,(select top 4 od.ProductID, count(*) as NumberOfProduct\n"
                        + "from [Order Details] od\n"
                        + "group by od.ProductID order by NumberOfProduct desc) as od where p.ProductID = od.ProductID";
            }
            if (c == 3) {
                sql = "select top 4 * from Products order by ProductID desc";
            }
            if (c == 4) {
                sql = "select * from Products";
            }
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //                c = new Category();
                //                c.setCategoryID(ID);
                //                c.setCategoryName(rs.getString("CategoryName"));
                //                c.setDescription(rs.getString("Description"));

                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                Double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                Boolean Discontinued = rs.getBoolean("Discontinued");
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                listPro.add(p);
            }
        } catch (SQLException e) {
        }
        return listPro;
    }

    public ArrayList<Product> getProductsAtPage(int pageNumber, int itemsPerPage, int id, int opt) {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "";
        PreparedStatement ps = null;
        try {
            if (opt == 1) {
                sql = "SELECT * FROM Products ORDER BY ProductID asc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                ps = connection.prepareStatement(sql);
//            ps.setInt(1, id);
                ps.setInt(1, (pageNumber - 1) * itemsPerPage);
                ps.setInt(2, itemsPerPage);
            } else if (opt == 2) {
                sql = "SELECT * FROM Products where CategoryID=? ORDER BY ProductID asc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setInt(2, (pageNumber - 1) * itemsPerPage);
                ps.setInt(3, itemsPerPage);
            }else if(opt == 3){
                sql = "SELECT * FROM Products where ProductName like ? ORDER BY ProductID asc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                ps = connection.prepareStatement(sql);
//            ps.setInt(1, id);
                
                ps.setInt(2, (pageNumber - 1) * itemsPerPage);
                ps.setInt(3, itemsPerPage);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                products.add(new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued));
            }
        } catch (SQLException e) {
        }
        return products;
    }

    public ArrayList<Order> getOrdersBySqlQurey(String sql) {
        ArrayList<Order> orderList = new ArrayList<Order>();
        try {
            //Buoc 2: Tạo đối tượng PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            //Buoc 3: Thực thi truy vấn
            ResultSet rs = ps.executeQuery();

            //Buoc 4: Xử lý kết quả trả về
            while (rs.next()) {
                //Lấy dữ liệu từ ResultSet gán cho các biến cục bộ
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = Date.valueOf(rs.getString("OrderDate").split(" ")[0]);
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                Order o = new Order(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                orderList.add(o);
            }
        } catch (SQLException e) {
        }
        return orderList;
    }

    public ArrayList<OrderDetail> getOrderDetails(int orderID) {
        ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        try {
            String sql = "select * from [Order Details]\n"
                    + "where OrderID = ?";

            //Buoc 2: Tạo đối tượng PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(orderID));
            //Buoc 3: Thực thi truy vấn
            ResultSet rs = ps.executeQuery();

            //Buoc 4: Xử lý kết quả trả về
            while (rs.next()) {
                //Lấy dữ liệu từ ResultSet gán cho các biến cục bộ
                int OrderID = rs.getInt("OrderID");
                int ProductID = rs.getInt("ProductID");
                double UnitPrice = rs.getDouble("UnitPrice");
                int Quantity = rs.getInt("Quantity");
                double Discount = rs.getDouble("Discount");

                OrderDetail od = new OrderDetail(OrderID, ProductID, UnitPrice, Quantity, Discount);
                orderDetailList.add(od);
            }
        } catch (SQLException e) {
        }
        return orderDetailList;
    }

    public Order getOrder(int orderID) {
        Order o = null;
        try {
            String sql = "select * from Orders\n"
                    + "where OrderID= ?\n";

            //Buoc 2: Tạo đối tượng PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderID);
            //Buoc 3: Thực thi truy vấn
            ResultSet rs = ps.executeQuery();

            //Buoc 4: Xử lý kết quả trả về
            while (rs.next()) {
                //Lấy dữ liệu từ ResultSet gán cho các biến cục bộ
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = Date.valueOf(rs.getString("OrderDate").split(" ")[0]);
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                o = new Order(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);

            }
        } catch (SQLException e) {
        }
        return o;
    }

    public ArrayList<Product> getProductListBySqlQuery(String sql) {
        ArrayList<Product> list = new ArrayList<Product>();

        try {
            //Buoc 2: Tạo đối tượng PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);

            //Buoc 3: Thực thi truy vấn
            ResultSet rs = ps.executeQuery();

            //Buoc 4: Xử lý kết quả trả về
            while (rs.next()) {
                //Lấy dữ liệu từ ResultSet gán cho các biến cục bộ
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                //Khởi tạo đối tượng kiểu Product
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                //Bổ sung 'p' vào 'products'
                list.add(p);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public CartItem getCartItem(Product p, int i) {
        CartItem c = new CartItem(p, i);
        return c;
    }

    public int addCustomer(Customer c) {
        int i = 0;
        try {
            String sql = "insert into Customers\n"
                    + "values(?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getCustomerID());
            ps.setString(2, c.getCompanyName());
            ps.setString(3, c.getContactName());
            ps.setString(4, c.getContactTitle());
            ps.setString(5, c.getAddress());
            i = ps.executeUpdate();
        } catch (Exception e) {
        }
        return i;
    }
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

    public Order addOrder(Order o) {
        int k = 0;
        Order output = null;
        try {
            String sql = "insert into Orders(CustomerID, OrderDate, RequiredDate, ShipAddress, Freight)\n"
                    + "values(?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(o.getCustomerID()));
            ps.setString(2, f.format(o.getOrderDate()));
            ps.setString(3, f.format(o.getRequiredDate()));
            ps.setString(4, o.getShipAddress());
            try {
                ps.setString(5, String.valueOf(o.getFreight()));
            } catch (Exception e) {
                ps.setString(5, "0");
            }

            k = ps.executeUpdate();

            if (k != 0) {
                String sqlGetLastOrder = "select * from Orders\n"
                        + "where OrderID = (select max(OrderID) from Orders)";

                //Buoc 2: Tạo đối tượng PrepareStatement
                PreparedStatement ps1 = connection.prepareStatement(sqlGetLastOrder);

                //Buoc 3: Thực thi truy vấn
                ResultSet rs = ps1.executeQuery();

                //Buoc 4: Xử lý kết quả trả về
                while (rs.next()) {
                    //Lấy dữ liệu từ ResultSet gán cho các biến cục bộ
                    int OrderID = rs.getInt("OrderID");
                    String CustomerID = rs.getString("CustomerID");
//                    System.out.println(rs.getString("OrderDate").split(" ")[0].trim());
                    Date OrderDate = Date.valueOf(rs.getString("OrderDate").split(" ")[0].trim());
                    Date RequiredDate = Date.valueOf(rs.getString("RequiredDate").split(" ")[0].trim());
                    double Freight = Double.parseDouble(rs.getString("Freight"));
                    String ShipAddress = rs.getString("ShipAddress");

                    output = new Order(OrderID, CustomerID, OrderDate, RequiredDate, ShipAddress);
                }
            }

        } catch (SQLException e) {
        }
        return output;
    }

    public int addOrderDetail(OrderDetail od) {
        int k = 0;
        try {
            String sql = "insert into [Order Details]\n"
                    + "values(?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(od.getOrderID()));
            ps.setString(2, String.valueOf(od.getProductID()));
            ps.setString(3, String.valueOf(od.getUnitPrice()));
            ps.setString(4, String.valueOf(od.getQuantity()));
            ps.setString(5, String.valueOf(od.getDiscount()));

            k = ps.executeUpdate();

        } catch (Exception e) {
        }
        return k;
    }

    public int updateOrder(int orderID) {
        String sql = "update Orders\n"
                + "set RequiredDate = null\n"
                + "where OrderID = ?";
        int result = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderID);
            result = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return result;
    }

    public double getweeklySale() {
        double x = 20;
        try {
            String sql = "select ROUND(sum(a.TotalAmount), 2)\n"
                    + "from (select o.OrderID, sum(od.UnitPrice * od.Quantity - od.UnitPrice * od.Quantity * od.Discount) as TotalAmount, o.OrderDate\n"
                    + "                from Orders o, [Order Details] od\n"
                    + "               where o.OrderID = od.OrderID and OrderDate >= GETDATE() - 7 and OrderDate <= GETDATE()\n"
                    + "               group by o.OrderID, o.OrderDate) as a";
//        int result = 0;

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getDouble(1);
//                return x;
            }
        } catch (SQLException e) {
        }

        return x;
    }

    public static void main(String[] args) throws SQLException {
        double c = new CategoryDAO().getweeklySale();
        System.out.println(c);
    }

    public double getTotalOrder() {
        double x = 20;
        try {
            String sql = "select ROUND(sum(a.TotalAmount), 2)\n"
                    + "from (select o.OrderID, sum(od.UnitPrice * od.Quantity - od.UnitPrice * od.Quantity * od.Discount) as TotalAmount, o.OrderDate\n"
                    + "                from Orders o, [Order Details] od\n"
                    + "               where o.OrderID = od.OrderID\n"
                    + "               group by o.OrderID, o.OrderDate) as a";
//        int result = 0;

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getDouble(1);
//                return x;
            }
        } catch (SQLException e) {
        }

        return x;
    }

    public int getTotalCus() {
        int x = 20;
        try {
            String sql = "select COUNT(*) from Customers";
//        int result = 0;

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getInt(1);
//                return x;
            }
        } catch (SQLException e) {
        }

        return x;
    }

    public int getTotalGuest() {
        int x = 20;
        try {
            String sql = "select count(*) from Accounts where Role=2";
//        int result = 0;

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getInt(1);
//                return x;
            }
        } catch (SQLException e) {
        }

        return getTotalCus()-x;
    }
    
    public int get1(int month, int year) {
        try {
            String sql = "SELECT count(*) from Orders \n"
                    + "where MONTH(OrderDate) = ? and YEAR(OrderDate)=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            
            //Set value
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return 0;
    }

    public ArrayList<Integer> getYear() {
        ArrayList<Integer> list=new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT YEAR(OrderDate) from Orders";
//        int result = 0;

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
//                return x;
            }
        } catch (SQLException e) {
        }

        return list;
    }

    public int updateProduct(Product product) {
        try {
            String sql = "UPDATE Products SET ProductName = ?, CategoryID = ?, QuantityPerUnit = ?, UnitPrice = ?, UnitsInStock = ?, UnitsOnOrder = ?, ReorderLevel = ?, Discontinued = ? WHERE ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getCategoryID());
            ps.setString(3, product.getQuantityPerUnit());
            ps.setDouble(4, product.getUnitPrice());
            ps.setInt(5, product.getUnitsInStock());
            ps.setInt(6, product.getUnitsOnOrder());
            ps.setInt(7, product.getReorderLevel());
            ps.setBoolean(8, product.getDiscontinued());
            ps.setInt(9, product.getProductID());
            return ps.executeUpdate();
        } catch (Exception e) {
        }
        return 0;
    }
    
       public int createProduct(Product product) {
        try {
            String sql = "INSERT INTO Products VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getCategoryID());
            ps.setString(3, product.getQuantityPerUnit());
            ps.setDouble(4, product.getUnitPrice());
            ps.setInt(5, product.getUnitsInStock());
            ps.setInt(6, product.getUnitsOnOrder());
            ps.setInt(7, product.getReorderLevel());
            ps.setBoolean(8, product.getDiscontinued());
            return ps.executeUpdate();
        } catch (Exception e) {
        }
        return 0;
    }


    public int deleteProduct(Product p) {
        int result = 0;
        try {
            String sql1 = "ALTER TABLE [dbo].[Order Details] DROP CONSTRAINT [FK_Order_Details_Products]";
            String sql2 = "delete Products\n"
                    + "where ProductID =?\n";
            String sql3 = "ALTER TABLE [dbo].[Order Details]  WITH NOCHECK ADD  CONSTRAINT [FK_Order_Details_Products] FOREIGN KEY([ProductID])\n"
                    + "REFERENCES [dbo].[Products] ([ProductID])";

            PreparedStatement ps1 = connection.prepareStatement(sql1);
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            PreparedStatement ps3 = connection.prepareStatement(sql3);
            ps2.setString(1, String.valueOf(p.getProductID()));

            ps1.executeUpdate();
            result = ps2.executeUpdate();
            ps3.executeUpdate();

        } catch (Exception e) {
        }
        return result;
    }
    public int getCountNewCus() {
        // Create : PrepareStatement
        try {
            String sql = "select count(*) from Customers where  >= GETDATE()-30";
            PreparedStatement ps = connection.prepareStatement(sql);
            // Implement statement
            ResultSet rs = ps.executeQuery();

            // Progress result returned
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }
}
