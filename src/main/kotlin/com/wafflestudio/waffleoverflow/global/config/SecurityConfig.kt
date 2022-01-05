package com.wafflestudio.waffleoverflow.global.config

import com.wafflestudio.waffleoverflow.global.auth.SigninAuthenticationFilter
import com.wafflestudio.waffleoverflow.global.auth.jwt.JwtAuthenticationEntryPoint
import com.wafflestudio.waffleoverflow.global.auth.jwt.JwtAuthenticationFilter
import com.wafflestudio.waffleoverflow.global.auth.jwt.JwtTokenProvider
import com.wafflestudio.waffleoverflow.global.auth.model.UserPrincipalDetailService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val userPrincipalDetailService: UserPrincipalDetailService,
) : WebSecurityConfigurerAdapter() {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setPasswordEncoder(passwordEncoder())
        provider.setUserDetailsService(userPrincipalDetailService)
        return provider
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()

        corsConfiguration.addAllowedOrigin("https://www.waffleoverflow.shop")
        corsConfiguration.addAllowedOrigin("http://localhost:3000")
        corsConfiguration.addAllowedHeader("*")
        corsConfiguration.addAllowedMethod("*")
        corsConfiguration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfiguration)

        return source
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .authenticationProvider(daoAuthenticationProvider())
    }

    override fun configure(http: HttpSecurity) {
        http
            .cors().configurationSource(corsConfigurationSource())
            .and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .addFilter(SigninAuthenticationFilter(authenticationManager(), jwtTokenProvider))
            .addFilter(JwtAuthenticationFilter(authenticationManager(), jwtTokenProvider))
            .authorizeRequests()
            .antMatchers("/api/user/signin/").permitAll()
            .antMatchers(HttpMethod.POST, "/api/user/signup/").anonymous()
            .antMatchers(HttpMethod.GET, "/api/ping/").permitAll()
            .antMatchers(HttpMethod.GET, "/api/question/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/answer/**").permitAll()
            .anyRequest().authenticated()
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(*AUTH_WHITELIST_SWAGGER)
    }

    companion object {
        private val AUTH_WHITELIST_SWAGGER = arrayOf( // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
        )
    }
}
