<main>

    <div>
        <h3 class="text-center text-dark pt-5"></h3>
        <div class="container">
            <h3>Wysłane | <a href="/inbox">Skrzynka odbiorcza</a></h3>
            <#list messages>
                <table class="table table-striped table-bordered table-hover">
                    <col width="20%">
                    <col width="70%">
                    <col width="10%">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Do</th>
                        <th scope="col">Wiadomość</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#items as message>
                        <tr>
                            <td>${message.recipient.userDetails.name} ${message.recipient.userDetails.surname}</td>
                            <td>${message.message}</td>
                            <td><a href="/newmessage?id=${message.recipient.id}">Napisz nową</a></td>
                        </tr>
                    </#items>
                    </tbody>
                </table>
            <#else>
                <h3>Brak Wiadomości</h3>
            </#list>
        </div>
    </div>


</main>