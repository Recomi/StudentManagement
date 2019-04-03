package site.recomi.studentmanagement.gui.fragments.main;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.MainActivity;
import site.recomi.studentmanagement.utils.MyDatabaseHelper;
import site.recomi.studentmanagement.entity.Notes;
import site.recomi.studentmanagement.gui.adapter.NotesAdapter;

public class NoteFragment extends Fragment {
    MainActivity mainActivity;
    private List<Notes> notes = new ArrayList<>();
    private String title,content;
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase db;
    private NotesAdapter notesAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container , false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        myDatabaseHelper = new MyDatabaseHelper(getContext() , "Notes.db" , null ,1);
        db = myDatabaseHelper.getWritableDatabase();

        recyclerView = getActivity().findViewById(R.id.notesRL);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        notesAdapter = new NotesAdapter(notes);
        recyclerView.setAdapter(notesAdapter);

        notes.clear();
        initData();
        recyclerView.setAdapter(new NotesAdapter(notes));
    }

    //初始化数据
    protected void initData(){
        Cursor cursor = db.query("TitleContentTable" ,new String[]{"title" , "content" , "month" , "day" , "id"} ,
                null , null , null , null , "id desc");
        if (cursor.moveToFirst()){
            do {
                String title = cursor.getString((cursor.getColumnIndex("title")));
                String content = cursor.getString((cursor.getColumnIndex("content")));
                if (content.length() > 50){
                    content = content.substring(0,50);
                }
                String month = cursor.getString((cursor.getColumnIndex("month")));
                String day = cursor.getString((cursor.getColumnIndex("day")));
                int id = cursor.getInt((cursor.getColumnIndex("id")));
                notes.add(new Notes(title , content , month , day , id));
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
