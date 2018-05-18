package com.qisejin.lintlib.detector;

import com.android.annotations.NonNull;
import com.android.annotations.Nullable;
import com.android.resources.ResourceConstants;
import com.android.resources.ResourceFolderType;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.ResourceXmlDetector;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.XmlContext;
import com.android.tools.lint.detector.api.XmlScanner;
import com.qisejin.lintlib.LintConfig;
import com.qisejin.lintlib.mode.ResourceModel;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static com.android.SdkConstants.ATTR_NAME;


/**
 * No Description
 * <p>
 * Created by 18:43 2018/5/16.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */

public class ResourceFormatDetector extends ResourceXmlDetector {
    public static final Issue ISSUE_WARNING = Issue.create("ColorNameFormatWarning",
            "命名问题警告",
            "命名问题警告",
            Category.MESSAGES,
            5,
            Severity.WARNING,
            new Implementation(ResourceFormatDetector.class,
                    Scope.RESOURCE_FILE_SCOPE));

    public static final Issue ISSUE_ERROR = Issue.create("ColorNameFormatError",
            "命名问题错误",
            "命名问题错误",
            Category.MESSAGES,
            5,
            Severity.ERROR,
            new Implementation(ResourceFormatDetector.class,
                    Scope.RESOURCE_FILE_SCOPE));


    private Set<ResourceFolderType> types = new HashSet<>();


    private void initTypes() {
        List<ResourceModel> models = LintConfig.getInstance().getResourceModels();
        for (ResourceModel model : models) {
            if (ResourceConstants.FD_RES_VALUES.equals(model.folderName)) {
                types.add(ResourceFolderType.VALUES);
            } else if (ResourceConstants.FD_RES_XML.equals(model.folderName)) {
                types.add(ResourceFolderType.XML);
            } else if (ResourceConstants.FD_RES_TRANSITION.equals(model.folderName)) {
                types.add(ResourceFolderType.TRANSITION);
            } else if (ResourceConstants.FD_RES_RAW.equals(model.folderName)) {
                types.add(ResourceFolderType.RAW);
            } else if (ResourceConstants.FD_NAVIGATION.equals(model.folderName)) {
                types.add(ResourceFolderType.NAVIGATION);
            } else if (ResourceConstants.FD_RES_MIPMAP.equals(model.folderName)) {
                types.add(ResourceFolderType.MIPMAP);
            } else if (ResourceConstants.FD_RES_MENU.equals(model.folderName)) {
                types.add(ResourceFolderType.MENU);
            } else if (ResourceConstants.FD_RES_LAYOUT.equals(model.folderName)) {
                types.add(ResourceFolderType.LAYOUT);
            } else if (ResourceConstants.FD_RES_INTERPOLATOR.equals(model.folderName)) {
                types.add(ResourceFolderType.INTERPOLATOR);
            } else if (ResourceConstants.FD_RES_FONT.equals(model.folderName)) {
                types.add(ResourceFolderType.FONT);
            } else if (ResourceConstants.FD_RES_DRAWABLE.equals(model.folderName)) {
                types.add(ResourceFolderType.DRAWABLE);
            } else if (ResourceConstants.FD_RES_COLOR.equals(model.folderName)) {
                types.add(ResourceFolderType.COLOR);
            } else if (ResourceConstants.FD_RES_ANIMATOR.equals(model.folderName)) {
                types.add(ResourceFolderType.ANIMATOR);
            } else if (ResourceConstants.FD_RES_ANIM.equals(model.folderName)) {
                types.add(ResourceFolderType.ANIM);
            }
        }

    }

    @Override
    public void beforeCheckProject(Context context) {
        LintConfig.getInstance().readConfigIfNot(context);
        initTypes();
    }


    @Override
    public void beforeCheckLibraryProject(Context context) {
        LintConfig.getInstance().readConfigIfNot(context);
        initTypes();

    }

    @Override
    @Nullable
    public Collection<String> getApplicableAttributes() {
        List<ResourceModel> models = LintConfig.getInstance().getResourceModels();
        List<String> attrs = new ArrayList<>();
        for (ResourceModel model : models) {
            attrs.add(model.attrKey);
        }

        return attrs;
    }

    @Override
    public boolean appliesTo(@NonNull ResourceFolderType folderType) {
        for (ResourceFolderType type : types) {
            if (type == folderType) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<String> getApplicableElements() {
        List<ResourceModel> models = LintConfig.getInstance().getResourceModels();
        Set<String> tags = new HashSet<>();
        for (ResourceModel model : models) {
            tags.add(model.tagName);
        }
        return tags;
    }

    @Override
    public void visitElement(XmlContext context, Element element) {

        List<ResourceModel> models = LintConfig.getInstance().getResourceModels();
        for (ResourceModel model : models) {
            final Attr attributeNode = element.getAttributeNode(model.attrKey);
            if (attributeNode != null) {
                final String val = attributeNode.getValue();
                final String tag = attributeNode.getOwnerElement().getTagName();
                if (tag.equals(model.tagName) && !Pattern.matches(model.attrValueRegex, val)) {
                    context.report("error".equals(model.getSeverity()) ? ISSUE_ERROR : ISSUE_WARNING,
                            attributeNode,
                            context.getLocation(attributeNode),
                            model.getMessage());
                }
            }
        }
    }
}
