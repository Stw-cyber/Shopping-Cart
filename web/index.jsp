<%@include file="template/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="DAL.*" %>
<%@page import="controllers.*" %>
<%@page import="models.*" %>

<div id="content">
    <div id="content-left">
        <h3>CATEGORY</h3>



        <%
   //lay du lieu tu Controller chuyen sang
   ArrayList<Category> list = new CategoryDAO().getCategory();
   for (Category c : list) {
        %>
        <ul>
            <a href="category?cid=<%=c.getCategoryID()%>"><li><%=c.getCategoryName() %></li></a>
        </ul>
        <%
    }
        %>
    </div>
    <div id="content-right">
        <%if(request.getParameter("keyword")!=null){
        String search = request.getParameter("keyword").trim();
        %>
        <div class="path">Product contain '<%=search%>' in the name</b></div>
        <div >
            <%
           //lay du lieu tu Controller chuyen sang
       
                
                    ArrayList<Category> categoryList = new CategoryDAO().getCategory();
                    ArrayList<Product> productlist = new CategoryDAO().getProductListBySqlQuery("select * from Products\n"
                    +"where ProductName like '%"+search+"%'");
                    //request.setAttribute("productlist", productlist);
                    request.setAttribute("search", search);%>
           
            <%--<%@include file="template/paging.jsp" %>--%>
        </div>
        <%
            
            int pageQuantity= new CategoryItemController().getpageQuantity(request, response,productlist.size());
            int pageNumber = 1;
        try {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
        }
        if (pageNumber < 1 || pageNumber > pageQuantity) {
            response.sendRedirect("index.jsp");
//response.getWriter().print(pageNumber);
            return;
        }
        request.setAttribute("pageNumber", pageNumber);
        ArrayList<Product> products = new MyGeneric<Product>(productlist).dataPaging(pageNumber, 8);
            request.setAttribute("products", products);
        %>
        <div class="content-main">
        <%for (Product p : products) {
            %>

            <div class="product">
                <a href="detail.jsp?detailId=<%=p.getProductID()%>"><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="detail.jsp?detailId=<%=p.getProductID()%>"><%=p.getProductName()%></a></div>
                <div class="price">$<%=p.getUnitPrice()%></div>
                <div><a href="cart-control?pid=<%=p.getProductID()%>">Buy now</a></div>
            </div>

            <%
        }
            %></div>
        <div class="page-wrapper">
            <c:if test="${pageQuantity > 1}">
                <c:if test="${pageNumber != 1}">
                    <a href="?page=${pageNumber - 1}&keyword=${search}" class="director"><</a>
                </c:if>
                <c:forEach begin="1" end="${pageQuantity}" var="page">
                    <a href="?page=${page}&keyword=${search}" class="page <c:if test="${pageNumber == page}">selected</c:if>">${page}</a>
                </c:forEach>
                <c:if test="${pageNumber != pageQuantity}">
                    <a href="?page=${pageNumber + 1}&keyword=${search}" class="director">></a>
                </c:if>
            </c:if>
        </div>
        <%}else{%>
        <div class="path">HOT</b></div>
        <div class="content-main">
            <%
           //lay du lieu tu Controller chuyen sang
           ArrayList<Product> listPro = new CategoryDAO().getPro(1);
           for (Product p : listPro) {
            %>

            <div class="product">
                <a href="detail.jsp?detailId=<%=p.getProductID()%>"><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="detail.jsp?detailId=<%=p.getProductID()%>"><%=p.getProductName()%></a></div>
                <div class="price">$<%=p.getUnitPrice()%></div>
                <div><a href="cart-control?pid=<%=p.getProductID()%>">Buy now</a></div>
            </div>

            <%
        }
            %>

        </div>

        <div class="path">BEST SALE</b></div>
        <div class="content-main">
            <%
           //lay du lieu tu Controller chuyen sang
           ArrayList<Product> listPro2 = new CategoryDAO().getPro(2);
           for (Product p : listPro2) {
            %>

            <div class="product">
                <a href="detail.jsp?detailId=<%=p.getProductID()%>"><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="detail.jsp?detailId=<%=p.getProductID()%>"><%=p.getProductName()%></a></div>
                <div class="price">$<%=p.getUnitPrice()%></div>
                <div><a href="cart-control?pid=<%=p.getProductID()%>">Buy now</a></div>
            </div>

            <%
        }
            %>

        </div>

        <div class="path">NEW PRODUCT</b></div>
        <div class="content-main">
            <%
           //lay du lieu tu Controller chuyen sang
           ArrayList<Product> listPro3 = new CategoryDAO().getPro(3);
           for (Product p : listPro3) {
            %>

            <div class="product">
                <a href="detail.jsp?detailId=<%=p.getProductID()%>"><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="detail.jsp?detailId=<%=p.getProductID()%>"><%=p.getProductName()%></a></div>
                <div class="price">$<%=p.getUnitPrice()%></div>
                <div><a href="cart-control?pid=<%=p.getProductID()%>">Buy now</a></div>
            </div>

            <%
        }
            %>

        </div>
        <%}%>
    </div>
</div>

<%@include file="template/footer.jsp" %>
