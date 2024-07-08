package com.web.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // NOTE: Eğer @Autowired annotationunu kullanmak ister isek  @AllArgsConstructor annotationunu kaldırız ve constructur metod ekleriz.
    /*
    ***
        @Autowired
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
    ***
    */

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

}
