package com.qisejin.lintlib.mode;

/**
 * No Description
 * <p>
 * Created by 15:56 2018/5/11.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */

public class MethodDeprecatedApi extends DeprecatedApi {
    private String methodRegex;
    private String memberClass;

    public String getMemberClass() {
        return memberClass;
    }

    public void setMemberClass(String memberClass) {
        this.memberClass = memberClass;
    }

    public String getMethodRegex() {
        return methodRegex;
    }

    public void setMethodRegex(String methodRegex) {
        this.methodRegex = methodRegex;
    }


    @Override
    public String toString() {
        return super.toString() + "MethodDeprecatedApi{" +
                "methodRegex='" + methodRegex + '\'' +
                ", memberClass='" + memberClass + '\'' +
                '}';
    }
}
