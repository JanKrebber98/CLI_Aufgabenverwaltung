import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<Task>();
    private static final Path FILE = Path.of("Daten/Aufgaben.csv");
    private List<String> tasksString = new ArrayList<String>();

    TaskManager() {
        if (Files.exists(FILE)) {
            try {
                tasksString = Files.readAllLines(FILE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            tasksString.remove(0);

            for (String t : tasksString) {
                String[] teile = t.split(";");
                Task newTask = new Task(Integer.parseInt(teile[0]), teile[1], teile[2], Status.valueOf(teile[3]), Prio.valueOf(teile[4]));
                tasks.add(newTask);
            }
        }
    }

    public Task createTask( String title, String desc, Prio prio) {
        Status status = Status.OFFEN;

        Task newTask = new Task(calcNewID(), title, desc, status, prio);
        tasks.add(newTask);
        return newTask;
    }

    private int calcNewID() {
        Task lastTask;
        int lastID;

        if(!tasks.isEmpty()) {
            lastTask = tasks.getLast();
            lastID = lastTask.getId();
        } else {
            return 1;
        }

        lastID += 1;
        return lastID;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    public void setStatus(int id, Status status) {
        boolean hit = false;

        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setStatus(status);
                hit = true;
                break;
            }
        }

        if (!hit) {
            System.out.println("Keine Aufgabe gefunden.");
        }
    }

    public void setPrio(int id, Prio prio) {
        boolean hit = false;

        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setPrio(prio);
                hit = true;
                break;
            }
        }

        if (!hit) {
            System.out.println("Keine Aufgabe gefunden.");
        }
    }

    public void writeCSV() {
        List<String> tasksCSV = new ArrayList<>();
        tasksCSV.add("ID;Titel;Beschreibung;Status;Priorität");

        for (Task t : tasks) {
            tasksCSV.add(t.toCSV());
        }

        try {
            Files.createDirectories(FILE.getParent());
            Files.write(FILE, tasksCSV);
        } catch (IOException e) {
            System.out.println("Speichern fehlgeschlagen: " + e.getMessage());
        }
    }
}

