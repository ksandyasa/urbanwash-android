package id.urbanwash.wozapp.widget;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.callback.StartOrderActivityCallback;
import id.urbanwash.wozapp.util.Utility;

/**
 * Created by apridosandyasa on 4/11/16.
 */
@SuppressLint("ValidFragment")
public class DialogNoConnection extends AppCompatDialogFragment {
    private AppCompatActivity appCompatActivity;
    private View rootView;
    private AppCompatButton btn_content2_no_connection;
    private StartOrderActivityCallback startOrderActivityCallback;

    public DialogNoConnection() {

    }

    public DialogNoConnection(AppCompatActivity aca, StartOrderActivityCallback listener) {
        this.appCompatActivity = aca;
        this.startOrderActivityCallback = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.5f;
        windowParams.width = (int) (Utility.DisplayUtilty.getDisplayMetricFromWindow(this.appCompatActivity).widthPixels * 0.95);
        windowParams.height = (int) (Utility.DisplayUtilty.getDisplayMetricFromWindow(this.appCompatActivity).heightPixels * 0.4);
        windowParams.gravity = Gravity.CENTER;
        window.setAttributes(windowParams);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.dialog_order_cancelled, container, false);

        this.btn_content2_no_connection = (AppCompatButton) this.rootView.findViewById(R.id.btn_content2_no_connection);

        this.btn_content2_no_connection.setOnClickListener(new DismissDialogNoConnection());

        this.rootView.setAnimation(Utility.AnimationUtility.setAnimationOnView(this.appCompatActivity));

        return this.rootView;
    }

    class DismissDialogNoConnection implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            startOrderActivityCallback.ShowOrderCompleteView();
            dismiss();
        }
    }

}
