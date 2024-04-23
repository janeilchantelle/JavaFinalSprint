import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MedicineReminderManager {
    private List<MedicineReminder> reminders;

    public MedicineReminderManager() {
        this.reminders = new ArrayList<>();
    }

    public void addReminder(MedicineReminder reminder) {
        reminders.add(reminder);
    }

    public List<MedicineReminder> getRemindersForUser(int userId) {
        List<MedicineReminder> userReminders = new ArrayList<>();
        for (MedicineReminder reminder : reminders) {
            if (reminder.getUserId() == userId) {
                userReminders.add(reminder);
            }
        }
        return userReminders;
    }

    public List<MedicineReminder> getDueReminders(int userId) {
        List<MedicineReminder> dueReminders = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (MedicineReminder reminder : reminders) {
            // Here, you need to implement the logic to check if the reminder is due
            // For now, let's just check if the current time is after the reminder's schedule
            LocalDateTime reminderTime = LocalDateTime.parse(reminder.getSchedule(), formatter);
            if (now.isAfter(reminderTime)) {
                dueReminders.add(reminder);
            }
        }

        return dueReminders;
    }
}
