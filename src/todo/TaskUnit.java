/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author udhavsethi
 */
public class TaskUnit {
    private SimpleStringProperty taskName;
    private SimpleBooleanProperty isDone;
    
    public TaskUnit(String taskName, Boolean isDone) {
        this.taskName = new SimpleStringProperty(taskName);
        this.isDone = new SimpleBooleanProperty(isDone);
    }
    
    public String getTaskName() {
        return taskName.get();
    }
    
    public void setTaskName(String tName) {
        taskName.set(tName);
    }
    
    public Boolean getIsDone() {
        return isDone.get();
    }
    
    public void setIsDone(Boolean iDone) {
        isDone.set(iDone);
    }
}
