package sk.momosilabs.recharger.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
open class SecurityConfiguration {

    companion object {
        private val SWAGGER_RESOURCES = listOf(
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
        ).toTypedArray()
    }

    @Bean
    open fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests {
                req -> req
                    .requestMatchers("/api/version").permitAll()
                    .requestMatchers(*SWAGGER_RESOURCES).permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2ResourceServer { oAuth2 -> oAuth2.jwt(withDefaults()) }
//            .oauth2ResourceServer { oAuth2 -> oAuth2.jwt { jwt -> jwt.decoder() } }
            .sessionManagement { sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .cors(withDefaults())
            .csrf { csrf -> csrf.disable() }

        return http.build()
    }

}
