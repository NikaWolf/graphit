package graphit.BFord;
//класс - ребра графа

import javafx.scene.shape.Shape;

public class ribObj {
	int end;   //в какую точку направлено ребро
	int weight; //вес
       // Line ribLine;
        Shape ribLine;
	

	public ribObj() { //конструктор создает нулевую точку.
		end=0;
		weight=0;
	}
	public void setEnd(int x) {
		end=x;
	}
        public int getEnd(){
            return end;
        }
	public void setWeight(int x){
		weight=x;
	}
        public int getWeight(){
		return weight;
	}

}

