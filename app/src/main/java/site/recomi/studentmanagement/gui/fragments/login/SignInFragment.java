package site.recomi.studentmanagement.gui.fragments.login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.arcsoft.face.ErrorInfo;
import com.arcsoft.face.FaceEngine;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.common.Constants;
import site.recomi.studentmanagement.gui.activities.AccountActivity;
import site.recomi.studentmanagement.gui.activities.FaceLoginActivity;
import site.recomi.studentmanagement.gui.activities.GuideActivity;
import site.recomi.studentmanagement.gui.fragments.Base.BaseFragment;

public class SignInFragment extends BaseFragment {
    private Toast toast = null;
    private static final int ACTION_REQUEST_PERMISSIONS = 0x001;
    private static final String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.READ_PHONE_STATE
    };


    @BindView(R.id.button_login_login)
    Button login;
    @BindView(R.id.btn_signin_face)
    Button btn_signin_face;
    @BindView(R.id.btn_load_face)
    Button btn_load_face;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.frag_account_signin,container,false);
        mContext = mView.getContext();
        ButterKnife.bind(this,mView);
        init();
        return mView;
    }

    private void init() {
        login.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, AccountActivity.class);
            startActivity(intent);
        });
        btn_signin_face.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, FaceLoginActivity.class);
            startActivity(intent);
        });
        btn_load_face.setOnClickListener(v -> {
            activeEngine(v);
        });
    }

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }


    /**
     * 激活引擎
     *
     * @param view
     */
    public void activeEngine(final View view) {
        if (!checkPermissions(NEEDED_PERMISSIONS)) {
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), NEEDED_PERMISSIONS, ACTION_REQUEST_PERMISSIONS);
            return;
        }
        if (view != null) {
            view.setClickable(false);
        }
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                FaceEngine faceEngine = new FaceEngine();
                int activeCode = faceEngine.active(mContext, Constants.APP_ID, Constants.SDK_KEY);
                emitter.onNext(activeCode);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer activeCode) {
                        if (activeCode == ErrorInfo.MOK) {
                            showToast(getString(R.string.active_success));
                        } else if (activeCode == ErrorInfo.MERR_ASF_ALREADY_ACTIVATED) {
                            showToast(getString(R.string.already_activated));
                        } else {
                            showToast(getString(R.string.active_failed, activeCode));
                        }

                        if (view != null) {
                            view.setClickable(true);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 检查权限
     * */
    private boolean checkPermissions(String[] neededPermissions) {
        if (neededPermissions == null || neededPermissions.length == 0) {
            return true;
        }
        boolean allGranted = true;
        for (String neededPermission : neededPermissions) {
            allGranted &= ContextCompat.checkSelfPermission(mContext, neededPermission) == PackageManager.PERMISSION_GRANTED;
        }
        return allGranted;
    }

    private void showToast(String s) {
        if (toast == null) {
            toast = Toast.makeText(mContext, s, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast.setText(s);
            toast.show();
        }
    }

}
