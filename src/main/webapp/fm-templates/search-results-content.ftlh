
<main role="main">

    <section class="jumbotron text-center" >
        <div class="container">
            <#list searchResults as sr>
                        <div class="album py-5 bg-light">
                            <div class="container">
                                <div class="row">
                                <#--<#list users as user>-->
                                        <div class="col-md-8 offset-md-2">
                                        <div class="card mb-8 shadow-sm">
                                            <div class="row justify-content-center">
                                            <div class="col-md-2">

                                                <div class="card-body">
                                                    <p class="card-text">
                                                        ${sr.userDetails.name} </br>
                                                        ${sr.userDetails.surname}
                                                    </p>
                                                    <div class="d-flex justify-content-between align-items-center">
                                                        <div class="btn-groups">
                                                            <a type = "submit" href="#" class="btn btn-primary my-2">Kontakt</a>
                                                        </div>
                                                    </div>
                                                </div>



                                            </div>
                                            <div class="col-md-2 offset-md-4">
                                                <img src="http://www.mountainhearth.com/wp-content/uploads/2014/01/P1010070-1024x768.jpg" height="300px">
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