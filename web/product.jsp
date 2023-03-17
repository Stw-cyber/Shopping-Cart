<%@include file="template/headerAdmin.jsp" %>
<div id="content">
    <div id="content-left">
        <ul>
            <a href="manage-dashboard"><li>Dashboard</li></a>
            <a href="manage-order"><li>Orders</li></a>
            <a href="manage-product"><li id="highlight">Products</li></a>
            <a href="#"><li>Customers</li></a>
        </ul>
    </div>
    <div id="content-right">
        <div class="path-admin">PRODUCTS LIST</b></div>
        <div class="content-main">
            <div id="content-main-dashboard">
                <div id="product-title-header">
                    <div id="product-title-1" style="width: 25%;">
                        <b>Filter by Catetory:</b>
                        <form action="search-pro">
                            <select name="CategoryID" >
                                <option value="-1" selected>All</option>
                                <c:forEach items="${categories}" var="c">
                                    <c:choose>
                                        <c:when test="${c.getCategoryID() eq category.categoryID}">
                                            <option selected value="${c.getCategoryID()}">${c.getCategoryName()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${c.getCategoryID()}">${c.getCategoryName()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                    <%--<c:if test="${c.getCategoryID() eq category.categoryID}">--%>
                                        <!--<option selected value="${c.getCategoryID()}">${c.getCategoryName()}</option>-->
                                    <%--</c:if>--%>
                                </c:forEach>       
                            </select>
                            <input type="submit" value="Filter">
                        
                    </div>
                    <div id="product-title-2" style="width: 55%;">
                        <!--                        <form>
                                                    <input type="text" name="txtSearch" placeholder="Enter product name to search"/>
                                                    <input type="submit" value="Search">
                                                </form>-->
                        <!--<form action="search-pro">-->
                            <input type="text" name="keyword" placeholder="Enter product name to search" autocomplete="off" value="${keyword}">
                            <input type="submit" value="Search">
                        </form>
                    </div>
                    <div id="product-title-3" style="width: 20%;">
                        <a href="manage-crudPro?action=create">Create a new Product</a>
                        <form action="">
                            <label for="upload-file">Import .xls or .xlsx file</label>
                            <input type="file" name="file" id="upload-file" />
                        </form>
                    </div>
                </div>
                <div id="order-table-admin">
                    <table id="orders">
                        <tr>
                            <th>ProductID</th>
                            <th>ProductName</th>
                            <th>UnitPrice</th>
                            <th>Unit</th>
                            <th>UnitsInStock</th>
                            <th>Category</th>
                            <th>Discontinued</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${products}" var="products">
                            <tr>
                                <td>${products.productID}</td>
                                <td>${products.productName}</td>
                                <td>${products.unitPrice}</td>
                                <td>${products.quantityPerUnit}</td>
                                <td>${products.unitsInStock}</td>
                                <td>${cat.get(products.categoryID).categoryName}</td>
                                <%--<td>${category.categoryName}</td>--%>
                                <td>${products.discontinued}</td>
                                <td>
                                    <a href="manage-crudPro?id=${products.productID}&action=edit">Edit</a> &nbsp; | &nbsp; 
                                    <a href="manage-crudPro?id=${products.productID}&action=delete">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div><br>
                <!--                    <div id="paging">
                                        <div class="pagination">
                                            <a href="#">&laquo;</a>
                                            <a href="#">1</a>
                                            <a href="#" class="active">2</a>
                                            <a href="#">3</a>
                                            <a href="#">4</a>
                                            <a href="#">5</a>
                                            <a href="#">6</a>
                                            <a href="#">&raquo;</a>
                                        </div>
                                    </div>-->
                <div class="page-wrapper">
                    <c:if test="${pageQuantity > 1}">
                        <c:if test="${pageNumber != 1}">
                            <a href="?page=${pageNumber - 1}<c:if test="${option == \"search\"}">&keyword=${keyword}&CategoryID=${CategoryID}</c:if>" class="director"><</a>
                        </c:if>
                        <c:forEach begin="1" end="${pageQuantity}" var="page">
                            <a href="?page=${page}<c:if test="${option == \"search\"}">&keyword=${keyword}&CategoryID=${CategoryID}</c:if>" class="page <c:if test="${pageNumber == page}">selected</c:if>">${page}</a>
                        </c:forEach>
                        <c:if test="${pageNumber != pageQuantity}">
                            <a href="?page=${pageNumber + 1}<c:if test="${option == \"search\"}">&keyword=${keyword}&CategoryID=${CategoryID}</c:if>" class="director">></a>
                        </c:if>
                    </c:if>
                </div><br>
            </div>
            <style>
                .container {
                    width: fit-content;
                    position: relative;
                }

                .page-wrapper {
                    margin: 0 auto;
                    width: fit-content;
                }

                .page {
                    padding: 4px 16px;
                    border-radius: 4px;
                    border: 1px solid sienna;
                    cursor: pointer;
                    text-decoration: none;
                    color: black;
                }

                .page.selected {
                    background-color: sienna;
                    color: white;
                }

                .director {
                    padding: 0 8px;
                    cursor: pointer;
                    font-size: 140%;
                    font-weight: bold;
                    text-decoration: none;
                    color: sienna;
                }

                .search {
                    position: absolute;
                    right: 0;
                    top: 0;
                }

                th, td {
                    padding: 2px 6px;
                }

                a {
                    text-decoration: none;
                }
            </style>
        </div>
    </div>

</div>
<%@include file="template/footer.jsp" %>
</div>
</body>
</html>