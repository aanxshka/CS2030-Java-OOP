class Task extends TaskParent {


    Task (int day, int startTime, int endTime, String desc) {
        super(day, startTime, endTime, desc);
    }

    Task(Task task) {
        super(task);
    }

    Task edit(int newStart, int newEnd) {
        return new Task(super.getDay(), newStart, newEnd, super.getDesc());
    }

    ImList<TaskParent> getTask(int day) {
        return new ImList<TaskParent>().add(this);
    }


    CancelledTask cancel() {
        return new CancelledTask(this);
    }


}
