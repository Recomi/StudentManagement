package site.recomi.studentmanagement;

public class Constant {
    public final static String PROTOCOL = "https://";
    public final static String DOMAIN = "recomi.site";
    public final static String PORT = "443";
    public final static String MODEL = "campus_system";
    public final static String ADDR_MODEL = PROTOCOL + DOMAIN+":" + PORT + "/" + MODEL;
    public final static String IMG_URL = ADDR_MODEL + "/img";
    public final static String ARTICLES_URL = ADDR_MODEL + "/articles";
    public final static String MAIN_PHP = ADDR_MODEL +"/CampusManagementSystem.php";
}
