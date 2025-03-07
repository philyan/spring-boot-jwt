package murraco.service;

import javax.servlet.http.HttpServletRequest;

import murraco.model.OpenAccount;
import murraco.model.OpenAccountType;
import murraco.model.Role;
import murraco.repository.OpenAccountRepository;
import murraco.repository.RoleRepistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import murraco.exception.CustomException;
import murraco.model.User;
import murraco.repository.UserRepository;
import murraco.security.JwtTokenProvider;

import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private RoleRepistory roleRepistory;

  @Autowired
  private OpenAccountRepository openAccountRepository;


  public String signinWithOpenAccount(Integer open_account_type, String open_id) {
    OpenAccount openAccount = openAccountRepository.findByOpenAccountTypeAndOpenId(open_account_type,open_id);
    return null; //TODO
  }

  public String signinWithUserNameAndPassword(String username, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String signinPhoneAndCode(String phone, String code) {
      return null;//TODO
  }

  public String signup(User user) {
    if (!userRepository.existsByUsername(user.getUsername())) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
      return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }

  public User search(String username) {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user;
  }

  public boolean validateToken(String token){
    return jwtTokenProvider.validateToken(token);
  }

  public User whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public String refresh(String username) {
    return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
  }

  public String loginWithPhoneCode(String phone, String phone1) {
    User user =userRepository.findByPhone(phone);
    if (user==null){
      throw new CustomException("your phone isn't register yet", HttpStatus.NOT_FOUND);
    }

    return null; //TODO手机号，验证码
  }

  public List<Role> getAdminRoles() {
    List<Role> roles = roleRepistory.findByRoleName("admin");
    return roles;
  }

  public List<Role> getClientRoles() {
    List<Role> roles = roleRepistory.findByRoleName("client");
    return roles;
  }



}
