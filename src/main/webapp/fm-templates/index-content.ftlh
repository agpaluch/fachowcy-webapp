
<#--<h2>Siema Heniu!</h2>

<a href='/details-client'>Client details</a>
<a href='/details-prof'>Professional details</a>
<a href='/login-client'>Client Login</a>
<a href='/login-prof'>Professional login</a>
<a href='/search-results'>Search</a>
<a href='/signup-client'>Client sign- up</a>
<a href='/signup-prof'>Professional sign-up</a>-->


<main role="main">

    <section class="jumbotron text-center">
        <div class="container" id="search">
            <h1 class="jumbotron-heading"></h1>
            <p class="lead text-muted">Wyszukaj fachowca po nazwie profesji</p>

            <form class="btn btn-secondary m-2" method="post" action="/search-results">
                <#--input type="text" name="search"/>-->
                <#--<div class="form-group">-->
                <div class="row justify-content-center p-3">
                    <div class="d-inline-block m-2">
                        <select class="form-control form-inline" name="search">
                        <#list professions as prof>
                            <option value="${prof.toString()}">${prof.toString()}</option>
                        </#list>
                    </select>
                    </div>
                    <div class="d-inline-block m-2">
                        <input class="btn btn-dark" type="submit" name="save" value="Szukaj"/>
                    </div>


                </div>
            </form>
        </div>

    </section>


    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row">
                <#--<#list users as user>-->
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <#--                    <img src="http://www.mountainhearth.com/wp-content/uploads/2014/01/P1010070-1024x768.jpg" height="300px">
                                            -->
                        <#--                        <img src="https://images.pexels.com/photos/2898199/pexels-photo-2898199.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260" height="300px">
                                                -->
                        <img src="../plumber1.jpg" height="300px">


                        <div class="card-body">
                            <div class="d-flex justify-content-center">
                                <p>${professionals[0].userDetails.name} ${professionals[0].userDetails.surname}</p>
                            </div>
                            <div class="d-flex justify-content-center">
                                <div class="btn-groups">
                                    <form method="post" action="/contact-prof">
                                        <button type="submit" name="email" class="btn btn-dark my-2"
                                                value="${professionals[0].email}">
                                            Kontakt
                                        </button>
                                    </form>
                                    <#--<a type = "submit" value=sr.email href="\details-prof" class="btn btn-dark my-2">Kontakt</a>-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <#--                        <img src="https://cdn.siadlak.com/wp-content/uploads/2017/01/axe-1008981_1920-1-1920x1280.jpg" height="300px">
                                                -->
                        <img src="../electrician1.jpg" height="300px">

                        <div class="card-body">
                            <div class="d-flex justify-content-center">
                                <p>${professionals[1].userDetails.name} ${professionals[1].userDetails.surname}</p>
                            </div>
                            <div class="d-flex justify-content-center">
                                <div class="btn-groups">
                                    <form method="post" action="/contact-prof">
                                        <button type="submit" name="email" class="btn btn-dark my-2"
                                                value="${professionals[1].email}">
                                            Kontakt
                                        </button>
                                    </form>
                                    <#--<a type = "submit" value=sr.email href="\details-prof" class="btn btn-dark my-2">Kontakt</a>-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <#--                        <img src="https://st2.depositphotos.com/1017228/10442/i/950/depositphotos_104429380-stock-photo-serious-bearded-young-lumberjack-with.jpg" height="300px">
                                               -->
                        <img src="../electrician2.jpg" height="300px">
                        <div class="card-body">
                            <div class="d-flex justify-content-center">
                                <p>${professionals[2].userDetails.name} ${professionals[2].userDetails.surname}</p>
                            </div>
                            <div class="d-flex justify-content-center">
                                <div class="btn-groups">
                                    <form method="post" action="/contact-prof">
                                        <button type="submit" name="email" class="btn btn-dark my-2"
                                                value="${professionals[2].email}">
                                            Kontakt
                                        </button>
                                    </form>
                                    <#--<a type = "submit" value=sr.email href="\details-prof" class="btn btn-dark my-2">Kontakt</a>-->
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
