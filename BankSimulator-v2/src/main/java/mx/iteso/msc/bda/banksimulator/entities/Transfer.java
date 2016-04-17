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

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author Mario Contreras - marioc@nazul.net
 */
@Entity("TRANSFERENCIAS")
public class Transfer {
    @Id
    private ObjectId id;
    @Reference
    private Account source;
    @Reference
    private Account destination;
    @Property("value")
    private float value;
    @Property("status")
    private TransferStatus status;
    @Property("lastModified")
    private long lastModified;

    public Transfer() {
        
    }
    
    public Transfer(Account source, Account destination, float value) {
        this.id = new ObjectId();
        this.source = source;
        this.destination = destination;
        this.value = value;
        this.status = TransferStatus.INITIAL;
        this.lastModified = System.currentTimeMillis();
    }
    
    public ObjectId getId() {
        return id;
    }

    public Account getSource() {
        return source;
    }

    public void setSource(Account source) {
        this.source = source;
    }

    public Account getDestination() {
        return destination;
    }

    public void setDestination(Account destination) {
        this.destination = destination;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
        this.lastModified = System.currentTimeMillis();
    }
    
    @Override
    public String toString() {
        return this.id.toString();
    }
}

// EOF
