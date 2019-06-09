
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<#--    <script type="text/javascript" src="/googleAPI.js"></script>
    <script type="text/javascript" src="/formValidation.js"></script>-->
</head>
<body>

<main>
    <h2>Rejestracja</h2>

    <form method="post" action="/signup" id="id1" onsubmit="event.preventDefault(); return checkEverything();">
    <#include "signup-form.ftlh">
    </form>

</main>


    <#include "JSform.ftlh">

</body>
</html>

