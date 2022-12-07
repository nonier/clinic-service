package com.nonier.cliniccore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class UserDialogId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "dialog_id")
    private Long dialogId;
}