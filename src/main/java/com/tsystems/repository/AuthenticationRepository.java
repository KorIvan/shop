package com.tsystems.repository;

import com.tsystems.model.Person;

public interface AuthenticationRepository {
	Person findUserByEmail(String email);
}
