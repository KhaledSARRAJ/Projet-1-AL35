package fr.eql.projet01.springSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//necessary for @PreAuthorize("hasRole('ADMIN or ...')")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private PasswordEncoder passwordEncoder;


	/*
	    @Autowired
	    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		  .withUser("user1").password(passwordEncoder.encode("pwduser1")).roles("USER").and()
		  .withUser("admin1").password(passwordEncoder.encode("pwdadmin1")).roles("ADMIN","USER").and()
		  .withUser("publisher1").password(passwordEncoder.encode("pwdpublisher1")).roles("PUBLISHER","USER").and()
		  .withUser("user2").password(passwordEncoder.encode("pwduser2")).roles("USER").and()
		  .withUser("admin2").password(passwordEncoder.encode("pwdadmin2")).roles("ADMIN").and()
		  .withUser("publisher2").password(passwordEncoder.encode("pwdpublisher2")).roles("PUBLISHER"); 
	    }
	 */

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;	

	@Autowired
	public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService)
		.passwordEncoder(passwordEncoder);
	}

	// roles admin allow to access /admin/**
	// roles user allow to access /user/**
	// custom 403 access denied handler
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/", "/**/*.css", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.css", "/**/*.js", "/home", "/about").permitAll()
		.antMatchers("/session-end").permitAll()
		.antMatchers("/admin/**").hasAnyRole("ADMIN")
		.antMatchers("/user/**").hasAnyRole("USER")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.permitAll()
		.and()
		.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}
}