package murraco.controller;

import javax.servlet.http.HttpServletRequest;

import io.swagger.models.auth.In;
import murraco.model.LoginType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import murraco.dto.UserDataDTO;
import murraco.dto.UserResponseDTO;
import murraco.model.User;
import murraco.service.UserService;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping("/signin")
  @ApiOperation(value = "${UserController.signin}")
  @ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"), //
                        @ApiResponse(code = 422, message = "Invalid username/password supplied")})
  public String login(
      @ApiParam("login_type") @RequestParam Integer login_type, //
      @ApiParam("username") @RequestParam(required = false) String username, //
      @ApiParam("password") @RequestParam(required = false)  String password,
      @ApiParam("phone") @RequestParam(required = false)  String phone, //
      @ApiParam("code") @RequestParam(required = false)  String code,
      @ApiParam("open_account_type") @RequestParam(required = false) Integer open_account_type,
      @ApiParam("open_id") @RequestParam(required = false)  String open_id) {
      if (login_type.equals(LoginType.OPEN_ACCOUNT)){ //openid登录
        if (open_account_type==null || StringUtils.isEmpty(open_id)){
           return "error";
        }
        return userService.signinWithOpenAccount(open_account_type, open_id);
      }else if (login_type.equals(LoginType.USERNAME_PASSWD)){ //用户名密码登录
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
          return "error";
        }
        return userService.signinWithUserNameAndPassword(username, password);
      }else if(login_type.equals(LoginType.PHONE_CODE)){//手机号验证码
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(phone)){
          return "error";
        }
        return userService.signinPhoneAndCode(phone, code);
      }else{
        return "error";
      }
  }

  @PostMapping("/signup")
  @ApiOperation(value = "${UserController.signup}")
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 422, message = "Username is already in use"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public String signup(@ApiParam("Signup User") @RequestBody UserDataDTO user) {
    return userService.signup(modelMapper.map(user, User.class));
  }

  @DeleteMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "${UserController.delete}")
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The user doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public String delete(@ApiParam("Username") @PathVariable String username) {
    userService.delete(username);
    return username;
  }

  @GetMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "${UserController.search}", response = UserResponseDTO.class)
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The user doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public UserResponseDTO search(@ApiParam("Username") @PathVariable String username) {
    return modelMapper.map(userService.search(username), UserResponseDTO.class);
  }

  @GetMapping(value = "/me")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  @ApiOperation(value = "${UserController.me}", response = UserResponseDTO.class)
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public UserResponseDTO whoami(HttpServletRequest req) {
    return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
  }

  @GetMapping("/refresh")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  public String refresh(HttpServletRequest req) {
    return userService.refresh(req.getRemoteUser());
  }

}
