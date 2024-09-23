package org.example.database.service;

import org.example.database.DatabaseConnection;
import org.example.database.query.Queries;
import org.example.database.repository.UserRepository;
import org.example.models.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class UserService implements UserRepository {
    private static UserService instance;

    private final ReentrantLock readLock = new ReentrantLock();
    private final ReentrantLock writeLock = new ReentrantLock();

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public void save(String username, String password) {
        writeLock.lock();
        try (PreparedStatement query = DatabaseConnection.getInstance().prepareStatement(Queries.SAVE_USER)) {
            query.setString(1, username);
            query.setString(2, hashPassword(password));

            query.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        readLock.lock();
        try (PreparedStatement query = DatabaseConnection.getInstance().prepareStatement(Queries.EXISTS_BY_USERNAME)) {
            query.setString(1, username);

            try (ResultSet result = query.executeQuery()) {
                return result.next() && result.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        readLock.lock();
        try (PreparedStatement query = DatabaseConnection.getInstance().prepareStatement(Queries.FIND_BY_ID)) {
            query.setLong(1, id);

            try (ResultSet result = query.executeQuery()) {
                return map(result);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        readLock.lock();
        try (PreparedStatement query = DatabaseConnection.getInstance().prepareStatement(Queries.FIND_BY_USERNAME)) {
            query.setString(1, username);

            try (ResultSet result = query.executeQuery()) {
                return map(result);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean checkPassword(User user, String password) {
        readLock.lock();
        try {
            return hashPassword(password).equals(user.getPassword());
        } finally {
            readLock.unlock();
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) sb.append(String.format("%02x", b));

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private Optional<User> map(ResultSet result) {
        try {
            return result.next() ?
                    Optional.of(new User(result.getLong("id"), result.getString("username"), result.getString("password")))
                    : Optional.empty();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }
}