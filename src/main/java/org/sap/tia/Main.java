package org.sap.tia;

import org.sap.tia.util.MethodUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This is main entrance of java jar.
 * For example, call java -jar hadrconnection.jar writeLogs taskId
 *
 * @author Fred
 * @since Sep 1st
 */
public class Main {
    public static void main(String args[]) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        CallMethod(args);
    }

    static void CallMethod(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (args.length < 1 || args.length > 4) {
            printHelpers();
            return;
        }
        if (!MethodUtil.isCorrectMethod(args[0])) {
            printHelpers();
            return;
        }
        Operations operations = new MysqlOperations();
        Method method = MysqlOperations.class.getMethod(args[0], String.class, String.class, String.class);
        method.invoke(operations, args[1], args[2], args[3]);
    }

    private static void printHelpers() {
        System.out.println("Usage :");
        System.out.println("method args1 args2 args3");
    }
}
