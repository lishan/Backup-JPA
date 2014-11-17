package org.sap.tia;

import java.util.List;

/**
 * @author Fred
 * @since Aug 22th
 * Main api to call operations to database
 */
public interface Operations {

    /**
     * This method is to write something into database to show task running status.
     * If already has record defined by [taskName,taskId], this will be update operation.
     *
     * @param taskName task name
     * @param taskId   id, these two params will confirm specific task
     * @param status   task status, including RUNNING,PENDING,SUCCESS,FAILED
     * @return write true or false
     */
    boolean writeTestInfo(String taskName, String taskId, String status);

    /**
     * Contains error message
     */
    boolean writeTestInfo(String taskName, String taskId, String status, String msg);

    /**
     * Contains startTime and modifyTime
     */
    boolean writeTestInfo(String taskName, String taskId, String status, Long startTime, Long modifyTime);

    /**
     * Contains error message
     */
    boolean writeTestInfo(String taskName, String taskId, String status, Long startTime, Long modifyTime, String msg);


    boolean changeStressNumber(String taskId, int stress);

    List<String> fetchAllTests(String taskId);

}
