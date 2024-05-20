package org.example.network;

import org.example.models.Address;
import org.example.models.Organisation;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    private String command;
    private String argument;
    private Address address;
    private Organisation organisation;
    public Request (String command){
        this.command = command;
    }
    public Request (String command, String argument){
        this.command = command;
        this.argument = argument;
    }
    public Request (String command, Address address){
        this.command = command;
        this.address = address;
    }
    public Request (String command, String argument, Organisation organisation){
        this.command = command;
        this.argument = argument;
        this.organisation = organisation;
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
}
