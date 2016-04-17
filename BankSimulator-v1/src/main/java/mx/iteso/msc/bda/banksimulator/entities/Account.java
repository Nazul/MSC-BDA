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
import java.util.ArrayList;
import java.util.List;
import mx.iteso.msc.bda.banksimulator.dbaccess.DbClient;

/**
 *
 * @author Mario Contreras - marioc@nazul.net
 */
public class Account extends Entity {
    private String name;
    private BigDecimal balance;

    public Account(String id, String name, BigDecimal balance) {
        super(id);
        this.name = name;
        this.balance = balance;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<String> getPendingTransactions() {
        try {
            return DbClient.getPendingTransfers(name);
        }
        catch (Exception ex) {
            List<String> list = new ArrayList<>();
            list.add("<getPendingTransactions() Error>");
            return list;
        }
    }

    @Override
    public String toString() {
        return "{ id: \"" + getId() + "\", name: \"" + name + "\", balance: " + balance + " }";
    }
}

// EOF
