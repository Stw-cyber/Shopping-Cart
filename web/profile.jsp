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
            <a href="<c:url value="/account/profile"/>"><li id="highlight">Personal information</li></a>
        </ul>
        <h3>My order</h3>
        <ul>
            <a href="<c:url value="/allOrder.jsp"/>"><li>All orders</li></a>
            <a href="../canceled.jsp"><li>Canceled order</li></a>
        </ul>
    </div>
    <div id="content-right">
        <div class="path">Personal information</b></div>
        <form action="<c:url value="/editProfile"></c:url>" method="get">
                <div class="content-main">

                    <div id="profile-content">
                        <!--<form action="editProfile" method="get">-->

                        <div class="profile-content-col">
                            <div>Company name: <br/>${cus.companyName}</div>
                        <div>Contact name: <br/>${cus.contactName}</div>
                        <div>
                            <input type="submit" value="Edit info">
                        </div>
                    </div>
                    <div class="profile-content-col">
                        <div>Company title: <br/>${cus.contactTitle}</div>
                        <div>Address: <br/>${cus.address}</div>
                    </div>
                    <div class="profile-content-col">
                        <div>Email: <br/>${AccSession.email}</div>
                    </div>
                    <!--</form>-->
                </div>

            </div>
        </form>
    </div>
</div>

<%@include file="template/footer.jsp" %>