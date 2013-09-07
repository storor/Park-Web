<%-- 
    Document   : create_order
    Created on : 20.12.2012, 14:32:07
    Author     : Sergiy Tolok
    Provides the ability to create new type of service
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri ="/WEB-INF/tlds/parktaglib.tld" prefix ="parktag"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style/park_style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <fmt:setLocale value="${locale}" scope="request"></fmt:setLocale>
        <fmt:setBundle basename="ua.tolok.projects.project4.properties.language_resourse"></fmt:setBundle>
        <fmt:requestEncoding value="UTF-8" />
        <title> <fmt:message key="create_new_service"></fmt:message></title>
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
                            <fmt:message key="create_new_service"></fmt:message>
                        </th>
                        <th>
                        </th>
                    </tr>
                </thead>
                <tbody>                    
                    <tr>
                        <td class="left_right_center"></td>
                        <td class="content">  
                            <h2><fmt:message key="service_info"></fmt:message></h2>
                            <table class="content_table">
                                <tr>
                                    <th></th>
                                    <th><fmt:message key="eng"></fmt:message></th>
                                    <th><fmt:message key="rus"></fmt:message></th>
                                </tr>
                                <tr>
                                    <th>
                                        <fmt:message key="name_service"></fmt:message>
                                    </th>   
                                    <td>
                                        <input type="text" size="45" name="name" value="Cut down">
                                    </td>  
                                    <td>
                                        <input type="text" size="45" name="name_rus" value="Спилить">
                                    </td>
                                </tr>
                                <tr>
                                    <th>
                                        <fmt:message key="description"></fmt:message>
                                    </th>                                                                        
                                    <td>
                                        <textarea name="description" cols="40" rows="3">
                                            <c:out value="Cut, burn, whatever you want ..."></c:out>
                                        </textarea>
                                    </td>
                                    <td>
                                        <textarea name="description_rus" cols="40" rows="3">
                                            <c:out value="Срубать, уничтожичь, сжечь и утопить"></c:out>
                                        </textarea>
                                    </td>
                                </tr>
                            </table>
                            <input type="submit" value="<fmt:message key="create_new_service"></fmt:message>" name="create_service_command"/>
                        </td>
                        <td class="left_right_center"></td>
                    </tr>
                    <tr class="bottom_center">
                        <td ></td>
                        <td id="bottom_info">
                       <fmt:message key="info"></fmt:message>
                    </td>
                        <td>
                             <a href="create_service.jsp?language=ru&AMP;region=RU">Рус</a>|
                            <a href="create_service.jsp?language=en&AMP;region=US">Eng</a>
                        </td>
                    </tr>
                </tbody>
            </table>        
        </FORM>

    </body>
</html>

