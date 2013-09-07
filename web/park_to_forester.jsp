<%-- 
    Document   : park_to_forester
    Created on : 20.12.2012, 12:46:35
    Author     : Sergiy Tolok
    Displays to forester details about park 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="/WEB-INF/tlds/parktaglib.tld" prefix ="parktag"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style/park_style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <fmt:setLocale value="${locale}" scope="request"></fmt:setLocale>
        <fmt:setBundle basename="ua.tolok.projects.project4.properties.language_resourse"></fmt:setBundle>
        <title><fmt:message key="show_park"></fmt:message></title>
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
                    <th></th>
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
                            <th><fmt:message key="owner"></fmt:message></th>
                            </tr>
                            <tr>
                            <td>
                                <c:out value="${park.name}"></c:out>
                            </td>                                      
                            <td>
                                <c:out value="${park.description}"></c:out>
                            </td>
                            <td>
                                <c:out value="${park.owner}"></c:out>
                            </td>
                            </tr>                                
                        </table>
                        <br>
                        <c:choose>
                            <c:when test="${not empty park.plantCollection}">
                                <h2><fmt:message key="plants_in_the_park"></fmt:message></h2>
                                <table class="content_table">
                                    <tr>
                                    <th>
                                        <fmt:message key="plant_type"></fmt:message>
                                    </th>
                                    <th>
                                        <fmt:message key="plant_sort"></fmt:message>
                                    </th> 
                                    <th>
                                        <fmt:message key="date_placed"></fmt:message>
                                    </th>
                                    
                                    </tr>
                                    <c:forEach var="plant" items="${park.plantCollection}" varStatus="i">
                                        <c:if test="${not empty plant.plantedDate and empty plant.cutedDate}">
                                            <tr>
                                        <td>
                                            <parktag:getLocaleValueTag locale="${locale}" plantType="${plant.plantInfo}"/>
                                        </td>
                                        <td>
                                            <parktag:getLocaleValueTag locale="${locale}" plantSort="${plant.plantInfo}"/>
                                        </td>
                                        <td>
                                            <c:out value="${plant.plantedDate}"></c:out>
                                        </td>
                                        
                                        </tr>
                                        </c:if>
                                        
                                    </c:forEach>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <h2><fmt:message key="no_plants_in_park"></fmt:message></h2>
                            </c:otherwise>
                        </c:choose>
                        <br>
                    </td>
                    <td class="left_right_center"></td>
                    </tr>
                    <tr class="bottom_center">
                    <td></td>
                    <td id="bottom_info">
                        <fmt:message key="info"></fmt:message>
                    </td>
                    <td>
                        <a href="park_to_forester.jsp?language=ru&AMP;region=RU">Рус</a>|
                        <a href="park_to_forester.jsp?language=en&AMP;region=US">Eng</a>
                    </td>
                    </tr>
                </tbody>
            </table>     
        </FORM>
    </body>
</html>

