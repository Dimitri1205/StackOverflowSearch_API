package test.com.company;

import com.company.enums.SortBy;
import com.company.enums.SortOrder;
import com.company.beans.ResultData;
import com.company.StackOverflowQuery;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class ApiTest2 {

    public static void main(String[] args) {
        ApiTest2 test = new ApiTest2();
        try {
            test.testConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() throws IOException {
        StackOverflowQuery query = new StackOverflowQuery();

        query.setSearchTerm("Hello World");
        query.setSortBy(SortBy.RELEVANCE);
        query.setSortOrder(SortOrder.ASCENDING);

        List<ResultData> result = query.execute();
//        System.out.println("Result count: " + result.size());
        assertNotEquals(0, result.size());

    }



}
