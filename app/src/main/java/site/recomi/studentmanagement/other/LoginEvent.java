package site.recomi.studentmanagement.other;

public class LoginEvent {
    private String id;
    private String name;
    private String headphoto;

    public LoginEvent(String id, String name, String headphoto) {
        this.id = id;
        this.name = name;
        this.headphoto = headphoto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadphoto() {
        return headphoto;
    }

    public void setHeadphoto(String headphoto) {
        this.headphoto = headphoto;
    }
}
