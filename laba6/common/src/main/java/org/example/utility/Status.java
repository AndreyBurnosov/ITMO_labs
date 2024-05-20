package org.example.utility;

import java.io.Serializable;

public enum Status implements Serializable {
    OK,
    EXIT,
    WRONG_ARGUMENT,
    ASK_ORGANISATION,
    EXECUTE_SCRIPT,

    ERROR,
    ASK_ID,
    ASK_ORGANISATION_WITH_ID,
    ASK_ADDRESS

}
