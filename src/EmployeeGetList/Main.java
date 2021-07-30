package EmployeeGetList;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("D:\\Employee.json"));
//        Object IdDel = parser.parse(new FileReader("C:\\Get_emps_del.json"));

        String yourJsonString = obj.toString();
//        String idJsonString = IdDel.toString();
        String idJsonString = "{\"Employees\":{\"Employee\":[{\"EMP\":2011},{\"EMP\":6322},{\"EMP\":9068},{\"EMP\":27038},{\"EMP\":28026}]}}";

        ReadContext delEmp = JsonPath.parse(idJsonString);
        String filterId = "$..EMP";
        List<Map<String, Object>> IdArr = delEmp.read(filterId, List.class);

        String[] s = IdArr.toString().replace("[", "").replace("]", "").split(",");
        for (int i = 0; i < s.length; i++) {
//            System.out.println(s[i]);
        }
//        System.out.println(IdArr);


        try {
//            String filter = "$.Employee[?(\"46078\" in @.AdditionalFields[*].Value)]";
            String[] id = s;
            ReadContext ctx = JsonPath.parse(yourJsonString);
            List<Map<String, Object>> rez = new ArrayList<>();
            String[] filter = new String[id.length];
            StringBuilder strEmp = new StringBuilder();

            for (int i = 0; i < filter.length; i++) {
                filter[i] = "$.Employee[?(\"" + id[i] + "\" in @.AdditionalFields[*].Value)]";
//                System.out.println(filter[i]);
//                List<Map<String, Object>> rez = ctx.read(filter[i], List.class);
                rez = ctx.read(filter[i], List.class);
                strEmp.append(rez);
//                System.out.println(rez);
            }
//            System.out.println(rez);
            System.out.println("{\n \"Employee\": " + strEmp.toString().replace("][", ",") + "\n}");

//            List<Map<String, Object>> rez = ctx.read(filter, List.class);
//            System.out.println(rez);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
