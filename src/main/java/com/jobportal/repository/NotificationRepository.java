package com.jobportal.repository;

import com.jobportal.entity.Notification;
import com.jobportal.enums.NotificationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification,Long> {
    List<Notification> findByReceiverIdAndStatus(Long id, NotificationStatus status);
}
