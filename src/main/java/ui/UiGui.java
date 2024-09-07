package ui;

import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents the user interface of the application.
 */
public class UiGui {

    /**
     * Constructs a user interface.
     */
    public UiGui() {
    }

    /**
     * Shows the welcome message.
     *
     * @return The welcome message as a string.
     */
    public String showWelcome() {
        return "___________________________________________________________\n"
                + " Hello! I'm Friday\n"
                + " What can I do for you?\n"
                + "___________________________________________________________\n";
    }

    /**
     * Shows the goodbye message.
     *
     * @return The goodbye message as a string.
     */
    public String showGoodbye() {
        return "___________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "___________________________________________________________\n";
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks.
     * @return The task list message as a string.
     */
    public String showTaskList(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("    _______________________________________________________\n");
        sb.append("     Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("     ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        sb.append("    _______________________________________________________\n");
        return sb.toString();
    }

    /**
     * Shows the task added message.
     *
     * @param task The task added.
     * @param size The size of the task list.
     * @return The task added message as a string.
     */
    public String showTaskAdded(Task task, int size) {
        return "    _______________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + task + "\n"
                + "     Now you have " + size + " tasks in the list.\n"
                + "    _______________________________________________________\n";
    }

    /**
     * Shows the task removed message.
     *
     * @param task The task removed.
     * @param size The size of the task list.
     * @return The task removed message as a string.
     */
    public String showTaskRemoved(Task task, int size) {
        return "    _______________________________________________________\n"
                + "     Noted. I've removed this task:\n"
                + "       " + task + "\n"
                + "     Now you have " + size + " tasks in the list.\n"
                + "    _______________________________________________________\n";
    }

    /**
     * Shows the task marked message.
     *
     * @param task The task marked.
     * @return The task marked message as a string.
     */
    public String showTaskMarked(Task task) {
        return "    _______________________________________________________\n"
                + "     Nice! I've marked this task as done:\n"
                + "       " + task + "\n"
                + "    _______________________________________________________\n";
    }

    /**
     * Shows the task unmarked message.
     *
     * @param task The task unmarked.
     * @return The task unmarked message as a string.
     */
    public String showTaskUnmarked(Task task) {
        return "    _______________________________________________________\n"
                + "     OK, I've marked this task as not done yet:\n"
                + "       " + task + "\n"
                + "    _______________________________________________________\n";
    }

    /**
     * Shows the general error message.
     *
     * @return The error message as a string.
     */
    public String showError(String message) {
        return "    _______________________________________________________\n"
                + "     OOPS!!! " + message + "\n"
                + "    _______________________________________________________\n";
    }

    /**
     * Shows the loading error message.
     *
     * @return The loading error message as a string.
     */
    public String showLoadingError() {
        return "    _______________________________________________________\n"
                + "     An error occurred while loading tasks.\n"
                + "    _______________________________________________________\n";
    }

    /**
     * Shows the search list.
     *
     * @param tasks The list of tasks.
     * @param searchDate The search date.
     * @return The search list message as a string.
     */
    public String showSearchList(List<Task> tasks, LocalDate searchDate) {
        StringBuilder sb = new StringBuilder();
        sb.append("    _______________________________________________________\n");
        sb.append("     Here are the deadlines/events in your list that's due/occurring on ").append(searchDate).append(":\n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline deadline) {
                if (deadline.getDeadline().equals(searchDate)) {
                    sb.append("     ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
                }
            } else if (tasks.get(i) instanceof Event event) {
                if (event.getEndEvent().equals(searchDate)
                        || event.getStartEvent().equals(searchDate)
                        || (event.getStartEvent().isBefore(searchDate)
                        && event.getEndEvent().isAfter(searchDate))) {
                    sb.append("     ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
                }
            }
        }
        sb.append("    _______________________________________________________\n");
        return sb.toString();
    }

    /**
     * Shows the find list.
     *
     * @param tasks The list of tasks.
     * @param keyword The keyword to search for.
     * @return The find list message as a string.
     */
    public String showFindList(List<Task> tasks, String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("    _______________________________________________________\n");
        sb.append("     Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskName().contains(keyword)) {
                sb.append("     ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        sb.append("    _______________________________________________________\n");
        return sb.toString();
    }
}
