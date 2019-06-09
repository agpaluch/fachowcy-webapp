


        <div>E-mail: <input type="email" id="register-email" name="email"
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
        <div>Numer telefonu: <input type="number" id="register-phoneNumber" name="phoneNumber"
                                    oninput="validatePhoneNumberField()"
                                    <#if inputData.phoneNumber?? >
                                    value="${inputData.phoneNumber}"
                                    </#if>
        /><br/><br/></div>
        <div id="phoneNumber-error">${errors.phoneNumber}</div>

        <#if professions??>
         Profesja: <select name="profession" id="register-profession">
           <#list professions as prof>
               <#if inputData.profession?? && inputData.profession==prof>
                   <option value="${prof}" selected>${prof.fullName}</option>
               <#else>
               <option value="${prof}">${prof.fullName}</option>
               </#if>
           </#list>
        </select><br/><br/>
        </#if>


        Miasto: <select name="city" id="register-city">
           <#list cities as city>
               <#if inputData.city?? && inputData.city==city>
                    <option value="${city}" selected>${city.fullName}</option>
               <#else>
                <option value="${city}">${city.fullName}</option>

               </#if>
           </#list>
            <option value="">Uwaga</option>
    </select><br/><br/>
        <div id="city-error">${errors.city}</div>


        <div>Szerokość geograficzna: <input id="register-latitude" type="number" name="latitude" readonly
                                            <#--onchange="validateLocationField('latitude')"-->
                <#if inputData.latitude?? >
                 value="${inputData.latitude}"
                </#if>
                /><br/><br/></div>
        <div id="latitude-error">${errors.latitude}</div>

        <div>Długość geograficzna: <input id="register-longitude" type="number" name="longitude" readonly
                                          <#--onchange="validateLocationField('longitude')"-->
                               <#if inputData.longitude?? >
                                value="${inputData.longitude}"
                               </#if>
                               /><br/><br/></div>
        <div id="longitude-error">${errors.longitude}</div>


        <button id="submitButton" class="btn btn-success">Zarejestruj</button>

    <#--    AIzaSyDA3SOTQlxW22gZhsIyKHgkq_1gVYsEhHM-->






