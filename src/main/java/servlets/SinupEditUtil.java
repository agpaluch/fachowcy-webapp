package servlets;

import domain.Role;
import repository.City;
import repository.TypeOfProfession;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class SinupEditUtil {

    public static Map<String, Object> addCitiesAndProfessions(Map<String, Object> dataMap, Role role){

        dataMap.put("cities", Arrays.stream(City.values()).collect(Collectors.toList()));

        if (role==Role.PROFESSIONAL){
            dataMap.put("professions", Arrays.stream(TypeOfProfession.values()).collect(Collectors.toList()));
        } else {
            dataMap.put("professions",null);
        }

        return dataMap;

    }


/*    public static checkPhoneNumber


            try {
        phoneNumber = Long.parseLong(req.getParameter("phoneNumber"));
        city = City.valueOf(req.getParameter("city"));
        longitude = Double.parseDouble(req.getParameter("longitude"));
        latitude = Double.parseDouble(req.getParameter("latitude"));
    } catch (IllegalArgumentException e) {

        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Wprowadziłeś niepoprawne dane.");
        //TODO: Zwrócić resp status do widoku.

    }*/
}
