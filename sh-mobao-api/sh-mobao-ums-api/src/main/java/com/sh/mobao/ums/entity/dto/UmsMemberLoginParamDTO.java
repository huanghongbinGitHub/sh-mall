package com.sh.mobao.ums.entity.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Size;

@Data
@ToString
public class UmsMemberLoginParamDTO {

    @Size(min = 1,max = 8,message = "用户名长度在1~8之间")
    private String username;

    private String password;

}
