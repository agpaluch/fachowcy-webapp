package servlets;

import domain.*;
import exceptions.FrontEndFormValidationException;
import repository.City;
import repository.TypeOfProfession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class SignupEditUtil {

    public static Map<String, Object> addCitiesAndProfessions(Map<String, Object> dataMap, Role role){

        dataMap.put("cities", Arrays.stream(City.values()).collect(Collectors.toList()));

        if (role==Role.PROFESSIONAL){
            dataMap.put("professions", Arrays.stream(TypeOfProfession.values()).collect(Collectors.toList()));
        } else {
            dataMap.put("professions",null);
        }
        return dataMap;
    }


    public static UserDTO createUserDTOToValidate(HttpServletRequest req, String function) throws FrontEndFormValidationException{
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        Role role = null;
        TypeOfProfession profession = null;
        Integer numberOfLikes = null;

        String professionString = req.getParameter("profession");

        try {
            Long phoneNumber = Long.parseLong(req.getParameter("phoneNumber"));
            City city = City.valueOf(req.getParameter("city"));
            Double longitude = Double.parseDouble(req.getParameter("longitude"));
            Double latitude = Double.parseDouble(req.getParameter("latitude"));

            if (function.equals("edit")){
                role = Role.valueOf(req.getParameter("role"));
                numberOfLikes = Integer.parseInt(req.getParameter("numberOfLikes"));
            }

            if(professionString!=null){
                if (function.equals("signup")){
                    role = Role.PROFESSIONAL;
                }
                profession = TypeOfProfession.valueOf(professionString);
            } else {
                if (function.equals("signup")){
                    role = Role.CLIENT;
                }
            }

            UserDTO userDTO = UserDTO.builder()
                    .email(email)
                    .password(password)
                    .confirmPassword(confirmPassword)
                    .role(role)
                    .name(name)
                    .surname(surname)
                    .profession(profession)
                    .phoneNumber(phoneNumber)
                    .city(city)
                    .longitude(longitude)
                    .latitude(latitude)
                    .build();

            if (function.equals("edit")){
                userDTO.setNumberOfLikes(numberOfLikes);
            }

            return userDTO;

        } catch (IllegalArgumentException e) {
            throw new FrontEndFormValidationException();
        }
    }


    public static Set<ConstraintViolation<UserDTO>> validateUserDTO(UserDTO userDTO){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(userDTO);
    }

    public static UserLogin createUserLoginFromUserDTO(UserDTO userDTO){

        UserDetails userDetails = UserDetails.builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .phoneNumber(userDTO.getPhoneNumber())
                .city(userDTO.getCity())
                .numberOfLikes(userDTO.getNumberOfLikes())
                .longitude(userDTO.getLongitude())
                .latitude(userDTO.getLatitude())
                .numberOfLikes(userDTO.getNumberOfLikes())
                .build();

        UserLogin userLogin = UserLogin.builder()
                .userDetails(userDetails)
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .build();


        if(userDTO.getProfession() !=null){
            userLogin.setProfession(new Professions(userDTO.getProfession()));
        }

        return userLogin;
    }



}
