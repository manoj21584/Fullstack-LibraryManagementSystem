package org.application.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserSecurityService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authManager) throws Exception {
		authManager.authenticationProvider(authenticationProvider());
	}

	protected void configure(HttpSecurity http) throws Exception {
		/// Security.httpBasic().disable();

		http.authorizeRequests()
				.antMatchers("/").hasAnyAuthority("USER", "CREATER", "ADMIN")
				.antMatchers("/userTable").hasAnyAuthority("CREATER")
				.antMatchers("/feedbackTable").hasAnyAuthority( "CREATER","ADMIN")
				.antMatchers("/handleDownloads").hasAnyAuthority( "CREATER","ADMIN")
				.antMatchers("/register").permitAll()
				.antMatchers("/feedback").permitAll()
				.antMatchers("/downloads").permitAll()
				.antMatchers("/forgot").permitAll()
				.antMatchers("/reset").permitAll()
				.antMatchers("/updatePassword").permitAll()
				.antMatchers("/reset/**").permitAll()
				.antMatchers("/saveEmployee").permitAll()
				.antMatchers("/saveFeedback").permitAll()
				.antMatchers("/saveDocument").permitAll()
				.antMatchers("/allDocuments").permitAll()
				.antMatchers("/activate/**").permitAll()
				.antMatchers("/updatePassword").permitAll()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/about").permitAll()

				.antMatchers("/favicon.ico").permitAll()
				
				.antMatchers("/js/**").permitAll()
				
				.antMatchers("/document/**").permitAll()
				
/*
				.antMatchers("/table").hasAnyAuthority("ADMIN", "CREATER")
				.antMatchers("/update/**").hasAnyAuthority("ADMIN", "CREATER")
				.antMatchers("/delete/**").hasAnyAuthority("ADMIN")
*/
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login").defaultSuccessUrl("/dashboard")
				.permitAll()
				.and()
				.logout()
				.permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/errorPage");

		http.csrf().disable();
		http.headers().frameOptions().disable();

	}
}
