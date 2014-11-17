package org.sap.tia.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * @author Fred
 * @since Aug 22th
 * Tasks name should be controlled.
 */
public class PropertiesUtil {
    private static Set<String> taskNameSet = null;
    private static Set<String> taskStatusSet = null;
    private static final String TASK_PROPERTY = "task";
    private static final String STATUS_PROPERTY = "taskStatus";
    private static Properties prop = null;

    private static void resolve() {
        if (taskNameSet == null) {
            try {
                prop = loadProperties();
                taskNameSet = new HashSet<>();
                Collections.addAll(taskNameSet, prop.getProperty(TASK_PROPERTY).split(","));
                taskStatusSet = new HashSet<>();
                Collections.addAll(taskStatusSet, prop.getProperty(STATUS_PROPERTY).split(","));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Properties loadProperties() throws IOException {
        if (prop == null) {
            InputStream in = PropertiesUtil.class.getResourceAsStream("/META-INF/config.properties");
            prop = new Properties();
            prop.load(in);
        }
        return prop;
    }

    public static boolean isCorrectTask(String taskName) {
        resolve();
        return taskNameSet.contains(taskName);
    }

    public static boolean isCorrectStatus(String status) {
        resolve();
        return taskStatusSet.contains(status);
    }

    public static String printAllDefinedTasks() {
        return taskNameSet.toString();
    }

    public static String printAllDefinedStatus() {
        return taskStatusSet.toString();
    }
}
