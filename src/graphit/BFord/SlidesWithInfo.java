
package graphit.BFord;


import javafx.animation.ParallelTransition;
import javafx.scene.text.Text;

public class SlidesWithInfo {
    private ParallelTransition slidesAnimation = new ParallelTransition();
    private String text = new String();
    private String text1 = new String();
    Text textPointArea = new Text();
    
    public void setTextPointArea(Text text){
        textPointArea = text;
    }
    public Text getTextPointArea(){
        return textPointArea;
    }
    public void setInfo(String str)
    {
        text = str;
    }
    public void setInfo1(String str)
    {
        text1 = str;
    }
    
    public String getInfo()
    {
        return text;
    }
    public String getInfo1()
    {
        return text1;
    }
    
    public ParallelTransition getAnimation()
    {
        return slidesAnimation;
    }
    
   
}


