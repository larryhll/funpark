package com.myeden.entity;

import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by felhan on 10/30/2016.
 */

@Entity
@Table(name = "LOG")
public class DownLogDO {


    @Id
    @Column(name = "LOG_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "LOG_MOBILE")
    private String logMobile;

    @Column(name = "LOG_DOWNLOAD_DATE")
    private Calendar logDownloadDate;

    @Column(name = "LOG_PRODUCT_NAME")
    private String logProductName;

    @Column(name = "LOG_DELETED")
    private int logDeleted;

    @Column(name = "LOG_MEMBER_DELETE_STATUS")
    private int logMemberDeleteStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogMobile() {
        return logMobile;
    }

    public void setLogMobile(String logMobile) {
        this.logMobile = logMobile;
    }

    public Calendar getLogDownloadDate() {
        return logDownloadDate;
    }

    public void setLogDownloadDate(Calendar logDownloadDate) {
        this.logDownloadDate = logDownloadDate;
    }

    public String getLogProductName() {
        return logProductName;
    }

    public void setLogProductName(String logProductName) {
        this.logProductName = logProductName;
    }

    public int getLogDeleted() {
        return logDeleted;
    }

    public void setLogDeleted(int logDeleted) {
        this.logDeleted = logDeleted;
    }

    public int getLogMemberDeleteStatus() {
        return logMemberDeleteStatus;
    }

    public void setLogMemberDeleteStatus(int logMemberDeleteStatus) {
        this.logMemberDeleteStatus = logMemberDeleteStatus;
    }
}
