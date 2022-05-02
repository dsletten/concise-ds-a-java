package containers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

//    Raw Supplier????
public class GeneralRemoteControl {
    private Map<String, Supplier> iface = new HashMap<>();

    public GeneralRemoteControl(Map<String, Supplier> iface) {
        this.iface.putAll(iface);
    }

    public Object press(String methodName) {
        Supplier method = iface.get(methodName);

        if ( method == null ) {
            throw new IllegalArgumentException("No method found: " + methodName);
        } else {
            return method.get();
        }
    }
}
