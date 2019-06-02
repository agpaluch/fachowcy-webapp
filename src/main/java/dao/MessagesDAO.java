package dao;

import domain.Messages;
import javax.ejb.Local;

@Local
public interface MessagesDAO extends IRootDAO<Messages> {

    // some additional methods

}
