package dao;

public class ClientLogin implements UserLogin {

    //login credentials
    private String email;
    private String password;
    private String signUpDate;



    public ClientLogin(String email, String password, String signUpDate) {


        this.email = email;
        this.password = password;
        this.signUpDate = signUpDate;


    }

    public ClientLogin() {
        // Non-parametric constructor used in MenuAdd
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getSignUpDate() {
        return signUpDate;
    }



    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setSignUpDate(String signUpDate) {
        this.signUpDate = signUpDate;
    }


    public String toString(){
        return("Email: " + getEmail() + "\n" +
                "Password: " + getPassword() + "\n" + "Sign-up-date: " + getSignUpDate());
    }


}