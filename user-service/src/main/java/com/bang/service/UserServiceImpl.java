package com.bang.service;

import com.bang.model.User;
import com.bang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by johnnyGrimes on 22/02/2018.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public Iterable<User> list() {
        return userRepository.findAll();
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }
}
