package com.FeeReport;

import com.FeeReport.FRGUI.Controller.Controller;
import java.util.concurrent.ConcurrentHashMap;

public class DependencyInjector extends Controller {
    private static final ConcurrentHashMap<Class<?>, Object> dependencies = new ConcurrentHashMap<>();

    public static void addDependency(Class<?> interFace, Object dependency) {
        dependencies.put(interFace, dependency);
    }

    public static Object injectDependency(Class<?> interFace) {
        for (var dep : dependencies.values())
            if (interFace.isInstance(dep))
                return dep;
        return null;
    }

}
