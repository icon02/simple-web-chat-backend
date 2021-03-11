package io.github.icon02.simplewebchatbackend.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageOutPayload {
    private Long fromUserId;
    private String message;
    private String attachmentName;
    private String attachmentDownloadLink;

}
