package com.nonier.cliniccore.specification;

import com.nonier.cliniccore.entity.Doctor;
import com.nonier.cliniccore.entity.DoctorSpecialization;
import com.nonier.cliniccore.entity.Specialization;
import com.nonier.cliniccore.entity.User;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DoctorSpecification implements Specification<Doctor> {

    private final Optional<String> name;
    private final List<Long> specializationIds;

    @Override
    public Predicate toPredicate(Root<Doctor> doctor, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        name.ifPresent(name -> {
            Join<Doctor, User> user = doctor.join("user");
            predicates.add(
                    cb.like(cb.lower(cb.concat(cb.concat(user.get("name"), cb.literal(" ")), user.get("surname"))),
                            "%" + name.toLowerCase() + "%"));
        });

        if (!specializationIds.isEmpty()) {
            Join<Doctor, DoctorSpecialization> doctorSpecializations = doctor.join("doctorSpecializations");
            Join<DoctorSpecialization, Specialization> specialization =
                    doctorSpecializations.join("specialization");
            predicates.add(
                    specialization.get("id").in(specializationIds)
            );
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
