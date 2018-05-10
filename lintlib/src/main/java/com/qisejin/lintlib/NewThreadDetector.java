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

import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.ast.AstVisitor;
import lombok.ast.ConstructorInvocation;
import lombok.ast.ForwardingAstVisitor;
import lombok.ast.Node;
import lombok.ast.TypeReference;


/**
 * No Description
 * <p>
 * Created by 15:05 2018/5/10.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */

public class NewThreadDetector extends Detector implements Detector.JavaScanner {
    public static final Issue ISSUE = Issue.create(
            "NewThread",
            "避免使用new Thread",
            "请使用线程池",
            Category.SECURITY, 5, Severity.WARNING,
            new Implementation(NewThreadDetector.class, Scope.JAVA_FILE_SCOPE));


    @Override
    public List<Class<? extends Node>> getApplicableNodeTypes() {
        return Collections.singletonList(ConstructorInvocation.class);
    }


    @Override
    public AstVisitor createJavaVisitor(final @NonNull JavaContext context) {
        return new ForwardingAstVisitor() {

            @Override
            public boolean visitConstructorInvocation(ConstructorInvocation node) {
                TypeReference reference = node.astTypeReference();
                String typeName = reference.astParts().last().astIdentifier().astValue();
                // TODO: Should we handle factory method constructions of HashMaps as well,
                // e.g. via Guava? This is a bit trickier since we need to infer the type
                // arguments from the calling context.
                if (typeName.equals("Thread")) {
                    context.report(ISSUE,
                            context.getLocation(node),
                            "最好使用线程池");
                }
                return super.visitConstructorInvocation(node);
            }
        };
    }


//    @Override
//    public void visitConstructor(JavaContext context, JavaElementVisitor visitor, PsiNewExpression node, PsiMethod constructor) {
//        Location location = context.getLocation(node);
//        String message = "Suggest use thread pool";
//        context.report(ISSUE, node, location, message);
//    }

//    private static boolean specifiesLocale(@NonNull UastLanguagePlugin.ResolvedMethod method) {
//        for (int i = 0, n = method.getArgumentCount(); i < n; i++) {
//            TypeDescriptor argumentType = method.getArgumentType(i);
//            if (argumentType.matchesSignature(LOCALE_CLS)) {
//                return true;
//            }
//        }
//        return false;
//    }


    //    @Override
//    public void visitConstructor(@NonNull JavaContext context, @Nullable AstVisitor visitor,
//                                 @NonNull ConstructorInvocation node, @NonNull ResolvedMethod constructor) {
//        if (!specifiesLocale(constructor)) {
//            Location location = context.getLocation(node);
//            String message =
//                    "To get local formatting use `getDateInstance()`, `getDateTimeInstance()`, " +
//                            "or `getTimeInstance()`, or use `new SimpleDateFormat(String template, " +
//                            "Locale locale)` with for example `Locale.US` for ASCII dates.";
//            context.report(DATE_FORMAT, node, location, message);
//        }
//    }

//    @Override
//    public void checkCall(ClassContext context, ClassNode classNode, MethodNode method, MethodInsnNode call) {
//        String owner = call.owner;
//        if (owner.startsWith("android/util/Log")) {
//            context.report(ISSUE,
//                    method,
//                    call,
//                    context.getLocation(call),
//                    "You must use `LogUtils`");
//        }
//    }
}
