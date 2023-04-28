package com.nonier.cliniccore.service;

import com.nonier.cliniccore.entity.Dialog;
import com.nonier.cliniccore.stomp.Notification;

public interface NotificationService {

    void sendNotificationToUsers(Notification<?> notification, Dialog dialog);
}