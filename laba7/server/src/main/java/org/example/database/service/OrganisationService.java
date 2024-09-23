package org.example.database.service;

import org.example.database.DatabaseConnection;
import org.example.database.query.Queries;
import org.example.database.repository.OrganisationRepository;
import org.example.models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class OrganisationService implements OrganisationRepository {
    private static OrganisationService instance;

    private final ReentrantLock readLock = new ReentrantLock();
    private final ReentrantLock writeLock = new ReentrantLock();

    private OrganisationService() {
    }

    public static OrganisationService getInstance() {
        return instance == null ? instance = new OrganisationService() : instance;
    }

    @Override
    public Organisation insert(Organisation organisation, User user) {
        writeLock.lock();
        try {
            organisation.initSub();
            try (PreparedStatement insertOrganisationStmt = DatabaseConnection.getInstance().getConnection().prepareStatement(Queries.INSERT_ORGANISATION, PreparedStatement.RETURN_GENERATED_KEYS)) {
                insertOrganisationStmt.setString(1, organisation.getName());
                insertOrganisationStmt.setLong(2, user.getId());
                insertOrganisationStmt.setInt(3, organisation.getCord_x());
                insertOrganisationStmt.setDouble(4, organisation.getCord_y());
                insertOrganisationStmt.setTimestamp(5, Timestamp.from(organisation.getCreationDate().toInstant()));
                insertOrganisationStmt.setDouble(6, organisation.getAnnualTurnover());
                insertOrganisationStmt.setLong(7, organisation.getEmployeesCount());
                insertOrganisationStmt.setString(8, organisation.getType().name());
                insertOrganisationStmt.setString(9, organisation.getStreet());
                insertOrganisationStmt.setInt(10, organisation.getTown_x());
                insertOrganisationStmt.setFloat(11, organisation.getTown_y());
                insertOrganisationStmt.setInt(12, organisation.getTown_z());
                insertOrganisationStmt.setInt(13, organisation.getId());

                insertOrganisationStmt.executeUpdate();

                try (ResultSet keys = insertOrganisationStmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        organisation.setId(keys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            writeLock.unlock();
        }
        return organisation;
    }

    @Override
    public Organisation save(Organisation organisation, User user) {
        writeLock.lock();
        try {
            organisation.initSub();
            try (PreparedStatement saveOrganisationStmt = DatabaseConnection.getInstance().getConnection().prepareStatement(Queries.SAVE_ORGANISATION, PreparedStatement.RETURN_GENERATED_KEYS)) {
                saveOrganisationStmt.setString(1, organisation.getName());
                saveOrganisationStmt.setLong(2, user.getId());
                saveOrganisationStmt.setInt(3, organisation.getCord_x());
                saveOrganisationStmt.setDouble(4, organisation.getCord_y());
                saveOrganisationStmt.setTimestamp(5, Timestamp.from(organisation.getCreationDate().toInstant()));
                saveOrganisationStmt.setDouble(6, organisation.getAnnualTurnover());
                saveOrganisationStmt.setLong(7, organisation.getEmployeesCount());
                saveOrganisationStmt.setString(8, organisation.getType().name());
                saveOrganisationStmt.setString(9, organisation.getStreet());
                saveOrganisationStmt.setInt(10, organisation.getTown_x());
                saveOrganisationStmt.setFloat(11, organisation.getTown_y());
                saveOrganisationStmt.setInt(12, organisation.getTown_z());

                saveOrganisationStmt.executeUpdate();

                try (ResultSet keys = saveOrganisationStmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        organisation.setId(keys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            writeLock.unlock();
        }
        return organisation;
    }

    @Override
    public Organisation update(Organisation organisation, User user) {
        writeLock.lock();
        try {
            organisation.initSub();
            try (PreparedStatement updateOrganisationStmt = DatabaseConnection.getInstance().getConnection().prepareStatement(Queries.UPDATE_ORGANISATION)) {
                updateOrganisationStmt.setString(1, organisation.getName());
                updateOrganisationStmt.setInt(2, organisation.getCord_x());
                updateOrganisationStmt.setDouble(3, organisation.getCord_y());
                updateOrganisationStmt.setObject(4, organisation.getCreationDate());
                updateOrganisationStmt.setDouble(5, organisation.getAnnualTurnover());
                updateOrganisationStmt.setLong(6, organisation.getEmployeesCount());
                updateOrganisationStmt.setString(7, organisation.getType().name());
                updateOrganisationStmt.setString(8, organisation.getStreet());
                updateOrganisationStmt.setInt(9, organisation.getTown_x());
                updateOrganisationStmt.setFloat(10, organisation.getTown_y());
                updateOrganisationStmt.setInt(11, organisation.getTown_z());
                updateOrganisationStmt.setInt(12, organisation.getId());
                updateOrganisationStmt.setLong(13, user.getId());

                updateOrganisationStmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            writeLock.unlock();
        }
        return organisation;
    }

    @Override
    public void remove(Organisation organisation, User user) {
        writeLock.lock();
        try {
            organisation.initSub();
            try (PreparedStatement query = DatabaseConnection.getInstance().getConnection().prepareStatement(Queries.DELETE_ORGANISATION_BY_ID_AND_USER)) {
                query.setInt(1, organisation.getId());
                query.setLong(2, user.getId());

                query.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void removeAllByUser(User user) {
        writeLock.lock();
        try {
            try (PreparedStatement query = DatabaseConnection.getInstance().getConnection().prepareStatement(Queries.DELETE_ALL_ORGANISATIONS_BY_USER)) {
                query.setLong(1, user.getId());

                query.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public List<Organisation> findAllByUserId(User user) {
        readLock.lock();
        List<Organisation> organisations = new ArrayList<>();
        try {
            try (PreparedStatement query = DatabaseConnection.getInstance().getConnection().prepareStatement(Queries.FIND_ALL_ORGANISATIONS_BY_USER)) {
                query.setLong(1, user.getId());

                try (ResultSet result = query.executeQuery()) {
                    while (result.next()) {
                        organisations.add(map(result));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            readLock.unlock();
        }
        return organisations;
    }

    @Override
    public List<Organisation> findAll() {
        readLock.lock();
        List<Organisation> organisations = new ArrayList<>();
        try {
            try (PreparedStatement query = DatabaseConnection.getInstance().getConnection().prepareStatement(Queries.FIND_ALL_ORGANISATIONS)) {

                try (ResultSet result = query.executeQuery()) {
                    while (result.next()) {
                        organisations.add(map(result));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            readLock.unlock();
        }
        return organisations;
    }

    @Override
    public Optional<Organisation> findById(int id) {
        readLock.lock();
        try {
            try (PreparedStatement query = DatabaseConnection.getInstance().getConnection().prepareStatement(Queries.FIND_ORGANISATION_BY_ID)) {
                query.setInt(1, id);

                try (ResultSet result = query.executeQuery()) {
                    if (result.next()) {
                        return Optional.of(map(result));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            readLock.unlock();
        }
        return Optional.empty();
    }

    private Organisation map(ResultSet result) throws SQLException {
        Organisation organisation = new Organisation(
                result.getString("name"),
                new Coordinates(result.getInt("cord_x"), result.getDouble("cord_y")),
                result.getDouble("annual_turnover"),
                result.getLong("employees_count"),
                OrganizationType.valueOf(result.getString("organisation_type")),
                new Address(result.getString("street"), new Location(
                        result.getInt("town_x"),
                        result.getFloat("town_y"),
                        result.getInt("town_z")
                ))
        );
        organisation.setId(result.getInt("id"));
        organisation.setOwnerId(result.getLong("owner_id"));
        organisation.setCreationDate(result.getTimestamp("creation_date"));
        return organisation;
    }
}