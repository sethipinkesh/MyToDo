package mytodoapp.codepath.com.mytodo;

import java.util.Date;

/**
 * Created by psethi2 on 9/2/17.
 */

public class ToDoItem {
    private String itemName;
    private Date dueDate;

    public ToDoItem(){

    }

    public String getItemName(){
        return itemName;
    }

    public void setItemName(String name){
        itemName =  name;
    }

    public Date getDueDate(){
        return dueDate;
    }

    public void setDueDate(Date date){
        dueDate =  date;
    }

}
