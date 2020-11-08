import com.racoon.ui.view.alogin.ILoginMethod;
import com.racoon.ui.view.alogin.LoginController;
import com.racoon.ui.view.login.Login;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
//        Login login = new Login();
//        login.show();
        //第二个版本的实现
        ILoginMethod loginMethod = new LoginController((userId, userPassword)->{
            System.out.println("登陆 userId：" + userId + "userPassword：" + userPassword);
        });
        loginMethod.doShow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
