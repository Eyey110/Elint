package com.qisejin.lintlib.detector;

import com.android.annotations.NonNull;
import com.android.resources.ResourceFolderType;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;


/**
 * No Description
 * <p>
 * Created by 17:13 2018/5/18.
 * Email:46499102@qq.com
 *
 * @author Eyey
 */

public abstract class BaseResourceXmlDetector extends BaseDetector implements Detector.XmlScanner {
//    @Override
//    public boolean appliesTo(@NonNull ResourceFolderType folderType) {
//        return true;
//    }
//
//    @Override
//    public void run(@NonNull Context context) {
//        // The infrastructure should never call this method on an xml detector since
//        // it will run the various visitors instead
//        assert false;
//    }
}
