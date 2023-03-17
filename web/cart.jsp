<%@include file="template/header.jsp" %>
<%@page import="DAL.*" %>
<%@page import="models.*" %>
<%@page import="java.util.*" %>
<div id="content">
    <div id="cart">
        <div id="cart-title">
            <h3>SHOPPING CART</h3>
        </div>  
        <div id="cart-content">

            <c:forEach items="${listCartProSession}" var="item">
                <div class="cart-item">
                    <div class="cart-item-infor">
                        <div class="cart-item-img">
                            <img src="img/1.jpg"/>
                        </div>
                        <div class="cart-item-name">
                            <a href="detail.jsp?detailId=${item.p.productID}">${item.p.productName}</a>
                        </div>
                        <div class="cart-item-price">
                            ${item.p.unitPrice*item.quantity} $
                        </div>
                        <div class="cart-item-button">
                            <a href="CartActControl?action=remove&id=${item.p.productID}">Remove</a>
                        </div>
                    </div>
                    <div class="cart-item-function">
                        <a href="CartActControl?action=minus&id=${item.p.productID}">-</a>  
                        <a href="CartActControl?action=plus&id=${item.p.productID}">+</a>
                        <input type="text" value="${item.quantity}" disabled/>
                    </div>
                </div>
            </c:forEach>



        </div>
        <div id="cart-summary">
            <% double totalMoney=0;
        try{
                for (CartItem item : (ArrayList<CartItem>) request.getSession().getAttribute("listCartProSession")) {
            totalMoney+=item.getQuantity()*item.getP().getUnitPrice();
        }
                }catch(Exception e){
                    totalMoney = 0;
                }
            %>
            <div id="cart-summary-content">Total amount: <span style="color:red"><%=totalMoney%> $</span></div>
        </div>
        <form action="<c:url value="/CartActControl"/>" method="post">
            <c:if test="${co!=0}">
                <div id="customer-info">
                    <div id="customer-info-content">
                        <h3>CUSTOMER INFORMATION:</h3>
                        <div id="customer-info-detail">


                            <%          
                                        Customer cust = null;
                                        try{
                                           Account acc3 = (Account) request.getSession().getAttribute("AccSession");
                                        cust = new AccountDAO().getCustomer(acc3.getCustomerID());
                                        }catch(Exception e){
                                            cust = null;
                                        }
                                        request.setAttribute("cust", cust);
                            %>
                            <div id="customer-info-left">
                                <input type="text" name="compName" placeholder="Company name *" value="<c:out value="${cust eq null ? CompanyName : cust.companyName}"/>" <c:if test="${cust ne null}">readonly</c:if>/>
                                <c:if test="${msgCompanyName!=null}">
                                    <span class="msg-error">${msgCompanyName}</span><br/>
                                </c:if><br/>
                                <input type="text" name="contName" placeholder="Contact name *" value="<c:out value="${cust eq null ? ContactName : cust.contactName}"/>" <c:if test="${cust ne null}">readonly</c:if>/>
                                <c:if test="${msgContactName!=null}">
                                    <span class="msg-error">${msgContactName}</span><br/>
                                </c:if><br/>
                                <label for="required-date">Required Date *</label><br>
                                <input id="required-date" type="date" name="txtDate" value="<c:out value="${RequiredDate}"/>"/><br/>
                                <c:if test="${msgRequiredDate!=null}">
                                    <span class="msg-error">${msgRequiredDate}</span><br/>
                                </c:if>
                                <c:if test="${msgCompareDate!=null}">
                                    <span class="msg-error">${msgCompareDate}</span><br/>
                                </c:if><br/>
                            </div>

                            <div id="customer-info-right">
                                <input type="text" name="titName" placeholder="Contact title *" value="<c:out value="${cust eq null ? ContactTitle : cust.contactTitle}"/>" <c:if test="${cust ne null}">readonly</c:if>/>
                                <c:if test="${msgContactTitle!=null}">
                                    <span class="msg-error">${msgContactTitle}</span><br/>
                                </c:if><br/>
                                <input type="text" name="addName" placeholder="Address *" value="<c:out value="${cust eq null ? Address : cust.address}"/>" <c:if test="${cust ne null}">readonly</c:if>/>
                                <c:if test="${msgAddress!=null}">
                                    <span class="msg-error">${msgAddress}</span><br/>
                                </c:if><br/>

                            </div><br>
                        </div>
                    </div>
                </div>
                <div id="customer-info">
                    <div id="customer-info-content">
                        <h3>PAYMENT METHODS:</h3>
                        <div id="customer-info-payment">
                            <div>
                                <input type="radio" name="rbPaymentMethod" checked/>
                                Payment C.O.D - Payment on delivery
                            </div>
                            <div>
                                <input type="radio" name="rbPaymentMethod" />
                                Payment via online payment gateway
                            </div>
                        </div>
                    </div>
                </div>
                <div id="cart-order">
                    <input type="submit" value="ORDER"/>
                </div>
            </c:if>
        </form>
    </div>
</div>

<%@include file="template/footer.jsp" %>