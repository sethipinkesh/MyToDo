package mytodoapp.codepath.com.mytodo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by psethi2 on 9/2/17.
 */

public class ItemArrayAdapter extends ArrayAdapter<String> {

    public ItemArrayAdapter(Context context, ArrayList<String> toDoItems){
        super(context, 0, toDoItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.toitem_list_item, parent, false);
        }

        TextView itemName = (TextView) convertView.findViewById(R.id.listItemLabel);
        TextView itemDate = (TextView) convertView.findViewById(R.id.listItemLDate);

        itemName.setText(getItem(position));
//        itemDate.setText(""+toDoItem.getDueDate());

        return convertView;
    }
}
