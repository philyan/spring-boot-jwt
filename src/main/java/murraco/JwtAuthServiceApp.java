package murraco;

import java.util.ArrayList;
import java.util.Arrays;

import murraco.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import murraco.service.UserService;

@SpringBootApplication
public class JwtAuthServiceApp implements CommandLineRunner {

  @Autowired
  UserService userService;

  public static void main(String[] args) {
    SpringApplication.run(JwtAuthServiceApp.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Override
  public void run(String... params) throws Exception {
//    User admin = new User();
//    admin.setUsername("admin");
//    admin.setPassword("admin");
//    admin.setEmail("admin@email.com");
//    admin.setRoles(userService.getAdminRoles());
//    userService.signup(admin);
//
//    User client = new User();
//    client.setUsername("client");
//    client.setPassword("client");
//    client.setEmail("client@email.com");
//    client.setRoles(userService.getClientRoles());
//
//    userService.signup(client);
  }

}
