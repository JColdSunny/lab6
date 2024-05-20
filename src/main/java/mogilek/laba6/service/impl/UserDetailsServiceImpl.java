package mogilek.laba6.service.impl;


import mogilek.laba6.entity.User;
import mogilek.laba6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        UserDetails build = org.springframework.security.core.userdetails.User.withUsername(username)
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
        return build;
    }
}
