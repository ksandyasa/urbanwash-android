package id.urbanwash.wozapp.widget;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.adapters.ArrayWheelAdapter;
import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.util.Utility;

/**
 * Created by apridosandyasa on 4/11/16.
 */
@SuppressLint("ValidFragment")
public class DialogEditDate extends AppCompatDialogFragment {
    private AppCompatActivity appCompatActivity;
    private View rootView;
    private AppCompatImageView iv_close_edit_date;
    private AppCompatButton btn_content3_edit_date;
    private AbstractWheel wheel_content_time_edit_date;
    private ArrayWheelAdapter<String> wheel_content_time_edit_date_adapter;
    private String[] wheelItems = {"08:00 to 08:30 AM", "08:30 to 09:00 AM",
                                   "09:30 to 10:00 AM", "10:00 to 10:30 AM",
                                   "10:30 to 11:00 AM", "11:00 to 11:30 AM",
                                   "11:30 to 12:00 AM", "12:00 to 13:30 PM",
                                   "13:30 to 14:00 PM", "14:00 to 14:30 PM",
                                   "14:30 to 15:00 PM", "15:00 to 15:30 PM"};

    public DialogEditDate() {

    }

    public DialogEditDate(AppCompatActivity aca) {
        this.appCompatActivity = aca;
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
        windowParams.height = (int) (Utility.DisplayUtilty.getDisplayMetricFromWindow(this.appCompatActivity).heightPixels * 0.6);
        windowParams.gravity = Gravity.CENTER;
        window.setAttributes(windowParams);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.dialog_edit_date, container, false);

        this.iv_close_edit_date = (AppCompatImageView) this.rootView.findViewById(R.id.iv_close_edit_date);
        this.wheel_content_time_edit_date = (AbstractWheel) this.rootView.findViewById(R.id.wheel_content_time_edit_date);
        this.btn_content3_edit_date = (AppCompatButton) this.rootView.findViewById(R.id.btn_content3_edit_date);

        this.wheel_content_time_edit_date_adapter =
                new ArrayWheelAdapter<String>(this.appCompatActivity, wheelItems);
        this.wheel_content_time_edit_date_adapter.setItemResource(R.layout.wheel_content);
        this.wheel_content_time_edit_date_adapter.setItemTextResource(R.id.tv_title_wheel_content);
        this.wheel_content_time_edit_date.setViewAdapter(this.wheel_content_time_edit_date_adapter);
        this.wheel_content_time_edit_date.setCurrentItem(5);

        this.iv_close_edit_date.setOnClickListener(new DismissDialogEditDate());
        this.btn_content3_edit_date.setOnClickListener(new SaveDialogEditDate());

        this.rootView.setAnimation(Utility.AnimationUtility.setAnimationOnView(this.appCompatActivity));

        return this.rootView;
    }

    class DismissDialogEditDate implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            dismiss();
        }
    }

    class SaveDialogEditDate implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            dismiss();
        }
    }
}
