<main>

    <div id="login">
        <h3 class="text-center text-dark pt-5"></h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" method="post">
                            <h3 class="text-center text-dark">Login</h3>
                            <div class="form-group">
                                <label for="email" class="text-dark">email:</label><br>
                                <input type="text" name="email" id="email" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-dark">hasło:</label><br>
                                <input type="password" name="password" id="password" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="remember-me" class="text-dark"><span>Zapamiętaj mnie</span> <span><input id="remember-me" name="remember-me" type="checkbox"></span></label><br>
                                <input type="submit" name="submit" class="btn btn-dark btn-md" value="Zaloguj się">
                            </div>
                            <div id="register-link" class="text-left">
                                <a href="/signup?role=PROFESSIONAL" class="text-dark">Zarejestruj się jako fachowiec</a>
                            </div>
                            <div id="register-link" class="text-left">
                                <a href="/signup?role=CLIENT" class="text-dark">Zarejestruj się jako klient</a>
                            </div>
                        </form>
                        <#if error=="1">
                            <div style="color:red">Podałeś zły login lub hasło</div>
                        </#if>
                        <h3 class="text-center text-dark pt-5"></h3>
                    </div>
                </div>
            </div>
        </div>
    </div>


</main>