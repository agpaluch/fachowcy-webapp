<html>
<head>
    <Title>Rejestracja fachowca</Title>
</head>
<body>
<h2>Rejestracja fachowca</h2>

<form method="post" action="/signup-prof">
    E-mail: <input type="text" id="register-email" name="email"/><br/><br/>
    Password: <input type="password" name="password"/><br/><br/>
    Confirm password: <input type="password" name="confirmPassword"/><br/><br/>
    Name: <input type="text" name="name"/><br/><br/>
    Surname: <input type="text" name="surname"/><br/><br/>
    Profession: <select name="profession">
           <#list professions as p>
               <option value = "${p}">${p.fullName}</option>
           </#list>
</select><br/><br/>
    Phone number: <input type="number" name="phoneNumber"/><br/><br/>
    City: <input type="text" name="city"/><br/><br/>
    City district:  <select name="cityDistrict">
           <#list districts as d>
             <option value = "${d}">${d.fullName}</option>
           </#list>
    </select><br/><br/>
    Longitude: <input type="number" name="longitude"/><br/><br/>
    Latitude: <input type="number" name="latitude"/><br/><br/>
    <input type="submit" name="save" value="save"/><br/><br/>
</form>

</body>
</html>