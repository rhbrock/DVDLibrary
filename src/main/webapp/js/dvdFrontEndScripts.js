/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function passId(id){
 
   $('#deleteButton').append('<a href="${pageContext.request.contextPath}/deleteDvd?dvdId=' + id + '"><button type="button" class="btn btn-danger" '
            + 'data-dismiss="modal">Yes</button>');
 }

function clearDelete(){
}