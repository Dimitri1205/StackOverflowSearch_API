package test.com.company;

import com.company.beans.ResultData;
import com.company.jsonParsers.JsonParse;
import com.company.jsonParsers.JsonParserJsonP;
import com.company.jsonParsers.JsonParserJackson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;


public class ApiTest1 {

    public static void main (String[] args) {
        ApiTest1 test = new ApiTest1();
        test.testJsonP();
        test.testJackson();
    }


    @Test
    public void testJsonP () {

        JsonParse parser = new JsonParserJsonP();

        try (FileInputStream inputStream = new FileInputStream("StackOverflow JSON Example.js")) {
            List<ResultData> results = parser.parseJson(inputStream);
            assertNotNull(results);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJackson() {

        JsonParserJackson parser = new JsonParserJackson();

        try (FileInputStream inputStream = new FileInputStream("StackOverflow JSON Example.js")) {
            List<ResultData> results = parser.parseJson(inputStream);
            assertNotNull(results);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
