package org.example.commands.abstracts;

import org.example.network.Request;
import org.example.network.Response;

public interface Executable {
    Response execute(Request request);
}
