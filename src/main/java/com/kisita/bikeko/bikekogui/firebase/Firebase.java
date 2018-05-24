/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kisita.bikeko.bikekogui.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kisita.bikeko.bikekogui.BikekoStore;
import com.kisita.bikeko.bikekogui.item.Data;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author HuguesKi
 */
public class Firebase {
    private static final String DATABASE_URL                = "https://glam-afc14.firebaseio.com";
    private static final DatabaseReference database         = initFirebase();
    private BikekoStore mContext;
    private static Firebase INSTANCE = new Firebase();
    
    public static Firebase newInstance(BikekoStore store){
        INSTANCE.setStore(store);
        return INSTANCE;
    }
    
    private Firebase(){
        startListeners("items");
    }
    
    private void  setStore(BikekoStore store){
        this.mContext = store;
    } 
    
    private void startListeners(String table) {
        database.child(table).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildName) {
                insertItemsData(dataSnapshot);
                System.out.println("Data " +  dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildName) {
                //System.out.println("Change " + dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildName) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("startListeners: unable to attach listener to posts");
                System.out.println("startListeners: " + databaseError.getMessage());
            }
        });
    }
    
    public static DatabaseReference initFirebase(){

        // Initialize Firebase
        try {
            // [START initialize]
            FileInputStream serviceAccount = new FileInputStream("C:\\uza-server\\uza-server.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl(DATABASE_URL)
                    .build();
            FirebaseApp.initializeApp(options);
            // [END initialize]
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: invalid service account credentials. See README.");
            System.out.println(e.getMessage());

            System.exit(1);
        }catch (IOException e1) {
            throw new RuntimeException("Google service account data not found");
        }
        
        return FirebaseDatabase.getInstance().getReference();
    }
    
    
    public void getCredentials() throws IOException{
		
		InputStream fileIs = Firebase.class
				.getResourceAsStream("uza-server.json");
		
		// now just do something with your file
		try (BufferedReader br = new BufferedReader(new InputStreamReader(fileIs))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       // process the line.
		    	System.out.println(line);
		    }
		}
	}
        void insertItemsData(DataSnapshot dataSnapshot){
            Data item = new Data(
                    FirebaseUtils.getItemId(dataSnapshot),
                    FirebaseUtils.getItemData(dataSnapshot,"name"),
                    FirebaseUtils.getItemData(dataSnapshot,"price"),
                    FirebaseUtils.getItemData(dataSnapshot,"author"),
                    FirebaseUtils.getItemData(dataSnapshot,"size"),
                    FirebaseUtils.getItemData(dataSnapshot,"weight"),
                    FirebaseUtils.getItemData(dataSnapshot,"type"),
                    FirebaseUtils.getItemData(dataSnapshot,"availability"), 
                    FirebaseUtils.getItemData(dataSnapshot,"description"),
                    FirebaseUtils.getPicures(dataSnapshot));
            
            item.printData();
            
            this.mContext.getTableViewController().getTableView().getItems().add(item);
        
            /*Data i = new Data(
                    FirebaseUtils.getItemId(dataSnapshot), 
                    FirebaseUtils.getItemData(dataSnapshot,"name"), 
                    FirebaseUtils.getItemData(dataSnapshot,"brand"), 
                    FirebaseUtils.getItemData(dataSnapshot,"category"), 
                    FirebaseUtils.getItemData(dataSnapshot,"type"), 
                    FirebaseUtils.getItemData(dataSnapshot,"price"), 
                    FirebaseUtils.getItemData(dataSnapshot,"currency"), 
                    FirebaseUtils.getItemData(dataSnapshot,"seller"), 
                    FirebaseUtils.getPicures(dataSnapshot), 
                    FirebaseUtils.getItemData(dataSnapshot,"author"), 
                    FirebaseUtils.getItemData(dataSnapshot,"description"), 
                    FirebaseUtils.getItemData(dataSnapshot,"size"), 
                    FirebaseUtils.getItemData(dataSnapshot,"weight"), 
                    FirebaseUtils.getItemData(dataSnapshot,"availability"), 
                    FirebaseUtils.getItemData(dataSnapshot,"url"));
            mContext.newItemFromFirebase(i);*/
    }
        
    /*public void updateItemsTable(List<Items> items){
        Map<String, Object> itemsUpdates = new HashMap<>();
        if(items != null){
            for(Items it : items) {
                if(it.getId().isEmpty()){
                    System.out.println("We have a null item. Please create an id");
                }
              
                itemsUpdates.put("/items/" + it.getId() + "/price/",it.getPrice()); 
                itemsUpdates.put("/items/" + it.getId() + "/name/",it.getName()); 
                itemsUpdates.put("/items/" + it.getId() + "/author/",it.getAuthor()); 
                itemsUpdates.put("/items/" + it.getId() + "/description/",setDescription(it)); 
                itemsUpdates.put("/items/" + it.getId() + "/size/",it.getSize()); 
                itemsUpdates.put("/items/" + it.getId() + "/availability/",setAvailability(it.getAvailability())); 
            }
            database.updateChildren(itemsUpdates);
        }
    }*/
    
    private String setAvailability(String s){
        if(s.equalsIgnoreCase("Available"))
            return "0";
        
        if(s.equalsIgnoreCase("Sold"))
            return "1";
        
        return "";
    }

    /*private String setDescription(Data it) {
        
        StringBuilder buf = new StringBuilder();
       
        buf.append(getSelectedValueIndex(it.getSignature(),SIGNATURE));
        buf.append(",");
        
        buf.append(getSelectedValueIndex(it.getTechnical(),TECHNIQUES));
        buf.append(",");
        
        buf.append(getSelectedValueIndex(it.getTirage(),DRAWING));
        buf.append(",");        
        buf.append(getSelectedValueIndex(it.getFraming(),FRAMING));
       
        return buf.toString();
    }*/

    private int getSelectedValueIndex(String selectedValue, String[] list) {
        System.out.println("length is  : "+list.length);
        for(int i = 0 ; i < list.length ; i ++){
            if(list[i].equals(selectedValue)){
                return i;
            }
        }
        return -1;
    }
}

