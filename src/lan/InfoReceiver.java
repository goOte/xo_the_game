package lan;

import java.net.*;
import java.io.*;

public class InfoReceiver {

    private final static int PORT = 1212;

    public String ReceiveInfoIpAddress() {

        byte data[] = new byte[131072];
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        DatagramPacket datagramPacket;

        System.out.println("Ожидание соединения.......\n");
        datagramPacket = new DatagramPacket(data, data.length);

        if (datagramSocket != null) {
            try {
                datagramSocket.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String ipAddress = new String(datagramPacket.getData());
        ipAddress = ipAddress.substring(0, datagramPacket.getLength());

        System.out.printf("\n*** Вы успешно подключились к %s ***\n", ipAddress);

        datagramSocket.close();

        return ipAddress;

    }

    public char ReceiveInfoPlayerChar() {

        byte data[] = new byte[131072];
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        DatagramPacket datagramPacket;

        System.out.println("Ожидание соединения.......\n");
        datagramPacket = new DatagramPacket(data, data.length);

        if (datagramSocket != null) {
            try {
                datagramSocket.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String playerCharStr = new String(datagramPacket.getData());
        playerCharStr = playerCharStr.substring(0, datagramPacket.getLength());

        System.out.println("Соединение установлено...\n");

        char playerChar = playerCharStr.charAt(0);

        datagramSocket.close();

        return playerChar;

    }

    public String[] ReceiveInfoPlayerMove() {

        byte data[] = new byte[131072];
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        DatagramPacket datagramPacket;

        System.out.println("Ожидание соединения.......\n");

        datagramPacket = new DatagramPacket(data, data.length);

        //Get playerChar
        if (datagramSocket != null) {
            try {
                datagramSocket.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String playerCharStr = new String(datagramPacket.getData());
        playerCharStr = playerCharStr.substring(0, datagramPacket.getLength());

        //Get rowNumber
        if (datagramSocket != null) {
            try {
                datagramSocket.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String rowNumberStr = new String(datagramPacket.getData());
        rowNumberStr = rowNumberStr.substring(0, datagramPacket.getLength());

        //Get lineNumber
        if (datagramSocket != null) {
            try {
                datagramSocket.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String lineNumberStr = new String(datagramPacket.getData());
        lineNumberStr = lineNumberStr.substring(0, datagramPacket.getLength());

        String[] playerMovePack = {playerCharStr, rowNumberStr, lineNumberStr};

        System.out.println("Соединение установлено...\n");

        datagramSocket.close();

        return playerMovePack;

    }
}
