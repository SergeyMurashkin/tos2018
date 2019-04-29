package net.thumbtack.school.backend.asynchrony.exersice_6.nettyServer;


import com.google.gson.Gson;

public class RestService {

    Gson json = new Gson();

    public String process (String inputJson) {
        System.out.println(inputJson);
        SumOfTwo sumOfTwo = json.fromJson(inputJson, SumOfTwo.class);
        int result = sumOfTwo.doSum();
        return "{\"result\": " + result + " }";
    }

}
