package com.qisejin.elint;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    static String json = "{\n" +
            "  \"lint-rules\": {\n" +
            "    \"deprecated-api\": [\n" +
            "      {\n" +
            "        \"method-regex\": \"(v|d|e|w|i)\",\n" +
            "        \"member-class\": \"android.util.Log\",\n" +
            "        \"message\": \"请勿直接使用android.Util.Log，应该使用LogUtils\",\n" +
            "        \"severity\": \"warning\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"field\": \"java.lang.System.out\",\n" +
            "        \"message\": \"请勿直接使用System.out，应该使用LogUtils\",\n" +
            "        \"severity\": \"warning\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"construction\": \"java.lang.Thread\",\n" +
            "        \"message\": \"避免单独创建Thread执行后台任务，存在性能问题，建议使用AsyncTask\",\n" +
            "        \"severity\": \"warning\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";

    @Test
    public void addition_isCorrect() throws Exception {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject rules = jsonObject.getJSONObject("lint-rules");
        JSONArray jsonArray = rules.getJSONArray("deprecated-api");
        for(int i=0;i<jsonArray.length();i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
        }
    }
}