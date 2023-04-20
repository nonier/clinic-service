package com.nonier.cliniccore.repository;

import com.nonier.cliniccore.entity.Role;
import com.nonier.cliniccore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("""
    select userRole.role
    from UserRole userRole
    where userRole.user.id = :userId
            """)
    List<Role> getRolesByUser(Long userId);
}