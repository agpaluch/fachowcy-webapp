
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="/googleAPI.js"></script>
    <script type="text/javascript" src="/formValidation.js"></script>
</head>
<body>

<main>
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form method="post" action="/signup" id="id1" onsubmit="event.preventDefault(); checkEverything();">
                    </br><h2 class="text-center text-dark">Rejestracja</h2>
                    <div class="form-group">
                        <label for="email" class="text-dark">Email</label><br>
                        <input type="email" id="register-email" name="email"
                               oninput="validateFieldEmail()" value="${inputData.email}" class="form-control">
                    </div>
                    <div id="email-error">${errors.email}</div>

                    <div class="form-group">
                        <label for="password" class="text-dark">Hasło</label><br>
                        <input type="password" id="register-password" name="password"
                               oninput="validateFieldPassword()" class="form-control">
                    </div>
                    <div id="password-error">${errors.password}</div>

                    <div class="form-group">
                       <label for="confirmPassword" class="text-dark">Potwierdź hasło</label><br>
                       <input type="password" id="register-confirmPassword" name="confirmPassword"
                              oninput="validateConfirmFieldPassword()" class="form-control">
                    </div>
                    <div id="confirmPassword-error">${errors.confirmPassword}</div>

                    <div class="form-group">
                        <label for="name" class="text-dark">Imię</label><br>
                        <input type="text" id="register-name" name="name" value="${inputData.name}"
                               oninput="validateNameField()" class="form-control">
                    </div>
                    <div id="name-error">${errors.name}</div>

                    <div class="form-group">
                        <label for="surname" class="text-dark">Nazwisko</label><br>
                        <input type="text" id="register-surname" name="surname"
                               oninput="validateSurnameField()" value="${inputData.surname}" class="form-control">
                    </div>
                    <div id="surname-error">${errors.surname}</div>

                    <div class="form-group">
                        <label for="phoneNumber" class="text-dark">Numer telefonu</label><br>
                        <input class="form-control" type="number" step="any" id="register-phoneNumber" name="phoneNumber"
                               oninput="validatePhoneNumberField()"
                                            <#if inputData.phoneNumber?? >
                                            value="${inputData.phoneNumber}"
                                            </#if> >
                    </div>
                    <div id="phoneNumber-error">${errors.phoneNumber}</div>

                    <div class="form-group">
                    <#if professions??>
                    <label for="profession" class="text-dark">Profesja</label><br>
                     <select class="form-control" name="profession" id="register-profession">
                       <#list professions as prof>
                           <#if inputData.profession?? && inputData.profession==prof>
                               <option value="${prof}" selected>${prof}</option>
                           <#else>
                           <option value="${prof}">${prof}</option>
                           </#if>
                       </#list>
                                </select>
                     </#if>
                     </div>

                    <div class="form-group">
                    <label for="city" class="text-dark">Miasto</label><br>
                        <select class="form-control" name="city" id="register-city">
                       <#list cities as city>
                           <#if inputData.city?? && inputData.city==city>
                                <option value="${city}" selected>${city.fullName}</option>
                           <#else>
                            <option value="${city}">${city.fullName}</option>
                           </#if>
                       </#list>
                            <option value="">Uwaga</option>
                        </select>
                    </div>
                    <div id="city-error">${errors.city}</div>


                    <div class="form-group">
                        <label for="latitude" class="text-dark">Szerokość geograficzna</label><br>
                        <input class="form-control" id="latitude" type="number" step="any" name="latitude" readonly
                        <#--onchange="validateLocationField('latitude')"-->
                            <#if inputData.latitude?? >
                             value="${inputData.latitude}"
                            </#if>>
                    </div>
                    <div id="latitude-error">${errors.latitude}</div>

                    <div class="form-group">
                        <label for="longitude" class="text-dark">Długość geograficzna</label><br>
                        <input class="form-control" id="longitude" type="number" step="any" name="longitude" readonly
                        <#--onchange="validateLocationField('longitude')"-->
                                           <#if inputData.longitude?? >
                                            value="${inputData.longitude}"
                                           </#if> >
                    </div>
                    <div id="longitude-error">${errors.longitude}</div>

                    <input id="pac-input" class="controls form-control"  type="text" placeholder="Search Box">
                    <#--    onchange="validateLocationField('latitude'); validateLocationField('longitude');">-->
                    <div id="map"></div> </br>
                    <div class="d-flex justify-content-center">
                        <button id="submitButton" class="btn btn-success">Zarejestruj</button>
                    </div>
                    </form>

                </div>
            </div>
        </div>
    </div>

</main>

<#--    <#include "JSform.ftlh">-->

<#--<script>initAutocomplete()</script>-->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDA3SOTQlxW22gZhsIyKHgkq_1gVYsEhHM&libraries=places&callback=initAutocomplete"></script>
<script src="https://unpkg.com/xregexp/xregexp-all.js"></script>
</body>
</html>

