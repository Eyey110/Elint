package com.qisejin.lintlib;

import com.android.annotations.NonNull;
import com.android.tools.lint.client.api.JavaParser;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import java.util.Collections;
import java.util.List;


/**
 * No Description
 * <p>
 * Created by 15:05 2018/5/10.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */

public class NewThreadDetector extends Detector implements Detector.UastScanner {
    public static final Issue ISSUE = Issue.create(
            "NewThread",
            "避免使用new Thread()",
            "请使用线程池",
            Category.SECURITY, 5, Severity.ERROR,
            new Implementation(NewThreadDetector.class, Scope.JAVA_FILE_SCOPE));


//    @Override
//    public List<Class<? extends Node>> getApplicableNodeTypes() {
//        return Collections.singletonList(ConstructorInvocation.class);
//    }
//
//    public AstVisitor createJavaVisitor(@NonNull JavaContext context) {
//        return new NewThreadConstructorAstVisitor(context);
//    }
//
//
//    private static class NewThreadConstructorAstVisitor extends ForwardingAstVisitor {
//
//        private JavaContext mContext;
//
//        public NewThreadConstructorAstVisitor(JavaContext mContext) {
//            this.mContext = mContext;
//        }
//
//        @Override
//        public boolean visitConstructorDeclaration(ConstructorDeclaration node) {
//            JavaParser.ResolvedNode resolvedType = mContext.resolve(node);
//            JavaParser.ResolvedClass resolvedClass = (JavaParser.ResolvedClass) resolvedType;
//
//            String typeName = node.astTypeName().astValue();
//            if (resolvedClass != null
//                    && typeName.equals("Thread")
////                    && resolvedClass.isSubclassOf("java.lang.Thread", false)
//                    ) {
//                mContext.report(ISSUE,
//                        node,
//                        mContext.getLocation(node),
//                        "You should not call `new Thread()` directly.");
//                return true;
//            }
//            return super.visitConstructorDeclaration(node);
//        }
//    }
}
