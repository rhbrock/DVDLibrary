<%-- 
    Document   : dvdInfo
    Created on : Sep 20, 2018, 9:13:38 AM
    Author     : Roger Brock
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <title>Dvd Library</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">      


    </head>
    <body>
        <div class="container">
            <h1><c:out value='${dvd.title}'/></h1>
            <hr>
            <p>
                Release Year: <c:out value='${dvd.releaseYear}'/>
            </p>
            <p>
                Director: <c:out value='${dvd.director.directorName}'/>
            </p>
            <p>
                Studio: <c:out value='${dvd.studio.studioName}'/>
            </p>
            <p>
                Rating: <c:out value='${dvd.rating}'/>
            </p>
            <p> 
                Review: <c:out value='${dvd.review}'/>
            </p>

            <button type='button' class='btn btn-default'
                    id='infoBackButton'>
                <a href="${pageContext.request.contextPath}/displayMainPage">
                    Back</a>
            </button>

        </div>
        <script src="js/jquery-2.2.4.min.js"></script>
        <script src="js/bootstrap.js"></script>
    </body>
</html>