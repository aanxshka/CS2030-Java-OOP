abstract class TaskParent {

    private final int day;
    private final int startTime;
    private final int endTime;
    private final String desc;

    TaskParent(int day, int startTime, int endTime, String desc) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.desc = desc;
    }

    TaskParent(TaskParent task) {
        this(task.day, task.startTime, task.endTime, task.desc);
    }



    int getDay() {
        return this.day;
    }

    int getStart() {
        return this.startTime;
    }

    int getEnd() {
        return this.endTime;
    }

    String getDesc() {
        return this.desc;
    }


    abstract ImList<TaskParent> getTask(int day);

    @Override
    public String toString() {
        return String.format("Task: %d,%d,%d,%s", this.day, this.startTime, this.endTime, this.desc);
    }

}
