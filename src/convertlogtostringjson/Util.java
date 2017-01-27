/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertlogtostringjson;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.ComboBox;

/**
 *
 * @author felipekn
 */
public class Util {
    
    public static ComboBox setComboBoxValues(ComboBox combobox,HashMap<String, String> hashKeyValue){
        combobox.getItems().addAll(hashKeyValue.values());
        return combobox;
    }
    public static String getComboBoxKey(HashMap<String, String> hashKeyValue,String value){
        for (Map.Entry<String, String> entry : hashKeyValue.entrySet()) {
            if(entry.getValue().equals(value)){
                return entry.getKey();
            }
        }
        return null;
    }
}
