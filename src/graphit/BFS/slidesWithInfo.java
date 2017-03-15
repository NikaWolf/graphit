/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphit.BFS;

import javafx.animation.ParallelTransition;
import javafx.scene.text.Text;

public class slidesWithInfo {
    private ParallelTransition slidesAnimation = new ParallelTransition();
    private String Text = new String();

    
    
    public void setInfo(String str)
    {
        Text = str;
    }
    
    public String getInfo()
    {
        return Text;
    }
    public ParallelTransition getAnimation()
    {
        return slidesAnimation;
    }

    
   
}


