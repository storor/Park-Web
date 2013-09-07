<%-- 
    Document   : forester_main
    Created on : 17.12.2012, 22:06:45
    Author     : Sergiy Tolok
    Presents general information to forester like actual orders, parks
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="/WEB-INF/tlds/parktaglib.tld" prefix ="parktag"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style/park_style.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <fmt:setLocale value="${locale}" scope="request"></fmt:setLocale>
        <fmt:setBundle basename="ua.tolok.projects.project4.properties.language_resourse"></fmt:setBundle>
        <title> <fmt:message key="current_orders"></fmt:message></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                            <c:when test="${not empty forester.parkCollection}">
                                <h2><fmt:message key="your_parks"></fmt:message></h2>
                                <table class="content_table">
                                    <tr> 
                                    <td>
                                        <select name="park_select">
                                            <c:forEach var="p" items="${forester.parkCollection}" varStatus="i">
                                                <option value="${i.index}">
                                                    <c:out value="${p.name}"/>
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <input align="center" type="submit" value="<fmt:message key="show_park"></fmt:message>" name="show_forester_park_command"/>
                                    </td>
                                    </tr>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <h2><fmt:message key="you_have_no_parks"></fmt:message></h2>
                            </c:otherwise>
                        </c:choose>
                        <br>
                        <c:choose>

                            <c:when test="${not empty forester.orderCollection}">
                                <h2><fmt:message key="your_orders"></fmt:message></h2>

                                <c:forEach var="order" items="${forester.orderCollection}" varStatus="i">
                                    <c:if test="${empty order.executionDate}">
                                        <c:if test="${empty not_executed_orders}">
                                            <c:set var="not_executed_orders" value="not_null"></c:set>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                                <table class="content_table">
                                    <tr>
                                    <td><fmt:message key="date_added"></fmt:message></td> 
                                    <td><fmt:message key="costomer"></fmt:message></td>
                                    <td><fmt:message key="name_park"></fmt:message></td>
                                    <td><fmt:message key="plant_type"></fmt:message></td>
                                    <td><fmt:message key="plant_sort"></fmt:message></td>
                                    <td><fmt:message key="service"></fmt:message></td>
                                    <td><fmt:message key="execution_date"></fmt:message></td>
                                    <c:if test="${not empty not_executed_orders}">
                                        <td></td>
                                    </c:if>
                                    </tr>
                                    <c:forEach var="order" items="${forester.orderCollection}" varStatus="i">
                                        <tr>
                                        <td>
                                            <fmt:formatDate value="${order.dateAdded}" type="both" timeStyle="short"></fmt:formatDate>
                                        </td>
                                        <td>
                                            <c:out value="${order.owner}"></c:out>
                                        </td>
                                        <td>
                                            <c:out value="${order.plant.park.name}"></c:out>
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
                                        <td>
                                            <c:choose>
                                                <c:when test="${not empty order.executionDate}">
                                                    <fmt:formatDate value="${order.executionDate}" type="both" timeStyle="short"></fmt:formatDate>
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:message key="not_executed_yet"></fmt:message>
                                                    <c:out value=""></c:out>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <c:if test="${not empty not_executed_orders}">
                                            <td>
                                                <c:if test="${empty order.executionDate}">
                                                    <input type="radio" name="order_select" value="${i.index}" />
                                                </c:if>
                                            </td>
                                        </c:if>
                                        </tr>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${not empty not_executed_orders}">
                                            <tr>
                                            <td colspan="5">
                                                <c:if test="${not empty message}">
                                                    <fmt:message key="${message}"></fmt:message>
                                                    <c:remove var="message" scope="session"></c:remove>
                                                </c:if>
                                            </td>
                                            <td colspan="3"  align="right">
                                                <input type="submit" value="<fmt:message key="execute_order"></fmt:message>" name="execute_order_command"/>
                                            </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                            <td colspan="8">
                                                <fmt:message key="all_orders_executed"></fmt:message>
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
                    <tr  class="bottom_center">
                    <td></td>
                    <td id="bottom_info">
                        <fmt:message key="info"></fmt:message>
                    </td>
                    <td>
                        <a href="forester_main.jsp?language=ru&AMP;region=RU">Рус</a>|
                        <a href="forester_main.jsp?language=en&AMP;region=US">Eng</a>
                    </td>
                    </tr>
                </tbody>
            </table>       
        </FORM>
    </body>
</html>
