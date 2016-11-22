package com.myeden.entity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by felhan on 10/30/2016.
 */


@Entity
@Table(name = "MEMBER")
public class MemberDO {

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "MEMBER_MOBILE")
    private String memberMobile;

    @Column(name = "MEMBER_PWD")
    private String pwd;

    @Column(name = "MEMBER_BIRTHDAY")
    private Calendar memberBirthday;

    @Column(name = "MEMBER_BABY_GENDER")
    private int memberBabyGender;

    @Column(name = "MEMBER_DELETED")
    private int memberDeleted;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @Column(name = "VERICODE")
    private String vericode;

    @Column(name = "ROLE")
    private String role;

    public int getMemberAgeScope() {
        return memberAgeScope;
    }

    public void setMemberAgeScope(int memberAgeScope) {
        this.memberAgeScope = memberAgeScope;
    }

    @Column(name="MEMBER_AGE_SCOPE")
    private int memberAgeScope;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Calendar getMemberBirthday() {
        return memberBirthday;
    }

    public void setMemberBirthday(Calendar memberBirthday) {
        this.memberBirthday = memberBirthday;
    }

    public int getMemberBabyGender() {
        return memberBabyGender;
    }

    public void setMemberBabyGender(int memberBabyGender) {
        this.memberBabyGender = memberBabyGender;
    }

    public int getMemberDeleted() {
        return memberDeleted;
    }

    public void setMemberDeleted(int memberDeleted) {
        this.memberDeleted = memberDeleted;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getVericode() {
        return vericode;
    }

    public void setVericode(String vericode) {
        this.vericode = vericode;
    }
}

