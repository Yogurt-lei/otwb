package com.yogurt.web.controller.base;

import com.github.pagehelper.PageInfo;
import com.yogurt.model.vo.base.ProvinceVO;
import com.yogurt.service.base.ProvinceService;
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
@Api(tags = "省份信息")
@RestController
public class ProvinceController extends BaseController {
    @Autowired
    private ProvinceService provinceService;

    @ApiOperation(value = "添加省份.", response = ResponseMessage.class)
    @PostMapping(value = "/base/province",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseMessage addUser(@RequestBody ProvinceVO provinceVO) {
        if (provinceService.addEntity(provinceVO)) {
            return new ResponseMessage(ResultCode.SUCCESS, true);
        } else {
            return new ResponseMessage(ResultCode.FAILED, false);
        }
    }

    @ApiOperation(value = "根据ID删除省份.", response = ResponseMessage.class)
    @ApiImplicitParam(name = "id", value = "ID", dataType = "string", required = true, paramType = "path")
    @ResponseBody
    @DeleteMapping("/base/province/{id}")
    public ResponseMessage deleteUserById(@PathVariable String id) {
        if (provinceService.deleteEntityById(id)) {
            return new ResponseMessage(ResultCode.SUCCESS, true);
        } else {
            return new ResponseMessage(ResultCode.FAILED, false);
        }
    }

    @ApiOperation(value = "更新省份.", response = ResponseMessage.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "string", required = true, paramType = "path")
    })
    @ResponseBody
    @PutMapping("/base/province/{id}")
    public ResponseMessage updateUser(@PathVariable String id, @RequestBody ProvinceVO provinceVO) {
        if (provinceService.updateEntity(provinceVO)) {
            return new ResponseMessage(ResultCode.SUCCESS, true);
        } else {
            return new ResponseMessage(ResultCode.FAILED, false);
        }
    }

    @ApiOperation(value = "根据ID查找省份.", response = ResponseMessage.class)
    @ApiImplicitParam(name = "id", value = "ID", dataType = "string", required = true, paramType = "path")
    @ResponseBody
    @GetMapping("/base/province/{id}")
    public ResponseMessage findUserById(@PathVariable String id) {
        ProvinceVO provinceVO = provinceService.findEntityById(id);
        return new ResponseMessage(ResultCode.SUCCESS, provinceVO);
    }

    @ApiOperation(value = "分页查找省份.", response = ResponseMessage.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", dataType = "int", paramType = "form")
    })
    @ResponseBody
    @GetMapping("/base/provinceList")
    public ResponseMessage findUserListByPage(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                              @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageInfo<ProvinceVO> pageInfo = provinceService.findEntityListByPage(pageNum, pageSize);
        return new ResponseMessage(ResultCode.SUCCESS, pageInfo);
    }
}
