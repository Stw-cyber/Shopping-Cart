<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="template/header.jsp" %>
<%@page import="DAL.*" %>
<%@page import="controllers.*" %>
<%@page import="models.*" %>
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
            <a href="../canceled.jsp"><li>Canceled order</li></a>
        </ul>
    </div>
    <div id="content-right">
        <div class="path">Personal information</b></div>
        <form action="editProfile" method="post">
            <div class="content-main">
                <div id="profile-content">
                    <div class="profile-content-col">
                        <div>Company name: <br/><input type="text" name="txtCompanyName" value="${cus.companyName}" style="width: 65%"></div>
                        <div>Contact name: <br/><input type="text" name="txtContact" value="${cus.contactName}" style="width: 65%"></div>
                        <div>
                            <input type="submit" value="Save"><br>
                            <a href="<c:url value="/account/profile"/>"><input type="button" value="Cancel"></a>
                            
                        </div>
                    </div>
                    <div class="profile-content-col">
                        <div>Company title: <br/><input type="text" name="txtComTitle" value="${cus.contactTitle}" style="width: 65%"></div>
                        <div>Address: <br/><input type="text" name="txtAddress" value="${cus.address}" style="width: 65%"></div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<%@include file="template/footer.jsp" %>