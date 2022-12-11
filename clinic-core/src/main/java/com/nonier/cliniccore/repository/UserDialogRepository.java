package com.nonier.cliniccore.repository;

import com.nonier.cliniccore.entity.UserDialog;
import com.nonier.cliniccore.entity.UserDialogId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDialogRepository extends JpaRepository<UserDialog, UserDialogId> {
}
