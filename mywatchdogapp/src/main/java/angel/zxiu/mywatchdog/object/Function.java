package angel.zxiu.mywatchdog.object;

import java.io.Serializable;

/**
 * Created by zxui on 15/10/15.
 */
public class Function implements Serializable {
    public int nameResId;
    public int imageResId;
    public Class clazz;

    public Function(int nameResId, int imageResId, Class clazz) {
        this.nameResId = nameResId;
        this.imageResId = imageResId;
        this.clazz = clazz;
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
