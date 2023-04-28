package com.nonier.cliniccore.service.impl;

import com.nonier.cliniccore.entity.Dialog;
import com.nonier.cliniccore.entity.User;
import com.nonier.cliniccore.entity.UserDialog;
import com.nonier.cliniccore.service.NotificationService;
import com.nonier.cliniccore.stomp.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private static final String CHANNEL_PREFIX = "/notifications";

    private final SimpMessagingTemplate template;

    @Override
    public void sendNotificationToUsers(Notification<?> notification, Dialog dialog) {
        dialog.getUserDialogs().stream()
                .map(UserDialog::getUser)
                .map(User::getId)
                .forEach(id -> {
                    template.convertAndSend(CHANNEL_PREFIX + "/%d" .formatted(id),
                            notification);
                });
    }
}