/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphit;

import graphit.BFS.BFS;
import graphit.BFord.BFord;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Валерий
 */
public class GraphitController implements Initializable {
    @FXML
    AnchorPane titleBoarder;
    @FXML
    AnchorPane paneForDrawing;
    @FXML
    ToolBar menuToolBar;
    @FXML 
    Button windowClose;
    @FXML
    AnchorPane mainBackground;
    @FXML 
    Button windowExpand;
    @FXML 
    Button windowMin;
    @FXML 
    Button menuBoarderButton;
    @FXML 
    AnchorPane menuAhchorPane;
    @FXML
    VBox menuVBox;
    @FXML
    Label labelGraphit;
    @FXML 
    Button firstButton;
    @FXML 
    Button prevButton;
    @FXML 
    Button nextButton;
    @FXML 
    Button lastButton;
    @FXML
    AnchorPane controlPane;
    @FXML
    AnchorPane WelcomePane;
    @FXML 
    Button repeatButton;
    @FXML 
    Button backButton;
    @FXML
    AnchorPane TheoryPane;
    @FXML 
    Button menuBackButton;
    
    private GraphIt application;
    private TranslateTransition menuHideTransition;//Анимация для ухода сцены
    private TranslateTransition menuShowTransition;//Анимация для возвращения сцены
    private TranslateTransition controlMenuShowTransition;//Анимация для показа меню-управления
    private FadeTransition welcomeFadeTransition;
    
    
    
    public void setApp(GraphIt application){
        this.application = application;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
                menuHideTransition = TranslateTransitionBuilder.create() //спрятать меню
                .duration(Duration.seconds(0.8))
                .node(menuVBox)
                .fromX(-20)
                .toX(-200)
                .cycleCount(1)
                .build();
                
                menuShowTransition = TranslateTransitionBuilder.create()//показать меню
                .duration(Duration.seconds(1))
                .node(menuVBox)
                .fromX(-200)
                .toX(0)
                .cycleCount(1)
                .build();
                
                controlMenuShowTransition = TranslateTransitionBuilder.create()//показать меню-control
                .duration(Duration.seconds(0.5))
                .node(controlPane)
                .fromY(0)
                .toY(-100)
                .cycleCount(1)
                .build();
                
                welcomeFadeTransition = FadeTransitionBuilder.create()
                .duration(Duration.seconds(1))
                .node(WelcomePane)
                .fromValue(1)
                .toValue(0)
                .cycleCount(1)
                .autoReverse(false)
                .build();
                
                firstButton.disableProperty().set(true);
                prevButton.disableProperty().set(true);
                nextButton.disableProperty().set(true);
                lastButton.disableProperty().set(true);
                
                
                
                //MainMenuCreate ();//Показываем главное меню
        
    }    
    public void exit(ActionEvent event) 
    {//выход из приложения
        Platform.exit();
    }
    
    public void maxWindow (ActionEvent event)
    {//изменение размера окна приложения
        this.application.fullsizeWindow();
    }
    
    public void minWindow (ActionEvent event)
    {//свертывание приложение
        this.application.iconfiedWindow();
    }

