
package graphit;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GraphIt extends Application 
{
    private Stage stage; //поле класса - основная сцена
    private final double MINIMUM_WINDOW_WIDTH = 800.0;
    private final double MINIMUM_WINDOW_HEIGHT = 600.0;
    private boolean fullsize;
    
    
    @Override
    public void start(Stage primaryStage)
    {
       try {
            stage = primaryStage;
            stage.setTitle("GraphIt");
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            fullsize = false;       //с самого начала окно открывается не во весь экран
            gotoMainMenu();         //открываем главное меню.
            stage.initStyle(StageStyle.UNDECORATED);//сняли рамку с приложения 
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(GraphIt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) 
    {
        launch(args);
    }

    void fullsizeWindow() //процедура изменения размера окна/сцены
    {
        if (!fullsize) 
        {//измеряем экран пользователя и задаем размер окну приложения
            Rectangle2D screenBounds =Screen.getPrimary().getVisualBounds();
            stage.setWidth(screenBounds.getWidth());
            stage.setHeight(screenBounds.getHeight());
            stage.setX(0);
            stage.setY(0);
            fullsize=true;
        } 
        else 
        {//используя min заданные размеры окна уменьшаем
            stage.setX(200);
            stage.setY(100);
            stage.setWidth(MINIMUM_WINDOW_WIDTH);
            stage.setHeight(MINIMUM_WINDOW_HEIGHT);
            fullsize=false;
        } 
    }
    void iconfiedWindow() //свертывание окна
    {
        stage.setIconified(true);
    }
    private void gotoMainMenu() 
    {//Переход к главному меню
        //фактический создание основной сцены.
        try {
            GraphitController graphit = (GraphitController) replaceSceneContent("graphit.fxml");
            graphit.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(GraphIt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private Initializable replaceSceneContent(String fxml) throws Exception 
    {//Перерисовывание/загрузка сцены 
        FXMLLoader loader = new FXMLLoader();
        InputStream in = GraphIt.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(GraphIt.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
    
    
    
}
