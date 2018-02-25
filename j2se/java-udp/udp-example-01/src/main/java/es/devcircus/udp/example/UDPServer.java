package es.devcircus.udp.example;

/**
 * This file is part of Java Examples.
 *
 * Java Examples is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2, or (at your option) any later version.
 *
 * Java Examples is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; see the file COPYING. If not, see
 * <http://www.gnu.org/licenses/>.
 */
import es.devcircus.udp.example.data.InventoryCollection;
import java.io.*;
import java.net.*;

/**
 * The Class UDPServer responds to client requests with inventory information.
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public final class UDPServer {

    // The resource list of computers.
    private static InventoryCollection inventoryList;
    // The udp server socket.
    private DatagramSocket udpServerSocket;
    // udpPacketIN is the packet received from client
    // udpPacketOUT is the packet sent to the server 
    private DatagramPacket udpPacketIN, udpPacketOUT;
    // The messages sent to and received from the client 
    private String msgFromClient, msgToClient;
    // Maintain connection?
    private boolean morePackets;
    // Buffer for receiving messages
    private byte[] buffIn;
    // Buffer for sending messages
    private byte[] buffOut;

    /**
     * The main method creates a new UDP server.
     *
     * @param args
     */
    public static void main(String[] args) {
        UDPServer udpServer = new UDPServer();
    }

    /**
     * Instantiates a new UDP server and initializes variables.
     */
    public UDPServer() {
        inventoryList = new InventoryCollection();
        morePackets = true;
        udpPacketIN = null;
        udpPacketOUT = null;
        msgFromClient = null;
        msgToClient = null;
        buffIn = new byte[256];
        buffOut = new byte[256];
        // Creamos el socket
        try {
            udpServerSocket = new DatagramSocket(5678);
        } catch (SocketException e1) {
            System.out.println("Unable to create DatagramSocket.");
            e1.printStackTrace();
        }
        startServer();
    }

    /**
     * Arrancamos el servidor.
     */
    public void startServer() {
        System.out.println("\nServer started...");
        System.out.println("Press 'control+c' at any time to close the server\n");
        // Mientras no terminemos, ejecutamos el contenido del bucle constantemente.
        while (morePackets) {
            try {
                // Receive UDP packet from client
                udpPacketIN = new DatagramPacket(buffIn, buffIn.length);
                udpServerSocket.receive(udpPacketIN);
                msgFromClient = new String(udpPacketIN.getData(), 0, udpPacketIN.getLength());
                System.out.println("New request from " + udpPacketIN.getSocketAddress() + ": '" + msgFromClient + "' \n");
                // Use client msg to decide what to do
                switch (msgFromClient) {
                    case "initialize":
                        System.out.println("Client: " + udpPacketIN.getSocketAddress() + " connected.\n");
                        msgToClient = inventoryList.toString();
                        break;
                    case "exit":
                        System.out.println("Client: " + udpPacketIN.getSocketAddress() + " disconnected.\n");
                        break;
                    default:
                        // compare client input against item id's in Inventory
                        msgToClient = inventoryList.getItem(msgFromClient);
                        System.out.println("Response to " + udpPacketIN.getSocketAddress() + ": \n" + msgToClient + " \n\n");
                        break;
                }
                // send the response to the client at address and port
                InetAddress address = udpPacketIN.getAddress();
                int port = udpPacketIN.getPort();
                buffOut = msgToClient.getBytes();
                udpPacketOUT = new DatagramPacket(buffOut, buffOut.length, address, port);
                udpServerSocket.send(udpPacketOUT);
            } catch (IOException e) {
                e.printStackTrace();
                morePackets = false;
            }
        }
        udpServerSocket.close();
    }
}
