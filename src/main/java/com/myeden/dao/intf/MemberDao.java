package com.myeden.dao.intf;

import com.myeden.entity.MemberDO;

import java.util.List;

/**
 * Created by felhan on 11/12/2016.
 */
public interface MemberDao {

    public void save(MemberDO memberDO);

    public void update(MemberDO memberDO);

    public MemberDO findMemberByMobile(String mobile, String pwd);

    public List<MemberDO> getAllMembs();


}
