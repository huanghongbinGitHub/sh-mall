package com.sh.mobaoums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.mobao.base.result.ResultWrapper;
import com.sh.mobao.common.util.JwtUtil;
import com.sh.mobao.ums.entity.UmsMember;
import com.sh.mobao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.sh.mobao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.sh.mobao.ums.service.UmsMemberService;
import com.sh.mobaoums.mapper.UmsMemberMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author 黄洪斌
 * @since 2021-09-05
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {

    @Autowired
    UmsMemberMapper umsMemberMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResultWrapper register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO){
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberRegisterParamDTO,umsMember);
        String encode = passwordEncoder.encode(umsMemberRegisterParamDTO.getPassword());
        umsMember.setPassword(encode);
        umsMemberMapper.insert(umsMember);
        return ResultWrapper.getSuccessBuilder().data(umsMember).msg("注册成功").build();
    }

    @Override
    public ResultWrapper login(UmsMemberLoginParamDTO umsMemberLoginParamDTO) {

        UmsMember umsMember = umsMemberMapper.selectByName(umsMemberLoginParamDTO.getUsername());
        String msg = null;
        if (null != umsMember){
            String passwordBb = umsMember.getPassword();
            if (!passwordEncoder.matches(umsMemberLoginParamDTO.getPassword(),passwordBb)){
                return  ResultWrapper.getFailureBuilder().data(umsMember).msg("密码不正确").build();
            }
        }else {
            return  ResultWrapper.getFailureBuilder().data(umsMember).msg("用户不存在").build();
        }
        String token = JwtUtil.createToken(umsMember.getUsername());
        return  ResultWrapper.getSuccessBuilder().data(token).msg("登录成功").build();
    }

    public ResultWrapper updateUser(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO){

            UmsMember member = new UmsMember();
            BeanUtils.copyProperties(umsMemberRegisterParamDTO,member);
            umsMemberMapper.updateUser(member);
            UmsMember umsMember = umsMemberMapper.selectByName(member.getUsername());
        return ResultWrapper.getSuccessBuilder().data(umsMember).msg("更新成功").build();
    }

    /**
     * 查询用户总数
     * @return
     */
    public int findlTotal(){
        int total = umsMemberMapper.findTotal();
        return total;
    }
}
