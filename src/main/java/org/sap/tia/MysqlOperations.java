package org.sap.tia;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.sap.tia.dom.DeployInfo;
import org.sap.tia.dom.TestInfo;
import org.sap.tia.util.PropertiesUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fred
 * @since Aug 22th
 * Mysql operations
 */
public class MysqlOperations implements Operations {
    private static final String INIT = "INIT";
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
    public boolean writeTestInfo(String taskName, String taskId, String status, String msg) {
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
    public boolean changeStressNumber(String taskId, int stress) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysqlUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<DeployInfo> namedQuery = entityManager.createNamedQuery("DeployInfo.findByTaskId", DeployInfo.class);
        namedQuery.setParameter("taskId", taskId);

        DeployInfo deployInfo = namedQuery.getSingleResult();

        deployInfo.setStress(stress);
        entityManager.persist(deployInfo);

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
    public List<String> fetchAllTests(String taskId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysqlUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<TestInfo> namedQuery = entityManager.createNamedQuery("TestInfo.loadAllByIdAndName", TestInfo.class);
        namedQuery.setParameter("id", taskId).setParameter("status",INIT);
        List<TestInfo> tests = namedQuery.getResultList();
        List<String> allTests = new ArrayList<>();
        for (TestInfo info : tests) {
            allTests.add(info.getTaskName());
        }
        logger.info(tests.toString());
        return allTests;
    }

}
