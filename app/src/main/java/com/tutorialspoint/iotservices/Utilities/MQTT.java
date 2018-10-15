package com.tutorialspoint.iotservices.Utilities;

import java.util.ArrayList;

public class MQTT {

    static String protocolName = "MQTT";
    public static ArrayList<Byte> packet = new ArrayList<Byte>();
    static short msgID = 0;

    public static void connect(String id) {
        packet.clear();
        byte rem_len = (byte) (2 +                // protocolName len
                protocolName.length() +            // protocolName
                1 +                                // level
                1 +                                // flags
                2 +                                // Keep Alive Time
                2 +                                // id len
                id.length());                    // id

        packet.add((byte) 0x10);                // Connect Packet Type
        packet.add(rem_len);                    // Remaining Length

        packet.add((byte) 0x00);                        // protocolName
        packet.add((byte) protocolName.length());
        for (byte b : protocolName.getBytes()) {
            packet.add(b);
        }

        packet.add((byte) 0x04);                        // Level
        packet.add((byte) 0x02);                        // flags

        packet.add((byte) 0xFF);                        // KAT MSB
        packet.add((byte) 0xFF);                        // KAT LSB

        packet.add((byte) 0x00);                        // id
        packet.add((byte) id.length());
        for (byte b : id.getBytes()) {
            packet.add(b);
        }
    }

    public static void subscribe(String topic) {
        packet.clear();
        byte rem_len = (byte) (2 +                // msgID
                2 +                                // topic len
                topic.length() +                    // topic
                1);                            // request QoS

        packet.add((byte) 0x82);                // sub Packet Type
        packet.add(rem_len);                    // Remaining Length

        packet.add((byte) (msgID >> 8));                        // msgID MSB
        packet.add((byte) msgID);                            // msgID LSB
        msgID++;

        packet.add((byte) 0x00);                            // Topic
        packet.add((byte) topic.length());
        for (byte b : topic.getBytes()) {
            packet.add(b);
        }

        packet.add((byte) 0x01);                        // request QoS
    }

    public static void publish(String topic, String msg, int msgSize, int qos) {
        packet.clear();
        byte rem_len = (byte) (
                2 +                                // topic len
                        topic.length() +                    // topic
                        msgSize);                        // msgSize

        if (qos > 0) {
            rem_len = (byte) (rem_len + 2); // for msgID
            packet.add((byte) 0x32);
        } else {
            packet.add((byte) 0x30);
        }

        packet.add(rem_len);                    // Remaining Length

        packet.add((byte) 0x00);                            // Topic
        packet.add((byte) topic.length());
        for (byte b : topic.getBytes()) {
            packet.add(b);
        }

        if (qos > 0) {
            packet.add((byte) (msgID >> 8));                        // msgID MSB
            packet.add((byte) msgID);                            // msgID LSB
            msgID++;
        }

        for (byte b : msg.substring(0, msgSize).getBytes()) {
            packet.add(b);
        }
    }
}

