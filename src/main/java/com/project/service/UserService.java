package com.project.service;

import com.project.dto.AddressDTO;
import com.project.dto.UserRequest;
import com.project.dto.UserResponse;
import com.project.model.Address;
import com.project.model.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> fetchAllUsers(){
        return userRepository.findAll().stream().map(
                this::mapToUserResponse
        ).collect(Collectors.toList());
    }

    public void addUser(UserRequest userRequest){
        User user=new User();
        updateUserFromRequest(user, userRequest);
        userRepository.save(user);
    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if(userRequest.getAddress()!=null){
            Address address=new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZip_code(userRequest.getAddress().getZip_code());
            user.setAddress(address);

        }
    }

    public Optional<UserResponse> getUser(long id) {
        return userRepository.findById(id)
                .map(this::mapToUserResponse);
    }
    public boolean updateUser(Long id, UserRequest userRequest) {

            return userRepository.findById(id)
                    .map(existingUser -> {
                        updateUserFromRequest(existingUser, userRequest);
                        userRepository.save(existingUser);
                        return true;
                    })
                    .orElse(false);
    }
    private UserResponse mapToUserResponse(User user){
        UserResponse userResponse=new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRole());

        if(user.getAddress()!=null){
            AddressDTO addressDTO=new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZip_code(user.getAddress().getZip_code());
            userResponse.setAddress(addressDTO);
        }
        return userResponse;

    }
}
