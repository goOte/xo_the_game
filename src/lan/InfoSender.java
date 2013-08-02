package lan;

import java.io.IOException;
import java.net.*;


public class InfoSender {

    public void SendInfoIpAddress(String ip_address, String ip_to_send) {

        DatagramSocket dgsocket = null;
        try {
            dgsocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        DatagramPacket datapack;
        InetAddress destination = null;

        try {
            destination = InetAddress.getByName(ip_address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        byte[] dt = ip_to_send.getBytes();

        datapack = new DatagramPacket(dt, dt.length, destination, 1212);
        if (dgsocket != null) {
            try {
                dgsocket.send(datapack);
                System.out.println("*** Данные соединения отправлены на " + ip_address +" ***\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dgsocket.close();
    }

    public void SendInfoPlayerChar(String ip_address, char player_char) {

        DatagramSocket dgsocket = null;
        try {
            dgsocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        DatagramPacket datapack;
        InetAddress destination = null;

        try {
            destination = InetAddress.getByName(ip_address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        String player_char_str = "" + player_char;

        byte[] dt = player_char_str.getBytes();

        datapack = new DatagramPacket(dt, dt.length, destination, 1213);
        if (dgsocket != null) {
            try {
                dgsocket.send(datapack);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dgsocket.close();
    }

    public void SendInfoPlayerMove(String ip_address, int port_index, char player_char, int row_number, int line_number) {

        DatagramSocket dgsocket = null;
        try {
            dgsocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        DatagramPacket datapack;
        InetAddress destination = null;
        int port = 1214 + port_index;

        try {
            destination = InetAddress.getByName(ip_address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // send player_char
        String player_char_str = "" + player_char;
        byte[] dt = player_char_str.getBytes();
        datapack = new DatagramPacket(dt, dt.length, destination, port);
        if (dgsocket != null) {
            try {
                dgsocket.send(datapack);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //send row_number
        String row_number_str = Integer.toString(row_number);
        byte[] row = row_number_str.getBytes();
        datapack = new DatagramPacket(row, row.length, destination, port);
        if (dgsocket != null) {
            try {
                dgsocket.send(datapack);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //send line_number
        String line_number_str = Integer.toString(line_number);
        byte[] line = line_number_str.getBytes();
        datapack = new DatagramPacket(line, line.length, destination, port);
        if (dgsocket != null) {
            try {
                dgsocket.send(datapack);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dgsocket.close();
    }
}
