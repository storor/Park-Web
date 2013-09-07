<%-- 
    Document   : error_page
    Created on : 06.02.2013, 11:53:25
    Author     : Sergiy Tolok
    Displays to user if exception have hapened
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style/park_style.css" />
    </head>
    <body> 
        <fmt:setLocale value="${locale}"></fmt:setLocale>
        <fmt:setBundle basename="ua.tolok.projects.project4.properties.language_resourse"></fmt:setBundle>
        <FORM action="servlet" method="POST">  
            <table id="main_table">
                <thead>
                    <tr>
                    <th style="background: url(style/bench.png); background-size: cover ">                            
                        <input type="submit" value="" class="main_button" name="open_main_command"/>
                    </th>
                    <th >
                        <fmt:message key="error_page"></fmt:message>    
                    </th>
                    <th>
                    </th>
                    </tr>
                </thead>
                <tbody>                    
                    <tr>
                    <td class="left_right_center"></td>
                    <td class="content" >  
                        <table class="content_table">
                            <tr>
                            <td>
                                <fmt:message key="error_message"></fmt:message>
                            </td>
                            </tr>
                            <tr>
                            <td>
                                <c:out value="${requestScope['javax.servlet.error.message']}" />
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
                        <a href="error_page.jsp?language=ru&AMP;region=RU">Рус</a>|
                        <a href="error_page.jsp?language=en&AMP;region=US">Eng</a>
                    </td>
                    </tr>
                </tbody>
            </table>
        </FORM>
    </body>
</html>
