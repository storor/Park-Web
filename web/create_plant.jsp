<%-- 
    Document   : create_plant
    Created on : 19.12.2012, 15:08:22
    Author     : Sergiy Tolok
    Provides the ability to create new type of plant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="/WEB-INF/tlds/parktaglib.tld" prefix ="parktag"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style/park_style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <fmt:setLocale value="${locale}" scope="request"></fmt:setLocale>
        <fmt:setBundle basename="ua.epam.projects.project4.properties.language_resourse"></fmt:setBundle>
        <title> <fmt:message key="сreate_plant"></fmt:message></title>
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
                        <fmt:message key="create_plant"></fmt:message>
                    </th>
                    <th></th>
                    </tr>
                </thead>
                <tbody>                    
                    <tr>
                    <td class="left_right_center"></td>
                    <td class="content">  
                        <c:choose>
                            <c:when test="${not empty plantInfoCollection}">
                                <h2><fmt:message key="plants"></fmt:message></h2>
                                <table class="content_table">
                                    <tr>
                                    <th><fmt:message key="plant_type"></fmt:message>
                                        <fmt:message key="eng"></fmt:message></th>
                                    <th><fmt:message key="plant_type"></fmt:message>
                                        <fmt:message key="rus"></fmt:message></th>
                                    <th><fmt:message key="plant_sort"></fmt:message>
                                        <fmt:message key="eng"></fmt:message></th>
                                    <th><fmt:message key="plant_sort"></fmt:message>
                                        <fmt:message key="rus"></fmt:message></th>
                                        </tr>                                        

                                    <c:forEach var="pi" items="${plantInfoCollection}" varStatus="i">
                                        <tr>
                                        <td><c:out value="${pi.type}"></c:out></td>
                                        <td><c:out value="${pi.typeRu}"></c:out></td>
                                        <td><c:out value="${pi.sort}"></c:out></td>
                                        <td><c:out value="${pi.sortRu}"></c:out></td>
                                        </tr>
                                    </c:forEach>



                                </table>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="no_types_of_plants"></fmt:message>
                            </c:otherwise>
                        </c:choose>

                        <h2><fmt:message key="plant_info"></fmt:message></h2>      
                        <table class="content_table">
                            <tr>
                            <th></th>
                            <th><fmt:message key="eng"></fmt:message></th>
                            <th><fmt:message key="rus"></fmt:message></th>
                            </tr>
                            <tr>
                            <th>
                                <fmt:message key="plant_type"></fmt:message>
                            </th>   
                            <td>
                                <input type="text" size="45" name="type" value="tree">
                            </td> 
                            <td>
                                <input type="text" size="45" name="typeRu" value="tree">
                            </td>
                            </tr>
                            <tr>
                            <th>
                                <fmt:message key="plant_sort"></fmt:message>
                            </th>                                                                        
                            <td>
                                <input type="text" size="45" name="sort" value="Apple">
                            </td>
                            <td>
                                <input type="text" size="45" name="sortRu" value="tree">
                            </td>
                            </tr> 
                        </table>
                        <input type="submit" value="<fmt:message key="create_plant"></fmt:message>" name="create_plant_command"/>
                    </td>
                    <td class="left_right_center"></td>
                    </tr>
                    <tr class="bottom_center">
                    <td ></td>
                    <td id="bottom_info">
                        <fmt:message key="info"></fmt:message>
                    </td>
                    <td>
                        <a href="create_plant.jsp?language=ru&AMP;region=RU">Рус</a>|
                        <a href="create_plant.jsp?language=en&AMP;region=US">Eng</a>
                    </td>
                    </tr>
                </tbody>
            </table>    
        </FORM>

    </body>
</html>
