package org.example.network;

import org.example.models.Address;
import org.example.models.Organisation;
import org.example.models.User;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    private String command;
    private String argument;
    private Address address;
    private Organisation organisation;

    private String username;
    private String password;
    private transient User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Request (String command){
        this.command = command;
    }
    public Request (String command, String argument){
        this.command = command;
        this.argument = argument;
    }

    public Request(String command, String argument, String username, String password) {
        this.command = command;
        this.argument = argument;
        this.username = username;
        this.password = password;
    }

    public Request (String command, Address address){
        this.command = command;
        this.address = address;
    }

    public Request(String command, Address address, String username, String password) {
        this.command = command;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public Request (String command, String argument, Organisation organisation){
        this.command = command;
        this.argument = argument;
        this.organisation = organisation;
    }

    public Request(String command, String argument, Organisation organisation, String username, String password) {
        this.organisation = organisation;
        this.username = username;
        this.password = password;
        this.command = command;
        this.argument = argument;
    }

    public String getCommand(){ return command;}
    public String getArgument(){ return argument;}
    public Address getAddress() { return address;}
    public Organisation getOrganisation(){return organisation;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request response = (Request) o;
        return Objects.equals(command, response.command);
    }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", argument='" + argument + '\'' +
                ", address=" + address +
                ", organisation=" + organisation +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                '}';
    }
}
