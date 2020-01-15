package com.example.d2dstore.helpers;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.d2dstore.R;
import com.example.d2dstore.utils.Constants;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddRecordDialogFragment extends DialogFragment {

    EditText etAmount;
    TextView tvSelectedDate;
    Spinner spinnerAmountType;
    Spinner spinnerRecordType;
    AutoCompleteTextView actvDescription;
    DatePicker dpDate;
    Button btnSaveRecord;
    Map<String, String> records;

    String[] recordTypes = {"Store", "Online", "Debt", "Todo"};
    String[] amountTypes = {"In", "Out"};
    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflater = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_record, null);

        etAmount = inflater.findViewById(R.id.et_amount);
        tvSelectedDate = inflater.findViewById(R.id.tv_selected_date);
        spinnerAmountType = inflater.findViewById(R.id.spinner_amount_type);
        spinnerRecordType = inflater.findViewById(R.id.spinner_record_type);
        actvDescription = inflater.findViewById(R.id.et_description);
        dpDate = inflater.findViewById(R.id.dp_record_date);
        btnSaveRecord = inflater.findViewById(R.id.btn_save_record);

        ArrayAdapter recordTypeAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, recordTypes);
        ArrayAdapter amountTypeAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, amountTypes);
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, Constants.mostUsedRecord);

        String dateSelected = dpDate.getDayOfMonth()+"-"+fullMonth(dpDate.getMonth())+"-"+dpDate.getYear();

        spinnerRecordType.setAdapter(recordTypeAdapter);
        spinnerAmountType.setAdapter(amountTypeAdapter);
        actvDescription.setAdapter(adapter);
        tvSelectedDate.setText(dateSelected);
        dpDate.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                tvSelectedDate.setText(dayOfMonth+"-"+fullMonth(monthOfYear)+"-"+year);
            }
        });

        records = new HashMap<>();
        btnSaveRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        builder.setTitle("Add new record");
        builder.setView(inflater)
                .setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AddRecordDialogFragment.this.getDialog().dismiss();
                    }
                });
        return builder.create();
    }

    public String fullMonth(int month){
        month++;
        String stringMonth = String.valueOf(month + 1);
        stringMonth = stringMonth.length() < 9 ? "0" + month : stringMonth;
        return stringMonth;
    }
}
