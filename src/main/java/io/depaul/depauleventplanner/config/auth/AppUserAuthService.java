package io.depaul.depauleventplanner.config.auth;

import io.depaul.depauleventplanner.model.user.User;
import io.depaul.depauleventplanner.repo.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class AppUserAuthService implements UserDetailsService {

    private final UserRepo users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ofNullable(users.getUser(username))
                .map(AppUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not a registered entity. Please try again."));
    }
}
