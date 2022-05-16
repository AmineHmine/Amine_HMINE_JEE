package me.mapping.manytomany;

import me.mapping.manytomany.entities.Role;
import me.mapping.manytomany.entities.User;
import me.mapping.manytomany.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ManyToManyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManyToManyApplication.class, args);
    }
    @Bean
    CommandLineRunner start(UserService userService){

        return args -> {
            User u=new User();
            u.setUsername("user1");
            u.setPassword("123456");
            userService.addNewUser(u);

            User u2=new User();
            u2.setUsername("user2");
            u2.setPassword("123456");
            userService.addNewUser(u2);

            Stream.of("ETU","PROF","ADMIN").forEach(r->{
                Role role1=new Role();
                role1.setRoleName(r);
                userService.addNewRole(role1);
            });

            userService.addRoleToUser("user1","ETU");
            userService.addRoleToUser("user2","ADMIN");
            userService.addRoleToUser("user2","PROF");

            try{
                User user=userService.login("user2","123456");
                System.out.println(user.getUserId());
                System.out.println(user.getUsername());
                System.out.println("Roles : ");
                user.getRoles().forEach(r->{
                    System.out.println("role -> "+r.getRoleName());
                });
            }catch (Exception exception){
                exception.printStackTrace();
            }
        };
    }

}
