
<%-- 
    Document   : admin
    Created on : 14.12.2012, 13:48:37
    Author     : Sergiy Tolok
    Provides the ability manage park system
--%>
<%@page import="java.util.Locale"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; char- set=ISO-8859-5" pageEncoding="ISO-8859-5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style/park_style.css" />
    </head>
    <body> 
        <fmt:setLocale value="${locale}"></fmt:setLocale>
        <fmt:setBundle basename="ua.epam.projects.project4.properties.language_resourse"></fmt:setBundle>
            <FORM action="servlet" method="POST">  
                <table id="main_table">
                    <thead>
                        <tr>
                        <th style="background: url(style/bench.png); background-size: cover ">                            
                            <input type="submit" value="" class="main_button" name="open_main_command"/>
                        </th>
                        <th >
                        <fmt:message key="system_park"></fmt:message>                         

                        </th>
                        <th style="background: url(style/log_out.png); background-size: cover ">
                            <input type="submit" value="" class="main_button" name="exit_command"/>
                        </th>
                        </tr>
                    </thead>
                    <tbody>                    
                        <tr>
                        <td class="left_right_center"></td>
                        <td class="content" >  
                            <table class="content_table">
                                <tr>
                                <td colspan="2">
                                <fmt:message key="addition"></fmt:message>
                                </td>
                                </tr>
                                <tr>
                                <td> 
                                    <input type="submit" value="<fmt:message key="create_new_service"></fmt:message>" name="open_create_service_command"/>
                                </td>
                                <td> 
                                    <input type="submit" value="<fmt:message key="create_new_sort_of_plant"></fmt:message>" name="open_create_plant_command"/>
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
                        <a href="admin.jsp?language=ru&AMP;region=RU">Рус</a>|
                        <a href="admin.jsp?language=en&AMP;region=US">Eng</a>
                    </td>
                    </tr>
                </tbody>
            </table>
        </FORM>
    </body>
</html>

