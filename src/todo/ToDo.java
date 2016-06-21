/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author udhavsethi
 */
public class ToDo extends Application {
    
    private static final ObservableList<TaskUnit> taskList = FXCollections.observableArrayList();
    private static final ObservableList<TaskUnit> doneList = FXCollections.observableArrayList();
    private TableView taskTable = new TableView();
    private TableView doneTable = new TableView();
    
    @Override
    public void start(Stage primaryStage) {
        taskList.addListener(new ListChangeListener() {
           
            @Override
            public void onChanged(ListChangeListener.Change change) {
//                System.out.println(taskList);
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
        
        //table to hold "todo" items start
        taskTable.setEditable(true);
        
        TableColumn taskNameCol = new TableColumn("taskName");
        taskNameCol.setMinWidth(100);
        taskNameCol.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        
        TableColumn isDoneCol = new TableColumn("isDone");
        isDoneCol.setMinWidth(100);
        isDoneCol.setCellValueFactory(new PropertyValueFactory<>("isDone"));
        
        
        taskTable.setItems(taskList);
        taskTable.getColumns().addAll(isDoneCol, taskNameCol);
        isDoneCol.setCellFactory(new Callback<TableColumn<TaskUnit,Boolean>, TableCell<TaskUnit,Boolean>>() {
         
            @Override
            public TableCell<TaskUnit,Boolean> call(TableColumn<TaskUnit,Boolean> p) {
                final CheckBoxTableCell<TaskUnit, Boolean> ctCell = new CheckBoxTableCell<>();
                ctCell.setSelectedStateCallback(new Callback<Integer, ObservableValue<Boolean>>() {
                   
                    @Override
                    public ObservableValue<Boolean> call(Integer index) {
                        System.out.println("ctCell callback");
                        return taskList.get(index).checkedProperty();
                    }
                });
                
                return ctCell;
            }
        });
        
        grid.add(taskTable, 0, 1);
        
        //table to hold "todo" items end
        
        //table to hold "done" items start
        taskTable.setEditable(true);
        
        TableColumn dtaskNameCol = new TableColumn("taskName");
        dtaskNameCol.setMinWidth(100);
        dtaskNameCol.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        
        TableColumn disDoneCol = new TableColumn("isDone");
        disDoneCol.setMinWidth(100);
        disDoneCol.setCellValueFactory(new PropertyValueFactory<>("isDone"));
        doneTable.setItems(doneList);
        doneTable.getColumns().addAll(disDoneCol, dtaskNameCol);
        disDoneCol.setCellFactory(new Callback<TableColumn<TaskUnit,Boolean>, TableCell<TaskUnit,Boolean>>() {
         
            @Override
            public TableCell<TaskUnit,Boolean> call(TableColumn<TaskUnit,Boolean> q) {
                final CheckBoxTableCell<TaskUnit, Boolean> dtCell = new CheckBoxTableCell<>();
                dtCell.setSelectedStateCallback(new Callback<Integer, ObservableValue<Boolean>>() {
                   
                    @Override
                    public ObservableValue<Boolean> call(Integer dIndex) {
                        System.out.println("dtCell callback");
                        System.out.println(doneList);
                        System.out.println(dIndex);
                        System.out.println(doneList.get(dIndex).checkedProperty());
                        return doneList.get(dIndex).checkedProperty();
                    }
                });
                
                return dtCell;
            }
        });
        
        grid.add(doneTable, 0, 2);
        
        //table to hold "done" items end
        
        TextField taskInput = new TextField();
        taskInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER)  {
                    if ((taskInput.getText() != null) && !(taskInput.getText().isEmpty())) {
                        TaskUnit tUnit = new TaskUnit(taskInput.getText(), false);
                        tUnit.checkedProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue o, Object oldVal, Object newVal) {
                                System.out.println((Boolean)oldVal);
                                if ((Boolean)oldVal == false) {
                                    taskList.remove(tUnit);
                                    doneList.add(tUnit);
                                } else {
                                    doneList.remove(tUnit);
                                    taskList.add(tUnit);
                                }
                            }
                        });
                        taskList.add(tUnit);
                        System.out.println(taskList);
                        taskInput.clear();
                    }
                }
            }
        });
        grid.add(taskInput, 0, 3);
        
        Button addTaskBtn = new Button();
        addTaskBtn.setText("Add Task");
        addTaskBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if ((taskInput.getText() != null) && !(taskInput.getText().isEmpty())) {
                    
                    TaskUnit tUnit = new TaskUnit(taskInput.getText(), false);
                    tUnit.checkedProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue o, Object oldVal, Object newVal) {
                            System.out.println(tUnit.getTaskName());                            
                            System.out.println(oldVal);
                            System.out.println(newVal);

                        }
                    });
                    taskList.add(tUnit);
                    taskInput.clear();
                }
            }
        });
        
        HBox addTaskBox = new HBox(10);
        addTaskBox.setAlignment(Pos.BOTTOM_RIGHT);
        addTaskBox.getChildren().add(addTaskBtn);
      
        grid.add(addTaskBox, 1, 3);
        
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