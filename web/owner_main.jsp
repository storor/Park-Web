<%-- 
    Document   : owner_main
    Created on : 17.12.2012, 22:06:14
    Author     : Sergiy Tolok
    Presents general information to owner like actual orders, parks
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="/WEB-INF/tlds/parktaglib.tld" prefix ="parktag"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style/park_style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <fmt:setLocale value="${locale}" scope="request"></fmt:setLocale>
        <fmt:setBundle basename="ua.tolok.projects.project4.properties.language_resourse"></fmt:setBundle>
        <title> <fmt:message key="owner_main"></fmt:message></title>
        </head>
        <body>
            <FORM action="servlet" method="POST">
                <table id="main_table">
                    <thead>                  
                        <tr>
                        <th style="background: url(style/bench.png); background-size: cover ">                            
                            <input type="submit" value="" class="main_button" name="open_main_command"/>
                        </th>
                        <th >
                        <fmt:message key="current_orders"></fmt:message>
                        </th>
                        <th style="background: url(style/log_out.png); background-size: cover ">
                            <input type="submit" value="" class="main_button" name="exit_command"/>
                        </th>
                        </tr>
                    </thead>
                    <tbody>                    
                        <tr>
                        <td class="left_right_center"></td>
                        <td class="content">  
                        <c:choose>
                            <c:when test="${not empty owner.parkCollection}">
                                <h2><fmt:message key="your_parks"></fmt:message></h2>
                                    <table class="content_table">
                                        <tr> 
                                        <td>
                                            <select name="park_select">
                                            <c:forEach var="p" items="${owner.parkCollection}" varStatus="i">
                                                <option value="${i.index}">
                                                    <c:out value="${p.name}"/>
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <input align="center" type="submit" value="<fmt:message key="show_park"></fmt:message>" name="show_park_command"/>
                                        </td>
                                        </tr>
                                    </table>
                            </c:when>
                            <c:otherwise>
                                <h2><fmt:message key="you_have_no_parks"></fmt:message></h2>                                 
                            </c:otherwise>
                        </c:choose>
                        <input align="center" type="submit" value="<fmt:message key="create_new_park"></fmt:message>" name="create_park_command"/>
                            <br>
                        <c:choose>
                            <c:when test="${not empty owner.orderCollection}">
                                <c:set var="executed_orders" value="${owner.executedOrders}"></c:set> 
                                    <h2>
                                    <fmt:message key="your_orders"></fmt:message>
                                    </h2>
                                    <table class="content_table">
                                        <tr>
                                        <th>
                                        <fmt:message key="date_added"></fmt:message>
                                        </th> 
                                        <th>
                                        <fmt:message key="execution_date"></fmt:message>
                                        </th>
                                        <th>
                                        <fmt:message key="name_park"></fmt:message>
                                        </th>
                                        <th>
                                        <fmt:message key="forester"></fmt:message>
                                        </th>
                                        <th>
                                        <fmt:message key="plant_type"></fmt:message>
                                        </th>
                                        <th>
                                        <fmt:message key="plant_sort"></fmt:message>
                                        </th>
                                        <th>
                                        <fmt:message key="service"></fmt:message>
                                        </th>
                                    <c:if test="${not empty executed_orders}">
                                        <th></th>
                                    </c:if>

                                    </tr>
                                    <c:forEach var="order" items="${owner.orderCollection}" varStatus="i">
                                        <tr>
                                        <td>
                                            <fmt:formatDate value="${order.dateAdded}" type="both" timeStyle="short"></fmt:formatDate>
                                            </td>
                                            <td>
                                            <c:choose>
                                                <c:when test="${not empty order.executionDate}">
                                                    <fmt:formatDate value="${order.executionDate}" type="both" timeStyle="short"></fmt:formatDate>
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:message key="not_executed_yet"></fmt:message>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:out value="${order.plant.park.name}"></c:out>
                                            </td>
                                            <td>
                                            <c:out value="${order.forester}"></c:out>
                                            </td>
                                            <td>
                                            <parktag:getLocaleValueTag locale="${locale}" plantType="${order.plant.plantInfo}"/>
                                        </td>
                                        <td>
                                            <parktag:getLocaleValueTag locale="${locale}" plantSort="${order.plant.plantInfo}"/>
                                        </td>
                                        <td>
                                            <parktag:getLocaleValueTag locale="${locale}" serviceName="${order.service}"/>
                                        </td>
                                        <c:if test="${not empty executed_orders}">
                                            <td>
                                                <c:if test="${not empty order.executionDate}">
                                                    <input type="radio" name="order_select" value="${order.orderId}" />
                                                </c:if>
                                            </td>
                                        </c:if>
                                        </tr>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${not empty executed_orders}">
                                            <tr>
                                            <td colspan="5"  align="centr">
                                                <c:if test="${not empty message}">
                                                    <fmt:message key="${message}"></fmt:message>
                                                    <c:remove var="message" scope="session"></c:remove>
                                                </c:if>
                                                <c:set var="page" value="main" scope="session"></c:set>
                                                </td>
                                                <td colspan="3"  align="right">
                                                    <input type="submit" value="<fmt:message key="confirm_execution"></fmt:message>" name="confirm_execution_command"/>
                                                </td>
                                                </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                            <td colspan="8">
                                                <fmt:message key="no_executed_orders"></fmt:message>
                                            </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <h2><fmt:message key="you_have_no_orders"></fmt:message></h2>
                            </c:otherwise>
                        </c:choose>                           
                    </td>
                    <td class="left_right_center"></td>
                    </tr>
                    <tr class="bottom_center">
                    <td></td>
                    <td id="bottom_info">
                        <fmt:message key="info"></fmt:message>
                    </td>
                    <td>
                        <a href="owner_main.jsp?language=ru&AMP;region=RU">Рус</a>|
                        <a href="owner_main.jsp?language=en&AMP;region=US">Eng</a>
                    </td>
                    </tr>
                </tbody>
            </table> 
        </FORM>
    </body>
</html>
