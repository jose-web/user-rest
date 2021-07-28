package com.idb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.idb.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
