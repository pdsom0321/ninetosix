package com.ninetosix.adminmodule.core.config;

import com.ninetosix.adminmodule.core.handler.CustomAccessDeniedHandler;
import com.ninetosix.adminmodule.core.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/v2/api-docs/**",
                        "/swagger*/**",
                        "/webjars/**",
                        "/login").permitAll()
                .antMatchers("/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)

                .and()
                .userDetailsService(customUserDetailsService)
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())

                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true) // 로그아웃 후 세션 초기화 설정
                .deleteCookies("JSESSIONID"); // 로그아웃 후 쿠기 삭제 설정

        return http.build();
    }

    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().antMatchers("/v2/api-docs/**",
                "/swagger*/**",
                "/images/**",
                "/js/**",
                "/css/**")
        );
    }
}
