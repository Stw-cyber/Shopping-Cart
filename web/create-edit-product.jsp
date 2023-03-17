
<!DOCTYPE html>
<html lang="en">
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
            <c:if test="${option == \"edit\"}">
                <div class="path-admin">Editing product id ${product.productID}</b></div>
            </c:if>
            <c:if test="${option == \"create\"}">
                <div class="path-admin">CREATE A NEW PRODUCT</b></div>
            </c:if>

            <div class="content-main">
                <form id="content-main-product" action="manage-crudPro?action=${option}" method="POST">

                    <div class="content-main-1">
                        <input type="hidden" name="ProductID" value="${product.productID}">
                        
                        <c:if test="${option eq 'edit'}">
                            ProductID: <input type="text" value="${product.productID}" disabled><br>
                        </c:if>
                        <label>Product name (*):</label><br/>
                        <input type="text" name="ProductName" value="${product.productName}" /><br/>
                        <span class="msg-error">${msgProductName}</span><br/>

                        <label>Unit price:</label><br/>
                        <input type="text" name="UnitPrice" value="${product.unitPrice}" /><br/>
                        <span class="msg-error">${msgUnitPrice}</span><br/>

                        <label>Quantity per unit:</label><br/>
                        <input type="text" name="QuantityPerUnit" value="${product.quantityPerUnit}" /><br/>
                        <span class="msg-error">${msgQuantityPerUnit}</span><br/>

                        <label>Units in stock (*):</label><br/>
                        <input type="number" name="UnitsInStock" value="${product.unitsInStock}" /><br/>
                        <span class="msg-error">${msgUnitsInStock}</span><br/>
                    </div>

                    <div class="content-main-1">
                        <label>Category (*):</label><br/>
                        <select name="CategoryID" >
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.categoryID}" <c:if test="${product.categoryID == category.categoryID}">selected</c:if>>${category.categoryName}</option>
                            </c:forEach>
                        </select>
                        <br/>

                        <label>Reorder level:</label><br/>
                        <input type="number" name="ReorderLevel" value="${product.reorderLevel}" /><br/>
                        <span class="msg-error">${msgReorderLevel}</span><br/>
                        
                        <c:if test="${option eq 'edit'}">
                            <input type="hidden" name="UnitsOnOrder" value="${product.unitsOnOrder}"">
                            <label>Units on order:</label><br/>
                        <input type="number" name="UnitsOnOrder" value="${product.unitsOnOrder}" disabled/><br/>
                        </c:if>
                        <c:if test="${option eq 'create'}">
                            <label>Units on order:</label><br/>
                        <input type="number" name="UnitsOnOrder" value="${product.unitsOnOrder}"/><br/>
                        </c:if>
                        
                        <span class="msg-error">${msgUnitsOnOrder}</span><br/>

                        <label>Discontinued:</label><br/>
                        <input type="checkbox" name="Discontinued" ${product.discontinued ? "checked" : ""}/><br/>
                        <input type="submit" value="Save"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@include file="template/footer.jsp" %>
</div>
</body>
</html>