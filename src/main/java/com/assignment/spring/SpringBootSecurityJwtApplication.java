package com.assignment.spring;

import com.assignment.spring.models.ERole;
import com.assignment.spring.models.Role;
import com.assignment.spring.models.User;
import com.assignment.spring.repository.RoleRepository;
import com.assignment.spring.repository.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableSwagger2
@EnableAsync
@Configuration
@EnableScheduling
public class SpringBootSecurityJwtApplication {
  @Autowired
  PasswordEncoder encoder;
  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String DEFAULT_INCLUDE_PATTERN = "/api.*";

  public static void main(String[] args) {
    SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
  }

  @Bean
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*"); // this allows all origin
    config.addAllowedHeader("*"); // this allows all headers
    config.addAllowedMethod("OPTIONS");
    config.addAllowedMethod("HEAD");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("DELETE");
    config.addAllowedMethod("PATCH");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

  @Bean
  CommandLineRunner init(RoleRepository RoleRepository, UserRepository userRepository) {
    return args -> {

      if (userRepository.findByEmailIgnoreCase("farahu2008@gmail.com").isEmpty()) {


        Role r1 = new Role(ERole.ROLE_ADMIN);
        Role r2 = new Role(ERole.ROLE_MODERATOR);
        Role r3 = new Role(ERole.ROLE_USER);

        RoleRepository.save(r1);
        RoleRepository.save(r2);
        RoleRepository.save(r3);
        Set<Role> r = new HashSet<Role>();
        r.add(r1);
        r.add(r2);
        r.add(r3);
        User user = new User("admin", "farahu2008@gmail.com", encoder.encode("Admin@123"), r);
        user.setName("Administrator");
        user.setStatus("Activated");
        userRepository.save(user);
      }
    };

  }

  ;

  /**
   * the following method adds the API info to the swagger api documentation
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Rahu - App")
      .description("This api document serves as collection")
      .termsOfServiceUrl("http://javainuse.com").license("JavaInUse License")
      .licenseUrl("farahu2008@gmail.com").version("1.0").build();
  }

  /**
   * the following method makes all the APIs under the CMS package available on
   * swagger
   *
   * @return
   */
  @Bean
  public Docket apis() {
    return new Docket(DocumentationType.SWAGGER_2).securitySchemes(Lists.newArrayList(apiKey()))
      .securityContexts(Lists.newArrayList(securityContext())).apiInfo(apiInfo()).select()
      .apis(RequestHandlerSelectors.basePackage("com.assignment.spring")).build();
  }

  /**
   * the following configuration adds the use of authorization key the APIs in swagger
   */
  private ApiKey apiKey() {
    return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth())
      .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN)).build();
  }

  List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
  }


}
