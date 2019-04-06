<html>
<head>

    <Title>Rejestracja fachowca</Title>
    <meta charset="utf-8">

    <style>
        div[id]{
            color: red;
        }

        #map {
            height: 200;
            width: 200;
        }
    </style>

</head>
<body>
<h2>Rejestracja fachowca</h2>

<#--TODO: 1. connect with Google API to get location
2. Make separate files for js and css code.-->

<form method="post" action="/signup-prof">

    <div>E-mail: <input type="text" id="register-email" name="email" oninput="validateFieldEmail()" /><br/><br/></div>
    <div id="email-validation"></div>
    <div>Password: <input type="password" id="register-password" name="password" oninput="validateFieldPassword()"/><br/><br/></div>
    <div id="password-validation"></div>
    <div>Confirm password: <input type="password" id="register-confirm-password" name="confirmPassword"
                                  oninput="validateConfirmFieldPassword()"/><br/><br/></div>
    <div id="confirm-password-validation"></div>
    <div>Name: <input type="text" id="register-name" name="name"
                      oninput="validateNameField()"/><br/><br/></div>
    <div id="name-validation"></div>
    <div>Surname: <input type="text" id="register-surname" name="surname"
                         oninput="validateSurnameField()"/><br/><br/></div>
    <div id="surname-validation"></div>
    <div>Phone number: <input type="text" id="register-phone-number" name="phoneNumber"
                              oninput="validatePhoneNumberField()"/><br/><br/></div>
    <div id="phone-number-validation"></div>

    Profession: <select name="profession">
           <#list professions as profession>
               <option value = "${profession}">${profession.fullName}</option>
           </#list>
</select><br/><br/>

    City:  <select name="city">
           <#list cities as city>
               <option value = "${city}">${city.fullName}</option>
           </#list>
</select><br/><br/>

    City district:  <select name="cityDistrict">
           <#list districts as district>
             <option value = "${district}">${district.fullName}</option>
           </#list>
    </select><br/><br/>

    <div>Latitude: <input type="number" name="latitude"/><br/><br/></div>
    <div>Longitude: <input type="number" name="longitude"/><br/><br/></div>

    <input type="submit" name="save" value="Zarejestruj" onclick="checkEverything()"/><br/><br/>

<#--    AIzaSyDA3SOTQlxW22gZhsIyKHgkq_1gVYsEhHM-->

</form>


<div id="map"></div>

<script>
    var map;
    var service;
    var infowindow;

    function initMap() {
        infowindow = new google.maps.InfoWindow();
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: 52.2298508, lng: 21.0095361},
            zoom: 8
        });

        var request = {
            query: 'Museum of Contemporary Art Australia',
            fields: ['name', 'geometry'],
        };

        service = new google.maps.places.PlacesService(map);

        service.findPlaceFromQuery(request, function(results, status) {
            if (status === google.maps.places.PlacesServiceStatus.OK) {
                for (var i = 0; i < results.length; i++) {
                    createMarker(results[i]);
                }
                map.setCenter(results[0].geometry.location);
            }
        });

        function createMarker(place) {
            var marker = new google.maps.Marker({
                map: map,
                position: place.geometry.location
            });

            google.maps.event.addListener(marker, 'click', function () {
                infowindow.setContent(place.name);
                infowindow.open(map, this);
            });
        }

    }
</script>



