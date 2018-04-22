package com.yogurt.web.controller.base;

import com.yogurt.web.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author <a href="yogurt_lei@foxmail.com">Yogurt_lei</a>
 * @version v1.0 , 2018-04-21 15:08
 */
@Api(tags = "省份信息")
@RestController("/base/province")
public class ProvinceController extends BaseController {
    // @Autowired
    // private UserService userService;
    //
    // @ApiOperation(value = "添加用户.", response = Boolean.class)
    // @ResponseBody
    // @PostMapping("/add")
    // public boolean addUser(@RequestParam UserVO userVO){
    //     return userService.addUser(userVO.toEntity());
    // }
    //
    // @ResponseBody
    // @GetMapping("/all")
    // public Object findAllUser(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
    //                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
    //     PageHelper.startPage(pageNum, pageSize);
    //     return userService.findAllUser(pageNum,pageSize);
    // }
}
