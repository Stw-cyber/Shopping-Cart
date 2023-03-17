<%@include file="template/header.jsp" %>
<%@page import="models.CategoryDAO" %>
<div id="content">
    <% int pID = Integer.parseInt(request.getParameter("detailId")); %>
    <%Product pro = new CategoryDAO().get1Pro(pID);%>
    <%Category cat = new CategoryDAO().get(pro.getCategoryID());%>
            <div id="content-detail">
                <div id="content-title">
                    <a href="index.jsp">Home</a> >
                    <a href="category?cid=<%=cat.getCategoryID()%>"><%=cat.getCategoryName()%></a> >
                    Model: SP1
                </div>
                <div id="product">
                    <div id="product-name">
                        <h2><%=pro.getProductName()%></h2>
                        <div id="product-detail">
                            <div id="product-detail-left">
                                <div id="product-img">
                                    <img src="img/1.jpg"/>
                                </div>
                                <div id="product-img-items">
                                    <div><a href="#"><img src="img/1.jpg"/></a></div>
                                    <div><a href="#"><img src="img/1.jpg"/></a></div>
                                    <div><a href="#"><img src="img/1.jpg"/></a></div>
                                    <div><a href="#"><img src="img/1.jpg"/></a></div>
                                </div>
                            </div>
                            <div id="product-detail-right">
                                <div id="product-detail-right-content">
                                    <div id="product-price">$ <%=pro.getUnitPrice()%></div>
                                    <div id="product-status">In stock</div>
                                    <div id="product-detail-buttons">
                                        <div id="product-detail-button">
                                            <a href="cart-control?pid=<%=pID%>"><input type="button" value="BUY NOW"></a>
                                            <a href="cart-control?pid=<%=pID%>&action=addCart"><input type="button" value="ADD TO CART" style="background-color: #fff; color:red;border: 1px solid gray;"></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="info-detail">
                    <div id="info-detail-title">
                        <h2>Information deltail</h2>
                        <div style="margin:10px auto;">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Illum, debitis. Asperiores soluta eveniet eos accusantium doloremque cum suscipit ducimus enim at sapiente mollitia consequuntur dicta quaerat, sunt voluptates autem. Quam!
                            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Rem illum autem veritatis maxime corporis quod quibusdam nostrum eaque laborum numquam quos unde eveniet aut, exercitationem voluptatum veniam fugiat, debitis esse?
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Distinctio eligendi ratione vitae nobis numquam dolorum assumenda saepe enim cumque blanditiis, deleniti neque voluptate vel ducimus in omnis harum aut nisi.
                        </div>
                    </div>
                </div>
            </div>
        </div>

<%@include file="template/footer.jsp" %>