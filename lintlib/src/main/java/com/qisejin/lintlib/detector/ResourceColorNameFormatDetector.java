package com.qisejin.lintlib.detector;

import com.android.annotations.NonNull;
import com.android.annotations.Nullable;
import com.android.resources.ResourceFolderType;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.ResourceXmlDetector;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.XmlContext;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Pattern;

import static com.android.SdkConstants.ATTR_NAME;
import static com.android.SdkConstants.TAG_COLOR;


/**
 * No Description
 * <p>
 * Created by 18:43 2018/5/16.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */

public class ResourceColorNameFormatDetector extends ResourceXmlDetector {
    public static final Issue ISSUE = Issue.create("ColorNameFormat",
            "Color name must be match the regex 'color_([0-9a-fA-F]{8}|[0-9a-fA-F]{6})' ",
            "Color name must be match the regex 'color_([0-9a-fA-F]{8}|[0-9a-fA-F]{6})'",
            Category.TYPOGRAPHY,
            5,
            Severity.WARNING,
            new Implementation(ResourceColorNameFormatDetector.class,
                    Scope.RESOURCE_FILE_SCOPE));


    //颜色命名的正则表达
    private static final String colorPattern = "color_([0-9a-fA-F]{8}|[0-9a-fA-F]{6})";

    @Override
    @Nullable
    public Collection<String> getApplicableAttributes() {
        return Collections.singletonList(ATTR_NAME);
    }

    @Override
    public boolean appliesTo(@NonNull ResourceFolderType folderType) {
        return ResourceFolderType.VALUES == folderType;
    }

    @Override
    public Collection<String> getApplicableElements() {
        return Collections.singletonList(TAG_COLOR);
    }

    @Override
    public void visitElement(XmlContext context, Element element) {
        final Attr attributeNode = element.getAttributeNode(ATTR_NAME);
        if (attributeNode != null) {
            final String val = attributeNode.getValue();
            if (!Pattern.matches(colorPattern, val)) {
                context.report(ISSUE,
                        attributeNode,
                        context.getLocation(attributeNode),
                        "Color name must be match the regex 'color_([0-9a-fA-F]{8}|[0-9a-fA-F]{6})'");
            }
        }
    }


}
