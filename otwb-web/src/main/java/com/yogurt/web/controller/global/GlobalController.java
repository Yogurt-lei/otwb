package com.yogurt.web.controller.global;

import com.yogurt.model.vo.ResponseMessage;
import com.yogurt.model.vo.ResultCode;
import com.yogurt.model.vo.global.VersionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GlobalController
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-16 21:43
 */
@Api(tags = "全局信息接口")
@RestController("/global")
public class GlobalController {

    @Value("${system.version}")
    private String version;

    @ApiOperation(value = "获取版本信息.", response = ResponseMessage.class)
    @GetMapping("/version")
    public ResponseMessage getVersion() {
        return new ResponseMessage(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), new VersionVO(version));
    }
}
