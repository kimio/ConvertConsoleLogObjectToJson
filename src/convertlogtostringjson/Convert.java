/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertlogtostringjson;

import java.util.HashMap;

/**
 *
 * @author felipekn
 */
public class Convert {
    public String log;
    public String json;
    public String model;
    public String filter;
    public HashMap<String,String> vars;
    
    public String convertToJson(){
        vars = new HashMap();
        String[] modelRows = model.split("\n");
        String[] logRows = log.split("\n");
        
        //verify type of attributes
        for(String property : modelRows){
            String[] columns = property.split(" ");
            if(columns.length>1){
                String varName = removeInvalidString(columns[columns.length-1]);
                String type = removeInvalidString(columns[columns.length-2]);

                //add var
                vars.put(varName, type);
            }
        }
        //verify log rows
        for(String logRow : logRows){
            json+= verifyXcodeLog(logRow.trim())+"\n";
        }
        return removeInvalidString(json.replace("null[", "[").replace(",\n}", "\n}"));
    }
    private String removeInvalidString(String string){
        return string.replace("*","").replace(";", "");
    }
    private String verifyXcodeLog(String logRow){
        if(logRow.contains(" {")){
            return "{";
        }
        if(logRow.equals("},")){
            return "},";
        }
        if(logRow.equals("}")){
            return "}";
        }
        if(logRow.contains("Array")){
            return "[";
        }
        if(logRow.equals(")")){
            return "]";
        }
        //verify var type
        String[] logColumns = logRow.split(" = ");
        
        if(logColumns.length>1){
            String key = logColumns[0].trim();
            String value = logColumns[1].trim().replace(";", "");
            if(vars.containsKey(key)){
                //Type is String
                boolean isString = (vars.get(key).toLowerCase().contains("string"));
                return "\""+key+"\":"+XcodeFilterNullAndCloseRow(isString, value);
            }
        }
        return "";
    }
    private String XcodeFilterNullAndCloseRow(boolean isString,String rowValue){
        if(filter.equals("1")){
            return rowValue.replace("(null)", "null")+",";
        }
        //remove null
        if(isString){
            return rowValue.replace("(null)", "\"\"")+",";
        }
        return rowValue.replace("(null)", "0")+",";
    }
    
}
