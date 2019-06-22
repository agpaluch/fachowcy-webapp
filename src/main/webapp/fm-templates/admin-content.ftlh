<main role="main" class="flex-shrink-0">

    <div class="container">
        <h1 class="mt-5"></h1>
    </div>

    <form action="/admin" method="post">
        <label for="search">Wyszukaj użytkownika po adresie e-mail: </label><br>
        <input type="text" name="search" id="search"/><br/><br/>

        <button class="btn btn-dark btn-md" type="submit" style="margin: 1%;">
            Szukaj
        </button>

    </form>


    <#if userLogin?? && userDetails??>
        <div>id: ${userLogin.id}</div>
        <div>e-mail: ${userLogin.email}</div>
        <div>hasło: ${userLogin.password}</div>
        <div>rola: ${userLogin.role.toString()}</div>
        <#if userLogin.profession??>
            <div>profesja: ${userLogin.profession.toString()}</div>
        </#if>
        <div>imię: ${userDetails.name}</div>
        <div>nazwisko: ${userDetails.surname}</div>
        <div>numer telefonu: ${userDetails.phoneNumber?c}</div>
        <div>miasto: ${userDetails.city.toString()}</div>
    <div>liczba polubień: ${userDetails.numberOfLikes?c}</div>
        <div>szerokość geograficzna: ${userDetails.latitude}</div>
        <div>długość geograficzna: ${userDetails.longitude}</div>

    <form action="/admin" method="get">
             <button class="btn btn-dark btn-md" type="submit" name="emailOfUserToDelete"
                     value="${userLogin.email}" style="margin: 1%;">
                 Usuń
             </button>
            <button class="btn btn-dark btn-md" type="submit" name="emailOfUserToEdit"
                    value="${userLogin.email}" style="margin: 1%;">
                Edytuj
            </button>
    </form>



    </#if>

    <div>${error}</div>


</main>