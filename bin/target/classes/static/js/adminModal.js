//____________________________________________________________Admin model_______________________________________________________
var adminModel = document.getElementById("myAdminModel");

// Get the button that opens the modal
var adminModelButton = document.getElementById("openAdminModel");

// Get the <span> element that closes the modal
var adminSpan = document.getElementsByClassName("closeA")[0];

// When the user clicks the button, open the modal 
adminModelButton.onclick = function() {
    adminModel.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
adminSpan.onclick = function() {
    adminModel.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == adminModel) {
        adminModel.style.display = "none";
    }
}