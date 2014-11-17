package org.sap.tia;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.sap.tia.dom.Task;
import org.sap.tia.dom.TestInfo;
import org.sap.tia.util.PropertiesUtil;

import javax.persistence.*;

/**
 * @author Fred
 * @since Aug 22th
 * Mysql operations
 */
public class MysqlOperations implements Operations {
    private static final Logger logger = Logger.getLogger(MysqlOperations.class);

    static {
        BasicConfigurator.configure();
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.ERROR);
        logger.setLevel(Level.INFO);
    }

    @Override
    public boolean writeTestInfo(String taskName, String taskId, String status) {
        if (!PropertiesUtil.isCorrectStatus(status)) {
            logger.info("Undefined status " + status + ", should be in " + PropertiesUtil.printAllDefinedStatus());
            return false;
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysqlUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<TestInfo> namedQuery = entityManager.createNamedQuery("TestInfo.findByIdAndName", TestInfo.class);
        namedQuery.setParameter("name", taskName).setParameter("id", taskId);
        TestInfo testInfo;
        try {
            testInfo = namedQuery.getSingleResult();
        } catch (NoResultException e) {
            testInfo = new TestInfo(taskName, taskId, status);
        }
        testInfo.setModifyTime(System.currentTimeMillis() / 1000);
        testInfo.setStatus(status);
        logger.info(testInfo.toString());
        entityManager.persist(testInfo);
        try {
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return true;
    }

    @Override
    public boolean writeTestInfo(String taskName, String taskId, String status, Long startTime, Long modifyTime) {
        if (!PropertiesUtil.isCorrectStatus(status)) {
            logger.info("Undefined status " + status + ", should be in " + PropertiesUtil.printAllDefinedStatus());
            return false;
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysqlUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<TestInfo> namedQuery = entityManager.createNamedQuery("TestInfo.findByIdAndName", TestInfo.class);
        namedQuery.setParameter("name", taskName).setParameter("id", taskId);
        TestInfo testInfo;
        try {
            testInfo = namedQuery.getSingleResult();
        } catch (NoResultException e) {
            testInfo = new TestInfo(taskName, taskId, status, startTime, modifyTime);
        }
        testInfo.setCreateTime(startTime);
        testInfo.setModifyTime(modifyTime);
        testInfo.setStatus(status);
        logger.info(testInfo.toString());
        entityManager.persist(testInfo);
        try {
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return true;
    }

    @Override
    public boolean writeTestInfo(String taskName, String taskId, String status, Long startTime, Long modifyTime, String msg) {
        if (!PropertiesUtil.isCorrectStatus(status)) {
            logger.info("Undefined status " + status + ", should be in " + PropertiesUtil.printAllDefinedStatus());
            return false;
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysqlUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<TestInfo> namedQuery = entityManager.createNamedQuery("TestInfo.findByIdAndName", TestInfo.class);
        namedQuery.setParameter("name", taskName).setParameter("id", taskId);
        TestInfo testInfo;
        try {
            testInfo = namedQuery.getSingleResult();
        } catch (NoResultException e) {
            testInfo = new TestInfo(taskName, taskId, status, startTime, modifyTime, msg);
        }
        testInfo.setCreateTime(startTime);
        testInfo.setModifyTime(modifyTime);
        testInfo.setStatus(status);
        testInfo.setMsg(msg);
        logger.info(testInfo.toString());
        entityManager.persist(testInfo);
        try {
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return true;
    }

    @Override
    @Deprecated
    public boolean writeLogs(String machineName, String taskName, String version, String status, String author, String platform) {
        if (!PropertiesUtil.isCorrectTask(taskName)) {
            logger.info("Undefined task name " + taskName + ", should be in " + PropertiesUtil.printAllDefinedTasks());
            return false;
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysqlUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Task log = new Task(machineName, taskName, version, status, author, platform);
        logger.info(log.toString());
        entityManager.persist(log);
        try {
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return true;
    }
}
