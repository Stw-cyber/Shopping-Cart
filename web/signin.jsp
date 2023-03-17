<%@include file="template/header.jsp" %>

<div id="content">
    <div id="form">
        <div id="form-title">
            <span><a href="<c:url value="/account/signup"/>">SIGN UP</a></span>
            <span><a href="<c:url value="/account/signin"/>" style="color: red;">SIGN IN</a></span>
        </div>
        <div style="color: red; text-align: center;">

            <c:if test="${msg!=null}">
                ${msg}
            </c:if>
        </div>
        <div id="form-content">
            <form action="" method="post">
                <label>Email<span style="color: red;">*</span></label><br/>
                <input type="text" name="txtEmail" value="${email}"/><br/>



                <c:if test="${msgEmail!=null}">
                    <span class="msg-error">${msgEmail}</span><br/>
                </c:if>


                <label>Password<span style="color: red;">*</span></label><br/>
                <input type="password" name="txtPass" value="${pass}"/><br/>



                <c:if test="${msgPass!=null}">
                    <span class="msg-error">${msgPass}</span><br/>
                </c:if>

                <div><a href="<c:url value="/forgot.jsp"/>">Forgot password?</a></div>
                <input type="submit" value="SIGN IN"/><br/>
                <input type="button" value="FACEBOOK LOGIN" style="background-color: #3b5998;"/><br/>
                <input type="button" value="ZALO LOGIN" style="background-color: #009dff;margin-bottom: 30px;"/>
            </form>
        </div>
    </div>
</div>

<%@include file="template/footer.jsp" %>