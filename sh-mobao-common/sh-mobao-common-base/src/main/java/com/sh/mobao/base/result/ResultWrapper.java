package com.sh.mobao.base.result;

import com.sh.mobao.base.enums.StateCodeEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ResultWrapper<T> implements Serializable {

    //状态码
    private int code;

    //提示信息
    private String msg;

    //
    private T data;

    /**
     * 返回成功的包装
     * @return
     */
    public static ResultWrapper.ResultWrapperBuilder getSuccessBuilder(){
        return ResultWrapper.builder().code(StateCodeEnum.SUCCESS.getCode()).msg(StateCodeEnum.SUCCESS.getMsg());
    }

    /**
     * 返回失败的包装
     * @return
     */
    public static ResultWrapper.ResultWrapperBuilder getFailureBuilder(){
        return ResultWrapper.builder().code(StateCodeEnum.FAILURE.getCode()).msg(StateCodeEnum.FAILURE.getMsg());
    }

}
