
package graphit.BFS;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.FillTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Slides 
{
    List <slidesWithInfo> slidesAnimation = new ArrayList <slidesWithInfo>();
    int cadr = 0;
    TextArea textArea;
    public void setArea(TextArea ta)
    {
        textArea = ta;
    }
    public void addCadr()
    {
        slidesWithInfo newCadr = new slidesWithInfo();
        slidesAnimation.add(newCadr);
        cadr = slidesAnimation.size() -1;
    }
    public void step1Text()
    {
        slidesAnimation.get(cadr).setInfo("Шаг 1: все вершины, за исключением исходного S, "
                + "окрашиваются в белый цвет, для каждой вершины присваивается значение ∞, "
                + "а в качестве родителя для каждой вершины устанавливается NIL");
    }
    public void step2Text()
    {
        slidesAnimation.get(cadr).setInfo("Далее пока остаются серые вершины в графе выполняется цикл: "
                + "просматриваются все вершины в списке смежности. "
                + "Если вершина белая, значит она еще не открыта и алгоритм открывает ее. "
                + "После того как все вершины из списка смежности просмотрены вершине присваивается черный цвет");
    }
    public void endStepText()
    {
        slidesAnimation.get(cadr).setInfo("Обход графа завершен. Красными линиями обозначено оставное дерево");
    
    }
    public void chengeColorOfNumberWhite(Text number)
    {
         FillTransition ft = new  FillTransition();
        ft.durationProperty().set(Duration.seconds(1));
        ft.shapeProperty().set(number);
        ft.fromValueProperty().set(Color.BLACK);
        ft.toValueProperty().set(Color.WHITE);
        ft.cycleCountProperty().set(1);
        ft.autoReverseProperty().set(false);
        slidesAnimation.get(cadr).getAnimation().getChildren().add(ft);
    }
    public void vectorFillGrayToRed(Line line)
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
    public void cirlceFillWhiteToGray(Circle circle)
    {
        FillTransition ft = new  FillTransition();
        ft.durationProperty().set(Duration.seconds(1));
        ft.shapeProperty().set(circle);
        ft.fromValueProperty().set(Color.WHITE);
        ft.toValueProperty().set(Color.GRAY);
        ft.cycleCountProperty().set(Timeline.INDEFINITE);
        ft.autoReverseProperty().set(true);
        slidesAnimation.get(cadr).getAnimation().getChildren().add(ft);
    }
    
    public void cirlceFillGrayToBlack(Circle circle)
    {
        FillTransition ft = new  FillTransition();
        ft.durationProperty().set(Duration.seconds(1));
        ft.shapeProperty().set(circle);
        ft.fromValueProperty().set(Color.GRAY);
        ft.toValueProperty().set(Color.BLACK);
        ft.cycleCountProperty().set(Timeline.INDEFINITE);
        ft.autoReverseProperty().set(true);
        slidesAnimation.get(cadr).getAnimation().getChildren().add(ft);
    }

    
    public void playCadr()
    {
        slidesAnimation.get(cadr).getAnimation().playFromStart();
        textArea.setText(slidesAnimation.get(cadr).getInfo());
       
        
    }
    
    public void stopCadr()
    {
        slidesAnimation.get(cadr).getAnimation().jumpTo(Duration.INDEFINITE);
        slidesAnimation.get(cadr).getAnimation().stop();
    }
    public void firstCadr()
    {
         while(cadr!=0) {
            prevCadr();
        }
    }
    public void lastCadr()
    {
        while(cadr<slidesAnimation.size()) {
            nextCadr();
        }
    }
    public void nextCadr()
    {
        stopCadr();
        cadr++;
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
    
    public void prevCadr()
    {
        slidesAnimation.get(cadr).getAnimation().jumpTo(Duration.ZERO);
        slidesAnimation.get(cadr).getAnimation().stop();
        cadr--;
        playCadr();
    }
    
    /*public void addCadr()
    {
        ParallelTransition newCadr = new ParallelTransition();
        slidesAnimation.add(newCadr);
        cadr = slidesAnimation.size() -1;
    }
    public void vectorBlack(Line line)
    {
        StrokeTransition strokeTransition;
        strokeTransition = StrokeTransitionBuilder.create()
            .duration(Duration.seconds(3))
            .shape(line)
            .fromValue(Color.RED)
            .toValue(Color.DODGERBLUE)
            .cycleCount(Timeline.INDEFINITE)
            .autoReverse(true)
            .build();
        slidesAnimation.get(cadr).getChildren().add(strokeTransition);       
        stopCadr();
        
    }
    public void playCadr()
    {
        slidesAnimation.get(cadr).play();
    }
    public void stopCadr()
    {
        slidesAnimation.get(cadr).jumpTo(Duration.ZERO);
        slidesAnimation.get(cadr).stop();
    }
    public void nextCadr()
    {
        if (cadr<slidesAnimation.size()-1)
        {
            stopCadr();
            cadr++;
            playCadr();
        }
    }
    public void lastCadr()
    {
            stopCadr();
            cadr=slidesAnimation.size()-1;
            playCadr();
    }
    public void prevCadr()
    {
        if (cadr>0)
        {
            stopCadr();
            cadr--;
            playCadr();
        }
    }
    public void firstCadr()
    {
            stopCadr();
            cadr=0;
            playCadr();
    }
   public boolean notEmpty()
   {
       
       if(slidesAnimation.size()>1) return true;
       else return false;
   }
   
   */
  
    
}
