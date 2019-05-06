package dao;

public enum Role {

    PROFESSIONAL("fachowiec"),
    CLIENT("klient");


    private final String fullName;

    Role(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
