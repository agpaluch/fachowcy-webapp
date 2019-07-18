package isa.webapp.contactClient;

import isa.webapp.contactService.ContactService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class ContactClient {

    public static void testClient() throws MalformedURLException {

        URL url = new URL("http://localhost:9999/service");

        QName qName = new QName("http://contactService.webapp.isa/", "ContactServiceImpService");
        Service service = Service.create(url, qName);
        ContactService port = service.getPort(ContactService.class);
        System.out.println(port.toUpperCase("ala"));
    }

}
