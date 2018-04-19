package wspolbieznosc;

public class TaskWithName {
    String name;
    Task task;
    TaskWithName(String name, Task task)
    {
        this.name = name;
        this.task = task;
    }
    Task getTask()
    {
        return task;
    }
    String getName()
    {
        return name;
    }
}
