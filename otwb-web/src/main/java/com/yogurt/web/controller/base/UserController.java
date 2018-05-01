package com.yogurt.web.controller.base;

import com.github.pagehelper.PageInfo;
import com.yogurt.model.vo.base.UserVO;
import com.yogurt.service.base.UserService;
import com.yogurt.web.controller.BaseController;
import com.yogurt.web.response.ResponseMessage;
import com.yogurt.web.response.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * UserController
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-21 15:08
 */
@Api(tags = "用户信息")
@RestController
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户.", response = ResponseMessage.class)
    @PostMapping(value = "/base/user/",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseMessage addUser(@RequestBody UserVO userVO) {
        if (userService.addEntity(userVO)) {
            return new ResponseMessage(ResultCode.SUCCESS, true);
        } else {
            return new ResponseMessage(ResultCode.FAILED, false);
        }
    }

    @ApiOperation(value = "根据ID删除用户.", response = ResponseMessage.class)
    @ApiImplicitParam(name = "id", value = "ID", dataType = "string", required = true, paramType = "path")
    @ResponseBody
    @DeleteMapping("/base/user/{id}")
    public ResponseMessage deleteUserById(@PathVariable String id) {
        if (userService.deleteEntityById(id)) {
            return new ResponseMessage(ResultCode.SUCCESS, true);
        } else {
            return new ResponseMessage(ResultCode.FAILED, false);
        }
    }

    @ApiOperation(value = "更新用户.", response = ResponseMessage.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "string", required = true, paramType = "path")
    })
    @ResponseBody
    @PutMapping("/base/user/{id}")
    public ResponseMessage updateUser(@PathVariable String id, @RequestBody UserVO userVO) {
        if (userService.updateEntity(userVO)) {
            return new ResponseMessage(ResultCode.SUCCESS, true);
        } else {
            return new ResponseMessage(ResultCode.FAILED, false);
        }
    }

    @ApiOperation(value = "根据ID查找用户.", response = ResponseMessage.class)
    @ApiImplicitParam(name = "id", value = "ID", dataType = "string", required = true, paramType = "path")
    @ResponseBody
    @GetMapping("/base/user/{id}")
    public ResponseMessage findUserById(@PathVariable String id) {
        UserVO userVO = userService.findEntityById(id);
        return new ResponseMessage(ResultCode.SUCCESS, userVO);
    }

    @ApiOperation(value = "分页查找用户.", response = ResponseMessage.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", dataType = "int", paramType = "form")
    })
    @ResponseBody
    @GetMapping("/base/userList")
    public ResponseMessage findUserListByPage(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                              @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageInfo<UserVO> pageInfo = userService.findEntityListByPage(pageNum, pageSize);
        return new ResponseMessage(ResultCode.SUCCESS, pageInfo);
    }

    @GetMapping("/userLogin")
    public ResponseMessage userLogin(@RequestParam String account,
                            @RequestParam String password) {
        System.out.println(account + "->" + password);
        return new ResponseMessage(ResultCode.SUCCESS, "ok");
    }

    @GetMapping("/userInfo")
    public String userInfo() {
        return "userInfo";
    }
}
