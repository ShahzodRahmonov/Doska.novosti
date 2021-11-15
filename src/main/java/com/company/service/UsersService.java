package com.company.service;

import com.company.dto.*;
import com.company.entity.Users;
import com.company.enums.UserRole;
import com.company.enums.UserStatus;
import com.company.exceptions.UserNotFoundException;
import com.company.exceptions.UsersLoginException;
import com.company.repository.UsersRepository;
import com.company.util.TokenProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public UsersDTO registration(RegistrationDTO dto){

        Optional<Users> optional = usersRepository.findByLogin(dto.getLogin());
        if (optional.isPresent()) {
            throw new UsersLoginException("Bu login bazada mavjud, boshqa login kiriting!!!");
        }
        Users users = new Users();
        users.setLogin(dto.getLogin());
        users.setPassword(dto.getPassword());
        users.setEmail(dto.getEmail());
        users.setAge(dto.getAge());
        users.setRole(UserRole.USER);
        usersRepository.save(users);
        return toDTO(users);
    }



    public UsersDTO authorization(AuthorizationDTO dto){
        Optional<Users> optional = usersRepository.findByLoginAndPassword(dto.getLogin(),dto.getPassword());
        if (!optional.isPresent()) {
            throw new UserNotFoundException("Неверный логин или пароль");
        }
        Users users = optional.get();

        UserDetails userDetails = new UserDetails();
        userDetails.setId(users.getId());
        userDetails.setRole(users.getRole());

        String jwt = TokenProcess.generateJwt(userDetails);

        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setId(users.getId());
        usersDTO.setLogin(users.getLogin());
        usersDTO.setPassword(users.getPassword());
        usersDTO.setEmail(users.getEmail());
        usersDTO.setAge(users.getAge());
        usersDTO.setRole(users.getRole());
        usersDTO.setRegistrationDate(users.getRegistrationDate());
        usersDTO.setToken(jwt);

        return usersDTO;
    }

    public Boolean addAdmin(Long id){
        Optional<Users> optional = usersRepository.findById(id);
        if (!optional.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        Users users = optional.get();
        users.setRole(UserRole.ADMIN);
        usersRepository.save(users);
        return true;
    }





    public UsersDTO toDTO(Users users){
        UsersDTO dto = new UsersDTO();
        dto.setId(users.getId());
        dto.setLogin(users.getLogin());
        dto.setPassword(users.getPassword());
        dto.setEmail(users.getEmail());
        dto.setAge(users.getAge());
        dto.setRole(users.getRole());
        dto.setRegistrationDate(users.getRegistrationDate());
        return dto;
    }
}
