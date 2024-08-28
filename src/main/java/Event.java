public class Event extends Task{
    private final String startEvent;
    private final String endEvent;
    public Event(String taskName) {
        super(taskName.split("/from")[0]);
        this.startEvent = taskName.split("/from")[1].split("/to")[0];
        this.endEvent = taskName.split("/from")[1].split("/to")[1];
    }

    public Event(String taskName, boolean isCompleted) {
        super(taskName.split("/from")[0], isCompleted);
        this.startEvent = taskName.split("/from")[1].split("/to")[0];
        this.endEvent = taskName.split("/from")[1].split("/to")[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.startEvent + " to:" + this.endEvent + ")";
    }

    public String writeToFile() {
        return "E " + (super.isCompleted() ? "0" : "1") + " "
                + this.getTaskName() + "/from" + this.startEvent + "/to" + this.endEvent;
    }
}