    public void WelcomeHideMenuShow()
    {
        welcomeFadeTransition.play();
        MainMenuShow();
        
        menuBoarderButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MainMenuShow();
                
            }
        });
    }
    
    
    public void MainMenuCreate ()//создание главного меню
    {
        menuVBox.getChildren().clear();//очищаем pane
        Button algorithmsButton = new Button(); //и добавляем все необходимые кнопки
        algorithmsButton.getStyleClass().add("menuButton");//с их обработчиками - переходами
        algorithmsButton.setPrefSize(200,50);//на нужные страницы
        algorithmsButton.setText("АЛГОРИТМЫ");
        algorithmsButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                AlgrithmsMenuShow();
                menuBackButton.disableProperty().set(false);
                menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                        MainMenuShow();         
            }
        });
            }
        });
        
        //menuVBox.getChildren().clear();
        Button tasksButton = new Button();
        tasksButton.getStyleClass().add("menuButton");
        tasksButton.setPrefSize(200,50);
        tasksButton.setText("ЗАДАНИЯ");
        tasksButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //ПЕРЕХОД НА ВКЛАДКУ С ЗАДАНИЯМИ
            }
        });
        
       //         menuVBox.getChildren().clear();
        Button settingsButton = new Button();
        settingsButton.getStyleClass().add("menuButton");
        settingsButton.setPrefSize(200,50);
        settingsButton.setText("НАСТРОЙКИ");
        settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //ПЕРЕХОД НА ВКЛАДКУ С НАСТРОЙКАМИ
            }
        });
        
        //menuVBox.getChildren().clear();
        Button creditsButton = new Button();
        creditsButton.getStyleClass().add("menuButton");
        creditsButton.setPrefSize(200,50);
        creditsButton.setText("ОБ АВТОРАХ");
        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //ПЕРЕХОД НА ВКЛАДКУ С ИНФОРМАЦИЕЙ О СОЗДАТЕЛЯХ
            }
        });
        
        Button ExitButton = new Button();
        ExitButton.getStyleClass().add("menuButton");
        ExitButton.setPrefSize(200,50);
        ExitButton.setText("ВЫХОД");
        ExitButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                 Platform.exit();
            }
        });
                
        
        menuVBox.getChildren().add(algorithmsButton);     //добавляем их всех на холст  
        menuVBox.getChildren().add(tasksButton);
        menuVBox.getChildren().add(settingsButton);
        menuVBox.getChildren().add(creditsButton);
        menuVBox.getChildren().add(ExitButton);

    }
    public void MainMenuShow()//анимация показа меню
    {//спрятать меню; обновить кнопки; показать меню
        menuBackButton.disableProperty().set(true);
        menuBoarderButton.disableProperty().set(true);
        paneForDrawing.getChildren().clear();
        controlPane.visibleProperty().set(false);
        menuHideTransition.play();
        menuHideTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainMenuCreate();
                menuShowTransition.play();
                
            }
        });
    }

    
    public void AlgrithmsMenuShow()
    {    //соответствующий обработчик для меню "алгоритмы"  
        menuBoarderButton.disableProperty().set(false);
        menuBackButton.disableProperty().set(false);
        menuHideTransition.play();
        menuHideTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AlgorithmsMenuCreate();
                menuShowTransition.play();
            }
        });
    }
    
    
    
    public void ElAlgMenuShow()
    {    //соответствующий обработчик для меню "алгоритмы"  
        menuBoarderButton.disableProperty().set(false);
        menuBackButton.disableProperty().set(false);
        menuHideTransition.play();
        menuHideTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ElAlgMenuCreate();
                menuShowTransition.play();
            }
        });
    }
    
    public void ElAlgMenuCreate()
    {//соответствующая фцнкция для показа "алгоритм" меню (ее создание и анимация показа)
        
        menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                        MainMenuShow();         
            }
        });
        menuVBox.getChildren().clear();
        Button WidthAlgButton = new Button();
        WidthAlgButton.getStyleClass().add("menuButton");
        WidthAlgButton.setPrefSize(200,50);
        WidthAlgButton.setText("ПОИСК В ШИРИНУ");
        WidthAlgButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //Окроет алгоритм в ширину
                //menuHideTransition.play();
                
                //menuHideTransition.setOnFinished(new EventHandler<ActionEvent>() {
                    //@Override//при нажатии переход на реализайию алгоритма "поиск в ширину"
                    //public void handle(ActionEvent event) {
                        //BFSmenuCreate();
                        BFSmenuShow();
                        
                        
                
                
            }
        });
        
        Button DFSAlgButton = new Button();
        DFSAlgButton.getStyleClass().add("menuButton");
        DFSAlgButton.setPrefSize(200,50);
        DFSAlgButton.setText("DFS алгоритм");
        DFSAlgButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                 //Переход на алгоритм 
            }
        }); 
        Button topSortAlgButton = new Button();
        topSortAlgButton.getStyleClass().add("menuButton");
        topSortAlgButton.setPrefSize(200,50);
        topSortAlgButton.setText("Топ-ая сортировка");
        topSortAlgButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                 //Переход на алгоритм 
            }
        });  
        
        
        
        menuVBox.getChildren().add(WidthAlgButton); //добавляем на холст кнопки
        menuVBox.getChildren().add(DFSAlgButton);

    }
    
    
        
     
    public void kratmenuShow()
    {    //соответствующий обработчик для меню "алгоритмы"   
        menuHideTransition.play();
        menuHideTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kratmenuCreate();
                menuShowTransition.play();
            }
        });
    }
    
    
    public void kratmenuCreate()
    {
        menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                AlgrithmsMenuShow();
            }
        });
        menuVBox.getChildren().clear();
        Button AlgDeixButton = new Button();
        AlgDeixButton.getStyleClass().add("menuButton");
        AlgDeixButton.setPrefSize(200,50);
        AlgDeixButton.setText("АЛГОРИТМ ДЕЙКСТРЫ");
        AlgDeixButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                
            }
        });
        
        Button BelForButton = new Button();
        BelForButton.getStyleClass().add("menuButton");
        BelForButton.setPrefSize(200,50);
        BelForButton.setText("АЛГОРИТМ БЕЛЛАНА-ФОРДА");
        BelForButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                 BFordmenuShow();
                 
                 
            }
        });  
        
        
        
        menuVBox.getChildren().add(AlgDeixButton); //добавляем на холст кнопки
        menuVBox.getChildren().add(BelForButton);
        
    }
    
    public void AlgorithmsMenuCreate() 
    {//соответствующая фцнкция для показа "алгоритм" меню (ее создание и анимация показа)
        
        menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                        MainMenuShow();         
            }
        });
        menuVBox.getChildren().clear();
        
        Button elAlgButton = new Button();
        elAlgButton.getStyleClass().add("menuButton");
        elAlgButton.setPrefSize(200,50);
        elAlgButton.setText("Элем-ые алгоритмы");
        elAlgButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                
                        ElAlgMenuShow();
                        
                        
                
                
            }
        });
        
        Button DeixtraAlgButton = new Button();
        DeixtraAlgButton.getStyleClass().add("menuButton");
        DeixtraAlgButton.setPrefSize(200,50);
        DeixtraAlgButton.setText("Построение мин. ост дерева");
        DeixtraAlgButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                 //Переход на алгоритм Дейкстры
            }
        });  
        Button kratAlgButton = new Button();
        kratAlgButton.getStyleClass().add("menuButton");
        kratAlgButton.setPrefSize(200,50);
        kratAlgButton.setText("Кратчайшие пути");
        kratAlgButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                 //Переход на алгоритм Дейкстры
                kratmenuShow();
            }
        }); 
        
        
        
        menuVBox.getChildren().add(elAlgButton); //добавляем на холст кнопки
        menuVBox.getChildren().add(DeixtraAlgButton);
        menuVBox.getChildren().add(kratAlgButton);

    }
    
    
    public void BFSmenuShow()
    {    //соответствующий обработчик для меню "алгоритмы"   
        menuHideTransition.play();
        menuHideTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BFSmenuCreate();
                menuShowTransition.play();
            }
        });
    }
    
    
    public void BFSmenuCreate()
    {
        menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                AlgrithmsMenuShow();
            }
        });
        menuVBox.getChildren().clear();
        Button TheoryButton = new Button();
        TheoryButton.getStyleClass().add("menuButton");
        TheoryButton.setPrefSize(200,50);
        TheoryButton.setText("ТЕОРИЯ");
        TheoryButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                menuHideTransition.play();
                menuHideTransition.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        TheoryPane.disableProperty().set(false);
                        TheoryPane.visibleProperty().set(true);
                    }
                });
                menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                        BFSmenuCreate();
                        menuShowTransition.play();  
                        TheoryPane.disableProperty().set(true);
                        TheoryPane.visibleProperty().set(false);
            }
        });
            }
        });
        
        Button RealisButton = new Button();
        RealisButton.getStyleClass().add("menuButton");
        RealisButton.setPrefSize(200,50);
        RealisButton.setText("ДЕМОНСТРАЦИЯ");
        RealisButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                 BFSpointsShow();
                 
                 
            }
        });  
        
        
        
        menuVBox.getChildren().add(TheoryButton); //добавляем на холст кнопки
        menuVBox.getChildren().add(RealisButton);
        
    }
    
    
        public void BFSpointsShow()
    {    //соответствующий обработчик для меню "BFS АЛГОРИТМ"   
        menuHideTransition.play();
        menuHideTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BFSpointsCreate();
                menuShowTransition.play();
            }
        });
    }
        
    
    
    
    public void BFSpointsCreate()
    {
        menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                BFSmenuShow();
            }
        });
        menuVBox.getChildren().clear();
        Button PointsButton5 = new Button();
        PointsButton5.getStyleClass().add("menuButton");
        PointsButton5.setPrefSize(200,50);
        PointsButton5.setText("5 ВЕРШИН");
        PointsButton5.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                menuVBox.getChildren().clear();
                        BFS BFSrealis = new BFS();
                        //mainBackground.getStyleClass().add("mainBackground");
                        BFSrealis.setControlButton(firstButton, prevButton, nextButton, lastButton, repeatButton);
                        BFSrealis.changeFileWithPoints(5);
                        BFSrealis.draw(paneForDrawing);
                        controlMenuShowTransition.play();
                        controlPane.visibleProperty().set(true);
                        
                        repeatButton.disableProperty().set(true);
                        
                        menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            paneForDrawing.getChildren().clear();
                                controlPane.visibleProperty().set(false);
                                BFSpointsShow();
                        }
                    });
            }
        });
        
        Button PointsButton9 = new Button();
        PointsButton9.getStyleClass().add("menuButton");
        PointsButton9.setPrefSize(200,50);
        PointsButton9.setText("9 ВЕРШИН");
        PointsButton9.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                 menuVBox.getChildren().clear();
                        BFS BFSrealis = new BFS();
                        //mainBackground.getStyleClass().add("mainBackground");
                        BFSrealis.setControlButton(firstButton, prevButton, nextButton, lastButton, repeatButton);
                        BFSrealis.changeFileWithPoints(9);
                        BFSrealis.draw(paneForDrawing);
                        controlMenuShowTransition.play();
                        controlPane.visibleProperty().set(true);
                        repeatButton.disableProperty().set(true);
                        
                        
                        menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            paneForDrawing.getChildren().clear();
                                controlPane.visibleProperty().set(false);
                                BFSpointsShow();
                        }
                    });
            }
        });  
        
        Button PointsButton15 = new Button();
        PointsButton15.getStyleClass().add("menuButton");
        PointsButton15.setPrefSize(200,50);
        PointsButton15.setText("12 ВЕРШИН");
        PointsButton15.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                  menuVBox.getChildren().clear();
                        BFS BFSrealis = new BFS();
                        //mainBackground.getStyleClass().add("mainBackground");
                        BFSrealis.setControlButton(firstButton, prevButton, nextButton, lastButton, repeatButton);
                        BFSrealis.changeFileWithPoints(12);
                        BFSrealis.draw(paneForDrawing);
                        controlMenuShowTransition.play();
                        controlPane.visibleProperty().set(true);
                        
                        repeatButton.disableProperty().set(true);
                       
                        
                        menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            paneForDrawing.getChildren().clear();
                                controlPane.visibleProperty().set(false);
                                BFSpointsShow();
                        }
                    });
            }
        });
        
        menuVBox.getChildren().add(PointsButton5); //добавляем на холст кнопки
        menuVBox.getChildren().add(PointsButton9);
        menuVBox.getChildren().add(PointsButton15);
        
    }
    
        public void BFordmenuShow()
    {    //соответствующий обработчик для меню "алгоритмы"   
        menuHideTransition.play();
        menuHideTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BFordmenuCreate();
                menuShowTransition.play();
            }
        });
    }
    
    
    public void BFordmenuCreate()
    {
        menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                AlgrithmsMenuShow();
            }
        });
        menuVBox.getChildren().clear();
        Button TheoryButton = new Button();
        TheoryButton.getStyleClass().add("menuButton");
        TheoryButton.setPrefSize(200,50);
        TheoryButton.setText("ТЕОРИЯ");
        TheoryButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                menuHideTransition.play();
                menuHideTransition.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        TheoryPane.disableProperty().set(false);
                        TheoryPane.visibleProperty().set(true);
                    }
                });
                menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                        BFordmenuCreate();
                        menuShowTransition.play();  
                        TheoryPane.disableProperty().set(true);
                        TheoryPane.visibleProperty().set(false);
            }
        });
            }
        });
        
        Button RealisButton = new Button();
        RealisButton.getStyleClass().add("menuButton");
        RealisButton.setPrefSize(200,50);
        RealisButton.setText("ДЕМОНСТРАЦИЯ");
        RealisButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                 BFordpointsShow();
                 
                 
            }
        });  
        
        
        
        menuVBox.getChildren().add(TheoryButton); //добавляем на холст кнопки
        menuVBox.getChildren().add(RealisButton);
        
    }
    
    
        public void BFordpointsShow()
    {    //соответствующий обработчик для меню "BFS АЛГОРИТМ"   
        menuHideTransition.play();
        menuHideTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BFordpointsCreate();
                menuShowTransition.play();
            }
        });
    }
        
    
    
    
    public void BFordpointsCreate()
    {
        menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                BFordmenuShow();
            }
        });
        menuVBox.getChildren().clear();
        Button PointsButton5 = new Button();
        PointsButton5.getStyleClass().add("menuButton");
        PointsButton5.setPrefSize(200,50);
        PointsButton5.setText("5 ВЕРШИН");
        PointsButton5.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                menuVBox.getChildren().clear();
                        BFord BFordrealis = new BFord();
                        //mainBackground.getStyleClass().add("mainBackground");
                        BFordrealis.setControlButton(firstButton, prevButton, nextButton, lastButton, repeatButton);
                        BFordrealis.changeFileWithPoints(5);
                        BFordrealis.draw(paneForDrawing);
                        controlMenuShowTransition.play();
                        controlPane.visibleProperty().set(true);
                        
                        repeatButton.disableProperty().set(true);
                        
                        menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            paneForDrawing.getChildren().clear();
                                controlPane.visibleProperty().set(false);
                                BFordpointsShow();
                        }
                    });
            }
        });
        
        Button PointsButton4 = new Button();
        PointsButton4.getStyleClass().add("menuButton");
        PointsButton4.setPrefSize(200,50);
        PointsButton4.setText("4 ВЕРШИНЫ");
        PointsButton4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                 menuVBox.getChildren().clear();
                        BFord BFordrealis = new BFord();
                        //mainBackground.getStyleClass().add("mainBackground");
                        BFordrealis.setControlButton(firstButton, prevButton, nextButton, lastButton, repeatButton);
                        BFordrealis.changeFileWithPoints(9);
                        BFordrealis.draw(paneForDrawing);
                        controlMenuShowTransition.play();
                        controlPane.visibleProperty().set(true);
                        repeatButton.disableProperty().set(true);
                        
                        
                        menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            paneForDrawing.getChildren().clear();
                                controlPane.visibleProperty().set(false);
                                BFordpointsShow();
                        }
                    });
            }
        });  
        
        Button PointsButton15 = new Button();
        PointsButton15.getStyleClass().add("menuButton");
        PointsButton15.setPrefSize(200,50);
        PointsButton15.setText("7 ВЕРШИН");
        PointsButton15.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                  menuVBox.getChildren().clear();
                        BFord BFordrealis = new BFord();
                        //mainBackground.getStyleClass().add("mainBackground");
                        BFordrealis.setControlButton(firstButton, prevButton, nextButton, lastButton, repeatButton);
                        BFordrealis.changeFileWithPoints(7);
                        BFordrealis.draw(paneForDrawing);
                        controlMenuShowTransition.play();
                        controlPane.visibleProperty().set(true);
                        
                        repeatButton.disableProperty().set(true);
                       
                        
                        menuBackButton.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            paneForDrawing.getChildren().clear();
                                controlPane.visibleProperty().set(false);
                                BFordpointsShow();
                        }
                    });
            }
        });
        
        menuVBox.getChildren().add(PointsButton4); //добавляем на холст кнопки
        menuVBox.getChildren().add(PointsButton5);
        menuVBox.getChildren().add(PointsButton15);
        
    }
    
    
    
}