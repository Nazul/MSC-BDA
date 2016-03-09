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

import java.util.Random;

/**
 *
 * @author Mario Contreras - marioc@nazul.net
 */
public class Sensor implements Runnable {
    private Thread t;
    private final SensorType type;
    private Double value = null;
    private final int minTime, maxTime, minValue, maxValue;
    private boolean running = false;
    
    public Sensor(SensorType type, int minTime, int maxTime, int minValue, int maxValue) {
        this.type = type;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    
    public SensorType getType() {
        return this.type;
    }
    
    public Double getValue() {
        return this.value;
    }

    @Override
    public void run() {
        try {
            Random r = new Random();
            while(running) {
                int delay = r.nextInt(maxTime - minTime) + minTime;

                Thread.sleep(delay);
                value = (double)r.nextInt(maxValue - minValue) + minValue;
            }
        } catch (InterruptedException ex) {
            System.out.println("Sensor thread interrupted.");
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
    
    public void reset() {
        this.value = null;
    }
}

// EOF
