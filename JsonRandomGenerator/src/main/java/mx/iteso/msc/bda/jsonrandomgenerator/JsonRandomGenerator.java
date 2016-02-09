/*
 * Copyright 2016 Mario_Contreras.
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
package mx.iteso.msc.bda.jsonrandomgenerator;

import javax.json.Json;
import javax.json.JsonObject;
import org.fluttercode.datafactory.impl.DataFactory;

/**
 *
 * @author Mario Contreras
 * http://www.andygibson.net/blog/article/generate-test-data-with-datafactory/
 */
public class JsonRandomGenerator {
    public static void main(String[] args) {
        DataFactory df = new DataFactory();
        String results = "";
        for (int i = 0; i < 10; i++) {
            JsonObject personObject = Json.createObjectBuilder()
                    .add("name", df.getFirstName())
                    .add("age", df.getNumberBetween(18, 80))
                    .add("address", 
                         Json.createObjectBuilder().add("street", df.getAddress())
                                                   .add("city", df.getCity())
                                                   .add("zipCode", df.getNumberBetween(10000, 99999))
                                                   .build()
                        )
                    .add("phoneNumber", 
                         Json.createArrayBuilder().add(df.getNumberBetween(10000000, 99999999))
                                                  .add(df.getNumberBetween(10000000, 99999999))
                                                  .add(df.getNumberBetween(10000000, 99999999))
                                                  .build()
                        )
                    .build();
            results += personObject + ", ";
        }
        results = "[" + results.substring(0, results.length() - 2) + "]";
        System.out.println(results);
    }
}
