package lan;

import java.net.*;
import java.io.*;

public class InfoReceiver {

    public String ReceiveInfoIpAddress() {

        byte data[] = new byte[131072];
        DatagramSocket dgsocket = null;
        try {
            dgsocket = new DatagramSocket(1212);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        DatagramPacket rcvpack;

        System.out.println("Ожидание соединения.......\n");
        rcvpack = new DatagramPacket(data, data.length);

        if (dgsocket != null) {
            try {
                dgsocket.receive(rcvpack);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String ip_address = new String(rcvpack.getData());
        ip_address = ip_address.substring(0, rcvpack.getLength());

        System.out.println("*** Вы успешно подключились к " + ip_address + " ***\n");

        return ip_address;

    }

    public char ReceiveInfoPlayerChar() {

        byte data[] = new byte[131072];
        DatagramSocket dgsocket = null;
        try {
            dgsocket = new DatagramSocket(1213);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        DatagramPacket rcvpack;

        System.out.println("Ожидание соединения.......\n");
        rcvpack = new DatagramPacket(data, data.length);

        if (dgsocket != null) {
            try {
                dgsocket.receive(rcvpack);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String player_char_str = new String(rcvpack.getData());
        player_char_str = player_char_str.substring(0, rcvpack.getLength());

        System.out.println("Соединение установлено...\n");

        char player_char = player_char_str.charAt(0);

        return player_char;

    }

    public String[] ReceiveInfoPlayerMove(int port_index) {

        int port = 1214 + port_index;
        byte data[] = new byte[131072];
        DatagramSocket dgsocket = null;
        try {
            dgsocket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        DatagramPacket rcvpack;

        System.out.println("Ожидание соединения.......\n");

        rcvpack = new DatagramPacket(data, data.length);

        //Get player_char
        if (dgsocket != null) {
            try {
                dgsocket.receive(rcvpack);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String player_char_str = new String(rcvpack.getData());
        player_char_str = player_char_str.substring(0, rcvpack.getLength());

        //Get row_number
        if (dgsocket != null) {
            try {
                dgsocket.receive(rcvpack);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String row_number_str = new String(rcvpack.getData());
        row_number_str = row_number_str.substring(0, rcvpack.getLength());

        //Get line_number
        if (dgsocket != null) {
            try {
                dgsocket.receive(rcvpack);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String line_number_str = new String(rcvpack.getData());
        line_number_str = line_number_str.substring(0, rcvpack.getLength());

        String[] player_move_pack = {player_char_str, row_number_str, line_number_str};

        System.out.println("Соединение установлено...\n");
        return player_move_pack;

    }
}
