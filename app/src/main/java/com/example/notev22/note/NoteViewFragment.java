package com.example.notev22.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.notev22.R;

public class NoteViewFragment extends Fragment {

    public static String ARG_NOTE = "note";
    protected Note note;

    public static NoteViewFragment newInstance(Note note) { //приходит название заметки
        NoteViewFragment noteViewFragment= new NoteViewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, note); // поместили в bundle
        noteViewFragment.setArguments(bundle); //аргумент помещаем в bundle
        return noteViewFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { //если кто-то сидит в аргументе
            this.note = getArguments().getParcelable(ARG_NOTE); // значит достаем заметку
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { //inflater - впихиватель / conteiner - создали в MainActivity (getSupportFragmentManager) и запихнули в FrameLayout (menu_notes)
        View view = inflater.inflate(R.layout.fragment_note_view, container, false);//Читается: помести в контейнер что-то | false - НЕ передаются темы, настройки и тд в родительский контейнер

        TextView name_note = view.findViewById(R.id.name_note);
        TextView content_note = view.findViewById(R.id.content_note);
        TextView data_note = view.findViewById(R.id.data_note);

        name_note.setText(this.note.getName());

        String[] content = getResources().getStringArray(R.array.content_note_res);
        content_note.setText(content[note.getIdMessage()]);

        String[] data = getResources().getStringArray(R.array.data_note_res);
        data_note.setText("Дата создания: " + data[note.getIdMessage()]);

        return view;//вернуть созданное view
    }

}
