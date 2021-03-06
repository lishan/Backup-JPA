package org.sap.tia;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class MysqlOperationsTest {
    @Test
    public void test_write_logs() {
        Operations operations = new MysqlOperations();
        System.out.println(operations.writeTestInfo("configure", "123", "SUCCESS"));
    }

    @Test
    public void test_call_method() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String args[] = {"writeTestInfo", "3", "3", "123"};
        Main.CallMethod(args);
    }

    @Test
    public void test_write_logs_with_time() {
        Operations operations = new MysqlOperations();
        operations.writeTestInfo("configure", "123", "SUCCESS","message");
    }

    @Test
    public void test_change_stress(){
        Operations operations = new MysqlOperations();
        operations.changeStressNumber("1414841735468",10);
    }

    @Test
    public void test_get_tests(){
        Operations operations = new MysqlOperations();
        System.out.println(operations.fetchAllTests("1411087195179"));
    }
}
