package site.recomi.studentmanagement.gui.fragments.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.AccountActivity;

public class SignInFragment extends Fragment {
    @BindView(R.id.button_login_login)
    Button login;

    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frag_account_signin,container,false);
        mContext = view.getContext();
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AccountActivity.class);
                startActivity(intent);
            }
        });
    }

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }
}
