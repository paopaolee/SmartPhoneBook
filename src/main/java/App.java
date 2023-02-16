import controller.ContactController;
import org.greenrobot.eventbus.EventBus;

public class App {
    public static void main(String[] args) {
        ContactController contactController = new ContactController();
        EventBus.getDefault().register(contactController);
        contactController.start();
    }
}
