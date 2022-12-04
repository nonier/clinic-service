package com.nonier.cliniccore.repository;

import com.nonier.cliniccore.entity.UserRole;
import com.nonier.cliniccore.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

}
