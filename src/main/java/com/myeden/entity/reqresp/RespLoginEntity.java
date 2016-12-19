package com.myeden.entity.reqresp;

import com.myeden.entity.CategoryDO;
import com.myeden.entity.LayoutDO;
import com.myeden.entity.MemberDO;

import java.util.List;

/**
 * Created by felhan on 11/13/2016.
 */
public class RespLoginEntity {

    private MemberDO memberDO;

    private List<LayoutDOApp> layoutDOs;

    private List<CategoryDO> categoryDOs;

    public List<CategoryDO> getCategoryDOs() {
        return categoryDOs;
    }

    public void setCategoryDOs(List<CategoryDO> categoryDOs) {
        this.categoryDOs = categoryDOs;
    }

    public MemberDO getMemberDO() {
        return memberDO;
    }

    public void setMemberDO(MemberDO memberDO) {
        this.memberDO = memberDO;
    }

    public List<LayoutDOApp> getLayoutDOs() {
        return layoutDOs;
    }

    public void setLayoutDOs(List<LayoutDOApp> layoutDOs) {
        this.layoutDOs = layoutDOs;
    }
}
