package servlets;

import dao.ProfessionalDetails;
import dao.ProfessionalLogin;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        System.out.println(Arrays.stream(ProfessionalDetails.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toMap(Function.identity(), n -> n + "-validation"))
                .toString());



/*        Field[] fields = ProfessionalDetails.class.getDeclaredFields();

        for (Field f : fields){
            System.out.println(f.getName());
        }*/

    }
}
