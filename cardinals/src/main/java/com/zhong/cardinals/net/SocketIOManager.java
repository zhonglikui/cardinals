package com.zhong.cardinals.net;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.transports.WebSocket;

/**
 * Created by anshuo on 2018/4/6.
 */

public class SocketIOManager {
    private static SocketIOManager instance;
    private final IO.Options option;
    private Socket socket;
    private OnTickListener otl;
    private OnTickHistoryListener othl;
    private OnMarketHistoryListener omhl;
    private OnPriceListener opl;
    private Map<String, SocketCall> maps = new HashMap<>();

    private SocketIOManager(String url, String path) {
        option = new IO.Options();
        option.transports = new String[]{WebSocket.NAME};
        option.reconnection = true;
        option.path = path;
        try {
            socket = IO.socket(url, option);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (socket != null) {
            registerEvent(socket);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    socket.connect();
                }
            }).start();
        }
    }

    public static synchronized SocketIOManager getInstance(String url, String path) {
        if (instance == null) {
            instance = new SocketIOManager(url, path);
        }
        return instance;
    }

    public static Map<String, String> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, String> map = new HashMap<>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj).toString());
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return map;
    }

    public void sendMassage(String event, Map msg) {
        if (socket != null) {
            socket.emit(event, new JSONObject(msg));
        }
    }

    public void setNewSocketListener(String event, SocketCall call) {
        if (socket != null) {
            socket.on(event, call);
        }
    }

    public void requestRoom(Map msg) {
        sendMassage("RequestRoom", msg);
    }

    public void requestArrayRoom(Map msg) {
        sendMassage("RequestArrayRoom", msg);
    }

    public void leaveRoom() {
        sendMassage("LeaveRoom", new HashMap());
    }

    public void bindFuture(Map msg) {
        sendMassage("BindFuture", msg);
    }

    abstract static class SocketCall<T> implements Emitter.Listener {

        public Class<T> valueType;

        public SocketCall(Class<T> valueType) {
            this.valueType = valueType;
        }

        @Override
        public void call(Object... args) {
            T t = new Gson().fromJson(args[0].toString(), valueType);
            onMsg(t);
        }

        abstract void onMsg(T data);

    }

    public interface OnTickListener<T> {
        void OnTick(T tick);
    }

    public interface OnTickHistoryListener<T> {
        void OnTickHistory(T tickHistory);
    }

    public interface OnMarketHistoryListener<T> {
        void OnMarketHistory(T marketHistory);
    }

    public interface OnPriceListener<T> {
        void OnPrice(T price);
    }

    public void setOnTickListener(OnTickListener otl) {
        this.otl = otl;
    }

    public void setOnTickHistoryListener(OnTickHistoryListener othl) {
        this.othl = othl;
    }

    public void setOnMarketHistoryListener(OnMarketHistoryListener omhl) {
        this.omhl = omhl;
    }

    public void setOnPriceListener(OnPriceListener opl) {
        this.opl = opl;
    }

    private void registerEvent(Socket socket) {


        socket.on("connection", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("connection", "call: " + args[0]);
            }
        });
        socket.on("error", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("error", "call: " + args[0]);
            }
        });
    }

    // private Map<String, SocketCall> maps = new HashMap<>();

    public <T> SocketIOManager addListener(String event, SocketCall<T> call) {
        maps.put(event, call);
        return this;
    }

    public void apply() {
        Iterator it = maps.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, SocketCall> entry = (Map.Entry<String, SocketCall>) it.next();

            socket.on(entry.getKey(), entry.getValue());
        }
    }

  /*  public interface OnTickListener<T> {
        void OnTick(T tick);
    }

    public interface OnTickHistoryListener<T> {
        void OnTickHistory(T tickHistory);
    }

    public interface OnMarketHistoryListener<T> {
        void OnMarketHistory(T marketHistory);
    }

    public interface OnPriceListener<T> {
        void OnPrice(T price);
    }

    abstract static class SocketCall<T> implements Emitter.Listener {

        public Class<T> valueType;

        public SocketCall(Class<T> valueType) {
            this.valueType = valueType;
        }

        @Override
        public void call(Object... args) {
            T t = new Gson().fromJson(args[0].toString(), valueType);
            onMsg(t);
        }

        abstract void onMsg(T data);

    }*/

}
