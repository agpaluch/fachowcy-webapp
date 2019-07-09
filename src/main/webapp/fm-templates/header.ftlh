<header>

    <link href="style.css" rel="stylesheet" />

    <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-md-7 py-4">
                    <h4 class="text-white"></h4>
                    <p class="text-muted"></p>
                </div>

                <div class="col-sm-4 offset-md-1 py-4">
                    <#--<ul class="list-unstyled">
                        <#if sessionInfo.getUserLogin()??>
                            <li><a href="/inbox" class="text-white">Wiadomości</a></li>
                        </#if>
                        <li><a href="/login-form" class="text-white">Login</a></li>
                        <li><a href="/signup?role=CLIENT" class="text-white">Rejestracja klient</a></li>
                        <li><a href="/signup?role=PROFESSIONAL" class="text-white">Rejestracja fachowiec</a></li>
                        <li><a href="#" class="text-white">Kontakt</a></li>
                    </ul>-->
                </div>
            </div>
        </div>
    </div>

    <#--<div class="row bg-dark">
        <div class="col-md-2 my-auto" style="padding-left: 2em">
            <a href="/index" class="navbar-brand d-flex align-items-center" style="color: white">
                <strong>Fachmann.pl</strong>
            </a>
        </div>

        <div class="col-md-7 bg-dark"></div>

        <div class="col-md-3">
            <nav class="navbar navbar-dark bg-dark shadow-sm float-right">
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent15"
                        aria-controls="navbarSupportedContent15" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent15">
                    <ul class="navbar-nav float-right text-right pr-3">

                    <#if sessionInfo.getUserLogin()??>
                        <li class="nav-item active">
                            <a href="/inbox" class="nav-link">Wiadomości</a>
                        </li>
                    </#if>
                        <li class="nav-item">
                            <a href="/signup?role=CLIENT" class="nav-link">Rejestracja klient</a>
                        </li>
                        <li class="nav-item">
                        &lt;#&ndash;<div class="jsx-1542968643 kirk-item-leftAddon"><svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="kirk-icon" width="24" height="24" aria-hidden="true" fill="#708C91"><g><path d="M23.739,8.061l-11-6c-0.149-0.082-0.331-0.081-0.479,0l-11,6C1.1,8.148,1,8.317,1,8.5v3C1,11.776,1.224,12,1.5,12h22 c0.276,0,0.5-0.224,0.5-0.5v-3C24,8.317,23.9,8.148,23.739,8.061z M23,11H2V8.797l10.5-5.728L23,8.797V11z"></path><path d="M16,20.5v-6c0-0.276-0.224-0.5-0.5-0.5S15,14.224,15,14.5v6c0,0.276,0.224,0.5,0.5,0.5S16,20.776,16,20.5z"></path><path d="M4,20.5v-6C4,14.224,3.776,14,3.5,14S3,14.224,3,14.5v6C3,20.776,3.224,21,3.5,21S4,20.776,4,20.5z"></path><path d="M10,20.5v-6c0-0.276-0.224-0.5-0.5-0.5S9,14.224,9,14.5v6C9,20.776,9.224,21,9.5,21S10,20.776,10,20.5z"></path><path d="M22,20.5v-6c0-0.276-0.224-0.5-0.5-0.5S21,14.224,21,14.5v6c0,0.276,0.224,0.5,0.5,0.5S22,20.776,22,20.5z"></path><path d="M23.5,23h-22C1.224,23,1,23.224,1,23.5S1.224,24,1.5,24h22c0.276,0,0.5-0.224,0.5-0.5S23.776,23,23.5,23z"></path><circle cx="12.5" cy="7.5" r="1"></circle></g></svg></div>&ndash;&gt;
                            <a href="/signup?role=PROFESSIONAL" class="nav-link">Rejestracja fachowiec</a>
                        </li>

                    <#if sessionInfo.getUserLogin()??>
                        <li class="nav-item">
                            <a style="color: red" href="/logout" class="nav-link">Wyloguj</a>
                        </li>
                    <#else>
                        <li class="nav-item">
                            <a style="color: green" href="/login-form" class="nav-link">Zaloguj</a>
                        </li>
                    </#if>
                    </ul>
                </div>
            </nav>
        </div>
    </div>-->



    <!--Navbar-->
    <nav class="bg-dark navbar navbar-dark light-blue lighten-4">

        <!-- Navbar brand -->
        <a class="navbar-brand" href="/index" style="color: white">
            <strong>Fachmann.pl</strong>
        </a>

        <#if sessionInfo.getUserLogin()??>
            <a class="btn btn-outline-success my-2 my-sm-0 offset-md-9" href="#" style="color: white">Moje konto</a>
        </#if>

        <!-- Collapse button -->
        <button class="navbar-toggler first-button" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent15" aria-controls="navbarSupportedContent15" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"><i class="fa fa-navicon"></i></span>
        </button>




        <!-- Collapsible content -->
        <div class="collapse navbar-collapse" id="navbarSupportedContent15">

            <!-- Links -->
            <ul class="navbar-nav">
                    <#if sessionInfo.getUserLogin()??>
                        <li class="nav-item active">
                            <a style="color: white" href="/inbox" class="nav-link">Wiadomości</a>
                        </li>
                        <li class="nav-item active">
                            <a style="color: white" href="#" class="nav-link">Moje konto</a>
                        </li>
                        <li class="nav-item">
                            <a style="color: red" href="/logout" class="nav-link">Wyloguj</a>
                        </li>
                    <#else>
                        <li class="nav-item">
                            <a style="color: white" href="/signup?role=CLIENT" class="nav-link">Rejestracja klient</a>
                        </li>
                        <li class="nav-item">
                        <#--<div class="jsx-1542968643 kirk-item-leftAddon"><svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="kirk-icon" width="24" height="24" aria-hidden="true" fill="#708C91"><g><path d="M23.739,8.061l-11-6c-0.149-0.082-0.331-0.081-0.479,0l-11,6C1.1,8.148,1,8.317,1,8.5v3C1,11.776,1.224,12,1.5,12h22 c0.276,0,0.5-0.224,0.5-0.5v-3C24,8.317,23.9,8.148,23.739,8.061z M23,11H2V8.797l10.5-5.728L23,8.797V11z"></path><path d="M16,20.5v-6c0-0.276-0.224-0.5-0.5-0.5S15,14.224,15,14.5v6c0,0.276,0.224,0.5,0.5,0.5S16,20.776,16,20.5z"></path><path d="M4,20.5v-6C4,14.224,3.776,14,3.5,14S3,14.224,3,14.5v6C3,20.776,3.224,21,3.5,21S4,20.776,4,20.5z"></path><path d="M10,20.5v-6c0-0.276-0.224-0.5-0.5-0.5S9,14.224,9,14.5v6C9,20.776,9.224,21,9.5,21S10,20.776,10,20.5z"></path><path d="M22,20.5v-6c0-0.276-0.224-0.5-0.5-0.5S21,14.224,21,14.5v6c0,0.276,0.224,0.5,0.5,0.5S22,20.776,22,20.5z"></path><path d="M23.5,23h-22C1.224,23,1,23.224,1,23.5S1.224,24,1.5,24h22c0.276,0,0.5-0.224,0.5-0.5S23.776,23,23.5,23z"></path><circle cx="12.5" cy="7.5" r="1"></circle></g></svg></div>-->
                            <a style="color: white" href="/signup?role=PROFESSIONAL" class="nav-link">Rejestracja fachowiec</a>
                        </li>
                        <li class="nav-item">
                            <a style="color: green" href="/login-form" class="nav-link">Zaloguj</a>
                        </li>
                    </#if>
            </ul>
            <!-- Links -->

        </div>
        <!-- Collapsible content -->

    </nav>




<#--    <nav class="navbar navbar-dark bg-dark shadow-sm">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent15"
                aria-controls="navbarSupportedContent15" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent15">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Features</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Pricing</a>
                </li>
            </ul>
        </div>
            <#if sessionInfo.getUserLogin()??>
                    <div style="color: #FFF; margin-right: 10px;"> Ahoj ${sessionInfo.getUserLogin().getEmail()}!</div>
            <#else>
                    <div style="color: #FFF; margin-right: 10px;">Ahoj gościu!</div>
            </#if>
    </nav>-->



    <#--<div class="navbar navbar-dark bg-dark shadow-sm">
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
        </div>
    </div>-->


</header>
