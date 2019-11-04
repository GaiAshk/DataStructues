package TaskManager;

public class Task implements Comparable<Task> {
    int serial;
    int priority;
    String description;

    public Task(int serial, int priority, String description){
        this.serial = serial;
        this.description = description;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task o) {
        if (this.priority != o.priority) return this.priority - o.priority;
        else {return this.serial - o.serial;}
    }

    public String toString(){
        return "task: " + this.description + ", priority: " + this.priority;
    }
}
