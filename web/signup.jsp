<%@include file="template/header.jsp" %>

<div id="content">
    <div id="form">
        <div id="form-title">
            <span><a href="<c:url value="/account/signup"/>" style="color: red;">SIGN UP</a></span>
            <span><a href="<c:url value="/account/signin"/>">SIGN IN</a></span>
        </div>
        <div style="color: red; text-align: center;">


            <c:if test="${msg2!=null}">
                ${msg2}
            </c:if>


        </div>
        <div id="form-content">
            <form action="" method="post">
                <label>Company name<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtCompany" value="${CompanyName}"/><br/>

                <c:if test="${msgCompanyName!=null}">
                    <span class="msg-error">${msgCompanyName}</span><br/>
                </c:if>

                

                <label>Contact name<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtContactN" value="${ContactName}"/><br/>
                
                <c:if test="${msgContactName!=null}">
                    <span class="msg-error">${msgContactName}</span><br/>
                </c:if>
                

                <label>Contact title<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtContactT" value="${ContactTitle}"/><br/>
                
                <c:if test="${msgContactTitle!=null}">
                    <span class="msg-error">${msgContactTitle}</span><br/>
                </c:if>
                

                <label>Address<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtAddress" value="${Address}"/><br/>
                
                <c:if test="${msgAddress!=null}">
                    <span class="msg-error">${msgAddress}</span><br/>
                </c:if>
                

                <label>Email<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtEmail" value="${Email}"/><br/>
                <c:if test="${msgEmail!=null}">
                    <span class="msg-error">${msgEmail}</span><br/>
                </c:if>
                <c:if test="${msgEmailForm!=null}">
                    <span class="msg-error">${msgEmailForm}</span><br/>
                </c:if>

                <label>Password<span style="color: red;">*</span></label><br/>
                <input type="password" name="txtPass" value="${Password}"/><br/>
                <c:if test="${msgPassword!=null}">
                    <span class="msg-error">${msgPassword}</span><br/>
                </c:if>

                <label>Re-Password<span style="color: red;">*</span></label><br/>
                <input type="password" name="txtRePass" value="${RePassword}"/><br/>
                <c:if test="${msgRePassword!=null}">
                    <span class="msg-error">${msgRePassword}</span><br/>
                </c:if>
                <div></div>
                <input type="submit" value="SIGN UP" style="margin-bottom: 30px;"/>
            </form>
        </div>
    </div>
</div>

<%@include file="template/footer.jsp" %>