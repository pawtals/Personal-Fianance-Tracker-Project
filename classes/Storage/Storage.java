package classes.Storage;

import classes.Expenses.Expense;
import java.io.*;
import java.util.*;

class Storage {

    private File storageFile;

    public Storage() {
        initializeStorageFile();
    }

    private void initializeStorageFile() {
        try {
            storageFile = new File("save.csv");

            if (!storageFile.exists()) {
                storageFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Expense> loadExpenses() throws IOException {
        List<Expense> expenses = new ArrayList<>();
        try (
            BufferedReader reader = new BufferedReader(
                new FileReader(storageFile)
            )
        ) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                int amount = Integer.parseInt(parts[0]);
                String name = parts[1];
                String[] category = parts[2].split(";");
                expenses.add(new Expense(amount, name, category));
            }
        }
        return expenses;
    }

    public void saveExpenses(List<Expense> expenses) throws IOException {
        try (
            PrintWriter writer = new PrintWriter(new FileWriter(storageFile))
        ) {
            writer.println("amount,name,category");
            for (Expense e : expenses) {
                writer.println(
                    e.amount + "," + e.name + "," + String.join(";", e.category)
                );
            }
        }
    }
}
