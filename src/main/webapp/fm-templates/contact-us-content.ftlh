<main>

<div>
    <h3 class="text-center text-dark pt-5"></h3>
    <div class="container">

        <form action="/contact-us" method="post">
            <h3 class="text-center text-dark">Formularz kontaktowy</h3>
            </br>
            <div class="form-group">
                <label for="email" class="text-dark">Email:</label><br>
                <input type="text" name="email" id="email" class="form-control">
            </div>

            <div class="form-group">
                <label for="reason" class="text-dark">Przyczyna kontaktu:</label><br>
                <input type="text" name="reason" id="reason" class="form-control">
            </div>
            <div class="form-group">
                <label for="trackDesc" class="text-dark">Wiadomość:</label><br>
                <textarea class="md-textarea form-control" id="trackDesc" name="message"
                              cols="40" rows="5" required="">
                </textarea>
            </div>
            </br>
            <div class="d-flex justify-content-center">
                <input class="btn btn-dark btn-md" type="submit" value="Wyślij">
            </div>
        </form>
    </div>
</div>

</main>