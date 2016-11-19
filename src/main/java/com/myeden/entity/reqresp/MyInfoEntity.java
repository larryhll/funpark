package com.myeden.entity.reqresp;

import com.myeden.entity.MemberDO;
import com.myeden.entity.SystemDO;

/**
 * Created by felhan on 11/18/2016.
 */
public class MyInfoEntity {

    private MemberDO memberDO;
    private SystemDO systemDO;


    public MemberDO getMemberDO() {
        return memberDO;
    }

    public void setMemberDO(MemberDO memberDO) {
        this.memberDO = memberDO;
    }

    public SystemDO getSystemDO() {
        return systemDO;
    }

    public void setSystemDO(SystemDO systemDO) {
        this.systemDO = systemDO;
    }
}
