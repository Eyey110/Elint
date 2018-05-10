package com.qisejin.lintlib;

import com.android.tools.lint.client.api.JavaParser;
import com.android.tools.lint.detector.api.*;

import java.util.Collections;
import java.util.List;
import com.android.tools.lint.client.api.JavaParser;
import com.android.tools.lint.detector.api.*;

public class LogDetector extends Detector implements Detector.UastScanner {

    public static final Issue ISSUE = Issue.create(
            "LogUse",
            "避免使用Log/System.out.println",
            "使用LogUtils，防止在正式包打印log",
            Category.SECURITY, 5, Severity.WARNING,
            new Implementation(LogDetector.class, Scope.JAVA_FILE_SCOPE));




//    @Override
//    public List<Class<? extends Node>> getApplicableNodeTypes() {
//        return Collections.singletonList(MethodInvocation.class);
//    }
//
//    @Override
//    public AstVisitor createJavaVisitor(final JavaContext context) {
//        return new ForwardingAstVisitor() {
//            @Override
//            public boolean visitMethodInvocation(MethodInvocation node) {
//                if (node.toString().startsWith("System.out.println")) {
//                    context.report(ISSUE, node, context.getLocation(node),
//                            "请使用Ln，避免使用System.out.println");
//                    return true;
//                }
//                JavaParser.ResolvedNode resolve = context.resolve(node);
//                if (resolve instanceof JavaParser.ResolvedMethod) {
//                    JavaParser.ResolvedMethod method = (JavaParser.ResolvedMethod) resolve;
//                    JavaParser.ResolvedClass containingClass = method.getContainingClass();
//                    if (containingClass.matches("android.util.Log")) {
//                        context.report(ISSUE, node, context.getLocation(node),
//                                "请使用Ln，避免使用Log");
//                        return true;
//                    }
//                }
//                return super.visitMethodInvocation(node);
//            }
//        };
//    }
}