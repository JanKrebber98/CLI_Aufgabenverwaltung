enum Status { OFFEN, IN_BEARBEITUNG , ERLEDIGT};
enum Prio { NIEDRIG, MITTEL , HOCH};

public class Task {
    int id;
    String title;
    String desc;
    Status status;
    Prio prio;

    public Task(int id, String title, String desc, Status status, Prio prio) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.status = status;
        this.prio = prio;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPrio(Prio prio) {
        this.prio = prio;
    }

    public int getId() { return id; }

    @Override
    public String toString() {
        return "#" + id + " " + title + " – " + desc + " (" + prio + ", " + status + ")";
    }

    public String toCSV(){
        return id+";"+title+";"+desc+";"+status+";"+prio;
    }
}
