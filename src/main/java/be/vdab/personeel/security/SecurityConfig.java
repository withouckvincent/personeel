package be.vdab.personeel.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String GEENROL = "geenrol";

	private static final String USERS_BY_USERNAME = "select email as username, paswoord as password, '1' as enabled"
			+ " from werknemers where email = ?";

	private static final String AUTHORITIES_BY_USERNAME = "select email as username, 'geenrol' as authorities from werknemers where email = ?";

	@Bean
	JdbcDaoImpl jdbcDaoImpl(DataSource dataSource) {
		JdbcDaoImpl impl = new JdbcDaoImpl();
		impl.setDataSource(dataSource);
		impl.setUsersByUsernameQuery(USERS_BY_USERNAME);
		impl.setAuthoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME);
		return impl;
	}

	@Override
	public void configure(WebSecurity web) { // throws Exception moet er niet meer bij sedert een nieuwe spring boot
		web.ignoring().mvcMatchers("/images/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").and().authorizeRequests()
		.mvcMatchers("/werknemersfiche").hasAuthority(GEENROL)
		.mvcMatchers("/", "/login", "/jobtitels/**").permitAll().mvcMatchers("/**").authenticated();
		http.httpBasic();
	}
}