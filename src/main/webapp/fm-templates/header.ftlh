<header>
    <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-md-7 py-4">
                    <h4 class="text-white"></h4>
                    <p class="text-muted"></p>
                </div>
                <div class="col-sm-4 offset-md-1 py-4">
                    <ul class="list-unstyled">
                        <#if sessionInfo.getUserLogin()??>
                            <li><a href="/inbox" class="text-white">Wiadomości</a></li>
                        </#if>
                        <li><a href="/login-form" class="text-white">Login</a></li>
                        <li><a href="/signup?role=CLIENT" class="text-white">Rejestracja klient</a></li>
                        <li><a href="/signup?role=PROFESSIONAL" class="text-white">Rejestracja fachowiec</a></li>
                        <li><a href="#" class="text-white">Kontakt</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <#--        <div class="container d-flex justify-content-between">-->
        <#--        <div class="row">-->
        <div class="col-3">
            <a href="/index" class="navbar-brand d-flex align-items-center" style="margin-right: 10px">
                <strong>Fachmann.pl</strong>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader"
                    aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
        <div class="col-7">

        </div>
        <div class="col-2">
            <#if sessionInfo.getUserLogin()??>
                <div style="color: #FFF; margin-right: 10px;"> ${sessionInfo.getUserLogin().getEmail()}</div>
                <a href="/logout" class="btn btn-outline-success my-2 my-sm-0">LogOut</a>
            <#else>
                <div style="color: #FFF; margin-right: 10px;">Użytkownik anonimowy</div>
                <a href="/login-form" class="btn btn-outline-success my-2 my-sm-0">LogIn</a>
            </#if>

            <#--<div style="color: #FFF; margin-right: 10px;"> Użytkownik anonimowy </div>-->
            <#--<a href="/login" class="btn btn-outline-success my-2 my-sm-0">LogIn</a>-->
        </div>
        <#--        </div>-->
        <#--        </div>-->

        <#--        <div class="container d-flex justify-content-between">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <a href="/" class="navbar-brand d-flex align-items-center" style="margin-right: 10px">
                        &lt;#&ndash;<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" aria-hidden="true" class="mr-2" viewBox="0 0 24 24" focusable="false"><path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"/><circle cx="12" cy="13" r="4"/></svg>&ndash;&gt;
                        <strong>Fachmann.pl</strong>
                    </a>
                </div>
                <div style="color: #FFF; margin-right: 10px;"> Użytkownik anonimowy </div>
                <a href="#" class="btn btn-outline-success my-2 my-sm-0">LogIn</a>-->
    </div>
</header>


