package com.zch.platform.controller;

import com.zch.platform.dto.LoginDTO;
import com.zch.platform.dto.ModifyPwdDTO;
import com.zch.platform.dto.RegisterDTO;
import com.zch.platform.result.ResultUtils;
import com.zch.platform.service.IUserService;
import com.zch.platform.vo.LoginVO;
import com.zch.platform.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "用户登录和注册")
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final IUserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public Result register(@Valid @RequestBody LoginDTO dto) {
        LoginVO vo = userService.login(dto);
        return ResultUtils.success(vo);
    }


    @PutMapping("/refreshToken")
    @ApiOperation(value = "刷新token", notes = "用refreshtoken换取新的token")
    public Result refreshToken(@RequestHeader("refreshToken") String refreshToken) {
        LoginVO vo = userService.refreshToken(refreshToken);
        return ResultUtils.success(vo);
    }


    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public Result register(@Valid @RequestBody RegisterDTO dto) {
        userService.register(dto);
        return ResultUtils.success();
    }

    @PutMapping("/modifyPwd")
    @ApiOperation(value = "修改密码", notes = "修改用户密码")
    public Result update(@Valid @RequestBody ModifyPwdDTO dto) {
        userService.modifyPassword(dto);
        return ResultUtils.success();
    }

}
