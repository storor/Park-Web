<%-- 
    Document   : park_to_owner
    Created on : 18.12.2012, 17:38:16
    Author     : Sergiy Tolok   
    Provides the ability to create new park
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="/WEB-INF/tlds/parktaglib.tld" prefix ="parktag"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style/park_style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <fmt:setLocale value="${locale}" scope="request"></fmt:setLocale>
        <fmt:setBundle basename="ua.tolok.projects.project4.properties.language_resourse"></fmt:setBundle>
        <title> <fmt:message key="create_new_park"></fmt:message></title>
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
                        <fmt:message key="create_new_park"></fmt:message>
                    </th>
                    <th ></th>
                    </tr>
                </thead>
                <tbody>                    
                    <tr>
                    <td class="left_right_center"></td>
                    <td class="content">
                        <table class="content_table">
                            <tr>
                            <td><fmt:message key="name_park"></fmt:message></td>      
                            <td><input type="text" size="30" 
                                       name="name" value="park"></td>
                            </tr>
                            <tr>
                            <td><fmt:message key="description"></fmt:message></td>
                            <td>
                                <textarea name="description" cols="40" rows="3"></textarea>
                            </td>
                            </tr>                                 
                            <tr>
                            <td><fmt:message key="forester"></fmt:message></td>
                            <td>
                                <select name="forster_select">
                                    <c:forEach var="forester" items="${foresters}" varStatus="i">
                                        <option value="${forester.foresterId}">
                                            <c:out value="${forester}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </td>
                            </tr>
                            <tr>
                            <td colspan="2">
                                <INPUT name="add_park_command" type="submit" value="<fmt:message key="сreate_park"></fmt:message>">
                            </td>
                            </tr>                                
                        </table>
                    </td>
                    <td class="left_right_center"></td>
                    </tr>
                    <tr class="bottom_center">
                    <td></td>
                    <td id="bottom_info">
                        <fmt:message key="info"></fmt:message>
                    </td>
                    <td>
                        <a href="create_park.jsp?language=ru&AMP;region=RU">Рус</a>|
                        <a href="create_park.jsp?language=en&AMP;region=US">Eng</a>
                    </td>
                    </tr>
                </tbody>
            </table>  
        </FORM>

    </body>
</html>
