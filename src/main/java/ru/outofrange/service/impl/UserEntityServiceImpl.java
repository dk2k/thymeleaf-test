package ru.outofrange.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.outofrange.exc.UserEntityNotFoundException;
import ru.outofrange.model.Gender;
import ru.outofrange.model.UserEntity;
import ru.outofrange.model.UserEntitySpecifications;
import ru.outofrange.repository.UserRepository;
import ru.outofrange.service.UserEntityService;

@Service
public class UserEntityServiceImpl implements UserEntityService {

	@Resource
    private UserRepository userRepository;
	
	@Override
    @Transactional
	public UserEntity create(UserEntity user) {
        UserEntity createdUserEntity = user;
        return userRepository.save(createdUserEntity);
	}

    @Override
    @Transactional(rollbackFor=UserEntityNotFoundException.class)
	public UserEntity delete(long id) throws UserEntityNotFoundException {
        UserEntity deletedUserEntity = userRepository.findOne(id);
        
        if (deletedUserEntity == null)
            throw new UserEntityNotFoundException();
         
        userRepository.delete(deletedUserEntity);
        return deletedUserEntity;
	}

    @Override
    @Transactional
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

    @Override
    @Transactional(rollbackFor=UserEntityNotFoundException.class)
	public UserEntity update(UserEntity user)
			throws UserEntityNotFoundException {
        UserEntity updatedUserEntity = userRepository.findOne(user.getId());
        
        if (updatedUserEntity == null)
            throw new UserEntityNotFoundException();
         
        updatedUserEntity.setName(user.getName());
        updatedUserEntity.setUsername(user.getUsername());
        updatedUserEntity.setAge(user.getAge());
        updatedUserEntity.setEmail(user.getEmail());
        updatedUserEntity.setGender(user.getGender());
        
        return updatedUserEntity;
	}
	
    @Override
    @Transactional
	public UserEntity findById(long id) {
    	return userRepository.findOne(id);
	}

    @Override
    @Transactional
    public List<UserEntity> searchForUsers(
    		String username,
			String email,
			String name,
			Integer lowerAge,
			Integer upperAge,
			Date lowerDate,
			Date upperDate,
			Gender gender){
    	
    	
    	UserEntitySpecifications spec = new UserEntitySpecifications(
        		username,
    			email,
    			name,
    			lowerAge,
    			upperAge,
    			lowerDate,
    			upperDate,
    			gender);
    	
    	List<UserEntity> users = userRepository.findAll(spec);
    	
		return users;
    	
    }
    
}
