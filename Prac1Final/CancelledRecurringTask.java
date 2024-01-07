class CancelledRecurringTask extends CancelledTask {

    CancelledRecurringTask(TaskParent task) {
        super(task);
    }


    @Override 
    public String toString() {
        return "Recurring " + super.toString();
    }
}
