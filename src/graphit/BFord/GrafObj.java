package graphit.BFord;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
//Объект графа - точка
public class GrafObj {
	Point position; //координаты точки
        int top;
	List<ribObj> ribs; //список ребер исходящий из этой точки(с весом и точками в которые входит)
	public GrafObj() //конструктор - динамическое присвоение пустого списка ребрам
	{
		ribs=new ArrayList<ribObj>();
                
	}
        public List<ribObj> getRibs(){
            return ribs;
        }
	public void setPoint(Point x){ //задание координаты точки
		position = x;
	}
	public Point getPosition(){  //возвращение координаты
		return position;
	}
        public void setTop(int x) {
		top=x;
	}
        public int getTop(){
            return top;
        }


}