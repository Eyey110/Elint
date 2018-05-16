package com.qisejin.lintlib;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qisejin.lintlib.mode.ConstructorDeprecatedApi;
import com.qisejin.lintlib.mode.DeprecatedApi;
import com.qisejin.lintlib.mode.MethodDeprecatedApi;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * No Description
 * <p>
 * Created by 11:56 2018/5/11.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */

public class LintConfig {


    private static LintConfig config;
    private final static Object lock = new Object();

    //   MethodDeprecatedApi deprecatedApi;
    static final String json = "{\n" +
            "  \"lint-rules\": {\n" +
            "    \"deprecated-api\": [\n" +
            "      {\n" +
            "        \"method-regex\": \"v|d|e|w|i\",\n" +
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

    private List<MethodDeprecatedApi> methodDeprecatedApiList;
   private List<ConstructorDeprecatedApi> constructorDeprecatedApiList;

    public List<ConstructorDeprecatedApi> getConstructorDeprecatedApiList() {
        return constructorDeprecatedApiList;
    }

    public List<MethodDeprecatedApi> getMethodDeprecatedApiList() {
        return methodDeprecatedApiList;
    }

    private LintConfig() {
//        File projectDir = context.getProject().getDir();
//        File configFile = new File(projectDir, "lint-config.json");
//        if (configFile.exists() && configFile.isFile()) {
//            // 读取配置文件...
//        }
        methodDeprecatedApiList = new ArrayList<>();
        constructorDeprecatedApiList = new ArrayList<>();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonObject rules = jsonObject.get("lint-rules").getAsJsonObject();
        JsonArray deprecatedApis = rules.get("deprecated-api").getAsJsonArray();
        for (int i = 0; i < deprecatedApis.size(); i++) {
            JsonObject jo = deprecatedApis.get(i).getAsJsonObject();
            if (jo.has("method-regex")) {
                MethodDeprecatedApi mda = new MethodDeprecatedApi();
                mda.setMethodRegex(jo.get("method-regex").getAsString());
                mda.setMemberClass(jo.get("member-class").getAsString());
                mda.setMessage(jo.get("message").getAsString());
                mda.setSeverity(jo.get("severity").getAsString());
                methodDeprecatedApiList.add(mda);
            } else if (jo.has("construction")) {
                ConstructorDeprecatedApi cda = new ConstructorDeprecatedApi();
                cda.setConstruction(jo.get("construction").getAsString());
                cda.setMessage(jo.get("message").getAsString());
                cda.setSeverity(jo.get("severity").getAsString());
                constructorDeprecatedApiList.add(cda);
            }
        }
    }


    public static LintConfig getInstance() {
        if (config == null) {
            synchronized (lock) {
                if (config == null) {
                    config = new LintConfig();
                }
            }
        }
        return config;
    }
}
