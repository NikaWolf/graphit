package graphit.BFS;
//класс - ребра графа

import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class ribObj {
	int end;   //в какую точку направлено ребро
	int weight; //вес
        Text weightText;
        Line ribLine;
	

	ribObj() { //конструктор создает нулевую точку.
		end=0;
		weight=0;
	}
	void setEnd(int x) {
		end=x;
	}
	void setWeight(int x){
		weight=x;
	}

}

