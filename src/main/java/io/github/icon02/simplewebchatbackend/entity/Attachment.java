package io.github.icon02.simplewebchatbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Attachment {

    private String name;
    private String fileType;
    @Column(columnDefinition = "BLOB")
    private byte[] data;
}
