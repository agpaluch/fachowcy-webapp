<main>

    <div>
        <h3 class="text-center text-dark pt-5"></h3>
        <div class="container">
            <h3>Skrzynka Odbiorcza | <a href="/sent">Wysłane</a></h3>
            <#list messages>
                <table class="table table-striped table-bordered table-hover">
                    <col width="20%">
                    <col width="70%">
                    <col width="10%">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Od</th>
                        <th scope="col">Wiadomość</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#items as message>
                        <tr>
                            <#if message.wasRead == false>
                            <td>
                                <strong>${message.sender.userDetails.name} ${message.sender.userDetails.surname}</strong>
                            </td>
                            <td>
                                <strong>${message.message}</strong>
                            </td>
                            <td><a href="/newmessage?id=${message.sender.id}">Odpowiedz</a>
                                <#else>
                            <td>${message.sender.userDetails.name} ${message.sender.userDetails.surname}</td>
                            <td>${message.message}</td>
                            <td><a href="/newmessage?id=${message.sender.id}">Odpowiedz</a>
                                </#if>
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