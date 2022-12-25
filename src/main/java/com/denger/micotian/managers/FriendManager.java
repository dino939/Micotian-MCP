package com.denger.micotian.managers;

import java.util.ArrayList;

public class FriendManager {
    public static ArrayList<String> friends = new ArrayList<String>();

    public static void add(String name){
        if(!friends.contains(name)){
            friends.add(name);
        }
    }

    public static boolean isFriend(String name){
        if(friends.contains(name)){
            return true;
        }
        return false;
    }

    public static void remove(String name){
        if(friends.contains(name)){
            friends.remove(name);
        }
    }
}
