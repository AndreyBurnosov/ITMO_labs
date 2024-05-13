package org.example.managers;

import org.example.models.Address;
import org.example.models.Organisation;

import java.util.*;

import static java.lang.Math.*;

public class CollectionManager {

    private Collection<Organisation> collection = new PriorityQueue<>();
    private java.util.Date lastInitTime;
    private java.util.Date lastSaveTime;
    private final DumpManager dumpManager;
    private ConsoleManager console;

    public CollectionManager(DumpManager dumpManager){
        lastInitTime = null;
        lastSaveTime = null;
        this.dumpManager = dumpManager;
    }

    public java.util.Date getLastInitTime(){ return lastInitTime;}

    public java.util.Date getLastSaveTime(){return lastSaveTime;}

    public Collection<Organisation> getCollection(){return collection;}

    public Organisation getCollectionById(Integer id){
        if (collection.isEmpty()) return null;
        for (Organisation item : collection) {
            if (item.getId() == id) { return item; }
        }
        return null;
    }

    public boolean add(Organisation organisation){
        collection.add(organisation);
        return true;
    }

    public boolean addById(Integer id, Organisation org){
        if (getCollectionById(id) != null) {return false;}
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

    public void printCollection(ConsoleManager console){
        for (Organisation item : collection) {
            console.println(item);
        }
    }

    public void removeById(Integer id){
        if (getCollectionById(id) != null) collection.remove(getCollectionById(id));
    }
    public void clearCollection(){
        collection.clear();
    }
    public Integer getMaxValue(){
        int maxId = 0;
        for (Organisation item : collection) {
            maxId = max(maxId, item.getId());
        }
        return maxId;
    }

    public Organisation getFirstElement(){
        if (collection.isEmpty()) return null;
        return collection.iterator().next();
    }

    public boolean isEmpty(){
        return collection.isEmpty();
    }

    public void removeLower(Integer id){
        LinkedList <Organisation> del = new LinkedList<>();
        for (Organisation item : collection) {
            if (item.getId() < id) {
                del.add(item);
            }
        }
        for (Organisation item : del) {
            collection.remove(item);
        }
    }

    public float averageOfAnnualTurnove(){
        float average = 0;
        for (Organisation item : collection) {
            average += item.getAnnualTurnover();
        }
        return average / (float)collection.size();
    }

    public int compareAddress(Address postalAddress){
        int count = 0;
        for (Organisation item : collection) {
            if (item.getPostalAddress().equals(postalAddress)) {
                count++;
            }
        }
        return count;
    }

    public Object[] employessCount(){
        LinkedList <Long> employessCount = new LinkedList<>();
        for (Organisation item : collection) {
            employessCount.add(item.getEmployeesCount());
        }
        employessCount.sort(Comparator.reverseOrder());
        return employessCount.toArray();
    }


}
