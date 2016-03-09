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


/**
 *
 * @author Mario Contreras - marioc@nazul.net
 */
public class Node implements Runnable {
    private Thread t;
    // Time ranges
    private static final int REFRESH_TIME = 200;
    private static final int MIN_TIME_TEMPERATURE = 150;
    private static final int MAX_TIME_TEMPERATURE = 200;
    private static final int MIN_TIME_HUMIDITY = 200;
    private static final int MAX_TIME_HUMIDITY = 250;
    private static final int MIN_TIME_PHOTOELECTRIC = 250;
    private static final int MAX_TIME_PHOTOELECTRIC = 300;
    // Value ranges
    private static final int MIN_VALUE_TEMPERATURE = 15;
    private static final int MAX_VALUE_TEMPERATURE = 30;
    private static final int MIN_VALUE_HUMIDITY = 0;
    private static final int MAX_VALUE_HUMIDITY = 100;
    private static final int MIN_VALUE_PHOTOELECTRIC = 1_000;
    private static final int MAX_VALUE_PHOTOELECTRIC = 5_000;
    // Sensors
    private final Sensor ts = new Sensor(SensorType.Temperature, MIN_TIME_TEMPERATURE, MAX_TIME_TEMPERATURE, MIN_VALUE_TEMPERATURE, MAX_VALUE_TEMPERATURE);
    private final Sensor hs = new Sensor(SensorType.Humidity, MIN_TIME_HUMIDITY, MAX_TIME_HUMIDITY, MIN_VALUE_HUMIDITY, MAX_VALUE_HUMIDITY);
    private final Sensor ps = new Sensor(SensorType.Photoelectric, MIN_TIME_PHOTOELECTRIC, MAX_TIME_PHOTOELECTRIC, MIN_VALUE_PHOTOELECTRIC, MAX_VALUE_PHOTOELECTRIC);
    // Fields
    private final int id;
    private boolean running;
    
    public Node(int id) {
        this.id = id;
        ts.start();
        hs.start();
        ps.start();
    }

    @Override
    public void run() {
        try {
            while(running) {
                Thread.sleep(REFRESH_TIME);
                Data d = new Data(id, System.currentTimeMillis(), ts.getValue(), hs.getValue(), ps.getValue() != null ? ps.getValue().intValue() : null);
                dbAccess.saveData(d);
                ts.reset();
                hs.reset();
                ps.reset();
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
