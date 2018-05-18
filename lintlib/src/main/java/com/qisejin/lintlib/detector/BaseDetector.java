package com.qisejin.lintlib.detector;

import com.android.annotations.NonNull;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.google.common.annotations.Beta;
import com.qisejin.lintlib.LintConfig;


/**
 * No Description
 * <p>
 * Created by 17:01 2018/5/18.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */
public abstract class BaseDetector extends Detector {
    @Override
    public void beforeCheckProject(@NonNull Context context) {
        // 读取配置
//        mLintConfig = new LintConfig();
        LintConfig.getInstance().readConfigIfNot(context);
    }

    @Override
    public void beforeCheckLibraryProject(@NonNull Context context) {
        // 读取配置
//        mLintConfig = new LintConfig(context);
        LintConfig.getInstance().readConfigIfNot(context);

    }

}
