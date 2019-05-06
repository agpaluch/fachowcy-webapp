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

    <form method="post" action="/signup-prof" id="id1" onsubmit="event.preventDefault(); return checkEverything();">

        Profesja: <select name="profession">
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

</main>



<#--
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDA3SOTQlxW22gZhsIyKHgkq_1gVYsEhHM&libraries=places&callback=initAutocomplete"></script>
-->


    <#include "JSform.ftlh">

</body>
</html>