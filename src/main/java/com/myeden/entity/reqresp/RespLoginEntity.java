package com.myeden.entity.reqresp;

import com.myeden.entity.LayoutDO;
import com.myeden.entity.MemberDO;

import java.util.List;

/**
 * Created by felhan on 11/13/2016.
 */
public class RespLoginEntity {

    private MemberDO memberDO;

    private List<LayoutDO> layoutDOs;

    public MemberDO getMemberDO() {
        return memberDO;
    }

    public void setMemberDO(MemberDO memberDO) {
        this.memberDO = memberDO;
    }

    public List<LayoutDO> getLayoutDOs() {
        return layoutDOs;
    }

    public void setLayoutDOs(List<LayoutDO> layoutDOs) {
        this.layoutDOs = layoutDOs;
    }
}
