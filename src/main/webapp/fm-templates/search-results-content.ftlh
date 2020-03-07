
<main role="main">

    <section class="jumbotron text-center" >
        <h2>Wyniki wyszukiwania: </h2>
        <div class="container">
            <#list searchResults as sr>
                        <div class="album py-5">
                            <div class="container">
                                <div class="row">
                                <#--<#list users as user>-->
                                        <div class="col-md-6 offset-md-3">
                                        <div class="card mb-6 shadow-sm">
                                            <div class="row d-flex justify-content-left">
                                            <div class="col-md-3 my-auto">
                                                <div class="card-body">
                                                    <p class="card-text d-flex justify-content-center" style="padding-left: 2em">
                                                        ${sr.userDetails.name} </br>
                                                        ${sr.userDetails.surname}
                                                    </p>
                                                    <div class="d-flex justify-content-between align-items-center" style="padding-left: 0.7em">
                                                        <div class="btn-groups">
                                                            <form method="post" action="/contact-prof">
                                                                <button type="submit" name="email" class="btn btn-dark my-2" value=${sr.email}>
                                                                    Kontakt
                                                                </button>
                                                            </form>
                                                            <#--<a type = "submit" value=sr.email href="\details-prof" class="btn btn-dark my-2">Kontakt</a>-->
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="col-md-2">
                                         <#--       <img src="http://www.mountainhearth.com/wp-content/uploads/2014/01/P1010070-1024x768.jpg" height="300px">
                                                -->
                                                <img src="../electrician1.jpg" height="300px">

                                            </div>

                                            </div>
                        <#--                        <div class="card-body">
                                                <p class="card-text">
                                                        ${sr.userDetails.name} </br>
                                                        ${sr.userDetails.surname}
                                                </p>
                                                <div class="d-flex justify-content-between align-items-center">
                                                    <div class="btn-groups">
                                                        <a type = "submit" href="#" class="btn btn-primary my-2">Kontakt</a>
                                                    </div>
                                                </div>
                                            </div>-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
            </#list>
        </div>
    </section>

    </main>