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
package mx.iteso.msc.bda.sensorsimulator;

import java.util.List;
import java.util.ArrayList;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Mario Contreras - marioc@nazul.net
 */
public class dbAccess {
    // Not a best practice! Real applications must not hardcode username and passwords (nor servernames)
    // Reference: db.createUser({user: "sensoresUser", pwd: "B34t.It", roles: [{role: "dbOwner", db: "SENSORES"}]})
    private static final String SERVER_URI = "mongodb://sensoresUser:B34t.It@msc-srv01.southcentralus.cloudapp.azure.com:27017/SENSORES";
    private static final String DATABASE = "SENSORES";
    private static final String COLLECTION = "DATOS";
    private static MongoClient client;
    private static MongoDatabase db;
    private static MongoCollection col;
    
    public static boolean connectDb() {
        boolean retval = false;
        // Error handling it's just a placeholder. It doesn't really really works correctly.
        try {
            client = new MongoClient(new MongoClientURI(SERVER_URI));
            retval = client != null;
            db = client.getDatabase(DATABASE);
            col = db.getCollection(COLLECTION);
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return retval;
    }
    
    public static List<Data> getData() {
        List<Data> data = new ArrayList<>();

        FindIterable<Document> docs = col.find();
        for(Document doc : docs) {
            data.add(new Data(doc.getInteger("id_nodo"), doc.getLong("tiempo"), doc.getDouble("temperatura"), doc.getDouble("humedad"), doc.getInteger("fotoeléctrico")));
        }
       
        return data;
    }
    
    public static void saveData(Data d) {
        Document b = Document.parse(d.toString());
        col.insertOne(b);
    }
}

// EOF
