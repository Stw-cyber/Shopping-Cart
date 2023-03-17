<%@include file="template/headerAdmin.jsp" %>
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
        <div class="path-admin">ORDERS LIST</b></div>
        <div class="content-main">
            <div id="content-main-dashboard">
                <div id="order-title">
                    <b>Filter by Order date:</b>
                    <form>
                        From: <input type="date" name="txtStartOrderDate" value="${fromDate}"/>
                        To: <input type="date" name="txtEndOrderDate" value="${toDate}"/>
                        <input type="submit" value="Filter">
                    </form>
                </div>
                <div id="order-table">

                    <table id="orders">
                        <tr>
                            <th>OrderID</th>
                            <th>OrderDate</th>
                            <th>RequiredDate</th>
                            <th>ShippedDate</th>
                            <th>Employee</th>
                            <th>Customer</th>
                            <th>Freight($)</th>
                            <th>Status</th>
                        </tr>

                        <c:forEach items="${Orders}" var="o">
                            <tr>
                                <td><a href="order-detail.jsp?id=${o.orderID}">#${o.orderID}</a></td>
                                <td>${o.orderDate}</td>
                                <td>${o.requiredDate}</td>
                                <td>${o.shippedDate}</td>
                                <td>${dao.getEmployee(o.employeeID).getFullName()}</td>
                                <td>${dao.getCustomer(o.customerID).contactName}</td>
                                <td>${o.freight}</td>
                                <!--<td style="color: green;">Completed</td>-->
                                <c:choose>
                                    <c:when test="${o.shippedDate ne null}">
                                        <td style="color: green;">Completed</td>
                                    </c:when>
                                    <c:when test="${o.shippedDate eq null and o.requiredDate ne null}">
                                        <td style="color: blue;">Pending | <a href="<c:url value="/cancelOrder"></c:url>?id=${o.orderID}&page=${currentPage}&txtStartOrderDate=${fromDate}&txtEndOrderDate=${toDate}">Cancel</a></td>                               
                                    </c:when>
                                    <c:when test="${o.requiredDate eq null}">
                                        <td style="color: red;">Order canceled</td>
                                    </c:when>
                                </c:choose>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
                <br><div class="page-wrapper">
                    <c:if test="${pageQuantity > 1}">
                        <c:if test="${pageNumber != 1}">
                            <a href="?page=${pageNumber - 1}&keyword=${search}" class="director"><</a>
                        </c:if>
                        <c:forEach begin="1" end="${pageQuantity}" var="page" varStatus="c">
                            <a href="?page=${page}&keyword=${search}" class="page <c:if test="${pageNumber == page}">selected</c:if>">${page}</a>
                           
                        </c:forEach>
                        <c:if test="${pageNumber != pageQuantity}">
                            <a href="?page=${pageNumber + 1}&keyword=${search}" class="director">></a>
                        </c:if>
                    </c:if>
                </div><br>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>
</div>
</body>
</html>