class Planner {

    private final ImList<TaskParent> tasks;

    Planner() {
        this(new ImList<TaskParent>());

    }

    Planner(ImList<TaskParent> tasks) {
        this.tasks = tasks;
    }

    void view(View v) {
        v.view(tasks);
    }

    Planner add(TaskParent t) {
        return new Planner(this.tasks.add(t));
    }

    @Override 
    public String toString() {
        String str = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            str = str + "\n" + this.tasks.get(i).toString();
        }
        return str;
    }

}
