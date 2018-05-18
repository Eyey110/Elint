package com.qisejin.lintlib;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.qisejin.lintlib.mode.ConstructorBaseModel;
import com.qisejin.lintlib.mode.MethodBaseModel;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.android.tools.lint.detector.api.Context;
import com.qisejin.lintlib.mode.ResourceModel;

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
    private boolean isInit = false;

    private static final String CONFIG_JSON_FILE_NAME = "lint-rules.json";

    //   MethodBaseModel deprecatedApi;

    private List<MethodBaseModel> methodDeprecatedApiList = new ArrayList<>();
    private List<ConstructorBaseModel> constructorDeprecatedApiList = new ArrayList<>();
    private List<ResourceModel> resourceModels = new ArrayList<>();

    public List<ConstructorBaseModel> getConstructorDeprecatedApiList() {
        return constructorDeprecatedApiList;
    }

    public List<MethodBaseModel> getMethodDeprecatedApiList() {
        return methodDeprecatedApiList;
    }

    public List<ResourceModel> getResourceModels() {
        return resourceModels;
    }

    private LintConfig() {

    }


    private synchronized void clear() {
        methodDeprecatedApiList.clear();
        constructorDeprecatedApiList.clear();
        resourceModels.clear();
    }

    public synchronized void readConfigIfNot(Context context) {
        if (isInit) {
            return;
        }
        try {
            JsonObject jsonObject = new JsonParser().parse(readJsonStrFromFile(context).toString()).getAsJsonObject();
            JsonObject rules = jsonObject.get("lint-rules").getAsJsonObject();
            JsonArray deprecatedApis = rules.get("deprecated-api").getAsJsonArray();
            parseDeprecatedApis(deprecatedApis);
            JsonArray resourceModelsArray = rules.get("resources").getAsJsonArray();
            parseResourceModels(resourceModelsArray);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } finally {
            isInit = true;
        }
    }


    private StringBuilder readJsonStrFromFile(Context context) {
        File projectDir = context.getProject().getDir();
        File configFile = new File(projectDir, CONFIG_JSON_FILE_NAME);
        StringBuilder stringBuilder = new StringBuilder();
        if (configFile.exists() && configFile.isFile()) {
            try {
                FileInputStream inputStream = new FileInputStream(configFile);
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String str = "";
                while ((str = bufferedReader.readLine()) != null) {
                    stringBuilder.append(str);
                }
                inputStream.close();
                reader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder;
    }


    private void parseDeprecatedApis(JsonArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jo = jsonArray.get(i).getAsJsonObject();
            if (jo.has("method-regex")) {
                MethodBaseModel mda = new MethodBaseModel();
                mda.setMethodRegex(jo.get("method-regex").getAsString());
                mda.setMemberClass(jo.get("member-class").getAsString());
                mda.setMessage(jo.get("message").getAsString());
                mda.setSeverity(jo.get("severity").getAsString());
                methodDeprecatedApiList.add(mda);
            } else if (jo.has("construction")) {
                ConstructorBaseModel cda = new ConstructorBaseModel();
                cda.setConstruction(jo.get("construction").getAsString());
                cda.setMessage(jo.get("message").getAsString());
                cda.setSeverity(jo.get("severity").getAsString());
                constructorDeprecatedApiList.add(cda);
            }
        }
    }

    private void parseResourceModels(JsonArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jo = jsonArray.get(i).getAsJsonObject();
            ResourceModel rm = new ResourceModel();
            rm.folderName = jo.get("folder").getAsString();
            rm.attrKey = jo.get("attr-key").getAsString();
            rm.tagName = jo.get("tag-name").getAsString();
            rm.attrValueRegex = jo.get("attr-value-regex").getAsString();
            rm.setMessage(jo.get("message").getAsString());
            rm.setSeverity(jo.get("severity").getAsString());
            resourceModels.add(rm);
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
