package com.qisejin.lintlib;

import com.android.annotations.NonNull;
import com.android.tools.lint.client.api.JavaParser;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.ClassContext;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Location;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.PsiMethod;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;
import org.jetbrains.uast.UElement;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Arrays;
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
            "避免使用new Thread",
            "请使用线程池",
            Category.SECURITY, 5, Severity.WARNING,
            new Implementation(NewThreadDetector.class, Scope.JAVA_FILE_SCOPE));

    @Nullable
    @Override
    public List<String> getApplicableConstructorTypes() {
        return Collections.singletonList("java.lang.Thread");
    }

    @Override
    public void visitConstructor(JavaContext context, UCallExpression node, PsiMethod constructor) {
        Location location = context.getLocation(node);
        context.report(ISSUE, node, location,
                "请尽量使用线程池");
    }

}
