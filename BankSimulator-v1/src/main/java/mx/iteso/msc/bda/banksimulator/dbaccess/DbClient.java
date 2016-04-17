/*
 * Copyright 2016 Mario Contreras - marioc@nazul.net.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mx.iteso.msc.bda.banksimulator.dbaccess;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import java.util.Arrays;
import java.util.HashMap;
import mx.iteso.msc.bda.banksimulator.entities.*;
import org.bson.Document;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mario Contreras - marioc@nazul.net
 */
public class DbClient {
    // Not a best practice! Real applications must not hardcode username and passwords (nor servernames)
    // Clear-text username and password musn't be used
    private static final String SERVER = "localhost";
    private static final int PORT = 27017;
    private static final String USERNAME = "GERENTE";
    private static final String PASSWORD = "BDA2016";
    private static final String DATABASE = "BANCO";
    private static final String ACCOUNT_COLLECTION = "CUENTAS";
    private static final String TRANSFER_COLLECTION = "TRANSFERENCIAS";
    // Parameters
    private static final int DB_SUCCESS_RATE = 90;
    private static final int DB_DELAY = 300;

    private static MongoClient openConnection() {
        Random r = new Random();
        if (r.nextInt(101) < DB_SUCCESS_RATE) {
            MongoCredential credential = MongoCredential.createCredential(USERNAME, DATABASE, PASSWORD.toCharArray());
            MongoClient client = new MongoClient(new ServerAddress(SERVER, PORT), Arrays.asList(credential));

            try {
               Thread.sleep(DB_DELAY);
            } catch (InterruptedException ex) {
                Logger.getLogger(DbClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return client;
        }
        else {
            System.out.println("Simulated database error");
            return null;
        }
    }
    
    public static void AddAccount(Account account) {
        try (MongoClient client = openConnection()) {
            Document d = Document.parse(account.toString());
            
            client.getDatabase(DATABASE).getCollection(ACCOUNT_COLLECTION).insertOne(d);
        }
        catch (Exception ex) {
            System.out.println("AddAccount() error");
        }
    }

    public static void AddTransfer(Transfer transfer) {
        try (MongoClient client = openConnection()) {
            Document d = Document.parse(transfer.toString());
            
            client.getDatabase(DATABASE).getCollection(TRANSFER_COLLECTION).insertOne(d);
        }
        catch (Exception ex) {
            System.out.println("AddTransfer() error");
        }
    }

    public static void SetTransferStatus(String id, TransferStatus newStatus) {
        try (MongoClient client = openConnection()) {
            Document filter = Document.parse("{id: \"" + id +"\"}");
            Document update = Document.parse("{$set: { status: \"" + newStatus.name() + "\" }}");
            
            client.getDatabase(DATABASE).getCollection(TRANSFER_COLLECTION).updateOne(filter, update);
        }
        catch (Exception ex) {
            System.out.println("SetTransferStatus() error");
        }
    }

    public static HashMap<String, Account> getAllAccounts() {
        HashMap<String, Account> accounts = new HashMap<>();

        try (MongoClient client = openConnection()) {
            FindIterable<Document> docs = client.getDatabase(DATABASE).getCollection(ACCOUNT_COLLECTION).find();
            
            for (Document doc : docs)
                accounts.put(doc.getString("id"), new Account(doc.getString("id"), doc.getString("name"), new BigDecimal(doc.getDouble("balance"))));

            return accounts;
        }
        catch (Exception ex) {
            System.out.println("getAllAccounts() error");
            return null;
        }
    }
    
    public static HashMap<String, Transfer> getAllTransfers() {
        HashMap<String, Transfer> transfers = new HashMap<>();

        try (MongoClient client = openConnection()) {
            FindIterable<Document> docs = client.getDatabase(DATABASE).getCollection(TRANSFER_COLLECTION).find();
            
            for (Document doc : docs)
                transfers.put(doc.getString("id"), new Transfer(doc.getString("id"), doc.getString("source"), doc.getString("destination"), new BigDecimal(doc.getDouble("value")), doc.getString("status"), doc.getLong("lastModified")));

            return transfers;
        }
        catch (Exception ex) {
            System.out.println("getAllTransfers() error");
            return null;
        }
    }
    
    public static HashMap<String, Transfer> getNewTransfers() {
        HashMap<String, Transfer> transfers = new HashMap<>();

        try (MongoClient client = openConnection()) {
            Document filter = Document.parse("{status: \"" + TransferStatus.INITIAL.name() + "\"}");
            FindIterable<Document> docs = client.getDatabase(DATABASE).getCollection(TRANSFER_COLLECTION).find(filter);
            
            for (Document doc : docs)
                transfers.put(doc.getString("id"), new Transfer(doc.getString("id"), doc.getString("source"), doc.getString("destination"), new BigDecimal(doc.getDouble("value")), doc.getString("status"), doc.getLong("lastModified")));

            return transfers;
        }
        catch (Exception ex) {
            System.out.println("getNewTransfers() error");
            return null;
        }
    }
    
    public static List<String> getPendingTransfers(String accountId) {
        List<String> transfers = new ArrayList<>();

        try (MongoClient client = openConnection()) {
            // { $or: [ { source: "abc" }, { destination: "xyz" } ] }
            Document account = Document.parse("{ $or: [ {source: \"" + accountId + "\"}, {destination: \"" + accountId + "\"} ] }");
            FindIterable<Document> docs = client.getDatabase(DATABASE).getCollection(TRANSFER_COLLECTION).find(account);
            
            for (Document doc : docs)
                transfers.add(doc.getString("id"));
        }
        return transfers;
    }
}

// EOF
