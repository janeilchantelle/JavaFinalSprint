public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isAdmin;
    private boolean isDoctor;

    public User(int id, String firstName, String lastName, String email, String password, boolean isAdmin, boolean isDoctor) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isDoctor = isDoctor;
    }
    

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isDoctor() {
        return isDoctor;
    }
}
