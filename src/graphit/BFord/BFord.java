package graphit.BFord;

import java.awt.Point;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;


public class BFord {
    Button firstButton;
    Button prevButton;
    Button nextButton;
    Button lastButton;
    Button repeatButton;
    // В качестве условной бесконечности 
    // выберем достаточно большое число 10^9
    private static final int INF = 1000 * 1000 * 1000;

    private AnchorPane drawPane;//Сам холст для рисования
    private List<GrafObj> GRAPH;//Система - ГРАФ
    private int points; //кол-во точек в графе
    private Circle circlePoint[]; //графические объекты - вершины графа
    private Text textPoint[];//отображение веса в вершине
    private StackPane stackPoint[];
    private Button pointButton; //Кнопка -  начальная вершина- для запуска алгоритма
    private Text number[];//номера вершин
    private TextArea stepsInfo;
    private Text NameAlg;
    Slides BFordslides = new Slides();//слайды, для отображения анимации
    String filePoints = "5points.txt";
    public void changeFileWithPoints(int x)
    {
        if(x==5)
            filePoints = "5points.txt";
        else if (x==4)
            filePoints = "4points.txt";
        else filePoints = "7points.txt";
    }
   
    public void draw(AnchorPane pane)
    {
                     
  BFordslides = new Slides();
         nextButton.disableProperty().set(true);
         prevButton.disableProperty().set(true);
         drawPane = pane;
         GRAPH =  new ArrayList<GrafObj>();//инициализируем граф - как список списка смежности точек
         points = 0;
         
         System.out.println(filePoints);
         readFromFile(filePoints);//Направляем в функцию отрисовки графа (передаем название файла)
         drawPoints();
         drawButtons(); 
         
         createTextArea();
         pane.getChildren().add(stepsInfo);
         
         createNameAlg();
         pane.getChildren().add(NameAlg);
         
        BFordslides.setArea(stepsInfo);
                 
         
    }
    void initialize()
    {
        //инициализация
    }
    
