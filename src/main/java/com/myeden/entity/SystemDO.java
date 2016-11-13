package com.myeden.entity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by felhan on 10/30/2016.
 */

@Entity
@Table(name = "SYSTEM")
public class SystemDO {

    @Id
    @Column(name = "SYSTEM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "SYSTEM_CURRENT_VERSION")
    private String systemCurrentVersion;

    @Column(name = "SYSTEM_LATEST_VERSION")
    private String systemLatestVersion;

    @Column(name = "SYSTEM_APK_DOWN_URL")
    private String systemApkDownUrl;

    @Column(name = "SYSTEM_TYPE_NAME")
    private String systemTypeName;

    @Column(name = "SYSTEM_TYPE_VALUE")
    private String systemTypeValue;

    @Column(name = "SYSTEM_CREATE_DATE")
    private Calendar systemCreateDate;

    @Column(name = "SYSTEM_DELETED")
    private int systemDeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSystemCurrentVersion() {
        return systemCurrentVersion;
    }

    public void setSystemCurrentVersion(String systemCurrentVersion) {
        this.systemCurrentVersion = systemCurrentVersion;
    }

    public String getSystemLatestVersion() {
        return systemLatestVersion;
    }

    public void setSystemLatestVersion(String systemLatestVersion) {
        this.systemLatestVersion = systemLatestVersion;
    }

    public String getSystemApkDownUrl() {
        return systemApkDownUrl;
    }

    public void setSystemApkDownUrl(String systemApkDownUrl) {
        this.systemApkDownUrl = systemApkDownUrl;
    }

    public String getSystemTypeName() {
        return systemTypeName;
    }

    public void setSystemTypeName(String systemTypeName) {
        this.systemTypeName = systemTypeName;
    }

    public String getSystemTypeValue() {
        return systemTypeValue;
    }

    public void setSystemTypeValue(String systemTypeValue) {
        this.systemTypeValue = systemTypeValue;
    }

    public Calendar getSystemCreateDate() {
        return systemCreateDate;
    }

    public void setSystemCreateDate(Calendar systemCreateDate) {
        this.systemCreateDate = systemCreateDate;
    }

    public int getSystemDeleted() {
        return systemDeleted;
    }

    public void setSystemDeleted(int systemDeleted) {
        this.systemDeleted = systemDeleted;
    }
}
