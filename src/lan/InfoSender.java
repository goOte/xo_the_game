package lan;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;


public class InfoSender {

    private final static int PORT = 1212;

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface networkInterface = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = networkInterface.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        if (inetAddress instanceof Inet4Address) {
                            return ((Inet4Address)inetAddress).getHostAddress().toString();
                        }
                    }
                }
            }
        } catch (SocketException ex) {
//            System.out.println("ServerActivity" + ex.toString());
        }
        return null;
    }

    public void SendInfoIpAddress(String ipAddress) {

        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        DatagramPacket datagramPacket;
        InetAddress destination = null;

        try {
            destination = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        String ipToSend = getLocalIpAddress();

        byte[] dt = ipToSend.getBytes();

        datagramPacket = new DatagramPacket(dt, dt.length, destination, PORT);
        if (datagramSocket != null) {
            try {
                datagramSocket.send(datagramPacket);
                System.out.printf("\n*** Данные соединения отправлены на %s ***\n", ipAddress);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        datagramSocket.close();
    }

    public void SendInfoPlayerChar(String ipAddress, char playerChar) {

        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        DatagramPacket datagramPacket;
        InetAddress destination = null;

        try {
            destination = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        String playerCharStr = "" + playerChar;

        byte[] dt = playerCharStr.getBytes();

        datagramPacket = new DatagramPacket(dt, dt.length, destination, PORT);
        if (datagramSocket != null) {
            try {
                datagramSocket.send(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        datagramSocket.close();
    }

    public void SendInfoPlayerMove(String ipAddress, char playerChar, int rowNumber, int lineNumber) {

        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        DatagramPacket datagramPacket;
        InetAddress destination = null;

        try {
            destination = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // send playerChar
        String playerCharStr = "" + playerChar;
        byte[] dt = playerCharStr.getBytes();
        datagramPacket = new DatagramPacket(dt, dt.length, destination, PORT);
        if (datagramSocket != null) {
            try {
                datagramSocket.send(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //send rowNumber
        String rowNumberStr = Integer.toString(rowNumber);
        byte[] row = rowNumberStr.getBytes();
        datagramPacket = new DatagramPacket(row, row.length, destination, PORT);
        if (datagramSocket != null) {
            try {
                datagramSocket.send(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //send lineNumber
        String lineNumberStr = Integer.toString(lineNumber);
        byte[] line = lineNumberStr.getBytes();
        datagramPacket = new DatagramPacket(line, line.length, destination, PORT);
        if (datagramSocket != null) {
            try {
                datagramSocket.send(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        datagramSocket.close();
    }
}
