package org.sap.tia.util;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * @since Sep 2nd
 * Since we need to run jar file to call specific method, we need have a proxy to do it.
 * All the properties should be defined in properties files.
 */
public class MethodUtil {
    private static Set<String> methods = null;
    private static final String METHODS = "methods";

    public static boolean isCorrectMethod(String method) {
        DynamicLoadMethods();
        return methods.contains(method);
    }

    private static void DynamicLoadMethods() {
        if (methods == null) {
            try {
                Properties properties = PropertiesUtil.loadProperties();
                String property = properties.getProperty(METHODS);
                String[] splitProperty = property.split(",");
                methods = new HashSet<>();
                Collections.addAll(methods, splitProperty);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
