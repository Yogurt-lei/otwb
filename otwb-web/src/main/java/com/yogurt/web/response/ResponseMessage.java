package com.yogurt.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "响应消息")
public class ResponseMessage {

    /**
     * 返回代码
     */
    @ApiModelProperty("代码")
    private Integer code;

    /**
     * 返回信息
     */
    @ApiModelProperty("信息")
    private String msg;

    /**
     * 返回数据
     */
    @ApiModelProperty("数据")
    private Object data;

    public ResponseMessage(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public ResponseMessage(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = "";
    }
}
