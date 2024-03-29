package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "jiyun_bank")
public class Bank implements Serializable {
    @Id
    @Column(name = "b_id")
    private Integer bId;

    @Column(name = "b_name")
    private String bName;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date cratetime;

    private static final long serialVersionUID = 1L;

    /**
     * @return b_id
     */
    public Integer getbId() {
        return bId;
    }

    /**
     * @param bId
     */
    public void setbId(Integer bId) {
        this.bId = bId;
    }

    /**
     * @return b_name
     */
    public String getbName() {
        return bName;
    }

    /**
     * @param bName
     */
    public void setbName(String bName) {
        this.bName = bName == null ? null : bName.trim();
    }

    /**
     * @return cratetime
     */
    public Date getCratetime() {
        return cratetime;
    }

    /**
     * @param cratetime
     */
    public void setCratetime(Date cratetime) {
        this.cratetime = cratetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bId=").append(bId);
        sb.append(", bName=").append(bName);
        sb.append(", cratetime=").append(cratetime);
        sb.append("]");
        return sb.toString();
    }
}