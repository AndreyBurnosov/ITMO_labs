package org.example.managers;

import org.example.models.Address;
import org.example.models.Organisation;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.max;

public class CollectionManager {

    private Collection<Organisation> collection = new PriorityQueue<>();
    private Date lastInitTime;
    private Date lastSaveTime;
    private final DumpManager dumpManager;

    public CollectionManager(DumpManager dumpManager){
        lastInitTime = null;
        lastSaveTime = null;
        this.dumpManager = dumpManager;
    }

    public Date getLastInitTime(){ return lastInitTime;}

    public Date getLastSaveTime(){return lastSaveTime;}

    public Collection<Organisation> getCollection(){return collection;}

    public Organisation getCollectionById(Integer id){
        return collection.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean add(Organisation organisation){
        organisation.setId(Organisation.getNextId());
        collection.add(organisation);
        return true;
    }

    public boolean addById(Integer id, Organisation org){
        if (getCollectionById(id) != null) {
            return false;
        }
        org.setId(id);
        collection.add(org);
        return true;
    }

    public boolean saveCollection(DumpManager dumpManager){
        if (dumpManager.writeCollection(collection)){
            lastSaveTime = new Date();
            return true;
        }
        return false;
    }

    public void loadCollection(DumpManager dumpManager){
        collection = dumpManager.readCollection();
        lastInitTime = new Date();
    }

    public String collectionType(){
        return collection.getClass().getName();
    }

    public int collectionSize(){
        return collection.size();
    }

    public String printCollection(){
        return collection.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public void removeById(Integer id){
        collection.removeIf(item -> item.getId().equals(id));
    }

    public void clearCollection(){
        collection.clear();
    }

    public Integer getMaxValue(){
        return collection.stream()
                .mapToInt(Organisation::getId)
                .max()
                .orElse(0);
    }

    public Organisation getFirstElement(){
        return collection.stream()
                .findFirst()
                .orElse(null);
    }

    public boolean isEmpty(){
        return collection.isEmpty();
    }

    public void removeLower(Integer id){
        collection.removeIf(item -> item.getId() < id);
    }

    public float averageOfAnnualTurnove(){
        return (float) collection.stream()
                .mapToDouble(Organisation::getAnnualTurnover)
                .average()
                .orElse(0.0);
    }

    public int compareAddress(Address postalAddress){
        return (int) collection.stream()
                .filter(item -> item.getPostalAddress().equals(postalAddress))
                .count();
    }

    public Object[] employessCount(){
        return collection.stream()
                .map(Organisation::getEmployeesCount)
                .sorted(Comparator.reverseOrder())
                .toArray();
    }
}
