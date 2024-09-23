package org.example.managers;

import org.example.database.service.OrganisationService;
import org.example.models.Address;
import org.example.models.Organisation;
import org.example.models.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.max;

public class CollectionManager {

    private Collection<Organisation> collection = new ArrayList<>();
    private Date lastInitTime;
    private Date lastSaveTime;
    private final DumpManager dumpManager;

    private CollectionManager(DumpManager dumpManager) {
        lastInitTime = null;
        lastSaveTime = null;
        this.dumpManager = dumpManager;
    }

    private static CollectionManager instance = null;

    public static CollectionManager getInstance(DumpManager dumpManager) {
        return instance == null ? instance = new CollectionManager(dumpManager) : instance;
    }

    public Date getLastInitTime() {
        return lastInitTime;
    }

    public Date getLastSaveTime() {
        return lastSaveTime;
    }

    public Collection<Organisation> getCollection() {
        return collection;
    }

    public Organisation getCollectionById(Integer id) {
        return collection.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean add(Organisation organisation, User user) {
        var saved = OrganisationService.getInstance().save(organisation, user);
        if (saved == null) return false;
        collection.add(saved);
        return true;
    }

    public boolean addById(Integer id, Organisation org, User user) {
        if (id == null) id = 0;
        if (OrganisationService.getInstance().findById(id).isPresent()) return false;
        var saved = OrganisationService.getInstance().save(org, user);
        if (saved == null) return false;
        if (getCollectionById(id) != null) return false;
        collection.add(saved);
        return true;
    }

    @Deprecated
    public boolean saveCollection(DumpManager dumpManager) {
        System.err.println("deprecated method");
        return false;
    }

    public void loadCollection(DumpManager dumpManager) {
        collection = OrganisationService.getInstance().findAll();
        lastInitTime = new Date();
    }

    public String collectionType() {
        return collection.getClass().getName();
    }

    public int collectionSize() {
        return OrganisationService.getInstance().findAll().size();
    }

    public String printCollection() {
        return OrganisationService.getInstance().findAll().stream()
                .map(Organisation::toString)
                .collect(Collectors.joining("\n"));
    }

    public void removeById(Integer id, User user) {
        OrganisationService.getInstance().findById(id).ifPresent(item -> {
            OrganisationService.getInstance().remove(item, user);
            collection.remove(item);
        });
    }

    public void clearCollection(User user) {
        OrganisationService.getInstance().findAllByUserId(user).forEach(item -> OrganisationService.getInstance().remove(item, user));
    }

    public Integer getMaxValue() {
        return OrganisationService.getInstance().findAll().stream()
                .map(Organisation::getId)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public Organisation getFirstElement() {
        return OrganisationService.getInstance().findAll().stream()
                .findFirst()
                .orElse(null);
    }

    public boolean isEmpty() {
        return collection.isEmpty();
    }

    public void removeLower(Integer id, User user) {
        OrganisationService.getInstance().findAllByUserId(user).stream()
                .filter(item -> item.getId() < id)
                .forEach(item -> {
                    OrganisationService.getInstance().remove(item, user);
                    collection.remove(item);
                });
    }

    public float averageOfAnnualTurnove() {
        return (float) OrganisationService.getInstance().findAll().stream()
                .mapToDouble(Organisation::getAnnualTurnover)
                .average()
                .orElse(0);
    }

    public int compareAddress(Address postalAddress) {
        return OrganisationService.getInstance().findAll().stream()
                .map(Organisation::getPostalAddress)
                .filter(Objects::nonNull)
                .mapToInt(item -> item.compareTo(postalAddress))
                .sum();
    }

    public Object[] employessCount() {
        return OrganisationService.getInstance().findAll().stream()
                .map(Organisation::getEmployeesCount)
                .toArray();
    }
}
