package com.yeahmobi.yscheduler.model;

import com.yeahmobi.yscheduler.model.type.DependingStatus;
import com.yeahmobi.yscheduler.model.type.WorkflowStatus;
import java.util.Date;

public class Workflow {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.owner
     *
     * @mbggenerated
     */
    private Long owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.crontab
     *
     * @mbggenerated
     */
    private String crontab;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.status
     *
     * @mbggenerated
     */
    private WorkflowStatus status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.can_skip
     *
     * @mbggenerated
     */
    private Boolean canSkip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.last_status_dependency
     *
     * @mbggenerated
     */
    private DependingStatus lastStatusDependency;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.common
     *
     * @mbggenerated
     */
    private Boolean common;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.timeout
     *
     * @mbggenerated
     */
    private Integer timeout;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.description
     *
     * @mbggenerated
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.last_schedule_time
     *
     * @mbggenerated
     */
    private Date lastScheduleTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column workflow.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.id
     *
     * @return the value of workflow.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.id
     *
     * @param id the value for workflow.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.name
     *
     * @return the value of workflow.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.name
     *
     * @param name the value for workflow.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.owner
     *
     * @return the value of workflow.owner
     *
     * @mbggenerated
     */
    public Long getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.owner
     *
     * @param owner the value for workflow.owner
     *
     * @mbggenerated
     */
    public void setOwner(Long owner) {
        this.owner = owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.crontab
     *
     * @return the value of workflow.crontab
     *
     * @mbggenerated
     */
    public String getCrontab() {
        return crontab;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.crontab
     *
     * @param crontab the value for workflow.crontab
     *
     * @mbggenerated
     */
    public void setCrontab(String crontab) {
        this.crontab = crontab == null ? null : crontab.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.status
     *
     * @return the value of workflow.status
     *
     * @mbggenerated
     */
    public WorkflowStatus getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.status
     *
     * @param status the value for workflow.status
     *
     * @mbggenerated
     */
    public void setStatus(WorkflowStatus status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.can_skip
     *
     * @return the value of workflow.can_skip
     *
     * @mbggenerated
     */
    public Boolean getCanSkip() {
        return canSkip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.can_skip
     *
     * @param canSkip the value for workflow.can_skip
     *
     * @mbggenerated
     */
    public void setCanSkip(Boolean canSkip) {
        this.canSkip = canSkip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.last_status_dependency
     *
     * @return the value of workflow.last_status_dependency
     *
     * @mbggenerated
     */
    public DependingStatus getLastStatusDependency() {
        return lastStatusDependency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.last_status_dependency
     *
     * @param lastStatusDependency the value for workflow.last_status_dependency
     *
     * @mbggenerated
     */
    public void setLastStatusDependency(DependingStatus lastStatusDependency) {
        this.lastStatusDependency = lastStatusDependency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.common
     *
     * @return the value of workflow.common
     *
     * @mbggenerated
     */
    public Boolean getCommon() {
        return common;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.common
     *
     * @param common the value for workflow.common
     *
     * @mbggenerated
     */
    public void setCommon(Boolean common) {
        this.common = common;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.timeout
     *
     * @return the value of workflow.timeout
     *
     * @mbggenerated
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.timeout
     *
     * @param timeout the value for workflow.timeout
     *
     * @mbggenerated
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.description
     *
     * @return the value of workflow.description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.description
     *
     * @param description the value for workflow.description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.last_schedule_time
     *
     * @return the value of workflow.last_schedule_time
     *
     * @mbggenerated
     */
    public Date getLastScheduleTime() {
        return lastScheduleTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.last_schedule_time
     *
     * @param lastScheduleTime the value for workflow.last_schedule_time
     *
     * @mbggenerated
     */
    public void setLastScheduleTime(Date lastScheduleTime) {
        this.lastScheduleTime = lastScheduleTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.create_time
     *
     * @return the value of workflow.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.create_time
     *
     * @param createTime the value for workflow.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column workflow.update_time
     *
     * @return the value of workflow.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column workflow.update_time
     *
     * @param updateTime the value for workflow.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", owner=").append(owner);
        sb.append(", crontab=").append(crontab);
        sb.append(", status=").append(status);
        sb.append(", canSkip=").append(canSkip);
        sb.append(", lastStatusDependency=").append(lastStatusDependency);
        sb.append(", common=").append(common);
        sb.append(", timeout=").append(timeout);
        sb.append(", description=").append(description);
        sb.append(", lastScheduleTime=").append(lastScheduleTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Workflow other = (Workflow) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getOwner() == null ? other.getOwner() == null : this.getOwner().equals(other.getOwner()))
            && (this.getCrontab() == null ? other.getCrontab() == null : this.getCrontab().equals(other.getCrontab()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCanSkip() == null ? other.getCanSkip() == null : this.getCanSkip().equals(other.getCanSkip()))
            && (this.getLastStatusDependency() == null ? other.getLastStatusDependency() == null : this.getLastStatusDependency().equals(other.getLastStatusDependency()))
            && (this.getCommon() == null ? other.getCommon() == null : this.getCommon().equals(other.getCommon()))
            && (this.getTimeout() == null ? other.getTimeout() == null : this.getTimeout().equals(other.getTimeout()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getLastScheduleTime() == null ? other.getLastScheduleTime() == null : this.getLastScheduleTime().equals(other.getLastScheduleTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflow
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getOwner() == null) ? 0 : getOwner().hashCode());
        result = prime * result + ((getCrontab() == null) ? 0 : getCrontab().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCanSkip() == null) ? 0 : getCanSkip().hashCode());
        result = prime * result + ((getLastStatusDependency() == null) ? 0 : getLastStatusDependency().hashCode());
        result = prime * result + ((getCommon() == null) ? 0 : getCommon().hashCode());
        result = prime * result + ((getTimeout() == null) ? 0 : getTimeout().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getLastScheduleTime() == null) ? 0 : getLastScheduleTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}