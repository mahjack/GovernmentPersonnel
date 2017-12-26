package com.sdzx.government.governmentpersonnel.bean;

/**
 * Created by Administrator on 2017/11/2 0002.
 */

public class Group_info {
    Integer id;
    Integer pid;
    Integer sort;
    String name;
    Integer type;
    Integer number;
    Integer job_7;
    Integer job_8;
    Integer job_9;
    Integer job_10;
    Integer job_15;
    Integer job_16;
    Integer job_17;
    Integer job_2x;
    Integer job_4x;
    Integer adduser;
    Integer addtime;
    String addip;
    Integer status;

    public Group_info() {
    }

    public Group_info(Integer id, Integer pid, Integer sort, String name) {
        this.id = id;
        this.pid = pid;
        this.sort = sort;
        this.name = name;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getJob_7() {
        return job_7;
    }

    public void setJob_7(Integer job_7) {
        this.job_7 = job_7;
    }

    public Integer getJob_8() {
        return job_8;
    }

    public void setJob_8(Integer job_8) {
        this.job_8 = job_8;
    }

    public Integer getJob_9() {
        return job_9;
    }

    public void setJob_9(Integer job_9) {
        this.job_9 = job_9;
    }

    public Integer getJob_10() {
        return job_10;
    }

    public void setJob_10(Integer job_10) {
        this.job_10 = job_10;
    }

    public Integer getJob_15() {
        return job_15;
    }

    public void setJob_15(Integer job_15) {
        this.job_15 = job_15;
    }

    public Integer getJob_16() {
        return job_16;
    }

    public void setJob_16(Integer job_16) {
        this.job_16 = job_16;
    }

    public Integer getJob_17() {
        return job_17;
    }

    public void setJob_17(Integer job_17) {
        this.job_17 = job_17;
    }

    public Integer getJob_2x() {
        return job_2x;
    }

    public void setJob_2x(Integer job_2x) {
        this.job_2x = job_2x;
    }

    public Integer getJob_4x() {
        return job_4x;
    }

    public void setJob_4x(Integer job_4x) {
        this.job_4x = job_4x;
    }

    public Integer getAdduser() {
        return adduser;
    }

    public void setAdduser(Integer adduser) {
        this.adduser = adduser;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public String getAddip() {
        return addip;
    }

    public void setAddip(String addip) {
        this.addip = addip;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
