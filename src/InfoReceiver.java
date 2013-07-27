import java.net.*;
import java.io.*;

public class InfoReceiver {

    public String ReceiveInfoIpAddress() throws IOException {

        byte data[] = new byte[131072];
        DatagramSocket dgsocket = new DatagramSocket(1212);
        DatagramPacket rcvpack;

        System.out.println("Ожидание соединения.......");
        rcvpack = new DatagramPacket(data, data.length);

        dgsocket.receive(rcvpack);
        String ip_address = new String(rcvpack.getData());
        ip_address = ip_address.substring(0, rcvpack.getLength());

        System.out.println("Соединение установлено...");

        return ip_address;

    }

    public char ReceiveInfoPlayerChar() throws IOException {

        byte data[] = new byte[131072];
        DatagramSocket dgsocket = new DatagramSocket(1213);
        DatagramPacket rcvpack;

        System.out.println("Ожидание соединения.......");
        rcvpack = new DatagramPacket(data, data.length);

        dgsocket.receive(rcvpack);
        String player_char_str = new String(rcvpack.getData());
        player_char_str = player_char_str.substring(0, rcvpack.getLength());

        System.out.println("Соединение установлено...");

        char player_char = player_char_str.charAt(0);

        return player_char;

    }

    public String[] ReceiveInfoPlayerMove(int port_index) throws IOException {

        int port = 1214 + port_index;
        byte data[] = new byte[131072];
        DatagramSocket dgsocket = new DatagramSocket(port);
        DatagramPacket rcvpack;

        System.out.println("Ожидание соединения.......");

        rcvpack = new DatagramPacket(data, data.length);

        //Get player_char
        dgsocket.receive(rcvpack);
        String player_char_str = new String(rcvpack.getData());
        player_char_str = player_char_str.substring(0, rcvpack.getLength());

        //Get row_number
        dgsocket.receive(rcvpack);
        String row_number_str = new String(rcvpack.getData());
        row_number_str = row_number_str.substring(0, rcvpack.getLength());

        //Get line_number
        dgsocket.receive(rcvpack);
        String line_number_str = new String(rcvpack.getData());
        line_number_str = line_number_str.substring(0, rcvpack.getLength());

        String[] player_move_pack = {player_char_str, row_number_str, line_number_str};

        System.out.println("Соединение установлено...");
        return player_move_pack;

    }
}
