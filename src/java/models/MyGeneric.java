/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import DAL.Category;
import DAL.Product;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class MyGeneric<T> {

    ArrayList<T> allItem;

    public MyGeneric(ArrayList<T> allItem) {
        this.allItem = allItem;
    }

    public ArrayList<T> dataPaging(int pageTh, int numberOfItemPerPage) {

        ArrayList<T> dataPaging = new ArrayList<T>();
        int startIndex = pageTh * numberOfItemPerPage - numberOfItemPerPage;
        int endIndex = (startIndex + numberOfItemPerPage) - 1;

        for (int i = startIndex; i <= endIndex; i++) {
            if (i == allItem.size()) {
                break;
            }
            dataPaging.add(allItem.get(i));
        }
        return dataPaging;
    }

    public static void main(String[] args) {
//        ArrayList<Category> cat= new MyGeneric<Category>(allItem) dataPaging(2,3);
        ArrayList<Product> allItem = new CategoryDAO().getProductListBySqlQuery("select * from Products\n"
                + "where ProductName like '%" + "a" + "%'");
        ArrayList<Product> productlist = new MyGeneric<Product>(allItem).dataPaging(12, 2);
    }
}
