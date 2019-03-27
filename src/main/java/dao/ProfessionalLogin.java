package dao;


public class ProfessionalLogin implements UserLogin {

    //login credentials
    private String email;
    private String password;
    private String signUpDate;


    public ProfessionalLogin(String email, String password, String signUpDate) {

        this.email = email;
        this.password = password;
        this.signUpDate = signUpDate;

    }

    public ProfessionalLogin() {
        //Non-parametric constructor used in MenuAdd
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




    /*    @Override
    public void setPassword() {

    }

    @Override
    public void setSignUpDate() {

    }*/

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
        return("Login: " + getEmail() + "\n" +
                "Password: " + getPassword() + "\n" + "Sign-up-date: " + getSignUpDate());
    }


}

