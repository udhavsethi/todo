/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author udhavsethi
 */
public class ToDo extends Application {
    
    private TableView taskTable = new TableView();
    
    @Override
    public void start(Stage primaryStage) {
        //initializing data structures to hold list of tasks
        List<String> demoList = new ArrayList<String>();
        ObservableList<String> taskList = FXCollections.observableList(demoList);
        taskList.addListener(new ListChangeListener() {
           
            @Override
            public void onChanged(ListChangeListener.Change change) {
                System.out.println(taskList);
            }
        });
        
        //layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        
        Text scenetitle = new Text("Things to do:");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        taskTable.setEditable(true);
        
        TableColumn taskNameCol = new TableColumn("taskName");
        taskNameCol.setMinWidth(100);
        taskNameCol.setCellValueFactory(new PropertyValueFactory<TaskUnit,String>("taskName"));
        
        TableColumn isDoneCol = new TableColumn("isDone");
        isDoneCol.setMinWidth(100);
        isDoneCol.setCellValueFactory(new PropertyValueFactory<TaskUnit,String>("isDone"));
        
        taskTable.setItems(taskList);
        taskTable.getColumns().addAll(taskNameCol, isDoneCol);
        
        grid.add(taskTable, 0, 1);
        
        TextField taskInput = new TextField();
        grid.add(taskInput, 0, 2);
        
        Button addTaskBtn = new Button();
        addTaskBtn.setText("Add Task");
        addTaskBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if ((taskInput.getText() != null) && !(taskInput.getText().isEmpty())) {
                    //System.out.println(taskInput.getText());
                    taskList.add(taskInput.getText());
                    taskInput.clear();
                }
            }
        });
        
        HBox addTaskBox = new HBox(10);
        addTaskBox.setAlignment(Pos.BOTTOM_RIGHT);
        addTaskBox.getChildren().add(addTaskBtn);
      
        grid.add(addTaskBox, 1, 2);
        
        grid.setGridLinesVisible(true);
        
        //If scene dimensions are not set, the scene
        //defaults to the min size to display contents
        Scene scene = new Scene(grid, 300, 275);
        
        primaryStage.setTitle("ToDo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}