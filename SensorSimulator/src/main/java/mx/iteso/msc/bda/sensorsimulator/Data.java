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
public class Data {
    private Integer node;
    private Long time;
    private Double temperature;
    private Double humidity;
    private Integer photoelectric;
    
    public Data(Integer n, Long ti, Double te, Double h, Integer p) {
        node = n;
        time = ti;
        temperature = te;
        humidity = h;
        photoelectric = p;
    }
    
    @Override
    public String toString() {
        String s = "";
        
        if(node != null)
            s = "id_nodo: " + node;
        if(time != null)
            s += s.length() > 0 ? ", tiempo: " + time : "tiempo: " + time;
        if(temperature != null)
            s += s.length() > 0 ? ", temperatura: " + temperature : "temperatura: " + temperature;
        if(humidity != null)
            s += s.length() > 0 ? ", humedad: " + humidity : "humedad: " + humidity;
        if(photoelectric != null)
            s += s.length() > 0 ? ", fotoeléctrico: " + photoelectric : "fotoeléctrico: " + photoelectric;

        return String.format("{" + s + "}");
    }

    /**
     * @return the node
     */
    public Integer getNode() {
        return node;
    }

    /**
     * @param node the node to set
     */
    public void setNode(Integer node) {
        this.node = node;
    }

    /**
     * @return the time
     */
    public Long getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Long time) {
        this.time = time;
    }

    /**
     * @return the temperature
     */
    public Double getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the humidity
     */
    public Double getHumidity() {
        return humidity;
    }

    /**
     * @param humidity the humidity to set
     */
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    /**
     * @return the photoelectric
     */
    public Integer getPhotoelectric() {
        return photoelectric;
    }

    /**
     * @param photoelectric the photoelectric to set
     */
    public void setPhotoelectric(Integer photoelectric) {
        this.photoelectric = photoelectric;
    }
}

// EOF
