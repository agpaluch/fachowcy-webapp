
<main role="main" class="flex-shrink-0">

    <div class="container">
        <h1 class="mt-5">Edytuj informacje o użytkowniku</h1>
    </div>

    <div id="login">
        <h3 class="text-center text-dark pt-5"></h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form method="post" class="form" action="/edit-user">
                            <div class="form-group">

                                <label for="id" class="text-dark">id</label><br>
                                <input type="number" name="id" id="id" value="${userToEdit.id}" class="form-control"/><br/><br/>

                                <label for="email" class="text-dark">e-mail</label><br>
                                <input type="text" name="email" id="email" value="${userToEdit.email}" class="form-control"/><br/><br/>

                                <label for="password" class="text-dark">hasło</label><br>
                                <input type="text" name="password" id="password" value="${userToEdit.password}" class="form-control"/><br/><br/>

                                <label for="role" class="text-dark">rola</label><br>
                                <input type="text" name="role" id="role" value="${userToEdit.role.fullName}" class="form-control"/><br/><br/>

                                <label for="profession" class="text-dark">profesja</label><br>
                                <input type="text" name="profession" id="profession" value="${userToEdit.profession.profession.fullName}" class="form-control"/><br/><br/>

                                <label for="name" class="text-dark">imię</label><br>
                                <input type="text" name="name" id="name" value="${userToEdit.userDetails.name}" class="form-control"/><br/><br/>

                                <label for="surname" class="text-dark">nazwisko</label><br>
                                <input type="text" name="surname" id="surname" value="${userToEdit.userDetails.surname}" class="form-control"/><br/><br/>

                                <label for="phoneNumber" class="text-dark">numer telefonu</label><br>
                                <input type="number" name="phoneNumber" id="phoneNumber" step="any" value="${userToEdit.userDetails.phoneNumber?c}" class="form-control"/><br/><br/>

                                <label for="city" class="text-dark">miasto</label><br>
                                <input type="text" name="city" id="city" value="${userToEdit.userDetails.city.fullName}" class="form-control"/><br/><br/>

                                <label for="longitude" class="text-dark">długość geograficzna</label><br>
                                <input type="number" name="longitude" id="longitude" value="${userToEdit.userDetails.longitude}" class="form-control"/><br/><br/>

                                <label for="latitude" class="text-dark">szerokość geograficzna</label><br>
                                <input type="number" name="latitude" id="latitude" value="${userToEdit.userDetails.latitude}" class="form-control"/><br/><br/>

                                <label for="numberOfLikes" class="text-dark">liczba polubień</label><br>
                                <input type="number" name="numberOfLikes" id="numberOfLikes" value="${userToEdit.userDetails.numberOfLikes}" class="form-control"/><br/><br/>


                            </div>

                            <div class="form-group">
                                <input type="submit" name="save" value="Zapisz" class="btn btn-dark btn-md"/><br/><br/>
                            </div>

                        </form>
                        <h3 class="text-center text-dark pt-5"></h3>
                    </div>
                </div>
            </div>
        </div>
    </div>



</main>





