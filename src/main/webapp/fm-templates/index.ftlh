<html>

<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Fachowcy</title>
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
</head>

<body>
<#--<h2>Siema Heniu!</h2>

<a href='/details-client'>Client details</a>
<a href='/details-prof'>Professional details</a>
<a href='/login-client'>Client Login</a>
<a href='/login-prof'>Professional login</a>
<a href='/search-results'>Search</a>
<a href='/signup-client'>Client sign- up</a>
<a href='/signup-prof'>Professional sign-up</a>-->


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
                        <li><a href="/signup-client" class="text-white">Rejestracja klient</a></li>
                        <li><a href="/login-client" class="text-white">Login klient</a></li>
                        <li><a href="/signup-prof" class="text-white">Rejestracja fachowiec</a></li>
                        <li><a href="/login-prof" class="text-white">Login fachowiec</a></li>
                        <li><a href="#" class="text-white">Kontakt</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container d-flex justify-content-between">
            <a href="#" class="navbar-brand d-flex align-items-center">
                <#--<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" aria-hidden="true" class="mr-2" viewBox="0 0 24 24" focusable="false"><path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"/><circle cx="12" cy="13" r="4"/></svg>-->
                <strong>Fachmann.pl</strong>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </div>
</header>

<main role="main">

    <section class="jumbotron text-center" >
        <div class="container">
            <h1 class="jumbotron-heading"> Sprzedam Opla </h1>
            <p class="lead text-muted"> Kup Pan Opla </p>

            <form class="btn btn-secondary my-2" method="post" action="/search-results">
                <input type="text" name="search"/>
                <input class="btn btn-primary my-2" type="submit" name="save" value="Szukaj" />
            </form>

        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row">
            <#--<#list users as user>-->
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <img src="http://www.mountainhearth.com/wp-content/uploads/2014/01/P1010070-1024x768.jpg" height="300px">
                        <div class="card-body">
                            <p class="card-text">Drwal 1</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <a type = "submit" href="/details-prof" class="btn btn-primary my-2">Professional details</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <img src="https://cdn.siadlak.com/wp-content/uploads/2017/01/axe-1008981_1920-1-1920x1280.jpg" height="300px">
                        <div class="card-body">
                            <p class="card-text">Drwal 2</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <a type = "submit" href="/details-prof" class="btn btn-primary my-2">Professional details</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <img src="https://st2.depositphotos.com/1017228/10442/i/950/depositphotos_104429380-stock-photo-serious-bearded-young-lumberjack-with.jpg" height="300px">
                        <div class="card-body">
                            <p class="card-text">Drwal 3</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <a type = "submit" href="/details-prof" class="btn btn-primary my-2">Professional details</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            <#--</#list>-->
            </div>
        </div>
    </div>

</main>

<footer class="text-muted">
    <div class="container">
        <p class="float-right">
            <a href="#">Back to top</a>
        </p>
        <p>Album example is &copy; Bootstrap, but please download and customize it for yourself!</p>
        <p>New to Bootstrap? <a href="https://getbootstrap.com/">Visit the homepage</a> or read our <a href="/docs/4.3/getting-started/introduction/">getting started guide</a>.</p>
    </div>
</footer>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>



</body>
</html>
