
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAL.*"  %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Index</title>
        <!--String path=request.getContextPath();--> 
        <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <!--Account acc=(Account)request.getSession().getAttribute("AccSession");--> 

        <div id="container">
            <div id="header">
                <div id="logo">
                    <a href="<c:url value="/index.jsp"/>">
                        <img src="<c:url value="/img/logo.png"/>"/></a>
                </div>
                
                <div id="banner">
                    <ul>
                        <li><div class="search">
                                <form action="index.jsp">
                                    <input type="text" name="keyword" <%if(request.getParameter("keyword")!=null){%>value="<%=request.getParameter("keyword")%><%}%>">
                                    <input type="submit" value="Search">
                                </form>
                            </div></li>
                    </ul>
                    <ul>
       
            
            <% int co=0;
        try{
                for (CartItem item : (ArrayList<CartItem>) request.getSession().getAttribute("listCartProSession")) {
            co+=item.getQuantity();
        }
                }catch(Exception e){
                    co = 0;
                }
                request.setAttribute("co",co);
            %>
                        
                        <li><a href="<c:url value="/cart.jsp"/>">Cart: <%=co%></a></li>
                            <c:choose>
                                <c:when test="${AccSession == null}">
                                <li><a href="<c:url value="/account/signin"/>">SignIn</a></li>
                                <li><a href="<c:url value="/account/signup"/>">SignUp</a></li>
                                </c:when>
                                <c:otherwise>
                                <li><a href="<c:url value="/account/profile"/>">Profile</a></li>
                                <li><a href="<c:url value="/account/signin"/>">SignOut</a></li>
                                </c:otherwise>
                            </c:choose>
                    </ul>
                </div>
            </div>
