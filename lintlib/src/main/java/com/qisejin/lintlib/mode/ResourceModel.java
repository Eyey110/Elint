package com.qisejin.lintlib.mode;

/**
 * No Description
 * <p>
 * Created by 17:27 2018/5/18.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */

public class ResourceModel extends BaseModel{

    public String folderName;
    public String tagName;
    public String attrKey;
    public String attrValueRegex;


    @Override
    public String toString() {
        return "ResourceModel{" +
                "folderName='" + folderName + '\'' +
                ", tagName='" + tagName + '\'' +
                ", attrKey='" + attrKey + '\'' +
                ", attrValueRegex='" + attrValueRegex + '\'' +
                '}';
    }
}
