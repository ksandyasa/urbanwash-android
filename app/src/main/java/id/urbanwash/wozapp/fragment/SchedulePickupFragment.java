package id.urbanwash.wozapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.adapters.ArrayWheelAdapter;
import id.urbanwash.wozapp.R;
import id.urbanwash.wozapp.util.Utility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

/**
 * Created by apridosandyasa on 3/7/16.
 */
@SuppressLint("ValidFragment")
public class SchedulePickupFragment extends Fragment implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener{

    private View rootView;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private AppCompatActivity appCompatActivity;
    private AbstractWheel wheel_content_time_schedule;
    private ArrayWheelAdapter<String> wheel_content_time_schedule_adapter;
    private String[] wheelItems = {"08:00 to 08:30 AM", "08:30 to 09:00 AM",
            "09:30 to 10:00 AM", "10:00 to 10:30 AM",
            "10:30 to 11:00 AM", "11:00 to 11:30 AM",
            "11:30 to 12:00 AM", "12:00 to 13:30 PM",
            "13:30 to 14:00 PM", "14:00 to 14:30 PM",
            "14:30 to 15:00 PM", "15:00 to 15:30 PM"};

    // This is empty constructor. Do not delete it otherwise it will cause force close
    public SchedulePickupFragment() {

    }

    public SchedulePickupFragment(AppCompatActivity aca) {
        this.appCompatActivity = aca;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.rootView = inflater.inflate(R.layout.content_schedule_pickup, container, false);
        this.wheel_content_time_schedule = (AbstractWheel) this.rootView.findViewById(R.id.wheel_content_time_schedule);

        setTodayCalendarDate();
        setTodayTime();

        this.wheel_content_time_schedule_adapter =
                new ArrayWheelAdapter<String>(this.appCompatActivity, wheelItems);
        this.wheel_content_time_schedule_adapter.setItemResource(R.layout.wheel_content);
        this.wheel_content_time_schedule_adapter.setItemTextResource(R.id.tv_title_wheel_content);
        this.wheel_content_time_schedule.setViewAdapter(this.wheel_content_time_schedule_adapter);
        this.wheel_content_time_schedule.setCurrentItem(5);

        this.rootView.setAnimation(Utility.AnimationUtility.setAnimationOnView(this.appCompatActivity));

        return this.rootView;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
    }

    private void setTodayCalendarDate() {
    }

    private void setTodayTime() {
    }

    class ShowCalendar implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            datePickerDialog = DatePickerDialog.newInstance(
                    SchedulePickupFragment.this,
                    Utility.DateUtility.getCalendarInstance().get(Calendar.YEAR),
                    Utility.DateUtility.getCalendarInstance().get(Calendar.MONTH),
                    Utility.DateUtility.getCalendarInstance().get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show(appCompatActivity.getFragmentManager(), "Datepickerdialog");
        }
    }

    class ShowTime implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            timePickerDialog = TimePickerDialog.newInstance(
                    SchedulePickupFragment.this,
                    Utility.DateUtility.getCalendarInstance().get(Calendar.HOUR_OF_DAY),
                    Utility.DateUtility.getCalendarInstance().get(Calendar.MINUTE),
                    Utility.DateUtility.getCalendarInstance().get(Calendar.SECOND),
                    false
            );
            timePickerDialog.show(appCompatActivity.getFragmentManager(), "Timepickerdialog");
        }
    }
}
