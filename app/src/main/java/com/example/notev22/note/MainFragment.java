package com.example.notev22.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.notev22.R;

public class MainFragment extends Fragment {

    Note currectNote;
    public static String KEY = "note";

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            currectNote = savedInstanceState.getParcelable(KEY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(KEY, currectNote);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main,container,false);

        LinearLayout linearLayout = (LinearLayout) v;
        String[] note_name = getResources().getStringArray(R.array.name_note_res);

        for (int i = 0; i < note_name.length; i++) {
            String name = note_name[i];
            TextView textView = new TextView((getContext()));
            textView.setText(name);
            textView.setTextSize(20);
            linearLayout.addView(textView);
            int valueI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currectNote = new Note(getResources().getStringArray(R.array.name_note_res)[valueI],valueI);
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, NoteViewFragment.newInstance(currectNote)).addToBackStack("").commit();
                }
            });
        }
        return v;
    }
}
