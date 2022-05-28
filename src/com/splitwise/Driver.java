package com.splitwise;

import com.splitwise.controllers.ExpenseController;
import com.splitwise.controllers.UserController;
import com.splitwise.repositories.ExpenseInMemoryRepo;
import com.splitwise.repositories.IExpenseRepo;
import com.splitwise.repositories.IUserRepo;
import com.splitwise.repositories.UserInMemoryRepo;
import com.splitwise.services.ConsoleCommandService;
import com.splitwise.services.ExpenseManagerService;

import java.io.*;

public class Driver {

    public static void main(String[] args) throws Exception {
        String filePath = args[0];
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath)));

        /*
        * Create Repositories
        */
        IUserRepo userRepo = new UserInMemoryRepo();
        IExpenseRepo expenseRepo = new ExpenseInMemoryRepo();

        /*
        * Create Controllers
        */
        UserController userController = new UserController(userRepo);
        ExpenseController expenseController = new ExpenseController(userRepo, expenseRepo);

        ExpenseManagerService managerService = new ExpenseManagerService(userController, expenseController);

        ConsoleCommandService service = new ConsoleCommandService(managerService);

        String line;
        while ((line = br.readLine()) != null) {
            service.processCommand(line);
        }
    }
}
