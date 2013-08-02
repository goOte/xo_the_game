import java.io.*;
import java.net.*;
import java.util.Scanner;

public class InfoSender {

    public void SendInfoIpAddress(String ip_address, String ip_to_send) throws IOException {

        DatagramSocket dgsocket = new DatagramSocket();
        DatagramPacket datapack;
        InetAddress destination;

        destination = InetAddress.getByName(ip_address);

        byte[] dt = ip_to_send.getBytes();

        datapack = new DatagramPacket(dt, dt.length, destination, 1212);
        dgsocket.send(datapack);

        dgsocket.close();
    }

    public void SendInfoPlayerChar(String ip_address, char player_char) throws IOException {

        DatagramSocket dgsocket = new DatagramSocket();
        DatagramPacket datapack;
        InetAddress destination;

        destination = InetAddress.getByName(ip_address);

        String player_char_str = "" + player_char;

        byte[] dt = player_char_str.getBytes();

        datapack = new DatagramPacket(dt, dt.length, destination, 1213);
        dgsocket.send(datapack);

        dgsocket.close();
    }

    public void SendInfoPlayerMove(String ip_address, int port_index, char player_char, int row_number, int line_number) throws IOException {

        DatagramSocket dgsocket = new DatagramSocket();
        DatagramPacket datapack;
        InetAddress destination;
        int port = 1214 + port_index;

        destination = InetAddress.getByName(ip_address);

        // send player_char
        String player_char_str = "" + player_char;
        byte[] dt = player_char_str.getBytes();
        datapack = new DatagramPacket(dt, dt.length, destination, port);
        dgsocket.send(datapack);

        //send row_number
        String row_number_str = Integer.toString(row_number);
        byte[] row = row_number_str.getBytes();
        datapack = new DatagramPacket(row, row.length, destination, port);
        dgsocket.send(datapack);

        //send line_number
        String line_number_str = Integer.toString(line_number);
        byte[] line = line_number_str.getBytes();
        datapack = new DatagramPacket(line, line.length, destination, port);
        dgsocket.send(datapack);

        dgsocket.close();
    }
}
