package angel.zxiu.mywatchdog.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxui on 15/10/15.
 */
public class Function {
    static Map<Class, List<Function>> functionMap = new HashMap<>();
    public int nameResId;
    public int imageResId;
    public Class clazz;

    public Function(int nameResId, int imageResId, Class clazz) {
        this.nameResId = nameResId;
        this.imageResId = imageResId;
        this.clazz = clazz;
    }

    public static List<Function> getFunctions(Class clazz) {
        if (!functionMap.containsKey(clazz)) {
            functionMap.put(clazz, new ArrayList<Function>());
        }
        return functionMap.get(clazz);
    }

    public boolean isType(Class parentClazz) {
        return clazz != null && parentClazz.isAssignableFrom(clazz);
    }

    @Override
    public String toString() {
        return "Function{" +
                "nameResId=" + nameResId +
                ", imageResId=" + imageResId +
                ", clazz=" + clazz +
                '}';
    }
}
