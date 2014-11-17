package org.sap.tia.dom;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This is main deploy table to show task running status
 *
 * @author Fred
 * @since Sep 2nd
 */
@Entity
@Table(name = "barrier_test_info")
@NamedQueries({
        @NamedQuery(name = "TestInfo.findByIdAndName",
                query = "SELECT c FROM TestInfo c WHERE c.taskName = :name and c.taskId = :id"),
        @NamedQuery(name = "TestInfo.loadAllByIdAndName",
                query =  "SELECT c FROM TestInfo c WHERE c.taskId = :id and c.status = :status")
})
public class TestInfo implements Serializable {
    @Id
    private int id;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_id")
    private String taskId;
    @Column(name = "task_status")
    private String status;
    @Column(name = "create_time")
    private Long createTime;
    @Column(name = "modify_time")
    private Long modifyTime;
    @Column(name = "msg")
    private String msg;

    public TestInfo() {
    }

    public TestInfo(String taskName, String taskId, String status) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.status = status;
        this.createTime = System.currentTimeMillis() / 1000;
        this.modifyTime = createTime;
    }

    public TestInfo(String taskName, String taskId, String status, Long createTime, Long modifyTime) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.status = status;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public TestInfo(String taskName, String taskId, String status, Long createTime, Long modifyTime, String msg) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.status = status;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "TestInfo{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskId='" + taskId + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", msg='" + msg + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
