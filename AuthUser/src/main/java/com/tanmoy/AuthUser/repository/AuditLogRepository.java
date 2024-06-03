package com.tanmoy.AuthUser.repository;

import com.tanmoy.AuthUser.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
