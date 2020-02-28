package com.company.jsonParsers;

import com.company.beans.ResultData;
import com.company.beans.ResultArray;
import com.fasterxml.jackson.jr.ob.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonParserJackson implements JsonParse {
    @Override
    public List<ResultData> parseJson(InputStream inputStream) throws IOException {
        ResultArray items = JSON.std.beanFrom(ResultArray.class, inputStream);
        return items.getItems();
    }
}
