(function () {
    "use strict";

    // CONSTANTS
   // const apiUrl = "https://college-cookbook-api.herokuapp.com";

   /*Change this for VM */
    const apiUrl = "http://137.112.104.112:4000";
    const Url = "http://137.112.104.112"

    /*Change this for running on local machine */
    // const apiUrl = "http://localhost:4000";
    // const URL = "http://localhost"
    function setup() {
        document.getElementById("button_check").onclick = function getUser(){
            console.log("clicked button");
            $.ajax({
                url: apiUrl + "/get_request",
                type: 'GET',
                data: {},
                dateType: 'JSON',
                success: (data) => {
                    console.log("Connected to Backend");
                },
                error: (request, status, error) => {
                    console.log("Error on backend");
                }
            });
        }
    }
    window.onload = setup;
})();