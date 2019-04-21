
<#--<h2>Siema Heniu!</h2>

<a href='/details-client'>Client details</a>
<a href='/details-prof'>Professional details</a>
<a href='/login-client'>Client Login</a>
<a href='/login-prof'>Professional login</a>
<a href='/search-results'>Search</a>
<a href='/signup-client'>Client sign- up</a>
<a href='/signup-prof'>Professional sign-up</a>-->




<main role="main">

    <section class="jumbotron text-center" >
        <div class="container">
            <h1 class="jumbotron-heading"> Sprzedam Opla </h1>
            <p class="lead text-muted"> Kup Pan Opla </p>

            <form class="btn btn-secondary my-2" method="post" action="/search-results">
                <input type="text" name="search"/>
                <input class="btn btn-dark my-2" type="submit" name="save" value="Szukaj" />
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
                                    <a type = "submit" href="/details-prof" class="btn btn-dark my-2">Professional details</a>
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
                                    <a type = "submit" href="/details-prof" class="btn btn-dark my-2">Professional details</a>
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
                                    <a type = "submit" href="/details-prof" class="btn btn-dark my-2">Professional details</a>
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

