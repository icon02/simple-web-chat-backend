package io.github.icon02.simplewebchatbackend.service;

import io.github.icon02.simplewebchatbackend.entity.ProfileImage;
import io.github.icon02.simplewebchatbackend.entity.User;
import io.github.icon02.simplewebchatbackend.payload.ContactPayload;
import io.github.icon02.simplewebchatbackend.repository.ProfileImageRepository;
import io.github.icon02.simplewebchatbackend.repository.UserRepository;
import io.github.icon02.simplewebchatbackend.util.ImageDownSizeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProfileImageRepository profileImageRepository;
    private final ImageDownSizeUtil imageDownSizeUtil;

    @Autowired
    public UserService(UserRepository userRepository, ProfileImageRepository profileImageRepository, ImageDownSizeUtil imageDownSizeUtil) {
        this.userRepository = userRepository;
        this.profileImageRepository = profileImageRepository;
        this.imageDownSizeUtil = imageDownSizeUtil;
    }

    public User login(String username, MultipartFile file) {
        User user = userRepository.getByUsername(username);

        if (user == null) {
            user = new User();
            user.setUsername(username);
            user = userRepository.save(user);
        }

        if(file != null) {
            ProfileImage newImage = new ProfileImage();
            newImage.setUserId(user.getId());
            newImage.setName(file.getOriginalFilename());
            newImage.setType(file.getContentType());
            imageDownSizeUtil.createImageData(newImage, file);
            profileImageRepository.save(newImage);
        }

        return user;
    }

    public List<ContactPayload> getContactsForUserId(Long userId) {
        List<User> userContacts = userRepository.getContacts(userId);
        List<ContactPayload> contacts = new ArrayList<>();

        for(User u : userContacts) {
            ProfileImage image = getProfileImageForUser(u.getId());
            contacts.add(new ContactPayload(u, image));
        }

        return contacts;
    }



    public ProfileImage getProfileImageForUser(Long userId) {
        ProfileImage image = profileImageRepository.getForUser(userId);

        return image;
    }

    public ProfileImage getProfileImageThumbnailForUser(Long userId) {
        ProfileImage image = profileImageRepository.getForUser(userId);
        if(image != null) image.setData(image.getThumbnail());

        return image;
    }

    @PostConstruct
    private void init() {
        userRepository.save(new User(1L, "icon02"));
        userRepository.save(new User(2L, "Max2"));
        userRepository.save(new User(3L, "User49"));
        userRepository.save(new User(4L, "test231"));
        userRepository.save(new User(5L, "greta44"));
    }
}
