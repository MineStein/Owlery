package me.tylergrissom.godricsowlery.chat;

import me.tylergrissom.godricsowlery.Main;

import java.util.HashMap;
import java.util.UUID;

/**
 * Copyright Tyler Grissom 2017
 */
public class ChatTracker {

    public static class ChatData {

        public enum Type {

            EDIT,

            CREATE
        }

        private String string;
        private Type type;
        private int id;

        public String getString() {
            return string;
        }

        public Type getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public void setString(String string) {
            this.string = string;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ChatData(Type type) {
            this.type = type;
        }

        public ChatData(Type type, int id) {
            this.type = type;
            this.id = id;
        }
    }

    private Main plugin;
    private HashMap<UUID, ChatData> createAnnouncement;

    public Main getPlugin() {
        return plugin;
    }

    public HashMap<UUID, ChatData> getCreateAnnouncement() {
        return createAnnouncement;
    }

    public ChatTracker(Main plugin) {
        this.plugin = plugin;
        this.createAnnouncement = new HashMap<>();
    }
}
