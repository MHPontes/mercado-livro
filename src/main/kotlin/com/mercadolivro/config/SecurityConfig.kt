package com.mercadolivro.config

import com.mercadolivro.repository.CustomerRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val customerRepository: CustomerRepository
) {

    private val PUBLIC_POST_MATCHERS = arrayOf(
        "customers"
    )

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {       //As implementacoes da [Aula 80], não funcionaram pois estavam deprecated e o WebSecurityAdapter foi descontinuado em versoes mais atualizadas do spring sec, por isso tive que adaptar dessa forma para que funcione(mais atualizado)
        http.authorizeHttpRequests { requests ->
            requests.requestMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll()
            requests.anyRequest().authenticated()
        }
        http.httpBasic {} // Configuração do HttpBasic
        http.sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
//        http.addFilter(AuthenticationFilter(authenticationManager(),customerRepository )) //Necessario adaptar esta linha para que funcione no filterChain
        http.csrf { it.disable() }
        http.cors { it.disable() }
        return http.build()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}