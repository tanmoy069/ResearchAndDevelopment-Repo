package com.tanmoy.AuthUser.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = AuditLog.COLLECTION_NAME)
public class AuditLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String COLLECTION_NAME = "audit_log";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "username")
    private String username;

    @Column(name = "request_url", nullable = false)
    private String requestUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_method")
    private RequestMethod requestMethod;

    @Column(name = "previous_object", columnDefinition = "longtext")
    private String previousObject;

    @Column(name = "new_object", columnDefinition = "longtext")
    private String newObject;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date().toInstant();
    }

}
