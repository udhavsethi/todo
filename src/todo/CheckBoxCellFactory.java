/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import javafx.scene.control.TableCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

/**
 *
 * @author udhavsethi
 */
public class CheckBoxCellFactory implements Callback{
    
    @Override
    public TableCell call(Object param) {
        CheckBoxTableCell<TaskUnit,Boolean> checkBoxCell = new CheckBoxTableCell();
        return checkBoxCell;
    } 
}
