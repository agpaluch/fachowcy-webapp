package exceptions;

public class UserAlreadyExistsException extends Exception{

        public void printStackTrace(){
            System.out.println("User already exists in database");
        }

}
