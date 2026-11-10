import java.util.Scanner;

public class Main {

    public static int chooseAnswer(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Bitte eine Zahl eingeben");
            return chooseAnswer(sc);
        }
    }

    public static int chooseInRange(Scanner sc, int min, int max) {
        int answer = chooseAnswer(sc);

        while (answer < min || answer > max) {
            System.out.println("Bitte eine Zahl von " + min + " bis " + max + " eingeben");
            answer = chooseAnswer(sc);
        }

        return answer;
    }

    public static void main(String[] args) {
        int answerChoose;
        int idChoose;
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        boolean exit = false;
        String[] choose = {"Aufgabe anlegen", "Aufgaben auflisten", "Status ändern", "Priorität ändern", "Aufgabe löschen"};

        while (!exit) {
            System.out.println("Was willst du machen?");
            System.out.println(" ");
            for (int i = 0; i < choose.length; i++) {
                System.out.println(i+1 + ":" + choose[i]);
            }
            System.out.println(" ");
            System.out.println("Bitte eingeben");

            answerChoose = chooseInRange(sc, 1, 5);

                switch (answerChoose) {
                    case 1 -> {
                        System.out.println("Was ist deine Aufgabe?");
                        String title = sc.nextLine();

                        System.out.println("Was musst du genau machen?");
                        String desc = sc.nextLine();

                        System.out.println("Wie hoch ist die Priorität?");
                        System.out.println("1: Niedrig");
                        System.out.println("2: Mittel");
                        System.out.println("3: Hoch");

                        System.out.println("");
                        System.out.println("Bitte eingeben:");

                        answerChoose = chooseInRange(sc, 1, 3);

                        Prio prio;

                        switch (answerChoose) {
                            case 1:
                                prio = Prio.NIEDRIG;
                                break;

                            case 2:
                                prio = Prio.MITTEL;
                                break;

                            case 3:
                                prio = Prio.HOCH;
                                break;

                            default: prio = Prio.MITTEL;
                        }

                        Task task = manager.createTask(title, desc, prio);
                        System.out.println("");
                        System.out.println("Aufgabe angelegt mit ID " + task.getId());
                    }

                    case 2 -> {

                        for (Task t : manager.getTasks()) {
                            System.out.println(t);
                        }

                    }

                    case 3 -> {
                        System.out.println("Von welcher Aufgabe (ID) willst du den Status ändern?");

                        for (Task t : manager.getTasks()) {
                            System.out.println(t);
                        }
                        ;

                        System.out.println("");
                        System.out.println("Bitte eingeben:");
                        idChoose = chooseAnswer(sc);

                        System.out.println("Welchen Status hat die Aufgabe?");
                        System.out.println("1: offen");
                        System.out.println("2: in Bearbeitung");
                        System.out.println("3: erledigt");

                        System.out.println("");
                        System.out.println("Bitte eingeben:");
                        answerChoose = chooseInRange(sc, 1, 3);

                        switch (answerChoose) {

                            case 1:
                                manager.setStatus(idChoose, Status.OFFEN);
                                break;

                            case 2:
                                manager.setStatus(idChoose, Status.IN_BEARBEITUNG);
                                break;

                            case 3:
                                manager.setStatus(idChoose, Status.ERLEDIGT);
                                break;
                        }
                    }

                    case 4 -> {
                        System.out.println("Von welcher Aufgabe (ID) willst du die Priorität ändern?");

                        for (Task t : manager.getTasks()) {
                            System.out.println(t);
                        }
                        ;

                        idChoose = chooseAnswer(sc);

                        System.out.println("Welche Priorität hat die Aufgabe?");
                        System.out.println("1: Niedrig");
                        System.out.println("2: Mittel");
                        System.out.println("3: Hoch");

                        System.out.println("Bitte eingeben:");
                        answerChoose = chooseInRange(sc, 1, 3);

                        switch (answerChoose) {

                            case 1:
                                manager.setPrio(idChoose, Prio.NIEDRIG);
                                break;

                            case 2:
                                manager.setPrio(idChoose, Prio.MITTEL);
                                break;

                            case 3:
                                manager.setPrio(idChoose, Prio.HOCH);
                                break;
                        }
                    }

                    case 5 -> {
                        System.out.println("Welche Aufgabe möchtest du löschen?");

                        for (Task t : manager.getTasks()) {
                            System.out.println(t);
                        }
                        ;

                        idChoose = chooseAnswer(sc);

                        manager.deleteTask(idChoose);
                    }
                }



            System.out.println("");
            System.out.println("Willst du beenden?");
            System.out.println("1: Ja");
            System.out.println("2: Nein");

            answerChoose = chooseInRange(sc, 1, 2);

            if (answerChoose == 1) {
                exit = true;
            }
        }

        manager.writeCSV();
        sc.close();
    }

}