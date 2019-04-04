package site.recomi.studentmanagement.gui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import site.recomi.studentmanagement.R;

public class ClubPiece extends LinearLayout {
    public ClubPiece(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_club_piece , this);
    }

    public ClubPiece(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClubPiece(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
