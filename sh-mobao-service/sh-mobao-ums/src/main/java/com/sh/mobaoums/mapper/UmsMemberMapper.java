package com.sh.mobaoums.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sh.mobao.ums.entity.UmsMember;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author 黄洪斌
 * @since 2021-09-05
 */

@Repository
@Mapper
public interface UmsMemberMapper extends BaseMapper<UmsMember> {

    public UmsMember selectByName(String name);

    public int updateUser(UmsMember member);

    int findTotal();

}
