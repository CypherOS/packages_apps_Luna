package co.aoscp.lovegood;

import android.content.ComponentName;
import android.view.View;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Workspace.ItemOperator;
import com.android.launcher3.util.ComponentKey;

public class ComponentItemOperator implements ItemOperator {

    public ComponentKey mComponentKey;

    public ComponentItemOperator(ComponentKey componentKey) {
        mComponentKey = componentKey;
    }

    public boolean evaluate(ItemInfo info, View view) {
        Object packageName;
        ComponentName componentName;
        Object obj = null;
        if (info != null) {
            ComponentName targetComponent = info.getTargetComponent();
            if (targetComponent != null) {
                packageName = targetComponent.getPackageName();
                componentName = mComponentKey.componentName;
				if (packageName.equals(componentName.getPackageName())) {
					if (info != null) {
						obj = info.user;
					}
					if (obj.equals(mComponentKey.user)) {
						return true;
					}
				}
                return false;				
            }
        }
        packageName = null;
        componentName = mComponentKey.componentName;
        return false;
    }
}