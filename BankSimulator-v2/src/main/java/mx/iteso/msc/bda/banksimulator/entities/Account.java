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
package mx.iteso.msc.bda.banksimulator.entities;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author Mario Contreras - marioc@nazul.net
 */
@Entity("CUENTAS")
public class Account {
    @Id
    private ObjectId id;
    @Property("name")
    private String name;
    @Property("balance")
    private float balance;
    @Reference
    private List<Transfer> pendingTransfers;
    
    public Account() {
        
    }

    public Account(String name, float balance) {
        this.id = new ObjectId();
        this.name = name;
        this.balance = balance;
        this.pendingTransfers = new ArrayList<>();
    }
    
    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<Transfer> getPendingTransfers() {
        return pendingTransfers;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}

// EOF
