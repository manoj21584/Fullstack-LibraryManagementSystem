package org.application.Security;

import org.application.Entity.Users;
import org.application.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UserRepo empRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = empRepo.findByEmail(username);
        if (users == null) {
            throw new UsernameNotFoundException("Could not find the user");
        }

        return new UserSecurity(users);
    }
}
