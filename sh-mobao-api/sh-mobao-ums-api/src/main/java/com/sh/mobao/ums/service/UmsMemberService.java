package com.sh.mobao.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sh.mobao.base.result.ResultWrapper;
import com.sh.mobao.ums.entity.UmsMember;
import com.sh.mobao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.sh.mobao.ums.entity.dto.UmsMemberRegisterParamDTO;


/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author 黄洪斌
 * @since 2021-09-05
 */

public interface UmsMemberService extends IService<UmsMember> {

    public ResultWrapper register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO);

    public ResultWrapper login(UmsMemberLoginParamDTO umsMemberLoginParamDTO);

    public ResultWrapper updateUser(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO);

}
