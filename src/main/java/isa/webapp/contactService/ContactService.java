package isa.webapp.contactService;

import domain.ContactEvent;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService
public interface ContactService {

    @XmlElement(required = true)
    @WebMethod
    String toUpperCase(@WebParam(name = "value") String value);

    @WebMethod
    void addContactEvent(ContactEvent contactEvent);

}
