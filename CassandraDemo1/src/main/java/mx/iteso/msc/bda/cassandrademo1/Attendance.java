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
public class Attendance {

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

        String queryAttendance = "Select * from bda2016.attendance;";
        PreparedStatement allAttendance = session.prepare(queryAttendance);
        BoundStatement boundAllAttendance = allAttendance.bind();
        ResultSet allAttendances = session.execute(boundAllAttendance);

        System.out.println("Records retrieved");
        String idStudent = "";
        Integer totalAssistances = 0;

        //HashMultiset //guava
        HashMultiset<String> aggregate = HashMultiset.create();
        HashMultiset<String> aggregateStudent = HashMultiset.create();
        int assistanceCount = 0;

        for (Row row : allAttendances) {
            String courseName = row.getString("subject");
            String period = row.getString("period");
            String recordId = row.getString("Idstudent");
            aggregate.add(courseName + "_" + period);
            aggregateStudent.add(recordId + "_" + courseName + "_" + period);
            assistanceCount++;
        }

        System.out.println("Total Assistances: " + assistanceCount);

        String insertTotalAssistance = "INSERT INTO bda2016.courseattendance "
                + "(subject, period, totalattendance) values (?, ?, ?);";

        for (Multiset.Entry<String> entry : aggregate.entrySet()) {
            System.out.println("Course: " + entry.getElement() + " Assistance: " + entry.getCount());
            PreparedStatement insertAssistance = session.prepare(insertTotalAssistance);
            BoundStatement boundInsertAssistance = insertAssistance.bind(entry.getElement().split("_")[0],
                    entry.getElement().split("_")[1], entry.getCount());
            session.execute(boundInsertAssistance);
        }

        String insertStudentAttendance = "INSERT INTO bda2016.studentattendance "
                + "(idstudent, subject, period, totalattendance) "
                + "values (?, ?, ?, ?);";

        for (Multiset.Entry<String> entry : aggregateStudent.entrySet()) {
            System.out.println("Student/Course: " + entry.getElement() + " Attendance: " + entry.getCount());
            PreparedStatement insertAssistance = session.prepare(insertStudentAttendance);
            BoundStatement boundInsertAssistance = insertAssistance.bind(entry.getElement().split("_")[0],
                    entry.getElement().split("_")[1],
                    entry.getElement().split("_")[2],
                    entry.getCount());
            session.execute(boundInsertAssistance);
        }

        session.close();
        cluster.close();
    }
}
