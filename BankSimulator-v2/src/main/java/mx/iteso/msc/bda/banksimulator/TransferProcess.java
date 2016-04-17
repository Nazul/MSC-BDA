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
package mx.iteso.msc.bda.banksimulator;

import java.util.List;
import mx.iteso.msc.bda.banksimulator.dbaccess.DbClient;
import mx.iteso.msc.bda.banksimulator.entities.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author Mario Contreras - marioc@nazul.net
 */
public class TransferProcess implements Runnable {
    private static final int REFRESH_TIME = 10_000;
    private Thread t;
    private boolean running;
    private final Morphia morphia = new Morphia();
    private final Datastore datastore;
    private List<Transfer> transfers;
    
    public TransferProcess() {
        morphia.mapPackage("mx.iteso.msc.bda.banksimulator.entities");
        datastore = morphia.createDatastore(DbClient.openConnection(), "BANCO");
        datastore.ensureIndexes();
    }

    @Override
    public void run() {
        // TODO: Do something, then Thread.sleep()
        try {
            while(running) {
                Query<Transfer> transfersQuery = datastore.createQuery(Transfer.class);

                transfers = transfersQuery.asList();
                if (transfers == null) {
                    System.out.println("Error while getting new transfers for processing or no new transfers were found");
                }
                else {
                    for(Transfer transfer : transfers) {
                        switch(transfer.getStatus()) {
                            case INITIAL:
                                // Set as pending
                                transfer.setStatus(TransferStatus.PENDING);
                                datastore.save(transfer);
                                break;
                            case PENDING:
                                if(!transfer.getSource().getPendingTransfers().contains(transfer))
                                    transfer.getSource().getPendingTransfers().add(transfer);
                                if(!transfer.getDestination().getPendingTransfers().contains(transfer))
                                    transfer.getDestination().getPendingTransfers().add(transfer);
                                datastore.save(transfer.getSource());
                                datastore.save(transfer.getDestination());
                                datastore.save(transfer);
                        }
                    }
                    //datastore.save(transfers);
                }
                Thread.sleep(REFRESH_TIME);
            }
        } catch (InterruptedException ex) {
            System.out.println("Thread interrupted.");
        }
    }

    public void start() {
        if(t == null) {
            t = new Thread(this);
            t.start();
            this.running = true;
        }
    }
    
    public void stop() {
        this.running = false;
    }
}

// EOF
