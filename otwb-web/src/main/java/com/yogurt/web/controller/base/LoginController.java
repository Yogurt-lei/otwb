package com.yogurt.web.controller.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yogurt.utils.common.image.CaptchaUtils;
import com.yogurt.web.constant.SessionConstants;
import com.yogurt.web.controller.BaseController;
import com.yogurt.web.response.ResponseMessage;
import com.yogurt.web.response.ResultCode;
import io.swagger.annotations.Api;
import org.patchca.service.Captcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LoginController
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-05-01 17:48
 */
@RestController
@Api("登录接口")
public class LoginController extends BaseController {
    /**
     * 获取验证码
     */
    @GetMapping("login/captcha")
    public void getImageCode(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        Captcha captcha = CaptchaUtils.generateCaptcha();

        try {
            request.getSession().setAttribute(SessionConstants.CAPTCHA, captcha.getChallenge());
            ImageIO.write(captcha.getImage(), "png", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     */
    @PostMapping("login/doLogin")
    public ResponseMessage doLogin(@RequestParam String account,
                                   @RequestParam String password,
                                   @RequestParam String captcha,
                                   @RequestParam String lang) {
        System.out.println(account + "->" + password + "->" + captcha + "->" + lang);

        JSONObject user = JSON.parseObject("{'uid': '1212'," +
                "'nick': 'Yogurt_lei'," +
                "'portrait':'https://pic1.zhimg.com/v2-1a87e496c35006eec6240c91d07229f6_r.jpg'}");

        return new ResponseMessage(ResultCode.SUCCESS, user);
    }
}
