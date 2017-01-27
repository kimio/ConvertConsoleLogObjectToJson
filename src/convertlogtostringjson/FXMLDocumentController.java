/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertlogtostringjson;

import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

/**
 *
 * @author felipekn
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextArea model;
    @FXML
    private TextArea log;
    @FXML
    private TextArea json;
    @FXML
    private ComboBox filter;
    
    private static HashMap<String,String> filterItens = new HashMap();
    
    @FXML
    private void convert(ActionEvent event) {
        Convert convert = new Convert();
        convert.model = model.getText();
        convert.log = log.getText();
        convert.filter = Util.getComboBoxKey(filterItens,(String)filter.getValue());
        json.setText(convert.convertToJson());
    }
    private void setFilterValues(){
        filterItens.put("1", "keep null values");
        filterItens.put("2", "remove null values");
        Util.setComboBoxValues(filter, filterItens);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFilterValues();
    }    
    
}
