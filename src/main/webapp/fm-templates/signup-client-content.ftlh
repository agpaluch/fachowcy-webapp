
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<main>
    <h2>Rejestracja klienta</h2>

    <form method="post" action="/signup" id="id1" onsubmit="event.preventDefault(); return checkEverything();">
    <#include "signup-form.ftlh">
    </form>

</main>

<#--<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myStyles.css">-->

    <#include "JSform.ftlh">

</body>
</html>

