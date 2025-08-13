package com.narensoft.user_service;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends CrudRepository<Long, User> {
}


//JDBC -> Hibernate -> JPA