package com.tanmoy.SpringSecurityJWT.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tanmoy.SpringSecurityJWT.model.User;


/**
 * Implementation of UserDetailsService class of spring security core interface
 * which loads user-specific data
 * 
 * @author tanmoy.tushar
 * @since 2022-03-04
 */
@Service
public class UserDetailsServiceImplements implements UserDetailsService {

	private UserService userService;

	@Autowired
	public UserDetailsServiceImplements(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findUserByUsername(username);
		List<GrantedAuthority> authorities = getUserAuthority();
		return buildUserForAuthentication(user, authorities);
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true,
				true, true, authorities);
	}

	private List<GrantedAuthority> getUserAuthority() {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("admin"));
		roles.add(new SimpleGrantedAuthority("user"));
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}

}
