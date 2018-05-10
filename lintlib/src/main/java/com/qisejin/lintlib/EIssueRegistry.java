
package com.qisejin.lintlib;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class EIssueRegistry extends IssueRegistry {
    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(LogDetector.ISSUE, NewThreadDetector.ISSUE);
    }
}
