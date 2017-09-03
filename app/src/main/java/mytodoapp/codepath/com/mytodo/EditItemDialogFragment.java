package mytodoapp.codepath.com.mytodo;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * Created by psethi2 on 9/2/17.
 */

public class EditItemDialogFragment extends DialogFragment implements View.OnClickListener {

    private EditText inputItemEditText;


    public EditItemDialogFragment(){

    }

    public static EditItemDialogFragment newInstance(int position, String item){
        EditItemDialogFragment dialogFragment = new EditItemDialogFragment();
        Bundle argument =  new Bundle();
        argument.putInt("item_position_key",position);
        argument.putString("item_key",item);
        dialogFragment.setArguments(argument);
        return dialogFragment;
    }

    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.75), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        super.onResume();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edititem_dialog_fragment, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputItemEditText = (EditText) view.findViewById(R.id.editText);
        view.findViewById(R.id.button).setOnClickListener(this);
        inputItemEditText.setText(getArguments().getString("item_key"));
        if(getArguments().getString("item_key").isEmpty()) {
            getDialog().setTitle("Add item");
        }else{
            getDialog().setTitle("Edit item");
        }
        inputItemEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onClick(View v) {
        OnEditItemListener listener = (OnEditItemListener) getActivity();
        listener.onFinishEditItem(getArguments().getInt("item_position_key"), inputItemEditText.getText().toString());
        dismiss();
    }

    public interface OnEditItemListener {
        void onFinishEditItem(int position, String item);
    }
}
