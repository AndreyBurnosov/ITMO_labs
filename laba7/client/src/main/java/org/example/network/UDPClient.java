package org.example.network;
import org.example.utility.Status;

import java.io.IOException;
import java.net.*;

public class UDPClient {
    private final int PACKET_SIZE = 1024*1024;
    private final DatagramSocket socket;
    private final InetSocketAddress address;

    public UDPClient(InetAddress host, int port, DatagramSocket socket) throws SocketException {
        this.socket = socket;
        this.address = new InetSocketAddress(host, port);
        this.socket.setSoTimeout(20000);

    }

    public void sendData(Request request) throws IOException {
        byte[] data = Serialisation.serializeObject(request);
        DatagramPacket packet = new DatagramPacket(data, data.length, address);
        socket.send(packet);
    }

    public Response readData() throws IOException, ClassNotFoundException {
        try {
            byte[] buffer = new byte[PACKET_SIZE];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        Response response = (Response) Serialisation.deserializeObject(packet.getData());
        return response;
        } catch (SocketTimeoutException e){
            return new Response(Status.ERROR, "Истекло время ожидания ответа от сервера.");
        }
    }

}
