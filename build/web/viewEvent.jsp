<%-- 
    Document   : viewEvent
    Created on : Sep 21, 2021, 8:14:58 PM
    Author     : Admin
--%>

<%@page import="DTO.EventDTO"%>
<%@page import="java.util.List"%>
<%@page import="Extension.AI"%>
<%@page import="Extension.AppDirectory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Events in FPT University</title>
        <%
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
        %>
    </head>
    <body> 
 






       <p class="message">${requestScope.EVENT_MESSAGE}</p> 
            <div class="member-list">


                <c:if test="${not empty requestScope.LIST_EVENT}">

                    <c:forEach var="event" varStatus="counter" items="${requestScope.LIST_EVENT}">
                        <div class="member-item">
                            <div class="card">


                                <!-- -->
                                <div class="img-div"><img src="./images/${event.eventId}.png" onerror="this.src='./images/default.png'" width="100" height="120" /></div>

                                <div class="member-content">
                                    <h3 class="member-title">
                                        <a  href="ViewEventDetailsController?eventId=${event.eventId}&index=${index}">${event.title}</a>

                                    </h3>
                                    <div class="member-foot">
                                        <p><i class="far fa-calendar"></i> ${event.startDatetime}</p>
                                        <p><i class="far fa-clock"></i> ${event.startSlot.getStart()} - ${event.endSlot.getEnd()}</p>
                                    </div>

                                    <%--      <p>End: ${event.endSlot.getEnd()}</p> --%>
                                    <%--       <a class="member-btn"  href="ViewEventDetailsController?eventId=${event.eventId}&index=${index}">Show More</a> --%>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>



            <div class="switchpage">
                <c:forEach begin="1" end="${sessionScope.endPage}" var="i">

                    <c:if test="${sessionScope.view_mode eq 'normal'}">
                        <a class="page-btn" href="ViewEventController?index=${i}&search=${Search}">${i}</a>
                    </c:if>
                    <c:if test="${sessionScope.view_mode eq 'followed'}">
                        <a class="page-btn" href="ViewFollowedEventController?index=${i}&search=${Search}">${i}</a>
                    </c:if>

                </c:forEach>
            </div>


    </body>
</html>
