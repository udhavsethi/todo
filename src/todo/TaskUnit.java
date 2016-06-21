/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
    
    //checkbox
    public SimpleBooleanProperty checkedProperty() {
        return this.isDone;
    }
    
    public Boolean getIsDone() {
        return this.checkedProperty().get();
    }
    
    public void setIsDone(Boolean iDone) {
        this.checkedProperty().set(iDone);
    }

}
