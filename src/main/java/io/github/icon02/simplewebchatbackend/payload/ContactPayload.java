package io.github.icon02.simplewebchatbackend.payload;

import io.github.icon02.simplewebchatbackend.entity.ProfileImage;
import io.github.icon02.simplewebchatbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactPayload {
    private Long id;
    private String username;
    private ProfileImage thumbnail;

    public ContactPayload(User user, ProfileImage profileImage) {
        this.id = user.getId();
        this.username=user.getUsername();
        if(profileImage != null) profileImage.setData(profileImage.getThumbnail());
        this.thumbnail = profileImage;
    }
}
