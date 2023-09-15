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
                        "/login",
                        "/**").permitAll()
                .antMatchers("/").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()

                // 시큐리티는 기본적으로 세션을 사용
                // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
                .and()
                .sessionManagement()

                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .permitAll()

                .and()
                .userDetailsService(customUserDetailsService)
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .logout()
                .logoutSuccessUrl("/login");

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
