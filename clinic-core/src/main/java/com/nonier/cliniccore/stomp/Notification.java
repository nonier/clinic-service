package com.nonier.cliniccore.stomp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification<T> {

    private NotificationType type;
    private T body;
}