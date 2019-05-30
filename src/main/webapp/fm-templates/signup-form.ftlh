


        <div>E-mail: <input type="text" id="register-email" name="email"
                            oninput="validateFieldEmail()" value="${inputData.email}" /><br/><br/></div>
        <div id="email-error">${errors.email}</div>
        <div>Hasło: <input type="password" id="register-password" name="password"
                           oninput="validateFieldPassword()"/><br/><br/></div>
        <div id="password-error">${errors.password}</div>
        <div>Potwierdź hasło: <input type="password" id="register-confirmPassword" name="confirmPassword"
                                     oninput="validateConfirmFieldPassword()"/><br/><br/></div>
        <div id="confirmPassword-error">${errors.confirmPassword}</div>
        <div>Imię: <input type="text" id="register-name" name="name" value="${inputData.name}"
                          oninput="validateNameField()"/><br/><br/></div>
        <div id="name-error">${errors.name}</div>
        <div>Nazwisko: <input type="text" id="register-surname" name="surname"
                              oninput="validateSurnameField()" value="${inputData.surname}"/><br/><br/></div>
        <div id="surname-error">${errors.surname}</div>
        <div>Numer telefonu: <input type="text" id="register-phoneNumber" name="phoneNumber"
                                    oninput="validatePhoneNumberField()" value="${inputData.phoneNumber}"/><br/><br/></div>
        <div id="phoneNumber-error">${errors.phoneNumber}</div>


        Miasto: <select name="city" id="register-city">
           <#list cities as city>
               <#if inputData.city==city>
                    <option value="${city}" selected>${city.fullName}</option>
               <#else>
                <option value="${city}">${city.fullName}</option>
               </#if>
           </#list>
    </select><br/><br/>
        <div id="city-error">${errors.city}</div>

    <#--    Dzielnica: <select name="cityDistrict">
               <#list districts as district>
                   <option value="${district}">${district.fullName}</option>
               </#list>
    </select><br/><br/>-->

        <div>Szerokość geograficzna: <input id="register-latitude" type="text" name="latitude" readonly
                                            <#--onchange="validateLocationField('latitude')"-->
                                            value="${inputData.latitude}"/><br/><br/></div>
        <div id="latitude-error">${errors.latitude}</div>

        <div>Długość geograficzna: <input id="register-longitude" type="text" name="longitude" readonly
                                          <#--onchange="validateLocationField('longitude')"-->
                                          value="${inputData.longitude}"/><br/><br/></div>
        <div id="longitude-error">${errors.longitude}</div>

        <#--<input id="submitButton" type="submit" name="save" value="Zarejestruj"/><br/><br/>-->

         <button id="submitButton" class="btn btn-success">Zarejestruj</button>

    <#--    AIzaSyDA3SOTQlxW22gZhsIyKHgkq_1gVYsEhHM-->






