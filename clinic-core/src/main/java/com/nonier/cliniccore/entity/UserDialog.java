package com.nonier.cliniccore.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@EqualsAndHashCode(exclude = "dialog")
@ToString(exclude = "dialog")
@Table(name = "user_dialog")
public class UserDialog {

    @EmbeddedId
    private UserDialogId userDialogId = new UserDialogId();

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @MapsId("dialogId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dialog_id", referencedColumnName = "id")
    private Dialog dialog;

}