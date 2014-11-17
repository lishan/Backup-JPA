package org.sap.tia.dom;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Main class dom for deploy info data table
 *
 * @author Fred
 * @since Nov 7th
 */
@Entity
@Table(name = "deploy_info")
@NamedQueries({
        @NamedQuery(name = "DeployInfo.findByTaskId",
                query = "SELECT c FROM DeployInfo c WHERE c.taskId = :taskId"),
})
public class DeployInfo implements Serializable {
    @Id
    private int id;
    @Column(name = "task_id")
    private String taskId;
    @Column(name = "stress")
    private int stress;

    @Override
    public String toString() {
        return "DeployInfo{" +
                "id=" + id +
                ", taskId='" + taskId + '\'' +
                ", stress='" + stress + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getStress() {
        return stress;
    }

    public void setStress(int stress) {
        this.stress = stress;
    }
}
