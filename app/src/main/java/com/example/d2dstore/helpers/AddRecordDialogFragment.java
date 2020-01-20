package com.example.d2dstore.helpers;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.d2dstore.R;
import com.example.d2dstore.backgroundTasks.RecordDataTask;
import com.example.d2dstore.utils.Constants;

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
    String selectedRecordType = "";
    String selectedAmountType = "";
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

        String dateSelected = dpDate.getYear()+"-"+fullMonth(dpDate.getMonth())+"-"+dpDate.getDayOfMonth();

        spinnerRecordType.setAdapter(recordTypeAdapter);
        spinnerAmountType.setAdapter(amountTypeAdapter);
        actvDescription.setAdapter(adapter);
        tvSelectedDate.setText(dateSelected);
        dpDate.init(dpDate.getYear(), dpDate.getMonth(), dpDate.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tvSelectedDate.setText(year+"-"+fullMonth(monthOfYear)+"-"+dayOfMonth);
            }
        });

        spinnerRecordType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRecordType = recordTypes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerAmountType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAmountType = amountTypes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnSaveRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordDataTask recordDataTask = new RecordDataTask(getContext(),
                        selectedRecordType,
                        selectedAmountType,
                        etAmount.getText().toString(),
                        actvDescription.getText().toString(),
                        tvSelectedDate.getText().toString());
                recordDataTask.execute((Void) null);

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
