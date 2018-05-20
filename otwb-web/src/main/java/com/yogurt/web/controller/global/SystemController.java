package com.yogurt.web.controller.global;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yogurt.utils.common.image.CaptchaUtils;
import com.yogurt.web.config.OtwbConfig;
import com.yogurt.web.constant.SessionConstants;
import com.yogurt.web.controller.BaseController;
import com.yogurt.web.response.ResponseMessage;
import com.yogurt.web.response.ResultCode;
import org.patchca.service.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    /**
     * 获取验证码
     */
    @GetMapping("system/captcha")
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
     * 登入
     */
    @PostMapping("system/doLogin")
    public ResponseMessage doLogin(HttpServletRequest request,
                                   @RequestParam String account,
                                   @RequestParam String password,
                                   @RequestParam String captcha,
                                   @RequestParam String lang) {
        System.out.println(account + "->" + password + "->" + captcha + "->" + lang);

        //// TODO by Yogurt_lei on 2018-05-12 23:13 : 登陆成功数据库查到用户将id存入session
        request.getSession().setAttribute(SessionConstants.CURRENT_USER_ID, account);

        JSONObject user = JSON.parseObject("{'uid': '1212'," +
                "'nick': 'Yogurt_lei'," +
                "'portrait':'https://pic1.zhimg.com/v2-1a87e496c35006eec6240c91d07229f6_r.jpg'}");

        return new ResponseMessage(ResultCode.SUCCESS, user);
    }

    /**
     * 登出
     */
    @PostMapping("system/doLogout")
    public ResponseMessage doLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(SessionConstants.CURRENT_USER_ID);

        return new ResponseMessage(ResultCode.SUCCESS);
    }
}


