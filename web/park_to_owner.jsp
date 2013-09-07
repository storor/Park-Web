<%-- 
    Document   : park_to_owner
    Created on : 19.12.2012, 12:56:38
    Author     : Sergiy Tolok
    Displays to owner details about park 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="/WEB-INF/tlds/parktaglib.tld" prefix ="parktag"%> 
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style/park_style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <fmt:setLocale value="${locale}" scope="request"></fmt:setLocale>
        <fmt:setBundle basename="ua.tolok.projects.project4.properties.language_resourse"></fmt:setBundle>
        <title> <fmt:message key="show_park"></fmt:message></title>
        </head>
        <body>
            <FORM action="servlet" method="POST"> 
                <table id="main_table">
                    <thead>
                        <tr>
                        <th style="background: url(style/bench.png); background-size: cover ">                            
                            <input type="submit" value="" class="main_button" name="open_main_command"/>
                        </th>
                        <th>
                        <fmt:message key="plants_in_the_park"></fmt:message>
                        </th>
                        <th>
                        </th>
                        </tr>
                    </thead>
                    <tbody>                    
                        <tr>
                        <td class="left_right_center"></td>
                        <td class="content">  
                            <h2><fmt:message key="park_info"></fmt:message></h2>
                            <table class="content_table">
                                <tr>
                                <th><fmt:message key="name_park"></fmt:message></th>                                      
                            <th><fmt:message key="description"></fmt:message></th>
                            <th><fmt:message key="forester"></fmt:message></th>
                                </tr>
                                <tr>
                                <td>
                                <c:out value="${park.name}"></c:out>
                                </td>                                      
                                <td>
                                <c:out value="${park.description}"></c:out>
                                </td>
                                <td>
                                <c:out value="${park.forester}"></c:out>
                                </td>
                                </tr>                                
                            </table>
                            <br/>
                        <c:if test="${not empty message}">
                            <table class="content_table">
                                <tr>
                                <td>
                                    <fmt:message key="${message}"></fmt:message>
                                    <c:remove var="message" scope="session"></c:remove>
                                    </td>                                      
                                    </tr>                                
                                </table>
                        </c:if>
                        <br/>
                        <c:choose>
                            <c:when test="${not empty orders}">
                                <h2>
                                    <fmt:message key="your_orders"></fmt:message>
                                </h2>
                                <c:forEach var="order" items="${orders}" varStatus="i">
                                    <c:if test="${not empty order.executionDate}">
                                        <c:if test="${empty executed_orders}">
                                            <c:set var="executed_orders" value="not_null"></c:set>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
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
                                    <c:forEach var="order" items="${orders}" varStatus="i">
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
                                                <c:set var="page" value="park" scope="session"></c:set>
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
                                <h2><fmt:message key="no_orders_in_park"></fmt:message></h2>
                            </c:otherwise>
                        </c:choose>   
                        <br/>
                        <c:choose>
                            <c:when test="${not empty park.plantCollection}">
                                <h2><fmt:message key="plants"></fmt:message></h2>
                                    <table class="content_table">
                                        <tr>
                                        <th><fmt:message key="plant_type"></fmt:message></th>                                     
                                    <th><fmt:message key="date_placed"></fmt:message></th>
                                    <th><fmt:message key="plant_sort"></fmt:message></th>
                                    <th><fmt:message key="order"></fmt:message></th>
                                        </tr>
                                    <c:forEach var="plant" items="${park.plantCollection}" varStatus="i">
                                        <c:if test="${not empty plant.plantedDate and empty plant.cutedDate}">
                                            <tr>
                                            <td>
                                                <parktag:getLocaleValueTag locale="${locale}" plantType="${plant.plantInfo}"/>
                                            </td>
                                            <td>
                                                <fmt:formatDate value="${plant.plantedDate}" type="both" timeStyle="short"></fmt:formatDate>
                                                </td>
                                                <td>
                                                <parktag:getLocaleValueTag locale="${locale}" plantSort="${plant.plantInfo}"/>
                                            </td>

                                            <td>
                                                <c:choose>
                                                    <c:when test="${empty plant.order}">
                                                        <input type="radio" name="plant_select" value="${i.index}" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <parktag:getLocaleValueTag locale="${locale}" serviceName="${plant.order.service}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${not empty services}">
                                            <tr>
                                            <td colspan="2">
                                                <select name="service_select">
                                                    <c:forEach var="s" items="${services}" varStatus="i">
                                                        <c:if test="${s.name ne 'Plant'}">
                                                            <option value="${i.index}">
                                                                <parktag:getLocaleValueTag locale="${locale}" serviceName="${s}"/>
                                                            </c:if>
                                                        </c:forEach>
                                                </select>
                                            </td>
                                            <td colspan="2"  align="right">
                                                <input type="submit" value="<fmt:message key="add_order"></fmt:message>" name="add_order_command"/>
                                                </td>
                                                </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="no_services_in_system"></fmt:message>
                                        </c:otherwise>
                                    </c:choose>                                    
                                </table>
                            </c:when>
                            <c:otherwise>
                                <h2><fmt:message key="no_plants_in_park"></fmt:message></h2>
                            </c:otherwise>
                        </c:choose>
                        <br/>
                        <c:choose>
                            <c:when test="${not empty plantInfoCollection}">
                                <h2><fmt:message key="add_plant"></fmt:message></h2>
                                    <table class="content_table">
                                        <tr>
                                        <th>
                                        <fmt:message key="choose_sort_of_plant"></fmt:message>
                                        </th>
                                        <th></th>
                                        </tr>
                                        <tr>
                                        <td>
                                            <select name="plant_info_select">
                                            <c:forEach var="pi" items="${plantInfoCollection}" varStatus="i">
                                                <option value="${i.index}">
                                                    <parktag:getLocaleValueTag locale="${locale}" plantSort="${pi}"/>
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="submit" value="<fmt:message key="add_plant"></fmt:message>" name="add_plant_command"/>
                                        </td>
                                        </tr>
                                    </table>
                            </c:when>
                            <c:otherwise>
                                <h2><fmt:message key="no_types_of_plants"></fmt:message></h2>
                            </c:otherwise>
                        </c:choose>
                        <br/>

                    </td>
                    <td class="left_right_center">                            
                    </td>
                    </tr>
                    <tr class="bottom_center">
                    <td>
                    <td id="bottom_info">
                        <fmt:message key="info"></fmt:message>
                    </td>
                    <td>
                        <a href="park_to_owner.jsp?language=ru&AMP;region=RU">Рус</a>|
                        <a href="park_to_owner.jsp?language=en&AMP;region=US">Eng</a>
                    </td>
                    </tr>
                </tbody>
            </table>     
        </FORM>
    </body>
</html>
