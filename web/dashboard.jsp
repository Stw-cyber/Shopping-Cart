<%@include file="template/headerAdmin.jsp" %>
<div id="content">
    <div id="content-left">
        <ul>
            <a href="manage-dashboard"><li id="highlight" >Dashboard</li></a>
            <a href="manage-order"><li>Orders</li></a>
            <a href="manage-product"><li>Products</li></a>
            <a href="#"><li>Customers</li></a>
        </ul>
    </div>
    <div id="content-right">
        <div class="path-admin">DASHBOARD</b></div>
        <div class="content-main">
            <div id="content-main-dashboard">
                <div id="dashboard-1">
                    <div id="dashboard-1-container">
                        <div class="dashboard-item">
                            <div class="dashboard-item-title">Weekly Sales</div>

                            <div class="dashboard-item-content">$${weeklySale}K</div>

                        </div>
                        <div class="dashboard-item">
                            <div class="dashboard-item-title">Total Orders</div>
                            <div class="dashboard-item-content">$${totalOrder}K</div>

                        </div>
                        <div class="dashboard-item">
                            <div class="dashboard-item-title">Total Customers</div>
                            <div class="dashboard-item-content">${cus}</div>
                        </div>
                        <div class="dashboard-item">
                            <div class="dashboard-item-title">Total Guest</div>
                            <div class="dashboard-item-content">${guest}</div>
                        </div>
                    </div>
                </div>
                        <form action="manage-dashboard" method="get">
                    <select name="Year">
                        <option selected value="-1">None</option>
                        <c:forEach items="${year}" var="year">
                            <c:choose>
                                <c:when test="${year == Year}">
                                    <option selected value="${Year}">${Year}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${year}">${year}</option>
                                </c:otherwise>
                            </c:choose>
                            <%--<c:if test="${c.getCategoryID() eq category.categoryID}">--%>
                                <!--<option selected value="${c.getCategoryID()}">${c.getCategoryName()}</option>-->
                            <%--</c:if>--%>
                        </c:forEach>       
                    </select>
                    <input type="submit" value="Show">
                </form>
                <div id="dashboard-2">
                    <div id="chart" style="text-align: center;">
                        <div id="chart1">
                            <h3>Statistic Orders (Month)</h3>
                            <canvas id="myChart1" style="width: 100%;"></canvas>
                        </div>
                        <div id="chart2">
                            <canvas id="myChart2" style="width: 80%;"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>
</div>
</body>
</html>
<script>
    function OrdersChart() {
        var xValues = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];

        new Chart("myChart1", {
            type: "line",
            data: {
                labels: xValues,
                datasets: [{
    data: [${dao.get1(1,Year)},${dao.get1(2,Year)},${dao.get1(3,Year)},${dao.get1(4,Year)},${dao.get1(5,Year)},${dao.get1(6,Year)},${dao.get1(7,Year)},${dao.get1(8,Year)},${dao.get1(9,Year)},${dao.get1(10,Year)},${dao.get1(11,Year)},${dao.get1(12,Year)}],
                        borderColor: "sienna",
                        fill: true
                    }]
            },
            options: {
                legend: {display: false}
            }
        });
    }

    function CustomersChart() {
        var xValues = ["Total", "New customer"];
        var yValues = [${cus}, ${cusNew}, 0];
        var barColors = ["green", "red"];

        new Chart("myChart2", {
            type: "bar",
            data: {
                labels: xValues,
                datasets: [{
                        backgroundColor: barColors,
                        data: yValues
                    }]
            },
            options: {
                legend: {display: false},
                title: {
                    display: true,
                    text: "New Customers (30 daily Avg)"
                }
            }
        });
    }

    OrdersChart();
    CustomersChart();
</script>