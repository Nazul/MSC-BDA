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

import java.math.BigDecimal;

/**
 *
 * @author Mario Contreras - marioc@nazul.net
 */
public class Transfer extends Entity {
    private String source;
    private String destination;
    private BigDecimal value;
    private TransferStatus status;
    private long lastModified;

    public Transfer(String id, String source, String destination, BigDecimal value) {
        super(id);
        this.source = source;
        this.destination = destination;
        this.value = value;
        this.status = TransferStatus.INITIAL;
        this.lastModified = System.currentTimeMillis();
    }
    
    public Transfer(String id, String source, String destination, BigDecimal value, String status, long lastModified) {
        this(id, source, destination, value);
        try {
            this.status = TransferStatus.valueOf(status);
        }
        catch (Exception ex) {
            this.status = TransferStatus.ERROR;
        }
        this.lastModified = lastModified;
    }
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
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
        return "{ id: \"" + getId() + "\", source: \"" + source + "\", destination: \"" + destination + "\", value: " + value + ", status: \"" + status.name() + "\", lastModified: " + lastModified + " }";
    }
}

// EOF
