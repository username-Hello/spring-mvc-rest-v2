package guru.springfamework.springmvcrestv2.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractRestControllerTest {

    public static String asJsonString (Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
