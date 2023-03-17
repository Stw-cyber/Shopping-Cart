<%@include file="template/headerAdmin.jsp" %>
<%@page import="DAL.*" %>
<%@page import="models.*" %>
<%@page import="java.util.ArrayList" %>
<div id="content">
    <div id="content-left">
        <ul>
            <a href="manage-dashboard"><li>Dashboard</li></a>
            <a href="manage-order"><li id="highlight">Orders</li></a>
            <a href="manage-product"><li>Products</li></a>
            <a href="#"><li>Customers</li></a>
        </ul>
    </div>
    <div id="content-right">
        <div class="path">ORDERS DETAIL</b></div>
        <%
         int id=Integer.parseInt(request.getParameter("id"));
         Order orders=new CategoryDAO().getOrder(id);
        request.setAttribute("orders", orders);%>
        <div class="content-main">
            <div id="profile-content-order">
                    <div>
                        <div class="profile-order-title">
                            <div class="profile-order-title-left">
                                <div>Order creation date: ${orders.orderDate}</div>
                                <div>Order: <a href="#">#${orders.orderID}</a></div>
                            </div>
                            <c:if test="${orders.shippedDate eq null&&orders.requiredDate ne null}">
                                <div class="profile-order-title-right">
                                    <span>Pending</span>
                                </div>
                            </c:if>

                            <c:if test="${orders.shippedDate ne null}">
                                <div class="profile-order-title-right">
                                    <span style="color: blue;">Completed</span>
                                </div>
                            </c:if>
                            <c:if test="${orders.requiredDate eq null}">
                                <div class="profile-order-title-right">
                                    <span style="color: red;">Order canceled</span>
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
                        <c:if test="${orders.shippedDate eq null&&orders.requiredDate ne null}">
                            <a href="<c:url value="/cancelOrder"></c:url>?&id=${orders.orderID}&page=${currentPage}&txtStartOrderDate=${fromDate}&txtEndOrderDate=${toDate}" class="cancel-order"  style="font-size: 18px;
                               float: right;
                               width: 100%;
                               text-align: right;
                               margin: 18px;
                               color: blue;
                               font-weight: bold;">Cancel Order</a> 

                        </c:if>
                    </div>

            </div>
        </div>
    </div>
</div>

<%@include file="template/footer.jsp" %>
