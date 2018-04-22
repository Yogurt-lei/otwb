package com.yogurt.web.controller.base;

import com.yogurt.model.vo.ResponseMessage;
import com.yogurt.model.vo.ResultCode;
import com.yogurt.model.vo.base.UserVO;
import com.yogurt.service.base.UserService;
import com.yogurt.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-21 15:08
 */
@Api(tags = "用户信息")
@RestController("/base/")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户.", response = ResponseMessage.class)
    @PostMapping(value = "/user",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseMessage addUser(@RequestBody UserVO userVO) {
        if (userService.addUser(userVO)) {
            return new ResponseMessage(ResultCode.SUCCESS, true);
        } else {
            return new ResponseMessage(ResultCode.FAILED, false);
        }
    }

    @ApiOperation(value = "删除用户.", response = ResponseMessage.class)
    @ApiImplicitParam(name = "id", value = "ID", dataType = "string", required = true, paramType = "path")
    @ResponseBody
    @DeleteMapping("/user/{id}")
    public ResponseMessage deleteUserById(@PathVariable String id) {
        if (userService.deleteUserById(id)) {
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
    @PutMapping("/user/{id}")
    public ResponseMessage updateUser(@RequestParam UserVO userVO, @PathVariable String id) {
        if (userService.updateUser(userVO)) {
            return new ResponseMessage(ResultCode.SUCCESS, true);
        } else {
            return new ResponseMessage(ResultCode.FAILED, false);
        }
    }

    @ApiOperation(value = "查找用户.", response = ResponseMessage.class)
    @ApiImplicitParam(name = "id", value = "ID", dataType = "string", required = true, paramType = "path")
    @ResponseBody
    @GetMapping("/user/{id}")
    public ResponseMessage findUserById(@PathVariable String id) {
        UserVO userVO = userService.findUserById(id);
        return new ResponseMessage(ResultCode.SUCCESS, userVO);
    }

    @ApiOperation(value = "分页查找用户.", response = ResponseMessage.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", dataType = "int", paramType = "form")
    })
    @ResponseBody
    @GetMapping("/user")
    public ResponseMessage findUserListByPage(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                              @RequestParam(required = false, defaultValue = "10") int pageSize) {
        List<UserVO> userList = userService.findUserListByPage(pageNum, pageSize);
        return new ResponseMessage(ResultCode.SUCCESS, userList);
    }
}
