package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "tb_stu_day")
public class Sd implements Serializable {
    @Id
    @Column(name = "sd_id")
    private Integer sdId;

    private String sdname;

    private String sdex;

    private String sdhobby;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date sdbirthday;

    private Integer mid;
    
    @Transient
    private Md md;

    public Md getMd() {
		return md;
	}

	public void setMd(Md md) {
		this.md = md;
	}

	private static final long serialVersionUID = 1L;

    /**
     * @return sd_id
     */
    public Integer getSdId() {
        return sdId;
    }

    /**
     * @param sdId
     */
    public void setSdId(Integer sdId) {
        this.sdId = sdId;
    }

    /**
     * @return sdname
     */
    public String getSdname() {
        return sdname;
    }

    /**
     * @param sdname
     */
    public void setSdname(String sdname) {
        this.sdname = sdname == null ? null : sdname.trim();
    }

    /**
     * @return sdex
     */
    public String getSdex() {
        return sdex;
    }

    /**
     * @param sdex
     */
    public void setSdex(String sdex) {
        this.sdex = sdex == null ? null : sdex.trim();
    }

    /**
     * @return sdhobby
     */
    public String getSdhobby() {
        return sdhobby;
    }

    /**
     * @param sdhobby
     */
    public void setSdhobby(String sdhobby) {
        this.sdhobby = sdhobby == null ? null : sdhobby.trim();
    }

    /**
     * @return sdbirthday
     */
    public Date getSdbirthday() {
        return sdbirthday;
    }

    /**
     * @param sdbirthday
     */
    public void setSdbirthday(Date sdbirthday) {
        this.sdbirthday = sdbirthday;
    }

    /**
     * @return mid
     */
    public Integer getMid() {
        return mid;
    }

    /**
     * @param mid
     */
    public void setMid(Integer mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sdId=").append(sdId);
        sb.append(", sdname=").append(sdname);
        sb.append(", sdex=").append(sdex);
        sb.append(", sdhobby=").append(sdhobby);
        sb.append(", sdbirthday=").append(sdbirthday);
        sb.append(", mid=").append(mid);
        sb.append("]");
        return sb.toString();
    }
}