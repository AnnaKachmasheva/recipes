package recipes.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import recipes.entity.repository.UserEntityRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var userEntity = userEntityRepository.findUserEntityByEmail(email);

        if (userEntity.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new UserDetailsImpl(userEntity.get().getEmail(), userEntity.get().getPassword());
    }

}