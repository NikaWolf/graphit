package graphit.BFS;

import java.awt.Point;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BFS {
    Button firstButton;
    Button prevButton;
    Button nextButton;
    Button lastButton;
    Button repeatButton;


    private AnchorPane drawPane;//Сам холст для рисования
    private List<GrafObj> GRAPH;//Система - ГРАФ
    private int points; //кол-во точек в графе
    private Circle circlePoint[]; //графические объекты - вершины графа
    private Button pointsButtons[]; //Кнопки - вершины (Для выбора начальной вершины)
    private Text number[];
    private Text weight[];
    private TextArea stepsInfo;
    private Text NameAlg;
    int BEGIN; //точка начала работы графа
    Slides BFSslides ;//слайды, для отображения анимации
    String filePoints = "9points.txt";
    public void changeFileWithPoints(int x)
    {
        if(x==5)
            filePoints = "5points.txt";
        else if (x==9)
            filePoints = "9points.txt";
        else filePoints = "12points.txt";
    }
    public void draw(AnchorPane pane)
    {
        BFSslides = new Slides();
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
         
        BFSslides.setArea(stepsInfo);
         
         
    }
    void initialize()
    {
        //инициализация
    }
    
    void readFromFile (String str)
    {//прочитать матрицу смежности из конкретного файла
        try {
            InputStream fis = BFS.class.getResourceAsStream(str);
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
            		GRAPH.add(newGO);   //добавляем в ГРАФ новую точку
            	}
            	int wght=0;//вес
            	ribObj newRib;

            	for (int n=0;n<points;n++) //cчитываем матрицу смежности. Проход по точкам графа (исходящие)
            	{
            		for (int j=0;j<points;j++) //Просматриваем возможные переходы
            		{
            			wght=sc.nextInt();  //если встретили в матрице ноль - перехода нет
            			if (wght!=0)       //не ноль - записываем вес. 
            			{                        
            				newRib = new ribObj();
            				newRib.setEnd(j);   //j - проход по точкам В КОТОРЫЕ попадаем
            				newRib.setWeight(wght); //вес, который не ноль (прочитали его ранее)
                                        Line myln = new Line();
                                       
                                        if(j>n)//то создаем ребро
                                        {
                                            myln.setStartX(GRAPH.get(n).position.x);
                                            myln.setStartY(GRAPH.get(n).position.y);
                                            myln.setEndX(GRAPH.get(j).position.x);
                                            myln.setEndY(GRAPH.get(j).position.y);
                                            myln.strokeProperty().set(Color.GRAY);
                                            myln.strokeWidthProperty().set(5);
                                            drawPane.getChildren().add(myln);
                                            
                                        }
                                        else//то ссылаемся на ребро
                                        {
                                            for(ribObj element: GRAPH.get(j).ribs)
                                            {
                                                if(element.end == n)
                                                {
                                                    myln=element.ribLine;
                                                   
                                                }
                                                    
                                            }
                                            
                                        }
                                        newRib.ribLine=myln;
                                        
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
                number = new Text[GRAPH.size()];
		for (int n=0;n<points;n++)
		{
                    circlePoint[n] =new Circle (30,  Color.rgb(156,216,255));
                    circlePoint[n].setCenterX(GRAPH.get(n).getPosition().x );
                    circlePoint[n].setCenterY(GRAPH.get(n).getPosition().y );
                    number[n] = new Text();
                    number[n].setLayoutX(GRAPH.get(n).getPosition().x-3);
                    number[n].setLayoutY(GRAPH.get(n).getPosition().y+3);
                    
                    number[n].textProperty().set(String.valueOf(n));
                    number[n].fillProperty().set(Color.BLACK);
                    circlePoint[n].setStyle("-fx-fill:  #eaeaea;");                  
                    drawPane.getChildren().add(circlePoint[n]);
                    drawPane.getChildren().add(number[n]);
		    System.out.println(n + ":(" + GRAPH.get(n).getPosition().x + ";" + GRAPH.get(n).getPosition().y + ")"); //Проверка точек содержащихся в графе
		}        
    }
    
    void drawButtons()
    {//рисование кнопок - вершин, для выбора начальной вершины 
        // в начале реализации алгоритма
        pointsButtons = new Button[points];
        for(int n=0;n<points; n++)
        {
            pointsButtons[n] = new Button ();
            pointsButtons[n].setPrefSize(48, 48);
            pointsButtons[n].getStyleClass().add("circleButton");
            pointsButtons[n].setLayoutX(circlePoint[n].getCenterX()-24);
            pointsButtons[n].setLayoutY(circlePoint[n].getCenterY()-24);
            drawPane.getChildren().add(pointsButtons[n]);
            pointsButtons[n].setText(String.valueOf(n));
            final int t=n;
            pointsButtons[n].setOnAction(new EventHandler<ActionEvent>() {
               @Override
                    public void handle(ActionEvent event) {
                        BEGIN = t;
                        for(int i=0;i<points;i++) 
                        {
                            pointsButtons[i].setVisible(false);
                            circlePoint[i].setRadius(20);
                            circlePoint[i].fillProperty().set(Color.WHITE);
                            circlePoint[i].strokeProperty().set(Color.BLACK);
                            
                            
                        }
                        circlePoint[t].strokeProperty().set(Color.RED);
                        circlePoint[t].strokeWidthProperty().set(2);
                        realisation(); 
                        //firstButton.disableProperty().set(false);
                        nextButton.disableProperty().set(false);
                        //BFSslides.addCadr();
                        //BFSslides.step1Text();
                        BFSslides.firstCadr();
                        repeatButton.disableProperty().set(false);
                        
                    } 
            });
        }
    }
    
    void realisation()
    {
                int color[] = new int[GRAPH.size()];//массив для обозначения текущей окраски каждой точки
		int d[] = new int[GRAPH.size()]; //расстояние от начальной вершины, до любой другой храниться так же в массиве
		//int pi[] = new int[GRAPH.size()];//массив предшественников
		for(int n=0; n<GRAPH.size(); n++){ //обнуляем все значения
			color[n]=0;
			d[n]=100000;
                        //weight[n].textProperty().set("∞");
                        //number[n].textProperty().set("∞");
                        //в книге это бесконечность. Но можно выбрать любое достаточно большое число
			//pi[n]=-1;//таким образом обозначаю, что предшественник ни один из имеющихся
		}
                
		color[BEGIN]=1; //для начальной точки цвет обозначаем - СЕРЫЙ
                BFSslides.addCadr();
                BFSslides.cirlceFillWhiteToGray(circlePoint[BEGIN]);
                BFSslides.step1Text();
                //BFSslides.slidesAnimation.get(1).setWeight(weight, GRAPH.size());
                
		d[BEGIN]=0;//Переход до нее = 0
		//pi[BEGIN]=-1;//предшественников нет - нужно для построения дерева
		Queue <Integer> Q = new PriorityQueue <Integer>();//создаем пустую очередь
		Q.add(BEGIN);//и добавляем в нее начальный элемент
		while(!Q.isEmpty()){ //пока не опустеет очередь
			int u=Q.element();//вытаскиваем первое значение из очереди
			Q.remove();//и удаляем его
                        BFSslides.addCadr();
			for(ribObj element: GRAPH.get(u).ribs){ // тут element это текущий элемент в теле цикла
				if(color[element.end]==0)//если цвет точки белый
				{
                                        BFSslides.vectorFillGrayToRed(element.ribLine);
                                        BFSslides.step2Text();
                                        BFSslides.cirlceFillWhiteToGray(circlePoint[element.end]);
                                        
                                    

					color[element.end]=1;//окрашиваем в серы
					d[element.end]=d[u]+1;//переход до точки становится большим на единицу, чем у предшественника
					//pi[element.end]=u;//записываем предшественника

                                        Q.add(element.end);//в очередь Q доавляем текущий проверенный элемент
				}
                               
				color[element.end]=2;//иначе (если серый элемент) то окрашиваем в черный и проходим дальше
                                //BFSslides.cirlceFillGrayToBlack(circlePoint[element.end]);
			}
                        BFSslides.step2Text();
                         BFSslides.cirlceFillGrayToBlack(circlePoint[u]);
                         BFSslides.chengeColorOfNumberWhite(number[u]);
                         //number[u].fillProperty().set(Color.WHITE);
                        color[u]=2;//иначе (если серый элемент) то окрашиваем в черный и проходим дальше    
		}  
                BFSslides.addCadr();
                BFSslides.endStepText();
    }
   
    
    public void setControlButton(Button first, Button prev, Button next, Button last, Button repeat)
    {//установка кнопок и контроллеров на них
        firstButton = first;
        prevButton = prev;
        nextButton = next;
        lastButton = last;
        repeatButton = repeat;
        
        
        
        
        prevButton.setOnAction(new EventHandler<ActionEvent>() {
               @Override
                    public void handle(ActionEvent event) {
                   if(!BFSslides.ifirst())
                       BFSslides.prevCadr();
                   else prevButton.disableProperty().set(true);
                   nextButton.disableProperty().set(false);

                    } 
            });
        
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
               @Override
                    public void handle(ActionEvent event) {
                   if (!BFSslides.iflast())
                        BFSslides.nextCadr();
                   else nextButton.disableProperty().set(true);
                   //firstButton.disableProperty().set(false);
                   prevButton.disableProperty().set(false);
                   //firstButton.disableProperty().set(false);
                   if (BFSslides.iflast())
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
        stepsInfo.setText("Пожалуйста, выберете начальную вершину"); 
        
    }
    
    void createNameAlg()
    {
        NameAlg = new Text();
        NameAlg.setLayoutX(10);
        NameAlg.setLayoutY(20);
        NameAlg.styleProperty().set("-fx-font: regular 15pt System; ");
        NameAlg.setText("BFS: алгоритм поиска в ширину");
        
    }
}
