<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<#--    <script type="text/javascript" src="formValidation.js"></script>-->
</head>
<body>

<main>
    <h2>Rejestracja fachowca</h2>

    <#--<form method="post" action="/signup-prof" id="id1" onsubmit="event.preventDefault(); return checkEverything();">-->
        <form id="form">


        Profesja: <select name="profession" id="register-profession">
           <#list professions as prof>
               <#if inputData.profession==prof>
                   <option value="${prof}" selected>${prof.fullName}</option>
               <#else>
               <option value="${prof}">${prof.fullName}</option>
               </#if>
           </#list>
    </select><br/><br/>

    <#include "signup-form.ftlh">
    </form>

    <div id="odp"></div>


</main>


<#--
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDA3SOTQlxW22gZhsIyKHgkq_1gVYsEhHM&libraries=places&callback=initAutocomplete"></script>
-->


    <#include "JSform.ftlh">



<script>
    (function() {
        const inputEmail = document.getElementById('register-email');
        const inputPassword = document.getElementById('register-password');
        const inputName = document.getElementById('register-name');
        const inputSurname = document.getElementById('register-surname');
        const inputPhoneNumber = document.getElementById('register-phoneNumber');
        const inputCity = document.getElementById('register-city');
        const inputLatitude= document.getElementById('register-latitude');
        const inputLongitude = document.getElementById('register-longitude');
        const inputProfession = document.getElementById('register-profession');
        const odp = document.getElementById('odp');

        document.getElementById('submitButton').addEventListener('click', (clickEvent) => {
            clickEvent.preventDefault();
            if (!checkEverything()) {
                return;
            }

                fetch('/signup-prof', {
                    method: 'POST',
                    body: JSON.stringify({
                        "userLogin": {
                            email: inputEmail.value,
                            password: inputPassword.value
                        },
                        "userDetails": {
                            name: inputName.value,
                            surname: inputSurname.value,
                            phoneNumber: inputPhoneNumber.value,
                            city: inputCity.value,
                            latitude: inputLatitude.value,
                            longitude: inputLongitude.value,
                            profession: inputProfession.value
                        }
                    }),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).then(response => {
                    if (response.status === 200) {
                        return response.json();
                    }
                    odp.innerHTML = response.statusText;
                })

        });

    })();
</script>


</body>
</html>