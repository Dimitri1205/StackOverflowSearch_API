package com.company.jsonParsers;

import com.company.beans.OwnerData;
import com.company.beans.ResultData;

import javax.json.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonParserJsonP implements JsonParse {



    @Override
    public List<ResultData> parseJson(InputStream inputStream) {
        JsonReader reader = Json.createReader(inputStream);
        JsonObject jsonObject = reader.readObject();
        reader.close();

        //parse jsonObject, return something
        List<ResultData> results = new ArrayList<>();
        JsonArray items = jsonObject.getJsonArray("items");
        for (JsonValue item: items) {
            if (item instanceof JsonObject) {
                ResultData result = createResultBean((JsonObject) item);
                results.add(result);
            }
        }
        return results;
    }


    public ResultData createResultBean(JsonObject jsonObject) {
        ResultData bean = new ResultData();

        JsonArray array = jsonObject.getJsonArray("tags");
        String[] tags = new String[array.size()];
        for (int i = 0; i < tags.length; i++) {
            tags[i] = array.getString(i);
        }

        bean.setTags(tags);

        bean.setIs_answered(jsonObject.getBoolean("is_answered", false));
        bean.setView_count(jsonObject.getInt("view_count", 0));
        bean.setAccepted_answer_id(jsonObject.getInt("accepted_answer_id", 0));
        bean.setAnswer_count(jsonObject.getInt("answer_count", 0));
        bean.setScore(jsonObject.getInt("score", 0));

        //this values can be converted to dates, if necessary
        bean.setLast_activity_date(getLong(jsonObject, "last_activity_date", 0));
        bean.setCreation_date(getLong(jsonObject, "creation_date", 0));
        bean.setLast_edit_date(getLong(jsonObject, "last_edit_date", 0));

        bean.setQuestion_id(jsonObject.getJsonNumber("question_id").longValue());
        bean.setLink(jsonObject.getString("link", null));
        bean.setTitle(jsonObject.getString("title", null));

        OwnerData owner = new OwnerData();
        JsonObject ownerJson = jsonObject.getJsonObject("owner");

        owner.setReputation( ownerJson.getInt("reputation", 0) );
        owner.setUser_id( ownerJson.getInt("user_id", 0) );
        owner.setUser_type( ownerJson.getString("user_type", null) );
        owner.setAccept_rate( ownerJson.getInt("accept_rate", 0) );
        owner.setProfile_image( ownerJson.getString("profile_image", null) );
        owner.setDisplay_name( ownerJson.getString("display_name", null) );
        owner.setLink( ownerJson.getString("link", null) );

        bean.setOwner(owner);

        return bean;
    }

    private long getLong(JsonObject json, String key, long defaultValue) {
        JsonNumber num = json.getJsonNumber(key);
        return (num == null) ? defaultValue : num.longValue();
    }


}
