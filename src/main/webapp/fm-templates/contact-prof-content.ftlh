
<main role="main">

    <style>
        .avatar {
            vertical-align: middle;
            width: 250px;
            height: 250px;
            border-radius: 50%;
        }
    </style>

    <section class="jumbotron text-center" >

        <article>
            <section class="text-center mb-xl mt-xl">
                <div class="jsx-1920606029 kirk-avatar--medium kirk-avatar--image inline-block kirk-avatar">
                    <img src="https://st2.depositphotos.com/1017228/10442/i/950/depositphotos_104429380-stock-photo-serious-bearded-young-lumberjack-with.jpg" alt="avatar" class="avatar">
                </div>
                <p></p>
                <h1 class="kirk-title my-l">${userLogin.userDetails.getName()} ${userLogin.userDetails.getSurname()}</h1>
                <p class="kirk-text kirk-text-body">${userLogin.userDetails.getCity()}</p>
                <p class="kirk-text kirk-text-body"><strong>email:</strong> ${userLogin.getEmail()}</p>
                <p class="kirk-text kirk-text-body"><strong>telefon:</strong> ${userLogin.userDetails.getPhoneNumber()}</p>

            </section>

        </article>

    </section>

</main>