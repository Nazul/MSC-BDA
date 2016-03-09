# Sensor Simulator

A Java application using MongoDB.

* AboutDialog.java - just an "About" dialog box.
* MainForm.java - Main form. Uses a JTable to display data. It has a background task (thread) to read every 2 seconds the MongoDB database and refresh the table. It contains a list of nodes (List<Node> network) which represents a network of 5 nodes. When the form is created, the list is created with 5 nodes and each nodes contains 3 sensors.
* dbAccess.java - Utility class for getting and saving data from MongoDB database.
* Data.java - Represents one document to be stored. It contains the node ID, the time when the record was generated, and the value of each sensor (if available).
* SensorType.java - Utility enum that defines the sensor type (Temperature, Humidity, Photoelectric).
* Sensor.java - The sensor. It generates a thread that generates that changes every cycle. That cycle depends of the minTime and maxTime attributes.
* Node.java - A node. It contains 3 sensors (one per type) and it collects the data every REFRESH_TIME miliseconds. When the data is collected, a new Data object is created and dbAccess.saveData() is called to store the document, then the sensors are reset.