    void readFromFile (String str)
    {//прочитать матрицу смежности из конкретного файла
        try {
            InputStream fis = BFord.class.getResourceAsStream(str);
            Scanner sc = new Scanner(fis);
            points = sc.nextInt();  //первое значение в файле - кол-во точек в графе
            GrafObj newGO;   //определение объекта класса
            Point myPoint;  //определение точки
            	for (int j=0;j<points;j++) //для заданного кол-ва точек
            	{   
            		newGO = new GrafObj(); //создаем/заново_создаем объекты
            		myPoint = new Point();
            		
            		myPoint.setLocation(sc.nextInt(), sc.nextInt()); //передаем координату точки изъятую из потока
            		System.out.println(myPoint.x + " " + myPoint.y); //выводим на терминал
            		newGO.setPoint(myPoint);  //передаем переменную, в которую ранее изъяли из потока координаты
                        newGO.setTop(j);
            		GRAPH.add(newGO);   //добавляем в ГРАФ новую точку
            	}
            	int wght;//вес
            	ribObj newRib;

            	for (int n=0;n<points;n++){ //cчитываем матрицу смежности. Проход по точкам графа (исходящие)
                    for (int j=0;j<points;j++){ //Просматриваем возможные переходы
                        wght=sc.nextInt();  //если встретили в матрице ноль - перехода нет
            		if (wght!=0){ //не ноль - записываем вес
                            newRib = new ribObj();
                            newRib.setEnd(j);   //j - проход по точкам В КОТОРЫЕ попадаем
                            newRib.setWeight(wght); //вес, который не ноль (прочитали его ранее)
                            Label lbl = new Label();  
                            
                            boolean isarc = false;
                            Path path = new Path();
                            double beta = Math.atan2(GRAPH.get(n).position.y-GRAPH.get(j).position.y,
                                    GRAPH.get(j).position.x-GRAPH.get(n).position.x);
                            double alfa = Math.PI/18;
                            int r =40;
                            
                            for(GrafObj element:GRAPH){
                                if (element.getTop() == newRib.getEnd()){
                                    for (ribObj rib: element.getRibs()){
                                        if (rib.getEnd() == n){
                                            path.getElements().addAll(
                                                    new MoveTo(GRAPH.get(n).position.x, GRAPH.get(n).position.y),
                                                    new ArcTo((GRAPH.get(j).position.x-GRAPH.get(n).position.x)/2.0+20,
                                                    (GRAPH.get(j).position.y-GRAPH.get(n).position.y)/2.0+20,0,
                                                    GRAPH.get(j).position.x, GRAPH.get(j).position.y, false, false),
                                                    new MoveTo(GRAPH.get(j).position.x+10, GRAPH.get(j).position.y-10),
                                                    new LineTo(GRAPH.get(j).position.x+30, GRAPH.get(j).position.y-10),
                                                    new MoveTo(GRAPH.get(j).position.x+10, GRAPH.get(j).position.y-10),
                                                    new LineTo(GRAPH.get(j).position.x+20, GRAPH.get(j).position.y-30)
                                                    );
                                            lbl.setLayoutX(GRAPH.get(j).position.x-(GRAPH.get(j).position.x-
                                                    GRAPH.get(n).position.x)*2/5.0);
                                            lbl.setLayoutY((GRAPH.get(j).position.y-GRAPH.get(n).position.y)*2/5.0+
                                                    GRAPH.get(j).position.y-30);
                                            isarc=true;
                                            break;
                                        }
                                    }
                                    break; 
                                                
                                }
                            }
                            if(!isarc){
                                path.getElements().addAll(
                                        new MoveTo(GRAPH.get(n).position.x,
                                        GRAPH.get(n).position.y),
                                        new LineTo(GRAPH.get(j).position.x, 
                                        GRAPH.get(j).position.y),
                                        new LineTo((GRAPH.get(j).position.x-r*Math.cos(beta+alfa)), 
                                        (GRAPH.get(j).position.y+r*Math.sin(beta+alfa))),
                                         new MoveTo(GRAPH.get(j).position.x, GRAPH.get(j).position.y),
                                         new LineTo((GRAPH.get(j).position.x-r*Math.cos(beta-alfa)), 
                                         (GRAPH.get(j).position.y+r*Math.sin(beta-alfa)))
                                        );
                                double x = GRAPH.get(j).position.x-GRAPH.get(n).position.x;
                                double y = GRAPH.get(j).position.y-GRAPH.get(n).position.y;
                                if(x == 0){
                                    lbl.setLayoutX((GRAPH.get(n).position.x)+10);
                                    lbl.setLayoutY(GRAPH.get(j).position.y-
                                            (GRAPH.get(j).position.y-GRAPH.get(n).position.y)*2/5.0);
                                }
                                else if(y == 0){
                                   lbl.setLayoutX(GRAPH.get(j).position.x-(GRAPH.get(j).position.x
                                           -GRAPH.get(n).position.x)*2/5.0);
                                   lbl.setLayoutY(GRAPH.get(n).position.y+10); 
                                }
                                else{
                                    lbl.setLayoutX(GRAPH.get(j).position.x-(GRAPH.get(j).position.x-GRAPH.get(n).position.x)*2/5.0);
                                    lbl.setLayoutY(GRAPH.get(j).position.y-(GRAPH.get(j).position.y-GRAPH.get(n).position.y)*2/5.0);
                                    
                                }
                            }
                            path.setFill(null);
                            path.setStroke(Color.GRAY);
                            path.setStrokeWidth(2);
                            Integer weightes = wght;
                            lbl.setText(weightes.toString());
                            drawPane.getChildren().add(path);
                            drawPane.getChildren().add(lbl);
                            newRib.ribLine=path;
                            GRAPH.get(n).ribs.add(newRib); //Добавляем новое ребро к точке графа i из внешнего цикла
                                        
                        }  			
                    }
                }
        } catch (Exception ex) {       //отлавливаем ошибки и исключения (не опустел ли поток ранее, чем мы заончили изымать из него точки
            ex.printStackTrace();
        }
         
    }
    
