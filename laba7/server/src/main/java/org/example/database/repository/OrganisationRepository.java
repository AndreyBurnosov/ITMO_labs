package org.example.database.repository;

import org.example.models.Organisation;
import org.example.models.User;

import java.util.List;
import java.util.Optional;

public interface OrganisationRepository {
    Organisation save(Organisation organisation, User user);
    Organisation insert(Organisation organisation, User user);
    void remove(Organisation organisation, User user);
    void removeAllByUser(User user);
    List<Organisation> findAllByUserId(User user);
    List<Organisation> findAll();
    Optional<Organisation> findById(int id);
    Organisation update(Organisation organisation, User user);
}
