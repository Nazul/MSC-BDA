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
import java.util.Arrays;

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
    private static final int DB_DELAY = 0;

    public static MongoClient openConnection() {
//        Random r = new Random();
//        if (r.nextInt(101) < DB_SUCCESS_RATE) {
            MongoCredential credential = MongoCredential.createCredential(USERNAME, DATABASE, PASSWORD.toCharArray());
            MongoClient client = new MongoClient(new ServerAddress(SERVER, PORT), Arrays.asList(credential));

//            try {
//               Thread.sleep(DB_DELAY);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(DbClient.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
            return client;
//        }
//        else {
//            System.out.println("Simulated database error");
//            return null;
//        }
    }
}

// EOF