    void drawPoints()
    {//нарисовать точки - вершины для графа (добавление на холст)
        System.out.println("Координаты точек");
         //появляются после выбора начальной вершины
         circlePoint = new Circle[GRAPH.size()];
         stackPoint = new StackPane[GRAPH.size()];
         textPoint = new Text[GRAPH.size()];
         number = new Text[GRAPH.size()];
         for (int n=0;n<points;n++){
             circlePoint[n] =new Circle (20,  Color.rgb(156,216,255));
             circlePoint[n].setCenterX(GRAPH.get(n).getPosition().x );
             circlePoint[n].setCenterY(GRAPH.get(n).getPosition().y );
             circlePoint[n].setStyle("-fx-fill:  #eaeaea;");
             circlePoint[n].strokeProperty().set(Color.BLACK);
             circlePoint[n].strokeWidthProperty().set(2);
             number[n] = new Text(Integer.toString(n));
             number[n].setLayoutX(GRAPH.get(n).getPosition().x-20);
             number[n].setLayoutY(GRAPH.get(n).getPosition().y-25);
             number[n].strokeWidthProperty().set(1);
             number[n].strokeProperty().set(Color.BLACK);
             
             textPoint[n] = new Text();
             textPoint[n].setText("∞");//условная бесконечность
             
             stackPoint[n] = new StackPane();
             stackPoint[n].setLayoutX(GRAPH.get(n).getPosition().x-20);
             stackPoint[n].setLayoutY(GRAPH.get(n).getPosition().y-20);
             stackPoint[n].getChildren().addAll(circlePoint[n], textPoint[n]);
             
             
             drawPane.getChildren().add(stackPoint[n]);
             drawPane.getChildren().add(number[n]);
             System.out.println(n + ":(" + GRAPH.get(n).getPosition().x + ";" + GRAPH.get(n).getPosition().y + ")"); //Проверка точек содержащихся в графе
         }
         textPoint[0].setText("0");
        
                
    }
    
