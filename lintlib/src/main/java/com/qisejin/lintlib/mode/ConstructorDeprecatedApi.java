package com.qisejin.lintlib.mode;

/**
 * No Description
 * <p>
 * Created by 15:57 2018/5/11.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */

public class ConstructorDeprecatedApi extends DeprecatedApi {
    private String construction;

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }


    @Override
    public String toString() {
        return super.toString() + "ConstructorDeprecatedApi{" +
                "construction='" + construction + '\'' +
                '}';
    }
}
