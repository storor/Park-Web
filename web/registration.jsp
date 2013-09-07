<%-- 
    Document   : registration
    Created on : 15.12.2012, 14:56:26
    Author     : Sergiy Tolok
    Provides the ability to register new users
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="/WEB-INF/tlds/parktaglib.tld" prefix ="parktag"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style/park_style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <fmt:setLocale value="${locale}" scope="request"></fmt:setLocale>
        <fmt:setBundle basename="ua.epam.projects.project4.properties.language_resourse"></fmt:setBundle>
        <title> <fmt:message key="registration"></fmt:message></title>
        </head>
        <body>
            <FORM method="post" action="servlet">
                <table id="main_table">
                    <thead>
                        <tr class="top_head">
                        <td id="left_side" style="background: url(style/bench.png); background-size: cover ">                            
                            <input type="submit" value="" class="main_button" name="open_main_command"/>
                        </td>
                        <th id="top_center">
                        <fmt:message key="registration"></fmt:message>
                        </th>
                        <th id="right_side"></th>
                        </tr>
                    </thead>
                    <tbody>                    
                        <tr>
                        <td class="left_right_center"></td>
                        <td class="content">
                            <table class="content_table">
                                <tr>
                                <td><fmt:message key="login"></fmt:message>:</td>
                                <td><input type="text" size="30" 
                                           name="login" value="login"></td>
                                </tr>
                                <tr>
                                <td><fmt:message key="password"></fmt:message></td>
                                <td><input type="text" size="30" 
                                           name="password" value="pass"></td>
                                </tr>
                                <tr>
                                <td><fmt:message key="confirn_password"></fmt:message>:</td>
                                <td><input type="text" size="30" 
                                           name="conf_password" value="pass"></td>
                                </tr>
                                <tr>
                                <td><fmt:message key="first_name"></fmt:message>:</td>
                                <td><input type="text" size="30" 
                                           name="firstname"></td>
                                </tr>
                                <tr>
                                <td><fmt:message key="last_name"></fmt:message>:</td>
                                <td><input type="text" size="30" 
                                           name="lastname"></td>
                                </tr>
                                <tr>
                                <td><fmt:message key="midle_name"></fmt:message>:</td>
                                <td><input type="text" size="30" 
                                           name="midlename"></td>
                                </tr>                                
                                <tr>
                                <td><fmt:message key="role"></fmt:message>:</td>
                                <td>
                                    <input type="radio" name="role" value="forester"> 
                                <fmt:message key="forester"></fmt:message> 
                                    <input type="radio" name="role" value="owner" checked> 
                                <fmt:message key="owner"></fmt:message> 
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
                        <input type="submit" name="confirm_registration_command" value="<fmt:message key="registration"></fmt:message>">

                        </td>
                        <td class="left_right_center"></td>
                        </tr>
                        <tr class="bottom_center">
                        <td class="bottom_center"></td>
                        <td id="bottom_info">
                        <fmt:message key="info"></fmt:message>
                    </td>
                    <td>
                        <a href="registration.jsp?language=ru&AMP;region=RU">Рус</a>|
                        <a href="registration.jsp?language=en&AMP;region=US">Eng</a>
                    </td>
                    </tr>
                </tbody>
            </table>
        </FORM>
    </body>
</html>
