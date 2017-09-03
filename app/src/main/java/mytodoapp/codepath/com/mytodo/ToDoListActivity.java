package mytodoapp.codepath.com.mytodo;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ToDoListActivity extends AppCompatActivity implements EditItemDialogFragment.OnEditItemListener, View.OnClickListener{

    private ListView todoListView;
    private ArrayList<String> toDoItemList;
    private ArrayAdapter<String> toDoListAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoListView = (ListView) findViewById(R.id.todolist);
        toDoItemList = Utils.getItemsListFromFile(getFilesDir());
        findViewById(R.id.addButton).setOnClickListener(this);
        toDoListAdaper = new ItemArrayAdapter(this,toDoItemList);
        todoListView.setAdapter(toDoListAdaper);
        todoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                toDoItemList.remove(position);
                Utils.writeItemToFile(getFilesDir(), toDoItemList);
                toDoListAdaper.notifyDataSetChanged();
                return true;
            }
        });

        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showEditDialog(position, toDoItemList.get(position));
            }
        });

    }

    private void showEditDialog(int position, String item){
        FragmentManager fm = getSupportFragmentManager();
        EditItemDialogFragment editItemDialogFragment = EditItemDialogFragment.newInstance(position, item);
        editItemDialogFragment.show(fm, "edit_fragment");

    }

    @Override
    public void onFinishEditItem(int position, String item) {
        if(!item.isEmpty()) {
            if(position != -1) {
                toDoItemList.set(position, item);
            }else{
                toDoItemList.add(item);
            }
            Utils.writeItemToFile(getFilesDir(), toDoItemList);
            toDoListAdaper.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        showEditDialog(-1, "");
    }
}
