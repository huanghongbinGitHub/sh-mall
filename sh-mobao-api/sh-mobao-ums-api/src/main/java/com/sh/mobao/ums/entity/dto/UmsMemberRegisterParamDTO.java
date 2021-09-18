package com.sh.mobao.ums.entity.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class UmsMemberRegisterParamDTO {

    @NotEmpty(message = "用户名不为空")
    private String username;


    private String password;

    /**
     * 头像
     */
    private String icon;

    /**
     * 邮箱
     */
    @Email
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 个人说明
     */
    private String note;


}
