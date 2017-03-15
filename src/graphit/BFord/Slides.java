
package graphit.BFord;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Slides 
{
    List <SlidesWithInfo> slidesAnimation = new ArrayList <SlidesWithInfo>();
    private int cadr;
    String previnf[] = new String[100];
    TextArea textArea;
    private static final int INF = 1000 * 1000 * 1000;
    
    public void setArea(TextArea ta)
    {
        textArea = ta;
    }
  

     public void addCadr()
    {
        SlidesWithInfo newCadr = new SlidesWithInfo();
        slidesAnimation.add(newCadr);
        cadr = slidesAnimation.size() -1;
        previnf[cadr]=new String();
        
    }
    public void stepText(int n,int from,int to,int weight)
    {
        switch(n){
            case 1:
                slidesAnimation.get(cadr).setInfo("Проверим, нужно ли проводить релаксацию для ребра "
                        + from +"-" + to + ". Для этого проверим, не входит ли оно в путь более оптимальный, чем"
                        + " ранее найденный(заданный), т.е. нельзя ли уменьшить вес минимального пути "
                        + "до вершины " + to + ". Действительно, для ребра " + from + "-" + to + 
                        " можно провести релаксацию и вес пути станет = " + weight);
                break;
            case 2:
                slidesAnimation.get(cadr).setInfo("Проверим, нужно ли проводить релаксацию для ребра "
                        + from +"-" + to + ". Для этого проверим, не входит ли оно в путь более оптимальный, чем"
                        + " ранее найденный(заданный), т.е. нельзя ли уменьшить вес минимального пути "
                        + "до вершины " + to + ". Нет, для ребра " + from + "-" + to + 
                        " релаксация не нужна. Вес остается прежним: " + weight);
                break;
            case -1:
                slidesAnimation.get(cadr).setInfo("Если в графе содержится цикл с отрицательным весом, дотижимым из истока,"
                        + "то решения не существует. В данном графе есть такой цикл -> решения не существует.");
                break;
            default:
                slidesAnimation.get(cadr).setInfo("Если в графе содержится цикл с отрицательным весом, дотижимым из истока,"
                        + "то решения не существует. В данном графе такого цикла нет. Вес всех кратчайших путей из стартовой "
                        + "вершины до всех остальных найден.");
   
        }
    }

    public void setTextPoint(Text text,Integer n){

        slidesAnimation.get(cadr).setTextPointArea(text);
        if(n == INF){
           slidesAnimation.get(cadr).setInfo1("∞"); 
        }
        else{
            slidesAnimation.get(cadr).setInfo1(n.toString());
        }

    };
       
    public void vectorFillGrayToRed(Shape line)
    {
        StrokeTransition st = new StrokeTransition();
        st.durationProperty().set(Duration.seconds(1));
        st.shapeProperty().set(line);
        st.fromValueProperty().set(Color.GRAY);
        st.toValueProperty().set(Color.RED);
        st.cycleCountProperty().set(Timeline.INDEFINITE);
        st.autoReverseProperty().set(true);
                
        slidesAnimation.get(cadr).getAnimation().getChildren().add(st);
    }
    public void playCadr()
    {
        
        previnf[cadr] = slidesAnimation.get(cadr).getTextPointArea().getText();
        slidesAnimation.get(cadr).getAnimation().play();
        textArea.setText(slidesAnimation.get(cadr).getInfo());
        slidesAnimation.get(cadr).getTextPointArea().setText(slidesAnimation.get(cadr).getInfo1());
        
       
    }
     public void playCadr1()
    {

        slidesAnimation.get(cadr).getAnimation().play();
        textArea.setText(slidesAnimation.get(cadr).getInfo());
        slidesAnimation.get(cadr).getTextPointArea().setText(slidesAnimation.get(cadr).getInfo1());
        
       
    }
    public void stopCadr()
    {
        slidesAnimation.get(cadr).getAnimation().jumpTo(Duration.INDEFINITE);
        slidesAnimation.get(cadr).getAnimation().stop();
    }
    public void nextCadr()
    {
        stopCadr();
        cadr++;
        playCadr();
    }

    public void prevCadr()
    {
        slidesAnimation.get(cadr).getAnimation().jumpTo(Duration.ZERO);
        slidesAnimation.get(cadr).getAnimation().stop();
        slidesAnimation.get(cadr).getTextPointArea().setText(previnf[cadr]);
        cadr--;
        playCadr1();
    }
    public void firstCadr()
    {
         while(cadr!=0) {
            prevCadr();
        }
    }
     public void ifirstCadr()
    {
         cadr=0;
         playCadr();
    }
    
   
    public boolean iflast()
    {
        if (cadr>slidesAnimation.size()-2)
            return true;
        else return false;
    }
    public boolean ifirst()
    {
        if (cadr<1)
            return true;
        else return false;
    }
    


 
}
 
 