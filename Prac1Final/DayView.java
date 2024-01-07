class DayView implements View {

    private final int day;

    DayView(int day) {
        this.day = day;
    }

    public void view(ImList<TaskParent> tasks) {
        ImList<TaskParent> finalList = new ImList<TaskParent>();
        for (TaskParent task : tasks) {
            if (task.getDay() == this.day) {
                finalList = finalList.addAll(task.getTask(this.day));
            }
        }


        finalList = finalList.sort(new RecurringTaskComparator());

        for (TaskParent task : finalList) {
            System.out.println(task.toString());
        }

    }
}
