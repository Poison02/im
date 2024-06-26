package com.zch.platform.controller;

import com.zch.platform.entity.User;
import com.zch.platform.result.ResultUtils;
import com.zch.platform.service.IUserService;
import com.zch.platform.session.SessionContext;
import com.zch.platform.session.UserSession;
import com.zch.platform.util.BeanUtils;
import com.zch.platform.vo.OnlineTerminalVO;
import com.zch.platform.vo.UserVO;
import com.zch.platform.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Api(tags = "用户")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/terminal/online")
    @ApiOperation(value = "判断用户哪个终端在线", notes = "返回在线的用户id的终端集合")
    public Result<List<OnlineTerminalVO>> getOnlineTerminal(@NotEmpty @RequestParam("userIds") String userIds) {
        return ResultUtils.success(userService.getOnlineTerminals(userIds));
    }


    @GetMapping("/self")
    @ApiOperation(value = "获取当前用户信息", notes = "获取当前用户信息")
    public Result<UserVO> findSelfInfo() {
        UserSession session = SessionContext.getSession();
        User user = userService.getById(session.getUserId());
        UserVO userVO = BeanUtils.copyProperties(user, UserVO.class);
        return ResultUtils.success(userVO);
    }


    @GetMapping("/find/{id}")
    @ApiOperation(value = "查找用户", notes = "根据id查找用户")
    public Result<UserVO> findById(@NotEmpty @PathVariable("id") Long id) {
        return ResultUtils.success(userService.findUserById(id));
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息，仅允许修改登录用户信息")
    public Result update(@Valid @RequestBody UserVO vo) {
        userService.update(vo);
        return ResultUtils.success();
    }

    @GetMapping("/findByName")
    @ApiOperation(value = "查找用户", notes = "根据用户名或昵称查找用户")
    public Result<List<UserVO>> findByName(@RequestParam("name") String name) {
        return ResultUtils.success(userService.findUserByName(name));
    }
}

