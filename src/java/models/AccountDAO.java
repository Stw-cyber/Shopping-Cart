//ACCOUNTDAO
package models;

import DAL.Account;
import DAL.Customer;
import DAL.DBContext;
import DAL.Employee;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO extends DBContext {

    public Account getAccount(String email, String pass) {
        Account account = null;
        try {
//            String sql = "select *\n"
//                    + "from Accounts a, Customers c\n"
//                    + "where a.CustomerID=c.CustomerID and Email=? and Password=?";
            String sql = "select * from Accounts where Email=? and Password=?";
            //Buoc 2: tao doi tuong PrepareStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            //set các giá tri cho các tham so cua sql
            ps.setString(1, email);
            ps.setString(2, pass);
            //B3: thuc thi truy van
            ResultSet rs = ps.executeQuery();
            //B4: Xu ly ket qua tra ve
            while (rs.next()) {
                //Lay du lieu tu rs gan cho cac bien cuc bo
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");

//                String CompanyName = rs.getString("CompanyName");
//                String ContactName = rs.getString("ContactName");
//                String ContactTitle = rs.getString("ContactTitle");
//                String Address = rs.getString("Address");

                account = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
            }
        } catch (SQLException e) {
        }
        return account;
    }

    public Customer getCustomer(String id) {
        Customer c = null;
        try {
            String sql = "select * from Customers where CustomerID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                c = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address);
            }
        } catch (SQLException e) {
        }
        return c;
    }
    public Employee getEmployee(int id) {
        Employee e = null;
        try {
            String sql = "select * from Employees\n"
                    + "where EmployeeID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int EmployeeID = rs.getInt("EmployeeID");
                String LastName = rs.getString("LastName");
                String FirstName = rs.getString("FirstName");
                int DepartmentID = rs.getInt("DepartmentID");
                String Title = rs.getString("Title");
                String TitleOfCourtesy = rs.getString("TitleOfCourtesy");
                Date BirthDate = rs.getDate("BirthDate");
                Date HireDate = rs.getDate("HireDate");
                String Address = rs.getString("Address");

                e = new Employee(EmployeeID, LastName, FirstName, DepartmentID, Title, TitleOfCourtesy, BirthDate, HireDate, Address);
            }
        } catch (Exception ex) {
        }
        return e;
    }

//    public Account addAccount(String CompanyName, String ContactName, String ContactTitle, String Address, String Email, String Password){
//        Account account = null;
//        
//        return account;
//    }
    public int AddAccount(Account acc, Customer cust) {
        int result1 = 0, result2 = 0;
        try {
            String sql1 = "insert into Customers(CustomerID, CompanyName, ContactName, ContactTitle,Address) values(?,?,?,?,?)";
            String sql2 = "insert into Accounts(Email, Password, CustomerID, Role) values(?,?,?,?)";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setString(1, cust.getCustomerID());
            ps1.setString(2, cust.getCompanyName());
            ps1.setString(3, cust.getContactName());
            ps1.setString(4, cust.getContactTitle());
            ps1.setString(5, cust.getAddress());

            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setString(1, acc.getEmail());
            ps2.setString(2, acc.getPassword());
            ps2.setString(3, cust.getCustomerID());
            ps2.setInt(4, 2);

            result1 = ps1.executeUpdate();
            result2 = ps2.executeUpdate();

        } catch (SQLException e) {
        }

        if (result1 != 0 && result2 != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        //System.out.println(new AccountDAO().getAccount("cust1@gmail.com", "123"));
        AccountDAO dao = new AccountDAO();
        System.out.println(dao.AddAccount(new Account(0, "", "", "", 0, 0), new Customer("", "", "", "", "")));
    }
}
