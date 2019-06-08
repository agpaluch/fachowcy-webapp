<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<#--    <script type="text/javascript" src="formValidation.js"></script>
    <script type="text/javascript" src="googleAPI.js"></script>-->
</head>
<body>

<main>
    <h2>Rejestracja fachowca</h2>
        <form id="form">


        Profesja: <select name="profession" id="register-profession">
           <#list professions as prof>
               <#if inputData.profession?? && inputData.profession==prof>
                   <option value="${prof}" selected>${prof.fullName}</option>
               <#else>
               <option value="${prof}">${prof.fullName}</option>
               </#if>
           </#list>
    </select><br/><br/>



    <#include "signup-form.ftlh">
    </form>



</main>



<#include "JSform.ftlh">


<script>


    (function() {

        document.getElementById('submitButton').addEventListener('click', (clickEvent) => {
            clickEvent.preventDefault();
   /*         if (!checkEverything()) {
                return;
            }*/

            const inputEmail = document.getElementById('register-email');
            const inputPassword = document.getElementById('register-password');
            const inputConfirmPassword = document.getElementById('register-confirmPassword');
            const inputName = document.getElementById('register-name');
            const inputSurname = document.getElementById('register-surname');
            const inputPhoneNumber = document.getElementById('register-phoneNumber');
            const inputCity = document.getElementById('register-city');
            const inputLatitude= document.getElementById('register-latitude');
            const inputLongitude = document.getElementById('register-longitude');
            const inputProfession = document.getElementById('register-profession');



            $.ajax({
                url: 'http://localhost:8080/signup-prof',
                type: 'POST',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify({
                    email: inputEmail.value,
                    password: inputPassword.value,
                    confirmPassword: inputConfirmPassword.value,
                    name: inputName.value,
                    surname: inputSurname.value,
                    phoneNumber: inputPhoneNumber.value,
                    city: inputCity.value,
                    latitude: inputLatitude.value,
                    longitude: inputLongitude.value,
                    profession: inputProfession.value
                }),
                success: function(){

                }

            });


        });

    })();
</script>


<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>



</body>
</html>