package com.jobportal.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.swing.text.Utilities;

@Document
@Data
public class Sequence {
    @Id
    private String id;
    private Long seq;
}
