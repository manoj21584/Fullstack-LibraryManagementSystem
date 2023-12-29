var MyTheme = "";
var theme = document.getElementById("stylesheet");
var emailIdFound = document.getElementById("userEmailIdSpan").innerHTML;

function CheckTheTheme() {
    jQuery.ajax({
        type: 'GET',
        cache: true,
        async: false,
        contentType: 'application/json',
        dataType: 'json',
        url: location.origin + "/theme/" + emailIdFound,
        success: function(data) {
            MyTheme = data.myTheme;
        }
    });
    if (MyTheme == "BRIGHT") {
        theme.href = "css/brightTheme.css";
    }
    if (MyTheme == "DARK") {
        theme.href = "css/darkTheme.css";
    }
}

function changeTheme() {
    jQuery.ajax({
        type: 'GET',
        cache: true,
        async: false,
        contentType: 'application/json',
        dataType: 'json',
        url: location.origin + "/theme/" + emailIdFound,
        success: function(data) {
            ThemFound = data.myTheme;
        }
    });
    if (ThemFound === "DARK") {
        var inputPayload = {
            "myTheme": "BRIGHT"
        }
        var inputPayloadString = JSON.stringify(inputPayload);
        jQuery.ajax({
            type: 'POST',
            cache: true,
            async: false,
            contentType: 'application/json',
            dataType: 'json',
            url: location.origin + "/theme/" + emailIdFound,
            data: inputPayloadString,
            success: function(data) {
                console.log("Bright theme applied successfully.");
            },
            error: function(data) {
                console.log("Error while changing to bright theme.");
            }
        });
    }
    if (ThemFound === "BRIGHT") {
        var inputPayload = {
            "myTheme": "DARK"
        }
        var inputPayloadString = JSON.stringify(inputPayload);
        jQuery.ajax({
            type: 'POST',
            cache: true,
            async: false,
            contentType: 'application/json',
            dataType: 'json',
            url: location.origin + "/theme/" + emailIdFound,
            data: inputPayloadString,
            success: function(data) {
                console.log("Dark theme applied successfully.");
            },
            error: function(data) {
                console.log("Error while changing to dark theme.");
            }
        });
    }
    CheckTheTheme();
}