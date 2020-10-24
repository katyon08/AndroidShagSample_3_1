package com.android.lessons.androidshagsample_3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lvMonth = (ListView) this.findViewById(R.id.lvMonth);
        String[] arrMonth = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        ArrayAdapter<String> lvAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, arrMonth);
        lvMonth.setAdapter(lvAdapter1);
        ListView lvDaysOfweek = (ListView) this.findViewById(R.id.lvDaysOfWeek);
        String[] arrDaysOfWeek = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
//        ArrayAdapter<String> lvAdapter2 = new ArrayAdapter<>(this, R.layout.my_listview_item, arrDaysOfWeek);
        ArrayAdapter<String> lvAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, arrDaysOfWeek);
        lvDaysOfweek.setAdapter(lvAdapter2);

        lvMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, String.valueOf(position) + " = " + parent.getAdapter().getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
        lvDaysOfweek.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        lvMonth.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
    }

    public void btnClick(View v) {
        ListView lvMonth = (ListView) this.findViewById(R.id.lvMonth);
        int index = lvMonth.getCheckedItemPosition();
        if (index != -1) {
            Toast.makeText(this, lvMonth.getAdapter().getItem(index).toString(), Toast.LENGTH_SHORT).show();
            makeAllert();
        } else {
            Toast.makeText(this, "Ничего	не	выбрано", Toast.LENGTH_SHORT).show();
        }
    }

    private void makeAllert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog);
        builder.setMessage("2 * 2 = 4?");
        builder.setTitle("Ответьте	на	вопрос");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(MainActivity.this, "Верно!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Не	верно!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    public void datePick(View v) {
        Calendar C = Calendar.getInstance();
        final int[] year = {C.get(Calendar.YEAR)};
        final int[] month = {C.get(Calendar.MONTH)};
        final int[] day = {C.get(Calendar.DAY_OF_MONTH)};
        DatePickerDialog picker = new DatePickerDialog(this, null, year[0], month[0], day[0]) {
            @Override
            public void onDateChanged(DatePicker view, int _year, int _month, int _day) {
                year[0] = _year;
                month[0] = _month;
                day[0] = _day;
            }
        };

        picker.setButton(DialogInterface.BUTTON_POSITIVE, "Выбрать", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String txt = ((day[0] < 10) ? "0" : "") + day[0] + "/" + ((month[0] < 9) ? "0" : "") + (month[0] + 1) + "/" + year[0];
                Toast.makeText(MainActivity.this, "Выбранная	дата	дд/мм/гггг	:	" + txt, Toast.LENGTH_SHORT).show();
            }
        });
        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Отменено", Toast.LENGTH_SHORT).show();
            }
        });
        picker.show();
    }
}