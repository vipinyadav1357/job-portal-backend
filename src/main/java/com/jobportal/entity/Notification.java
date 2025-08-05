package com.jobportal.entity;

import com.jobportal.enums.NotificationStatus;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notification")
@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private Long id;
    //employer
    private Long senderId;
    //talent
    private Long receiverId;
    private String action;
    private String route;
    private String msg;
    private NotificationStatus status;
    private LocalDateTime sentTime;
}
