package com.jobportal.dtos;

import com.jobportal.enums.NotificationStatus;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class NotificationDto {
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
