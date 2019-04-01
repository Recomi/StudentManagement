package site.recomi.studentmanagement.gui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;

import site.recomi.studentmanagement.R;

public class CampusAssociationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_association);
        ImmersionBar.with(this).init();
    }
}
