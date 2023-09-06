package com.medicalrecord.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.GET, "/patients").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/patients/create-patient").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/patients/create").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/patients/edit-patient/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/patients/update/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/patients/delete/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/patients/search-patient").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/patients/patient-name-egn").hasAnyRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/doctors").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/doctors/create-doctor").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/doctors/create").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/doctors/edit-doctor/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/doctors/update/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/doctors/delete/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/doctors/search-doctor").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/doctors/doctor-name-working-place").hasAnyRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/health-insurances").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/health-insurances/create-health-insurance").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/health-insurances/create").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/health-insurances/edit-health-insurance/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/health-insurances/update/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/health-insurances/delete/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/health-insurances/search-health-insurances").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/health-insurances/patient-name-egn").hasAnyRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/gp-patient-registrations").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/gp-patient-registrations/create-gp-patient-registration").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/gp-patient-registrations/create").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/gp-patient-registrations/edit-gp-patient-registration/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/gp-patient-registrations/update/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/gp-patient-registrations/delete/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/gp-patient-registrations/search-gp-patient-registration").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/gp-patient-registrations/patient-name-egn").hasAnyRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/patient-disease-records").hasAnyRole("ADMIN", "DOCTOR")
                .antMatchers(HttpMethod.GET, "/patient-disease-records/create-patient-disease-record").hasAnyRole("ADMIN", "DOCTOR")
                .antMatchers(HttpMethod.POST, "/patient-disease-records/create").hasAnyRole("ADMIN", "DOCTOR")
                .antMatchers(HttpMethod.GET, "/patient-disease-records/edit-patient-disease-record/{id}").hasAnyRole("ADMIN", "DOCTOR")
                .antMatchers(HttpMethod.POST, "/patient-disease-records/update/{id}").hasAnyRole("ADMIN", "DOCTOR")
                .antMatchers(HttpMethod.GET, "/patient-disease-records/delete/{id}").hasAnyRole("ADMIN", "DOCTOR")
                .antMatchers(HttpMethod.GET, "/patient-disease-records/search-patient-disease-record").hasAnyRole("ADMIN", "PATIENT", "DOCTOR")
                .antMatchers(HttpMethod.GET, "/patient-disease-records/patient-name-egn").hasAnyRole("ADMIN", "PATIENT", "DOCTOR")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/unauthorized")
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("doctor").password(passwordEncoder().encode("doctor")).roles("DOCTOR")
                .and()
                .withUser("patient").password(passwordEncoder().encode("patient")).roles("PATIENT")
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }
}
