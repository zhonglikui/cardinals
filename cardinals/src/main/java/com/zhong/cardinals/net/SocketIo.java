package com.zhong.cardinals.net;


import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.engineio.client.transports.WebSocket;

/**
 * Created by zhong on 17/11/10.
 */

public class SocketIo {
    private static String socket_host;
    private static IO.Options option;
    private static Socket socket, socket2;

    public static void init(String host, String path) {
        option = new IO.Options();
        option.transports = new String[]{WebSocket.NAME};
        option.reconnection = true;
        option.path = path;
        socket_host = host;

    }

    public static Socket getSocketIo() {
        if (socket == null) {
            try {
                socket = IO.socket(socket_host, option);
                return socket;
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return socket;
        }

    }

    public static Socket getSocketIo2() {

        if (socket2 == null) {
            try {
                socket2 = IO.socket(socket_host, option);
                return socket2;
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return socket2;
        }

    }

    public static Socket getNewSocketIo() {
        try {
            return IO.socket(socket_host, option);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

}
