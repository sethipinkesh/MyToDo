package mytodoapp.codepath.com.mytodo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by psethi2 on 9/1/17.
 */

public class Utils {
    public static void writeItemToFile(File fileDir, List<String> itemList){
        File file = new File(fileDir, "Item.txt");
        try {
            FileUtils.writeLines(file, itemList);
        }catch (Exception e){

        }
    }

    public static ArrayList<String> getItemsListFromFile(File fileDir){
        ArrayList<String> itemList =  new ArrayList<String>();
        File file = new File(fileDir, "Item.txt");
        try {
            itemList = (ArrayList<String>) FileUtils.readLines(file);
        }catch (Exception e){

        }
        return itemList;
    }
}
