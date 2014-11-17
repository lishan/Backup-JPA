package org.sap.tia.dom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Fred
 * @since Aug 22th
 * Main schema for logs
 */
@Entity
@Table(name = "hadr_task_info")
public class Task implements Serializable {
    @Id
    private int id;
    @Column(name = "machines")
    private String machineName;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "versions")
    private String version;
    @Column(name = "create_time")
    private Long createTime;
    @Column(name = "modify_time")
    private Long modifyTime;
    @Column(name = "status")
    private String status;
    @Column(name = "author")
    private String author;
    @Column(name = "platform")
    private String platform;

    public Task() {
    }

    public Task(String machineName, String taskName, String version, String status, String author, String platform) {
        this.machineName = machineName;
        this.taskName = taskName;
        this.version = version;
        this.status = status;
        this.author = author;
        this.platform = platform;
        this.createTime = System.currentTimeMillis() / 1000;
        this.modifyTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", machineName='" + machineName + '\'' +
                ", taskName='" + taskName + '\'' +
                ", version='" + version + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", status='" + status + '\'' +
                ", author='" + author + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }
}
