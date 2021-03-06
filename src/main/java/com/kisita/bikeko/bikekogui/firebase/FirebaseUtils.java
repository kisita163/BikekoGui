/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kisita.bikeko.bikekogui.firebase;

import com.google.firebase.database.DataSnapshot;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author HuguesKi
 */
public class FirebaseUtils {
    public static String getItemData(DataSnapshot dataSnapshot,String name){
        if(dataSnapshot.child(name).exists()){
            return (dataSnapshot.child(name).getValue().toString());
        }else{
            return ("");
        }
    }

    public static String getPicures(DataSnapshot dataSnapshot){
        StringBuilder buf = new StringBuilder();
        if(dataSnapshot.child("pictures").getValue() != null){
            for(DataSnapshot s : dataSnapshot.child("pictures").getChildren()){
                buf.append(s.getValue());
                buf.append(",");
            }
        }
        return buf.toString();
    }

    public static String columnsArray2string(String [] projection){
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < projection.length; i++) {
            buf.append(projection[i]);
            if (i < projection.length - 1)
                buf.append(",");
        }

        return buf.toString();
    }

    public static String getItemId(DataSnapshot dataSnapshot){
        return dataSnapshot.getKey();
    }

    public static String objectToSerialized(Object object){
        String serializedObject = "";
        // serialize the object
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(object);
            so.flush();
            serializedObject = bo.toString();
        } catch (Exception e) {
            System.out.println(e);
        }

        return serializedObject;
    }

    public static ArrayList<String> serializedToArray(String object){
        // deserialize the object
        ArrayList<String> obj = new ArrayList<>();
        try {
            byte b[] = object.getBytes();
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
            obj =  (ArrayList<String>) si.readObject();
        } catch (Exception e) {
            System.out.println( e);
        }
        return obj;
    }
}