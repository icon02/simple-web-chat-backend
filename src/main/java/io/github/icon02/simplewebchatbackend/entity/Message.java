package io.github.icon02.simplewebchatbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue
    private Long id;
    private Long fromUserId;
    private Long toUserId;
    private String message;
    private Date timestamp;
    @Embedded
    @Nullable
    private Attachment attachment;
}
