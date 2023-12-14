package com.carwash.washerservice.service;

import com.carwash.washerservice.exception.*;
import com.carwash.washerservice.model.Gender;
import com.carwash.washerservice.model.Washer;
import com.carwash.washerservice.model.WasherDto;
import com.carwash.washerservice.model.WasherUpdate;
import com.carwash.washerservice.repository.WasherRepository;
import com.carwash.washerservice.util.PhoneNumberValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WasherServiceImpl implements WasherService{

    @Autowired
    private SequenceGeneratorService seqService;

    @Autowired
    private WasherRepository washerRepository;

    @Override
    public Washer createWasher(Washer washerDto) {
        Washer washer=new Washer();
        washer.setUserId(seqService.getSequenceNumber(Washer.SEQUENCE_NAME));
        Optional<Washer> user1=washerRepository.findByUserName(washerDto.getUserName());
        if(user1.isPresent())
            throw new UserNameException("username already taken, choose new username");
        if(washerDto.getUserName().length()<3)
            throw new UserNameException("username should contain more then three charecters");
        washer.setUserName(washerDto.getUserName());

        if(washerDto.getFullName().isEmpty())
            throw new NameException("Name can't be blank");
        washer.setFullName(washerDto.getFullName());

        if(washerDto.getAge()<18 || washerDto.getAge()>40)
            throw new InvalidAgeException("please enter valid age");
        washer.setAge(washerDto.getAge());

            washer.setRole("WASHER");
        washer.setGender(washerDto.getGender());

        if(!(washerDto.getEmail().contains("@")) && (washerDto.getEmail().contains(".")))
            throw new InvalidEmailException("please enter valid email");
        washer.setEmail(washerDto.getEmail());

        if(!PhoneNumberValidation.isValidMobileNo(washerDto.getPhoneNo()))
            throw new PhoneNoException("invalid phone number");
        washer.setPhoneNo(washerDto.getPhoneNo());

        Optional<Washer> user2=washerRepository.findByPhoneNo(washerDto.getPhoneNo());
        if(user2.isPresent())
            throw new PhoneNoException("someone is rigistered with this number enter new");
        washer.setPhoneNo(washerDto.getPhoneNo());

        if(washerDto.getPassword().length()<8)
            throw new PasswordException("password should be more then 8 charecters");
        washer.setPassword(washerDto.getPassword());

        washer.setIsActive(true);
        washerRepository.save(washer);
        return washer;
    }

    @Override
    public WasherDto gatWasherByUserName(String userName) {
        Washer user=washerRepository.findByUserName(userName)
                .orElse(new Washer(0,"NA","order Not Accepted yet plz wait ",Gender.MALE,"","","",00,"",false));
         WasherDto dto= new WasherDto();
         dto.setUserId(user.getUserId());
         dto.setUserName(user.getUserName());
         dto.setFullName(user.getFullName());
         dto.setGender(user.getGender());
         dto.setEmail(user.getEmail());
         dto.setPhoneNo(user.getPhoneNo());
         dto.setAge(user.getAge());
         dto.setIsActive(user.getIsActive());
        return dto;
    }

    @Override
    public List<WasherDto> getAllWashers() {
        List<Washer>  washers= washerRepository.findUserByRole("WASHER").get();
          List<WasherDto> washerDtos= new ArrayList<>();
          for (Washer washer:washers){
              WasherDto dto = new WasherDto();
              dto.setUserId(washer.getUserId());
              dto.setUserName(washer.getUserName());
              dto.setFullName(washer.getFullName());
              dto.setGender(washer.getGender());
              dto.setEmail(washer.getEmail());
              dto.setPhoneNo(washer.getPhoneNo());
              dto.setAge(washer.getAge());
              dto.setIsActive(washer.getIsActive());
              washerDtos.add(dto);
          }
        return washerDtos;
    }

    @Override
    public String updateUser(WasherUpdate washerDto) {
        Optional<Washer> user1=washerRepository.findByUserName(washerDto.getUserName());
        if(user1.isEmpty())
            throw new UserNameException("account not found with this username");
        Washer user=washerRepository.getByUserName(washerDto.getUserName());
        if(washerDto.getFullName().isEmpty())
            throw new NameException("Name can't be blank");
        user.setFullName(washerDto.getFullName());
        if(!(washerDto.getEmail().contains("@")) && (washerDto.getEmail().contains(".")))
            throw new InvalidEmailException("please enter valid email");
        user.setEmail(washerDto.getEmail());

        if(!PhoneNumberValidation.isValidMobileNo(washerDto.getPhoneNo()))
            throw new PhoneNoException("invalid phone number");
        user.setPhoneNo(washerDto.getPhoneNo());
        user.setAge(washerDto.getAge());
        washerRepository.save(user);
        return user.getUserName();
    }

    @Override
    public void deleteUser(String userName) {
        Optional<Washer> user=washerRepository.findByUserName(userName);
        if(user.isEmpty()){
            throw new UserNameException("account not found with this username");
        }
        washerRepository.deleteByUserName(userName);
    }
}
