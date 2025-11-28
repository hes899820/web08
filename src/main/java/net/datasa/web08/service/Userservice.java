package net.datasa.web08.service;

import lombok.extern.slf4j.Slf4j;
import net.datasa.web08.dto.UserDTO;
import net.datasa.web08.entity.UserEntity;
import net.datasa.web08.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@Transactional

public class Userservice {
    @Autowired
    UserRepository repository;

    public void saveUser(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setPassword(dto.getPassword());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());

        repository.save(entity);
    }

    public List<UserDTO> userlist() {
        List<UserEntity> list = repository.findAll();
        //DTO를 담을 Arraylist생성
        //반복문으로 DTO객체를 생성해서 entity값 저장
        //ArrauList에 DTO추가
        //Arraylist 리턴
        List<UserDTO> dtolist = new ArrayList<>();
        for (UserEntity entity : list) {
            UserDTO dto = new UserDTO();
            dto.setId(entity.getId());
            dto.setPassword(entity.getPassword());
            dto.setName(entity.getName());
            dto.setPhone(entity.getPhone());
            dtolist.add(dto);

        }
        return dtolist;
    }


    public UserDTO login(String userid, String userpassword) {
        Optional<UserEntity> userOptional = repository.findById(userid);
        if (userOptional.isPresent()) {

            UserEntity entity = userOptional.get();
            log.debug("야야야야양야 {}",entity);
            if (entity.getPassword().equals(userpassword)) {
                UserDTO dto = new UserDTO();
                dto.setId(entity.getId());
                dto.setPassword(entity.getPassword());

                return dto;

            }
        } return null;
    }
}


