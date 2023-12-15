package tw.niq.micro.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class WebSecurity {
	
	@Value("${tw.niq.token.secret}")
    private String tokenSecret;
	
	public static String TOKEN_SECRET;
	public static final String TOKEN_PREFIX = "Bearer ";

	@Value("${tw.niq.token.secret}")
    public void setTokenSecret(String tokenSecret) {
		WebSecurity.TOKEN_SECRET = tokenSecret;
    }

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		
		AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
		
		CustomAuthorizationFilter customAuthorizationFilter = new CustomAuthorizationFilter(authenticationManager);
		
		http
			.authenticationManager(authenticationManager)
			.addFilter(customAuthorizationFilter)
			.headers(headers -> headers.
					frameOptions(frameOptions -> frameOptions.disable()))
			.csrf(csrf -> csrf.disable())
			.sessionManagement((sessionManagement) -> sessionManagement
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests((authorize) -> authorize
					.anyRequest().authenticated());
		
		return http.build();
	}
}
