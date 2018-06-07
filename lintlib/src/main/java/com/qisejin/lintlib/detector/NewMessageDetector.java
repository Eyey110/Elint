package com.qisejin.lintlib.detector;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.PsiMethod;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;

import java.util.Collections;
import java.util.List;

/**
 * No Description
 * <p>
 * Created by 21:36 2018/6/7.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */

public class NewMessageDetector extends Detector implements Detector.UastScanner {
    public static final Issue WARNING_ISSUE = Issue.create(
            "NewMessage",
            "避免直接使用构造函数",
            "避免直接使用构造函数，具体查看Message",
            Category.SECURITY, 5, Severity.WARNING,
            new Implementation(NewMessageDetector.class, Scope.JAVA_FILE_SCOPE));

    @Nullable
    @Override
    public List<String> getApplicableConstructorTypes() {
        return Collections.singletonList("android.os.Message");
    }

    @Override
    public void visitConstructor(JavaContext context, UCallExpression node, PsiMethod constructor) {
        context.report(WARNING_ISSUE,
                constructor,
                context.getLocation(constructor),
                "test Message()");
    }
}
