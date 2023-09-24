/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author gandh
 */

public class RoomDB {
    private String room;
    private String capacity;

    public RoomDB() {
        // empty constructor
    }

    public RoomDB(String room, String capacity) {
        this.room = room;
        this.capacity = capacity;
    }

    // getters
    public String getRoom() {
        return room;
    }

    public String getCapacity() {
        return capacity;
    }
}

