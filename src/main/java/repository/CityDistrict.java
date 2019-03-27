package repository;

public enum CityDistrict {

    BEMOWO("Bemowo"),
    BIALOLEKA("Białołęka"),
    BIELANY("Bielany"),
    MOKOTOW("Mokotów"),
    OCHOTA("Ochota"),
    PRAGAPLD("Praga-Południe"),
    PRAGAPLN("Praga-Północ"),
    REMBERTOW("Rembertów"),
    SRODMIESCIE("Śródmieście"),
    TARGOWEK("Targówek"),
    URSUS("Ursus"),
    URSYNOW("Ursynów"),
    WAWER("Wawer"),
    WESOLA("Wesoła"),
    WILANOW("Wilanów"),
    WLOCHY("Włochy"),
    WOLA("Wola"),
    ZOLIBORZ("Żoliborz");

/*
    @Override
    public String toString() {
        return "CityDistrict{" +
                "fullName='" + fullName + '\'' +
                '}';
    }*/

    private final String fullName;

    CityDistrict(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
