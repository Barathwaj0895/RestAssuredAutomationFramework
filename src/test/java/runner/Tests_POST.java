package runner;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Tests_POST {

    @Test
    public void testPostMethod() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("\"First Name\"", "\"Barathwaj\"");
        map.put("\"Last Name\"", "\"Ravisankar\"");
        map.put("\"Company\"","\"Boeing\"");
        map.put("\"Designation\"", "\"Senior Software Development Engineer in Test\"");
        System.out.println(map);
    }

}
