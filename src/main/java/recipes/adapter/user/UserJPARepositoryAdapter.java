package recipes.adapter.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import recipes.domain.User;
import recipes.entity.UserEntity;
import recipes.entity.repository.UserEntityRepository;
import recipes.mapper.user.User2UserEntityMapper;
import recipes.mapper.user.UserEntity2UserMapper;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserJPARepositoryAdapter implements UserRepositoryAdapter {

    private final UserEntityRepository userEntityRepository;

    private final User2UserEntityMapper user2UserEntityMapper;
    private final UserEntity2UserMapper userEntity2UserMapper;


    @Override
    public void createUser(User user) {
        UserEntity userEntity = user2UserEntityMapper.toUserEntity(user);
        var userEntityWithThisEmail = userEntityRepository.findUserEntityByEmail(userEntity.getEmail());

        if (userEntityWithThisEmail.isPresent()) {
            log.info("User with email {} already exists.", user.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        userEntityRepository.save(userEntity);
        log.debug("User with email {} successfully registered.", user.getEmail());
    }

    @Override
    public List<User> findAllUsers() {
        List<UserEntity> userEntities = userEntityRepository.findAll();

        List<String> emails = userEntities.stream()
                                            .map(UserEntity::getEmail)
                                            .toList();
        log.info("Find {} users with emails: {}.", userEntities.size(), emails);

        return userEntity2UserMapper.toUserList(userEntities);
    }

}
