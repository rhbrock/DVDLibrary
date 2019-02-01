<%-- 
    Document   : editDvd
    Created on : Sep 24, 2018, 8:51:18 AM
    Author     : Roger Brock
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Dvd Library</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Bootstrap core CSS-->
        <link href="css/bootstrap.css" rel="stylesheet">
    </head>

    <body>
        <div class='container'>

            <div id="editView" >
                <h1>Edit Dvd: <c:out value="${dvd.title}"/></h1>
                <hr>


                <sf:form class="form-horizontal" role="form" modelAttribute="dvd"
                         action="editDvd" method="POST">
                    <div class="form-group">
                        <label for="dvdTitle" class="col-md-4 control-label">Dvd Title:</label>
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" id="dvdTitle"
                                      path="title" placeholder="Dvd Title"/>
                            <sf:errors path="title" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="releaseYear" class="col-md-4 control-label">Released:</label>
                            <div class="col-md-8">
                            <sf:input type="text" class="form-control" id="releaseYear"
                                      path="releaseYear" placeholder="Released"/>
                            <sf:errors path="releaseYear" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="director" class="col-md-4 control-label">Director:</label>                          
                            <div class="col-md-8">
                            <sf:input type="text" class="form-control" id="director"
                                      path="director.directorName" placeholder="director"/>
                            <sf:hidden path="director.directorId"/>
                            <sf:errors path="director.directorName" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="studio" class="col-md-4 control-label">Studio:</label>
                            <div class="col-md-8">
                            <sf:input type="text" class="form-control" id="add-phone"
                                      path="studio.studioName" placeholder="studio"/>
                            <sf:hidden path="studio.studioId"/>
                            <sf:errors path="studio.studioName" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rating" class="col-md-4 control-label">Rating:</label>
                            <div class="col-md-1">
                            <sf:input type="text" class="form-control" id="rating"
                                      path="rating" placeholder="rating"/>
                            <sf:errors path="rating" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="review" class="col-md-4 control-label">Review:</label>
                            <div class="col-md-8">
                            <sf:input type="TextArea" class="form-control" id="review"
                                      path="review" placeholder="review"/>
                            <sf:errors path="review" cssclass="error"></sf:errors>
                            <sf:hidden path="dvdId"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Update Dvd"/>
                            <a href="${pageContext.request.contextPath}/displayMainPage"><button type='button' class='btn btn-default'
                                                                                                 id='infoBackButton'>
                                    Cancel</button><a/>
                        </div>
                    </div>
                </sf:form>  

            </div>
            <script src="js/jquery-2.2.4.min.js"></script>
            <script src="js/bootstrap.js"></script>
    </body>
</html>
