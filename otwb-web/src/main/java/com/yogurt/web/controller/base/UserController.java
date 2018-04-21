package com.yogurt.web.controller.base;

import com.github.pagehelper.PageHelper;
import com.yogurt.model.vo.base.UserVO;
import com.yogurt.service.base.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * UserController
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-21 15:08
 */
@Api(tags = "用户信息", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController("/base/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(UserVO userVO){
        return userService.addUser(userVO.toEntity());
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return userService.findAllUser(pageNum,pageSize);
    }
}
