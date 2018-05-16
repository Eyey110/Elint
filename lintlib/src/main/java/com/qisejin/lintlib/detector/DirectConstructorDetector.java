package com.qisejin.lintlib.detector;

import com.android.tools.lint.client.api.JavaEvaluator;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.PsiMethod;
import com.qisejin.lintlib.LintConfig;
import com.qisejin.lintlib.mode.ConstructorDeprecatedApi;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * No Description
 * <p>
 * Created by 17:28 2018/5/11.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */

public class DirectConstructorDetector extends Detector implements Detector.UastScanner {
    public static final Issue WARNING_ISSUE = Issue.create(
            "DirectConstructor",
            "避免直接使用构造函数",
            "避免直接使用构造函数，具体查看Message",
            Category.SECURITY, 5, Severity.WARNING,
            new Implementation(DirectConstructorDetector.class, Scope.JAVA_FILE_SCOPE));

    public static final Issue ERROR_ISSUE = Issue.create(
            "DirectConstructor",
            "避免直接使用构造函数",
            "避免直接使用构造函数，具体查看Message",
            Category.SECURITY, 9, Severity.ERROR,
            new Implementation(DirectConstructorDetector.class, Scope.JAVA_FILE_SCOPE));

    @Nullable
    @Override
    public List<String> getApplicableConstructorTypes() {
        List<ConstructorDeprecatedApi> das = LintConfig.getInstance().getConstructorDeprecatedApiList();
        List<String> constructions = new ArrayList<>();
        for (ConstructorDeprecatedApi ca : das) {
            String construction = ca.getConstruction();
            if (construction != null && !construction.isEmpty()) {
                constructions.add(construction);
            }
        }
        return constructions;
    }

    @Override
    public void visitConstructor(JavaContext context, UCallExpression node, PsiMethod constructor) {
        List<ConstructorDeprecatedApi> das = LintConfig.getInstance().getConstructorDeprecatedApiList();
        JavaEvaluator evaluator = context.getEvaluator();
        for (ConstructorDeprecatedApi api : das) {
            if (evaluator.isMemberInSubClassOf(constructor, api.getConstruction(), false)) {
                context.report("warning".equals(api.getSeverity()) ? WARNING_ISSUE : ERROR_ISSUE,
                        constructor,
                        context.getLocation(constructor),
                        api.getMessage());
                return;
            }
        }
    }

}
