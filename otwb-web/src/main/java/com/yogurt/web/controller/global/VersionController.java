package com.yogurt.web.controller.global;

import com.github.pagehelper.PageInfo;
import com.yogurt.model.vo.global.VersionVO;
import com.yogurt.service.global.VersionService;
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
 * VersionController
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-16 21:43
 */
@Api(tags = "版本信息")
@RestController
public class VersionController extends BaseController {
    @Autowired
    private VersionService versionService;

    @ApiOperation(value = "添加版本.", response = ResponseMessage.class)
    @PostMapping(value = "/global/version",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseMessage addVersion(@RequestBody VersionVO versionVO) {
        if (versionService.addEntity(versionVO)) {
            return new ResponseMessage(ResultCode.SUCCESS, true);
        } else {
            return new ResponseMessage(ResultCode.FAILED, false);
        }
    }

    @ApiOperation(value = "根据Id更新版本.", response = ResponseMessage.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "string", required = true, paramType = "path")
    })
    @ResponseBody
    @PutMapping("/global/version/{id}")
    public ResponseMessage updateVersion(@PathVariable String id, @RequestBody VersionVO versionVO) {
        versionVO.setId(id);
        if (versionService.updateEntity(versionVO)) {
            return new ResponseMessage(ResultCode.SUCCESS, true);
        } else {
            return new ResponseMessage(ResultCode.FAILED, false);
        }
    }

    @ApiOperation(value = "根据ID查找版本.", response = ResponseMessage.class)
    @ApiImplicitParam(name = "id", value = "ID", dataType = "string", required = true, paramType = "path")
    @ResponseBody
    @GetMapping("/global/version/{id}")
    public ResponseMessage findVersionById(@PathVariable String id) {
        VersionVO versionVO = versionService.findEntityById(id);
        return new ResponseMessage(ResultCode.SUCCESS, versionVO);
    }

    @ApiOperation(value = "分页查找版本.", response = ResponseMessage.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", dataType = "int", paramType = "form")
    })
    @ResponseBody
    @GetMapping("/global/versionList")
    public ResponseMessage findVersionListByPage(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                              @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageInfo<VersionVO> pageInfo = versionService.findEntityListByPage(pageNum, pageSize);
        return new ResponseMessage(ResultCode.SUCCESS, pageInfo);
    }
}

