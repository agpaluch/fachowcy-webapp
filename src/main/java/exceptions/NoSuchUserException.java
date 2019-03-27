package exceptions;

public class NoSuchUserException extends Exception{

    public void printStackTrace(){
        System.out.println("No such user in database");
    }


}
