public class Reminder {
    private int id;
    private int userId;
    private String description;
    private String reminderDate;

    public Reminder(int id, int userId, String description, String reminderDate) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.reminderDate = reminderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", reminderDate='" + reminderDate + '\'' +
                '}';
    }
}
