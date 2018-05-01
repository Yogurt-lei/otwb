package com.yogurt.web.controller.global;

import com.yogurt.web.config.OtwbConfig;
import com.yogurt.web.controller.BaseController;
import com.yogurt.web.response.ResponseMessage;
import com.yogurt.web.response.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * SystemController
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-05-01 20:58
 */
@RestController
public class SystemController extends BaseController {

    @Autowired
    private OtwbConfig otwbConfig;

    /**
     * 获取yml中配置的otwb的参数
     */
    @GetMapping("system/config")
    public ResponseMessage config(@RequestParam String key) {
        Map<String, Object> config = otwbConfig.getConfig();

        return new ResponseMessage(ResultCode.SUCCESS, config.get(key));
    }
}


