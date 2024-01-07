import java.util.Comparator;

class RecurringTaskComparator implements Comparator<TaskParent> {
    @Override
    public int compare(TaskParent t1, TaskParent t2) {
        if (t1.getDay() == t2.getDay()) {
            return t1.getStart() - t2.getStart();
        } else {
            return t1.getDay() - t2.getDay();
        }
    }

}
