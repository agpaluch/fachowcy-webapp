<main>

    <div>
        <h3 class="text-center text-dark pt-5"></h3>
        <div class="container">
            <h3>Nowa wiadomość | <a href="/inbox">Skrzynka odbiorcza</a> | <a href="/sent">Wysłane</a></h3>
            Odbiorca: ${recipient.userDetails.name} ${recipient.userDetails.surname}
            <form action="/newmessage" method="post">
                <textarea class="md-textarea form-control" id="trackDesc" name="message"
                          placeholder="Wprowadź wiadomość"
                          cols="40" rows="5" required="">
         </textarea>
                <input type="submit" value="Submit">
            </form>
        </div>
    </div>


</main>