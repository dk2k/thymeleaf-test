package ru.outofrange.service;

import java.util.Date;
import java.util.List;

import ru.outofrange.exc.UserEntityNotFoundException;
import ru.outofrange.model.Gender;
import ru.outofrange.model.UserEntity;

public interface UserEntityService {
	
    public UserEntity create(UserEntity user);
    public UserEntity delete(long id) throws UserEntityNotFoundException;
    public List<UserEntity> findAll();
    public UserEntity update(UserEntity user) throws UserEntityNotFoundException;
    public UserEntity findById(long id);
    
    public List<UserEntity> searchForUsers(
    		String username,
			String email,
			String name,
			Integer lowerAge,
			Integer upperAge,
			Date lowerDate,
			Date upperDate,
			Gender gender);

}
