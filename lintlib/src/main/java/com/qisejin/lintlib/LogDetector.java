package com.qisejin.lintlib;

import com.android.tools.lint.client.api.JavaEvaluator;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.PsiMethod;

import org.jetbrains.uast.UCallExpression;

import java.util.Arrays;
import java.util.List;

/**
 * No Description
 * <p>
 * Created by 21:59 2018/5/10.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */

public class LogDetector extends Detector implements Detector.UastScanner {

    public static final Issue ISSUE = Issue.create("LogUtilsNotUsed",
            "You must use our `LogUtils`",
            "Logging should be avoided in production for security and performance reasons. Therefore, we created a LogUtils that wraps all our calls to Logger and disable them for release flavor.",
            Category.MESSAGES,
            9,
            Severity.WARNING,
            new Implementation(LogDetector.class,
                    Scope.JAVA_FILE_SCOPE));


    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("v", "d", "i", "w", "e");
    }


    @Override
    public void visitMethod(JavaContext context, UCallExpression node, PsiMethod method) {
        JavaEvaluator evaluator = context.getEvaluator();
        if (evaluator.isMemberInSubClassOf(method, "android.util.Log", false)) {
            context.report(ISSUE,
                    method,
                    context.getLocation(method),
                    "You must use `LogUtils`");
        }
    }
}
