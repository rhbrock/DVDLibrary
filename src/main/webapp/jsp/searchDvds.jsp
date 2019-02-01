<%-- 
    Document   : searchDvds
    Created on : Oct 2, 2018, 9:45:54 AM
    Author     : Roger Brock
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <title>Dvd Library</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">      


    </head>
    <body>
        <div class="container">

            <div class='row'>
                <div class='col-md-3'>
                    <a href="${pageContext.request.contextPath}/displayCreateForm">
                        <button type='button' class='btn btn-default' id='createDvdButton'>
                            Create Dvd</button></a>
                </div>
                <div class="col-md-2">
                    <a href="${pageContext.request.contextPath}/displayMainPage">
                        <button type='button' class='btn btn-default'
                                id='infoBackButton'>
                            All Dvds</button></a>
                </div>
                <sf:form role="form" modelAttribute="dvdsearch"
                         action="search" method="GET">   
                    <div class='col-md-1'>
                        <input type='submit' value="Search" class='btn btn-default' id='searchButton'>
                    </div>
                    <div class='col-md-3'>

                        <sf:select class="form-control" path="searchType">
                            <option value="${searchType}" label="${selectedSearchType}"></option>
                            <option value="dvdTitle">Title</option>
                            <option value="dvdReleaseYear">Release Year</option>
                            <option value="directorId">Director</option>
                            <option value="mpaaRating">Rating</option>
                        </sf:select>
                        <sf:errors path="searchType" cssclass="error"></sf:errors>
                        </div>
                        <div class='col-md-3'>

                        <sf:input type="text" class="form-control" id="searchString"
                                  path="searchString" placeholder="Search Term"/>
                        <sf:errors path="searchString" cssclass="error"></sf:errors>
                        </div>
                </sf:form>  
            </div>

            <ul class="list-group" id="errorMessages"></ul>


            <div class='row'>
                <table class='table table-hover table-bordered'>
                    <thead style='background-color:gainsboro'>
                        <tr>
                            <th class='text-center'>Title</th>
                            <th class='text-center'>Release Date</th>
                            <th class='text-center'>Director</th>
                            <th class='text-center'>Rating</th>
                            <th class='text-center'></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var='dvd' items='${dvds}'>
                            <tr class="text-center">
                                <td style="border:none">
                                    <a href="dvdInfo?dvdId=${dvd.dvdId}" style="text-decoration:underline">
                                        <c:out value='${dvd.title}'/></a></td>
                                <td style="border:none"><c:out value='${dvd.releaseYear}'/></td>
                                <td style="border:none"><c:out value='${dvd.director.directorName}'/></td>
                                <td style="border:none"><c:out value='${dvd.rating}'/></td>
                                <td style="border:none">
                                    <a style="text-decoration:underline "
                                       href="${pageContext.
                                               request.contextPath}/displayEditForm?dvdId=${dvd.dvdId}">Edit</a> |
                                    <a style="text-decoration:underline" data-toggle="modal"
                                       href="${pageContext.
                                               request.contextPath}/deleteDvd?dvdId=${dvd.dvdId}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>


                        </div>
                        </div>      
                    </tbody>
                </table>
            </div>


            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/dvdFrontEndScripts.js"></script>

    </body>
</html>

