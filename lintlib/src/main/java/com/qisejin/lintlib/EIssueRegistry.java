
package com.qisejin.lintlib;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;
import com.qisejin.lintlib.detector.DeprecatedApiDetector;
import com.qisejin.lintlib.detector.DirectConstructorDetector;
import com.qisejin.lintlib.detector.ResourceFormatDetector;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;


public class EIssueRegistry extends IssueRegistry {
    @NotNull
    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(
                DeprecatedApiDetector.WARNING_ISSUE, DeprecatedApiDetector.ERROR_ISSUE,
                DirectConstructorDetector.WARNING_ISSUE, DirectConstructorDetector.ERROR_ISSUE,
                ResourceFormatDetector.ISSUE_ERROR, ResourceFormatDetector.ISSUE_WARNING
        );
    }
}
