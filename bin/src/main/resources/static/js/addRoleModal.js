//____________________________________________________________________________Upload Role model________________________________
var roleModal = document.getElementById("myRoleModel");

var addRoleCloseSpan = document.getElementsByClassName("closeRM")[0];

// When the user clicks on <span> (x), close the modal
addRoleCloseSpan.onclick = function() {
    roleModal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == roleModal) {
        roleModal.style.display = "none";
    }
}