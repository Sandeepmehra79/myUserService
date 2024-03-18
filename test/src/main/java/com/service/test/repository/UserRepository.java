package com.service.test.repository;

import com.service.test.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
    @Override
    public Optional<User> findById(Long id);

    @Override
    @Transactional
    public User save(User user);

    @Transactional
    public void deleteByEmail(String email);

    @Transactional
    public Optional<User> findByEmail(String email);

    public List<User> findAll();


}
