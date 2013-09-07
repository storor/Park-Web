<%@page import="java.util.Locale"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : 14.12.2012, 13:48:37
    Author     : Sergiy Tolok
    Provides the ability to sign in registred users
--%>

<%@ page language="java" contentType="text/html; char- set=ISO-8859-5" pageEncoding="ISO-8859-5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style/park_style.css">
    </head>
    <body> 
        <fmt:setLocale value="${locale}"></fmt:setLocale>
        <fmt:setBundle basename="ua.tolok.projects.project4.properties.language_resourse"></fmt:setBundle>
            <FORM action="servlet" method="POST">  
                <table id="main_table">
                    <thead>
                        <tr>
                        <th>
                            <img src="style/bench.png" width="100" height="100" alt="bench"/>
                        </th>
                        <th >
                        <fmt:message key="system_park"></fmt:message>                         
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
                                <fmt:message key="login"></fmt:message>
                                </td>
                                <td>
                                    <input type="text" size="45" name="login" maxlength="15">
                                </td>
                                </tr>
                                <tr>
                                <td>
                                <fmt:message key="password"></fmt:message>
                                </td>
                                <td>
                                    <input type="password" size="45" name="password">
                                </td>
                                </tr>
                                <tr>   
                                <td colspan="2" >                            
                                    <INPUT name="login_command" type="submit" value="<fmt:message key="sign_in" />">
                                <INPUT type="submit" name="registration_command" id="login" value="<fmt:message key="registration" />"> 
                            </td>
                            </tr>

                            <c:if test="${not empty message}">
                                <tr>   
                                <td colspan="2">       
                                    <fmt:message key="${message}"></fmt:message>
                                    <c:remove var="message" scope="session"></c:remove>
                                    </td>
                                    </tr>
                            </c:if>
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
                        <a href="index.jsp?language=ru&AMP;region=RU">Рус</a>|
                        <a href="index.jsp?language=en&AMP;region=US">Eng</a>
                    </td>
                    </tr>
                </tbody>
            </table>
        </FORM>
    </body>
</html>
