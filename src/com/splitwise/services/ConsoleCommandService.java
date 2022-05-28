package com.splitwise.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsoleCommandService {

    private ExpenseManagerService service;

    public ConsoleCommandService(ExpenseManagerService service) {
        this.service = service;
    }

    public void processCommand(String line) throws Exception {
        String[] str = line.split(" ");
        String type = str[0];
        String userId;
        switch (type) {
            case "REGISTER":
                userId = str[1];
                String userName = str[2];
                String phoneNo = str[3];
                String password = str[4];
                this.service.registerUser(userId, userName, phoneNo, password);
                break;
            case "HISTORY":
                userId = str[1];
                this.service.showExpenseHistoryByUser(userId);
                break;
            case "EXPENSE":
                userId = str[1];
                double amountPaid = Double.parseDouble(str[2]);
                int noOfUsers = Integer.parseInt(str[3]);
                List<String> users = new ArrayList<>();
                int i;
                for (i=0;i<noOfUsers;i++) {
                    users.add(str[4+i]);
                }
                String splittingType = str[4+i];
                String expenseName = "";
                if (str[4+i+1].equals("DESC")) {
                    expenseName = str[4+i+2];
                }
                this.service.createExpense(expenseName, userId, users, amountPaid, splittingType);
                break;
            case "BALANCE":
                userId = str[1];
                this.service.showBalanceHistoryByUser(userId);
                break;
        }
    }
}
