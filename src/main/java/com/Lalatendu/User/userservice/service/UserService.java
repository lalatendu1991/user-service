package com.Lalatendu.User.userservice.service;

import com.Lalatendu.User.userservice.bean.UserRequest;
import com.Lalatendu.User.userservice.bean.UserResponse;
import com.Lalatendu.User.userservice.bean.UserUpdateRequest;
import com.Lalatendu.User.userservice.entity.User;
import com.Lalatendu.User.userservice.exception.UserExcption;
import com.Lalatendu.User.userservice.repository.UserRepository;
import com.Lalatendu.User.userservice.util.DateUtil;
import javafx.scene.control.cell.MapValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private DateUtil dateUtil;

    public final String active = "ACTIVE";
    public final String inActive = "INACTIVE";

    @Autowired
    UserService(UserRepository userRepository,
                DateUtil dateUtil) {
        this.userRepository = userRepository;
        this.dateUtil = dateUtil;
    }

    public UserResponse addUser(UserRequest userBody) {
        LocalDate localDate = dateUtil.toLocalDate(userBody.getBirthDate());
        if (dateUtil.isFutureDate(localDate)) {
            throw new UserExcption("Please enter birth date on or before current date");
        }
        UserResponse userResponse = null;
        User userByEmail = userRepository.findByEmailAndStatus(userBody.getEmail(), active);
        if (!ObjectUtils.isEmpty(userByEmail)) {
            throw new UserExcption("Email already exist");
        }

        User user = User.builder()
                .firstName(userBody.getFName())
                .lastName(userBody.getLName())
                .email(userBody.getEmail())
                .pinCode(userBody.getPinCode())
                .birthDate(localDate)
                .status(active)
                .build();

        User newUser = userRepository.save(user);
        if (!ObjectUtils.isEmpty(newUser)) {
            userResponse = UserResponse.builder()
                    .resMsg("User created successfully")
                    .userId(newUser.getId())
                    .valErrors(null)
                    .build();
        } else {
            throw new RuntimeException("BAD USER");
        }

        return userResponse;
    }

    public User getUser(Long id) {
        User user = null;
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new UserExcption("User does not exist");
        }
        return user;
    }

    public UserResponse updateUserByEmail(UserRequest userRequest) {
        LocalDate localDate = dateUtil.toLocalDate(userRequest.getBirthDate());
        if (dateUtil.isFutureDate(localDate)) {
            throw new UserExcption("Please enter birth date on or before current date");
        }
        User userByEmail = userRepository.findByEmailAndStatus(userRequest.getEmail(), active);
        if (ObjectUtils.isEmpty(userByEmail)) {
            throw new UserExcption("User does not exist");
        }

        userByEmail.setBirthDate(localDate);
        userByEmail.setPinCode(userRequest.getPinCode());
        User user = userRepository.save(userByEmail);

        UserResponse userResponse = UserResponse.builder()
                .resMsg("User updated successfully")
                .userId(user.getId())
                .valErrors(null)
                .build();

        return userResponse;
    }

    public UserResponse updateUserById(UserUpdateRequest userUpdateRequestRequest) {
        User userById = null;
        LocalDate localDate = dateUtil.toLocalDate(userUpdateRequestRequest.getBirthDate());
        if (dateUtil.isFutureDate(localDate)) {
            throw new UserExcption("Please enter birth date on or before current date");
        }
        Optional<User> optionalUser = userRepository.findByIdAndStatus(userUpdateRequestRequest.getId(), active);

        if (optionalUser.isPresent()) {
            userById = optionalUser.get();
        } else {
            throw new UserExcption("User does not exist");
        }

        userById.setBirthDate(localDate);
        userById.setPinCode(userUpdateRequestRequest.getPinCode());
        User user = userRepository.save(userById);

        UserResponse userResponse = UserResponse.builder()
                .resMsg("User updated successfully")
                .userId(user.getId())
                .valErrors(null)
                .build();

        return userResponse;
    }

    public UserResponse deleteUser(Long id) {
        User userById = null;
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userById = optionalUser.get();
        } else {
            throw new UserExcption("User does not exist");
        }

        userById.setStatus(inActive);
        User user = userRepository.save(userById);

        UserResponse userResponse = UserResponse.builder()
                .resMsg("User deactivated successfully")
                .userId(user.getId())
                .valErrors(null)
                .build();

        return userResponse;
    }

    public List<User> getAllUsersBirthDateInCurrentMonth() {
        List<User> users = userRepository.findAll();
        if (ObjectUtils.isEmpty(users)) {
            throw new UserExcption("Please add user");
        }
        List<User> birthDateInCurrentMonthUsers = users.stream()
                .filter(user -> isBirthDateInCurrentMonth(user.getBirthDate()))
                .collect(Collectors.toList());
        if (ObjectUtils.isEmpty(birthDateInCurrentMonthUsers)) {
            throw new UserExcption("There is no users birth date in current month");
        }
        return birthDateInCurrentMonthUsers;
    }

    private boolean isBirthDateInCurrentMonth(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        Month todayMonth = today.getMonth();
        Month userBirthDateMonth = birthDate.getMonth();
        if (todayMonth.equals(userBirthDateMonth)) {
            return true;
        }
        return false;
    }

    public HashMap<Month, Integer> countBirthDateMonthWise() {
        List<User> users = userRepository.findAll();
        if (ObjectUtils.isEmpty(users)) {
            throw new UserExcption("Please add user");
        }
        HashMap<Month, Integer> hm = new HashMap<>();
        for (User user : users) {
            Month todayMonth = user.getBirthDate().getMonth();
            if (hm.containsKey(todayMonth)) {
                hm.put(todayMonth, hm.get(todayMonth) + 1);
            } else {
                hm.put(todayMonth, 1);
            }
        }
        return hm;
    }

}
