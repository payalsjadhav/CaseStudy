package com.carwash.userservice.service;

import com.carwash.userservice.exception.*;
import com.carwash.userservice.model.*;
import com.carwash.userservice.repository.UserRepository;
import com.carwash.userservice.util.PhoneNumberValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
	private SequenceGeneratorService seqService;

    @Override
    public User createUser(UserDto userDto){
		User user= new User();
		user.setUserId(seqService.getSequenceNumber(User.SEQUENCE_NAME));
    	Optional<User> user1=userRepository.findByUserName(userDto.getUserName());
    	if(user1.isPresent())
    		throw new UserNameException("username already taken, choose new username");
    	if(userDto.getUserName().length()<3)
    		throw new UserNameException("username should contain more then three charecters");
    	user.setUserName(userDto.getUserName());

    	if(userDto.getFullName().isEmpty())
    		throw new NameException("Name can't be blank");
    	user.setFullName(userDto.getFullName());

		 Role role= Role.USER;
			user.setRole(role);

        if(!(userDto.getEmail().contains("@")) && (userDto.getEmail().contains(".")))
       	  throw new InvalidEmailException("please enter valid email");
    	  user.setEmail(userDto.getEmail());

    	if(!PhoneNumberValidation.isValidMobileNo(userDto.getPhoneNo()))
		    throw new PhoneNoException("invalid phone number");
			user.setPhoneNo(userDto.getPhoneNo());

		Optional<User> user2=userRepository.findByPhoneNo(userDto.getPhoneNo());
    	if(user2.isPresent())
    		throw new PhoneNoException("someone is rigistered with this number enter new");
    	user.setPhoneNo(userDto.getPhoneNo());

    	if(userDto.getPassword().length()<8)
   		throw new PasswordException("password should be more then 8 charecters");
    	user.setPassword(userDto.getPassword());

    	user.setIsActive(true);
        userRepository.save(user);
        return user;
    }

    public UserDtos gatUserByUserName(String userName){
	   Optional<User> user=userRepository.findByUserName(userName);
       if(user.isEmpty()) {
       	throw new UserNameException("account not found with this username");
	   }
         UserDtos userDtos =new UserDtos();
         userDtos.setUserId(user.get().getUserId());
         userDtos.setUserName(user.get().getUserName());
         userDtos.setFullName(user.get().getFullName());
         userDtos.setPhoneNo(user.get().getPhoneNo());
         userDtos.setEmail(user.get().getEmail());
		return userDtos;
	}

	@Override
	public List<UserDtos> getAllUsers() {

		List<UserDtos> users= new ArrayList<>();
		List<User> user=userRepository.findUserByRole("USER").get();
		for (User user1:user) {
			UserDtos dtos= new UserDtos();
			dtos.setUserId(user1.getUserId());
			dtos.setUserName(user1.getUserName());
			dtos.setFullName(user1.getFullName());
			dtos.setEmail(user1.getEmail());
			dtos.setPhoneNo(user1.getPhoneNo());
			users.add(dtos);
		}
		return users;
	}


	@Override
	public String updateUser(UserUpdate userDto) {
		Optional<User> user1=userRepository.findByUserName(userDto.getUserName());
    	if(user1.isEmpty())
    		throw new UserNameException("account not found with this username");

    	User user=userRepository.getByUserName(userDto.getUserName());
		user.setUserName(userDto.getUserName());

		if(userDto.getFullName().isEmpty())
			throw new NameException("Name can't be blank");
		user.setFullName(userDto.getFullName());

		if(!(userDto.getEmail().contains("@")) && (userDto.getEmail().contains(".")))
			throw new InvalidEmailException("please enter valid email");
		user.setEmail(userDto.getEmail());

		if(!PhoneNumberValidation.isValidMobileNo(userDto.getPhoneNo()))
			throw new PhoneNoException("invalid phone number");
		user.setPhoneNo(userDto.getPhoneNo());


		Role role= Role.USER;
		user.setRole(role);
		user.setPhoneNo(userDto.getPhoneNo());

		user.setIsActive(true);
		userRepository.save(user);
		return user.getUserName();

	}

	@Override
	public void deleteUser(String userName) {
    Optional<User> user=userRepository.findByUserName(userName);
    	if(user.isEmpty()){
    		throw new UserNameException("account not found with this username");
		}
    	userRepository.deleteByUserName(userName);
	}

	@Override
	public String createAdmin(UserDto userDto) {
		User user= new User();
		user.setUserId(seqService.getSequenceNumber(User.SEQUENCE_NAME));
		Optional<User> user1=userRepository.findByUserName(userDto.getUserName());
		if(user1.isPresent())
			throw new UserNameException("username already taken, choose new username");
		user.setUserName(userDto.getUserName());

		if(userDto.getFullName().isEmpty())
			throw new NameException("Name can't be blank");
		user.setFullName(userDto.getFullName());

		Role role=Role.ADMIN;
			user.setRole(role );

		if(!(userDto.getEmail().contains("@")) && (userDto.getEmail().contains(".")))
			throw new InvalidEmailException("please enter valid email");
		user.setEmail(userDto.getEmail());

		if(!PhoneNumberValidation.isValidMobileNo(userDto.getPhoneNo()))
			throw new PhoneNoException("invalid phone number");

		Optional<User> user2=userRepository.findByPhoneNo(userDto.getPhoneNo());
		if(user2.isPresent())
			throw new PhoneNoException("someone is rigistered with this number enter new number");
		user.setPhoneNo(userDto.getPhoneNo());

		if(userDto.getPassword().length()<8)
			throw new PasswordException("password should be more then 8 charecters");
		user.setPassword(userDto.getPassword());

		user.setIsActive(true);

		userRepository.save(user);
		return user.getUserName();
	}


	public LoginResponse userLoginResponse(String userName){
		Optional<User> user=userRepository.findByUserName(userName);
		if(user.isEmpty()) {
			throw new UserNameException("account not found with this username");
		}
		LoginResponse response=new LoginResponse();
		response.setUserId(user.get().getUserId());
		response.setUserName(user.get().getUserName());
		response.setPassword(user.get().getPassword());
		response.setRole(user.get().getRole().name());

		return response;



	}

	@Override
	public Boolean userExistByUserName(String userName) {
    	 Boolean user= userRepository.existByUserName(userName);
       return user;
	}

}

