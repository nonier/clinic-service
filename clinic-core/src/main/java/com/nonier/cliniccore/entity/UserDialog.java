package com.nonier.cliniccore.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "dialog")
@ToString(exclude = "dialog")
@Table(name = "user_dialog")
public class UserDialog {

    @EmbeddedId
    @Builder.Default
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