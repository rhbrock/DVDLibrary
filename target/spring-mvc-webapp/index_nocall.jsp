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
            <div class='form-row'>
                <form role='form' id='dvdForm'>
                    <div class='form-group col-md-4'>
                        <button type='button' class='btn btn-default'
                                id='createDvdButton'>
                            Create Dvd
                        </button>
                    </div>
                    <div class='form-group col-md-2'>
                        <button type='button' class='btn btn-default'
                                id='searchButton'>
                            Search
                        </button>
                    </div>
                    <div class='form-group col-md-2'>
                        <select class='form-control' id='searchCategory' required>
                            <option value="">Search Category</option>
                            <option value="title">Title</option>
                            <option value="releaseYear">Release Year</option>
                            <option value="director">Director</option>
                            <option value="rating">Rating</option>
                        </select>
                    </div>
                    <div class='form-group col-md-4'>
                        <input type="text" class="form-control"
                               id='searchString' placeholder="Search Term" required/>
                    </div>
                </form>

                <ul class="list-group" id="errorMessages"></ul>
            </div>

            <div class='row'>
                <table id='dvdTable' class='table 
                       table-hover table-bordered'>
                    <thead style='background-color:gainsboro'>
                        <tr>
                            <th class='text-center'>Title</th>
                            <th class='text-center'>Release Date</th>
                            <th class='text-center'>Director</th>
                            <th class='text-center'>Rating</th>
                            <th class='text-center'></th>
                        </tr>
                    </thead>
                    <c:forEach var="dvd" items="${dvds}">
                        <tr class="text-center">
                            <td style="border:none"><a style="text-decoration:underline"><c:out value='${dvd.title}'/></a></td>
                            <td style="border:none"><c:out value='${dvd.releaseDate}'/></td>
                            <td style="border:none"><c:out value='${dvd.director}'/></td>
                            <td style="border:none"><c:out value='${dvd.rating}'/></td>
                            <td style="border:none">
                                <a style="text-decoration:underline">Edit | </a>
                                <a style="text-decoration:underline">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>

