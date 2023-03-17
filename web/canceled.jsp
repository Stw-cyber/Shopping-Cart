<%@include file="template/header.jsp" %>
<%@page import="DAL.*" %>
<%@page import="models.*" %>
<%@page import="java.util.ArrayList" %>
<div id="content">
    <div id="content-left">
        <h3 style="font-weight: normal;">Welcome, ${cus.contactName}</h3>
        <h3>Account Management</h3>
        <ul>
            <a href="<c:url value="/account/profile"/>"><li>Personal information</li></a>
        </ul>
        <h3>My order</h3>
        <ul>
            <a href="<c:url value="/allOrder.jsp"/>"><li>All orders</li></a>
            <a href="canceled.jsp"><li id="highlight">Canceled order</li></a>
        </ul>
    </div>
    <div id="content-right">
        <div class="path">CANCELED ORDER</b></div>
        <%Customer c = new AccountDAO().getCustomer(((Account)request.getSession().getAttribute("AccSession")).getCustomerID());  
    ArrayList<Order> allItem = new CategoryDAO().getOrdersBySqlQurey("select * from Orders\n"
                                + "where RequiredDate is null and CustomerID = '"+c.getCustomerID()+"'\n"
                                + "order by OrderDate desc");
        request.setAttribute("orders", allItem);%>
        <div class="content-main">
            <div id="profile-content-order">
                <c:forEach items="${orders}" var="orders">
                    <div>
                        <div class="profile-order-title">
                            <div class="profile-order-title-left">
                                <div>Order creation date: ${orders.orderDate}</div>
                                <div>Order: <a href="#">#${orders.orderID}</a></div>
                            </div>
                            <c:if test="${orders.shippedDate eq null}">
                                <div class="profile-order-title-right">
                                    <span>Canceled</span>
                                </div>
                            </c:if>

                            <c:if test="${orders.shippedDate ne null}">
                                <div class="profile-order-title-right">
                                    <span style="color: blue;">Completed</span>
                                </div>
                            </c:if>
                        </div>
                        
                            <c:forEach items="${CategoryDAO().getOrderDetails(orders.orderID)}" var="productItem">
                                <div class="profile-order-content">
                                    <div class="profile-order-content-col1">
                                    <a href="detail.html"><img src="img/2.jpg" width="100%"/></a>
                                </div>
                                <div class="profile-order-content-col2">${CategoryDAO().get1Pro(productItem.productID).productName}</div>
                                <div class="profile-order-content-col3">Quantity: ${productItem.quantity}</div>
                                <div class="profile-order-content-col4">${productItem.unitPrice*productItem.quantity} $</div>
                                </div>
                            </c:forEach>
                        
                    </div>
                </c:forEach>

            </div>
        </div>
    </div> 
</div>
<%@include file="template/footer.jsp" %>