    void drawButtons()
    {
        //рисование кнопок - вершин, для выбора начальной вершины 
        // в начале реализации алгоритма
        // в данном алгоритме -алгоритме Форда-Беллмана начальная вершина уже
        //задана поумолчанию - это 0 вершина, кнопка будет запускать алгоритм
        pointButton = new Button();
        pointButton.setPrefSize(60, 60);
        pointButton.getStyleClass().add("circleButton");
        pointButton.setLayoutX(circlePoint[0].getCenterX()-30);
        pointButton.setLayoutY(circlePoint[0].getCenterY()-30);
        drawPane.getChildren().add(pointButton);
        pointButton.setText(String.valueOf(0));
        pointButton.setOnAction(new EventHandler<ActionEvent>() {
               @Override
                    public void handle(ActionEvent event) {
                        
                       // circlePoint[0].fillProperty().set(Color.WHITE);
                        circlePoint[0].strokeProperty().set(Color.RED);
                        pointButton.setVisible(false);
                        circlePoint[0].setRadius(20);
                        for(int i=1;i<points;i++){
                            circlePoint[i].fillProperty().set(Color.WHITE);
                            circlePoint[i].strokeProperty().set(Color.BLACK);
                            
                        }
           
                        realisation();
                        nextButton.disableProperty().set(false);
                        BFordslides.addCadr();
                        BFordslides.ifirstCadr();
                        for(int i=1;i<GRAPH.size();i++){
                            BFordslides.setTextPoint(textPoint[i],INF);
                        }
                        repeatButton.disableProperty().set(false);
                        
                    } 
            });
        }
    
    
    void realisation()
    {

        // Создаем массив, i-ый элемент которого
        // будет хранить текущее расстояние от 1-ой
        // (или 0-ой в нашем случае 0-индексации)
        // до i-ой вершины графа
        int[] distance = new int[GRAPH.size()];
        // До начала работы алгоритма все расстояния,
        // кроме 0-го, равны бесконечности (условной)
        Arrays.fill(distance, INF);
        distance[0] = 0;
	for (int i = 0; i < GRAPH.size() - 1; i++) {
            for (GrafObj vert: GRAPH){
                for(ribObj element: vert.ribs) {
                    int from = vert.getTop();
                    int to = element.getEnd();
                    int weight = element.getWeight();
                    int dist = distance[to];
                    // Ясно, что если вершина from на
                    // текущем шаге работы алгоритма
                    // находится бесконечно далеко от
                    // 0-ой, то она не изменит ответ
                    if (distance[from] == INF) {
                        continue;
                    }

                    // В противном случае обновим
                    // расстояние до вершины to,
                    // это называют релаксацией

                    distance[to] = Math.min(distance[to],
                            distance[from] + weight);
                    System.out.println(textPoint[to]);
                    //System.out.println(from+","+to);
                    element.ribLine.setStrokeWidth(3);
                    BFordslides.addCadr();
                    if(dist == distance[to])//релаксации не было
                        BFordslides.stepText(2,from,to,distance[to]);
                    else//релаксация проведена
                        BFordslides.stepText(1,from,to,distance[to]);
                    BFordslides.vectorFillGrayToRed(element.ribLine);
                    BFordslides.setTextPoint(textPoint[to],distance[to]);                    
                }

            }
        }
        boolean notNegativeCycle = true; 
        BFordslides.addCadr();
        for(GrafObj vert:GRAPH){
            for(ribObj rib: vert.ribs){
                if(distance[rib.getEnd()] > distance[vert.getTop()] + rib.getWeight()){
                    BFordslides.stepText(-1,0,0,0);//есть отрицательный цикл
                    notNegativeCycle = false;
                    break;
                }
            }
        if(!notNegativeCycle)
            break;
        }
        if(notNegativeCycle) 
            BFordslides.stepText(0,0,0,0);//нет отрицательных циклов
        
        for (int i = 0; i < GRAPH.size(); i++) {
            if (distance[i] == INF) {
                System.out.println("NO");
            } else {
                System.out.println(distance[i]);
            }
        }
        	
    }

    
    public void setControlButton(Button first, Button prev, Button next, Button last, Button repeat)
    {//установка кнопок и контроллеров на них
        firstButton = first;
        prevButton = prev;
        nextButton = next;
        lastButton = last;
        repeatButton = repeat;
        
        lastButton.setVisible(false);
        
      /*  firstButton.setOnAction(new EventHandler<ActionEvent>() {
               @Override
                    public void handle(ActionEvent event) {
                        BFordslides.firstCadr();    
                    } 
            });*/
        prevButton.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   if(!BFordslides.ifirst())
                       BFordslides.prevCadr();
                   else prevButton.disableProperty().set(true);
                   nextButton.disableProperty().set(false);

                    }
            });
        
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   if (!BFordslides.iflast())
                        BFordslides.nextCadr();
                   else nextButton.disableProperty().set(true);
                  // firstButton.disableProperty().set(false);
                   prevButton.disableProperty().set(false);
                   if (BFordslides.iflast())
                        nextButton.disableProperty().set(true);
                    } 
            });
         repeatButton.setOnAction(new EventHandler<ActionEvent>() {
               @Override
                    public void handle(ActionEvent event) {
                   
                        drawPane.getChildren().clear();
                        draw(drawPane);
                    } 
            });
        

    }
    
    void createTextArea()
    {
        stepsInfo = new TextArea();
        stepsInfo.setLayoutX(25);
        stepsInfo.setLayoutY(100);
        stepsInfo.setPrefSize(175, 250);
        stepsInfo.setEditable(false);
        stepsInfo.setWrapText(true);
        stepsInfo.setStyle("-fx-background: null;");
        stepsInfo.setText("Инициализация графа перед началом работы алгоритма:"
                + "для всех вершин(кроме стартовой) расстояние полагается равным ∞."
                + " Поиск кратчайших путей из стартовой вершины во все остальные: для этого в цикле "
                + "по вершинам проводится релаксация для всех ребер."
                + " Для начала демонстрации алгоритма, "
                + "нажмите на стартовую вершину 0."); 
        
    }
    
        void createNameAlg()
    {
        NameAlg = new Text();
        NameAlg.setLayoutX(10);
        NameAlg.setLayoutY(20);
        NameAlg.styleProperty().set("-fx-font: regular 15pt System; ");
        NameAlg.setText("Алгоритм Беллмана-Форда");
        
    }
    
}
