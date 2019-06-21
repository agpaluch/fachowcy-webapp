package domain;

public enum Role {

    PROFESSIONAL("fachowiec"),
    ADMIN("administrator"),
    CLIENT("klient");


    private final String fullName;

    Role(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }



}
