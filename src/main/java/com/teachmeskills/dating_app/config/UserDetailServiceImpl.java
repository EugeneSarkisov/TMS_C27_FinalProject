
package com.teachmeskills.dating_app.config;

import com.teachmeskills.dating_app.model.user.UserAccount;
import com.teachmeskills.dating_app.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findUserAccountByUsername(username);
        if (userAccount != null) {
            Set<GrantedAuthority> grantedAuthoritySet = new HashSet();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userAccount.getRole());
            grantedAuthoritySet.add(simpleGrantedAuthority);
            return new org.springframework.security.core.userdetails.User(userAccount.getUsername(), userAccount.getPassword(), grantedAuthoritySet);
        } else {
            throw new UsernameNotFoundException(String.format("User with login '%s' was not found", username));
        }
    }
}
