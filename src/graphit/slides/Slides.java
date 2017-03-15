
package graphit.slides;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.FillTransition;
import javafx.animation.FillTransitionBuilder;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Slides 
{
    private List <ParallelTransition> slidesAnimation = new ArrayList <ParallelTransition>();
    int cadr = 0;
    
    public void addCadr()
    {
        ParallelTransition newCadr = new ParallelTransition();
        slidesAnimation.add(newCadr);
        cadr = slidesAnimation.size() -1;
    }
    public void vectorBlack(Line line)
    {
        FillTransition fillTransition;
        fillTransition = FillTransitionBuilder.create()
            .duration(Duration.seconds(3))
            .shape(line)
            .fromValue(Color.GRAY)
            .toValue(Color.BLACK)
            .cycleCount(Timeline.INDEFINITE)
            .autoReverse(true)
            .build();
        slidesAnimation.get(cadr).getChildren().add(fillTransition);
    }
    public void playCadr()
    {
        slidesAnimation.get(cadr).play();
    }
   
  
    
}
