package repository;

public enum City {

    WARSZAWA("Warszawa");


    private final String fullName;

    City(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
