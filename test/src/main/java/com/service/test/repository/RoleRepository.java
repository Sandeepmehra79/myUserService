package com.service.test.repository;

import com.service.test.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Override
    public Optional<Role> findById(Long id);

    @Transactional
    public javax.management.relation.Role save(javax.management.relation.Role role);

    @Transactional
    public void deleteByName(String Name);

    @Transactional
    public Optional<Role> findByName(String name);

    @Transactional
    public List<Role> findAll();

}
