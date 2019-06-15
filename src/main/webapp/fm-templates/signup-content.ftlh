
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
    <h2>Rejestracja</h2>

    <form method="post" action="/signup" id="id1" onsubmit="event.preventDefault(); return checkEverything(); ">
    <#include "signup-form.ftlh">


    </form>

</main>


<input id="pac-input" class="controls" type="text" placeholder="Search Box">
<#--    onchange="validateLocationField('latitude'); validateLocationField('longitude');">-->
<div id="map"></div>

<#--    <#include "JSform.ftlh">-->

<#--<script>initAutocomplete()</script>-->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDA3SOTQlxW22gZhsIyKHgkq_1gVYsEhHM&libraries=places&callback=initAutocomplete"></script>
<script src="https://unpkg.com/xregexp/xregexp-all.js"></script>
</body>
</html>

