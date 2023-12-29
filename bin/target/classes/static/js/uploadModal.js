//____________________________________________________________________________Upload document model________________________________
var uploadModel = document.getElementById("myUploadModel");

// Get the button that opens the modal
var uploadOpenButton = document.getElementById("openUploadModel");

// Get the <span> element that closes the modal
var uploadCloseSpan = document.getElementsByClassName("closeU")[0];

// When the user clicks the button, open the modal 
uploadOpenButton.onclick = function() {
    uploadModel.style.display = "block";
}

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