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
package mx.iteso.msc.bda.cassandrademo1;

/**
 *
 * @author Mario Contreras - marioc@nazul.net
 */
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 *
 * @author Mario Contreras - marioc@nazul.net
 */
public class DenueAggregator {

    /**
     * @param args the command line arguments
     */
    private static Cluster cluster;
    private static Session session;

    //Open Connection
    public static Session cassandraConnect() {
        if (cluster == null) {
            cluster = Cluster.builder()
                    .addContactPoint("msc-srv01.southcentralus.cloudapp.azure.com")
                    .withPort(9042)
                    .build();
            session = cluster.connect();

        }
        return session;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Open connection to cassandra.");
        session = cassandraConnect();
        System.out.println("Connected.");

        String queryAllRecords = "Select * from inegi_denue.denue_1;";
        PreparedStatement allDenue1 = session.prepare(queryAllRecords);
        BoundStatement boundAllDenue1 = allDenue1.bind();
        ResultSet rsAllDenue1 = session.execute(boundAllDenue1);

        System.out.println("Records retrieved");
        String idStratum = "";
        Integer totalStratum = 0;

        //HashMultiset //guava
        HashMultiset<String> aggregate = HashMultiset.create();
        HashMultiset<String> aggregateStudent = HashMultiset.create();
        int stratumCount = 0;

        for (Row row : rsAllDenue1) {
            String stratumName = row.getString("estrato_personal");
            aggregate.add(stratumName);
            aggregateStudent.add(stratumName);
            stratumCount++;
        }

        System.out.println("Total Stratum: " + stratumCount);

        String insertStratum = "INSERT INTO inegi_denue.recordsbystratum "
                + "(stratumname, recno) values (?, ?);";

        for (Multiset.Entry<String> entry : aggregate.entrySet()) {
            System.out.println("Stratum: " + entry.getElement() + " Record Count: " + entry.getCount());
            PreparedStatement psStratum = session.prepare(insertStratum);
            BoundStatement boundStratum = psStratum.bind(entry.getElement(), entry.getCount());
            session.execute(boundStratum);
        }

        session.close();
        cluster.close();
    }
}

// EOF
