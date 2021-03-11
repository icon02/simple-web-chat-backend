package io.github.icon02.simplewebchatbackend.controller;

import io.github.icon02.simplewebchatbackend.entity.ProfileImage;
import io.github.icon02.simplewebchatbackend.entity.User;
import io.github.icon02.simplewebchatbackend.payload.ContactPayload;
import io.github.icon02.simplewebchatbackend.payload.LoginPayload;
import io.github.icon02.simplewebchatbackend.service.UserService;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam(required = false) MultipartFile image) {
        User user = userService.login(username, image);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/contacts")
    public ResponseEntity<?> getContacts(@PathVariable Long userId) {
       List<ContactPayload> contacts = userService.getContactsForUserId(userId);
       return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{userId}/profile-image")
    public ResponseEntity<?> getProfileImage(@PathVariable Long userId) {
        ProfileImage pImage = userService.getProfileImageForUser(userId);
        // pImage.setData(Base64.getDecoder().decode(pImage.getData()));
        return ResponseEntity.ok(pImage);
    }

    @GetMapping("/{userId}/profile-image-thumbnail")
    public ResponseEntity<?> getProfileImageThumbnail(@PathVariable Long userId) {
        ProfileImage pImage = userService.getProfileImageThumbnailForUser(userId);

        return ResponseEntity.ok(pImage);
    }


}
