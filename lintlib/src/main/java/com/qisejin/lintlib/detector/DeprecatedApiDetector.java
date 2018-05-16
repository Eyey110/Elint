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
import com.qisejin.lintlib.mode.MethodDeprecatedApi;

import org.jetbrains.uast.UCallExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * No Description
 * <p>
 * Created by 15:49 2018/5/11.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */

public class DeprecatedApiDetector extends Detector implements Detector.UastScanner {
    public static final Issue WARNING_ISSUE = Issue.create("DeprecatedApiWarning",
            "避免调用此方法",
            "避免调用此方法，详情请看Message",
            Category.MESSAGES,
            5,
            Severity.WARNING,
            new Implementation(DeprecatedApiDetector.class,
                    Scope.JAVA_FILE_SCOPE));
    public static final Issue ERROR_ISSUE = Issue.create("deprecatedApiError",
            "避免调用此方法",
            "避免调用此方法，详情请看Message",
            Category.MESSAGES,
            9,
            Severity.ERROR,
            new Implementation(DeprecatedApiDetector.class,
                    Scope.JAVA_FILE_SCOPE));

    @Override
    public List<String> getApplicableMethodNames() {
        List<MethodDeprecatedApi> das = LintConfig.getInstance().getMethodDeprecatedApiList();
        List<String> methods = new ArrayList<>();
        for (MethodDeprecatedApi da : das) {
            String methodRegex = da.getMethodRegex();
            if (methodRegex != null && !methodRegex.isEmpty()) {
                String[] ms = methodRegex.split("\\|");
                for (String method : ms) {
                    if (method != null && !method.isEmpty()) {
                        methods.add(method);
                    }
                }
            }
        }
        return methods;
    }


    @Override
    public void visitMethod(JavaContext context, UCallExpression node, PsiMethod method) {
        JavaEvaluator evaluator = context.getEvaluator();
        List<MethodDeprecatedApi> das = LintConfig.getInstance().getMethodDeprecatedApiList();
        System.out.println(method.getName());
        System.out.println(method.getHierarchicalMethodSignature());
        for (MethodDeprecatedApi api : das) {
            if (evaluator.isMemberInSubClassOf(method, api.getMemberClass(), false)) {
                context.report("warning".equals(api.getSeverity()) ? WARNING_ISSUE : ERROR_ISSUE,
                        method,
                        context.getLocation(method),
                        api.getMessage());
                return;
            }
        }
    }

}
