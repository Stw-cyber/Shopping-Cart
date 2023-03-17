<%@include file="template/header.jsp" %>
<%@page import="DAL.*" %>
<%@page import="models.*" %>
<%@page import="java.util.ArrayList" %>

<div id="content">
    <div id="content-left">
        <h3>CATEGORY</h3>

        <c:forEach items="${listCat}" var="listCat">
            <ul>
                <a href="category?cid=${listCat.categoryID}">
                    <li  
                        
                        <c:if test="${listCat.categoryID eq category.categoryID}">
                           id="highlight"
                        </c:if>
                        
                            
                        >${listCat.categoryName}</li>
                </a>
            </ul>
        </c:forEach>
    </div>
    <div id="content-right">
        <div class="path">${category.categoryName}</b></div>
        <div class="content-main">

            <c:forEach items="${products}" var="p">
                <div class="product">
                    <a href="detail.jsp?detailId=${p.productID}"><img src="img/1.jpg" width="100%"/></a>
                    <div class="name"><a href="detail.jsp?detailId=${p.productID}">${p.productName}</a></div>
                    <div class="price">$${p.unitPrice}</div>
                    <div><a href="cart-control?pid=${p.productID}">Buy now</a></div>
                </div>
            </c:forEach>
        </div>
        <br><br>

        <div class="page-wrapper">
            <c:if test="${pageQuantity > 1}">
                <c:if test="${pageNumber != 1}">
                    <a href="?page=${pageNumber - 1}&cid=${category.categoryID}" class="director"><</a>
                </c:if>
                <c:forEach begin="1" end="${pageQuantity}" var="page">
                    <a href="?page=${page}&cid=${category.categoryID}" class="page <c:if test="${pageNumber == page}">selected</c:if>">${page}</a>
                </c:forEach>
                <c:if test="${pageNumber != pageQuantity}">
                    <a href="?page=${pageNumber + 1}&cid=${category.categoryID}" class="director">></a>
                </c:if>
            </c:if>
        </div>
        <%--<%@include file="paging.jsp" %>--%>
        <br><br>
        <style>

        </style>
    </div>  
</div>
<%@include file="template/footer.jsp" %>