<script>

    //Check al the fields (after the button to submit data is clicked)
    function checkEverything(){
        var res1 = validateFieldEmail();
        var res2 = validateFieldPassword();
        var res3 = validateConfirmFieldPassword();
        var res4 = validateNameField();
        var res5 = validateSurnameField();
        var res6 = validatePhoneNumberField();
        if (!(res1 && res2 && res3 && res4 && res5 && res6)){
            alert('Niepoprawne dane')
        }
    }


    //Check if the content of the field for e-mail is a valid e-mail address.
    function validateFieldEmail(){
        let element = document.getElementById('email-validation');
        if (validateEmail('register-email')){
            element.innerText = "";
            return true;
        } else if (isEmpty('register-email')){
            element.innerText = "Podaj swój adres e-mail."
            return false;
        } else {
            element.innerText = "Podany przez Ciebie adres e-mail jest nieprawidłowy.";
            return false;
        }
    }

    //Check if password has between 8 and 20 characters and does not contain whitespace characters.
    function validateFieldPassword(){
        let expression = /\s+/;
        let password = document.getElementById('register-password').value;
        let element = document.getElementById('password-validation');
        if (expression.test(password)){
            element.innerHTML = "Hasło nie może zawierać spacji, tabulatorów ani innych 'białych znaków'";
            return false;
        } else if ((!validateMinLengthString('register-password', 8) || !validateMaxLengthString('register-password', 20))){
            element.innerHTML = "Hasło musi zawierać pomiędzy 8 a 20 znaków.";
            return false;
        } else if (isEmpty('register-password')){
            element.innerText = "Podaj hasło."
            return false;
        } else {
            element.innerHTML = "";
            return true;
        }

        if (!(document.getElementById('register-confirm-password').value==="")){
            validateConfirmFieldPassword();
        }

    }

    //Check if confirm-password is the same as password.
    function validateConfirmFieldPassword(){
        let element = document.getElementById('confirm-password-validation');
        let check = document.getElementById('register-password').value === document.getElementById('register-confirm-password').value;
        if (!check){
            element.innerHTML = "Hasło jest niezgodne.";
            return false;
        } else if (isEmpty('register-password')) {
            element.innerText = "Potwierdź hasło."
            return false;
        } else {
            element.innerHTML = "";
            return true;
        }
    }


    //Check if name contains only letters and whitespace characters
    function validateNameField(){
        let htmlElement = document.getElementById('name-validation');
        let valueOfField = document.getElementById('register-name').value;
        if (validateString(valueOfField)){
            htmlElement.innerHTML = "";
            return true;
        } else if (valueOfField.replace(/\s/g,'').length===0) {
            htmlElement.innerHTML = "Wpisz imię.";
            return false;
        } else {
            htmlElement.innerHTML = "Niepoprawne imię. Imię musi zawierać tylko litery.";
            return false;
        }
    }


    //Check if surname contains only letters and white space characters
    function validateSurnameField(){
        let htmlElement = document.getElementById('surname-validation');
        let valueOfField = document.getElementById('register-surname').value;
        if (validateString(valueOfField)){
            htmlElement.innerHTML = "";
            return true;
        } else if (valueOfField.replace(/\s/g,'').length===0) {
            htmlElement.innerHTML = "Wpisz nazwisko.";
            return false;
        } else {
            htmlElement.innerHTML = "Niepoprawne nazwisko. Nazwisko musi zawierać tylko litery.";
            return false;
        }
    }



    //Only nine digits telephone numbers starting from 0048 or +48 or just bare nine digits phone numbers are accepted.
    function validatePhoneNumberField(){
        let expression = /^\d{9}$|^(0048)(\d{9})$|^(\+48)\d{9}$/;
        let phoneNumber = document.getElementById('register-phone-number').value.replace(/-|\s/g, "");
        let htmlElement = document.getElementById('phone-number-validation');
        if (expression.test(phoneNumber)){
            htmlElement.innerHTML = "";
            return true;
        } else if (isEmpty('register-phone-number')) {
            htmlElement.innerHTML = "Wpisz numer telefonu.";
            return false;
        } else {
            htmlElement.innerHTML = "Niepoprawny numer telefonu.";
            return false;
        }

    }


    //Checking if content of a given field is a valid e-mail address using regex expression from validate.js library
    function validateEmail(fieldID) {
        let expression = /^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])$/i;
        let email = document.getElementById(fieldID).value;
        return expression.test(email.toLowerCase());
    }



    //Util functions
    function validateString(valueOfField){
        let letters = /^[a-zA-Z\s]+$/;
        return letters.test(String(valueOfField));
    }

    function validateMaxLengthString(fieldID, maxLength){
        let name = document.getElementById(fieldID).value.replace(/\s/g,'');
        return name.length<=maxLength;
    }

    function validateMinLengthString(fieldID, minLength){
        let name = document.getElementById(fieldID).value.replace(/\s/g,'');
        return name.length>=minLength;
    }

    function isEmpty(fieldID){
        let len = document.getElementById(fieldID).value.replace(/\s/g,'').length;
        return len===0;
    }


</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDA3SOTQlxW22gZhsIyKHgkq_1gVYsEhHM&callback=initMap"
        async defer></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDA3SOTQlxW22gZhsIyKHgkq_1gVYsEhHM&libraries=places&callback=initMap"
        async defer></script>
</body>
</html>