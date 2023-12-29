//____________________________________________________________________________Upload document model________________________________
var uploadModel = document.getElementById("myEditModel");

var uploadCloseSpan = document.getElementsByClassName("closeE")[0];

// When the user clicks on <span> (x), close the modal
uploadCloseSpan.onclick = function() {
    uploadModel.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == uploadModel) {
        uploadModel.style.display = "none";
    }
}