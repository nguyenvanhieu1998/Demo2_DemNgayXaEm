package nguyenvanhieu.fithou.demo2_demngayxaem;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edtNgay1,edtNgay2;
    TextView txtKQ;
    Button btnTinh;
    Calendar calenOne = Calendar.getInstance();
    Calendar calenTwo = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getControls();
        getEvents();

    }

    private void getEvents() {
        edtNgay1.setOnClickListener(this);
        edtNgay2.setOnClickListener(this);
        btnTinh.setOnClickListener(this);
//        btnTinh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    if(edtNgay1.getText().equals(""))
//                    {
//                        Toast.makeText(MainActivity.this,"k đc để trống" , Toast.LENGTH_SHORT).show();
//                    }
//
//
//            }
//        });
    }

    private void getControls() {
        edtNgay1 = (EditText) findViewById(R.id.id_edtNgay1);
        edtNgay2 = (EditText) findViewById(R.id.id_edtNgay2);
        txtKQ = (TextView) findViewById(R.id.id_txtKetQua);
        btnTinh = (Button) findViewById(R.id.id_btnTinh);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.id_edtNgay1:
                   selectDateOne();
                break;
            case R.id.id_edtNgay2:
                 selectDateTwo();
                break;
            case R.id.id_btnTinh:
                ketQua();
                break;
        }
    }

    private void ketQua() {

        if( edtNgay1.getText().toString().equals("") ||  edtNgay2.getText().toString().equals("") )
        {
            Toast.makeText(this, "Bạn phải nhập dữ liệu cho 2 ngày !", Toast.LENGTH_SHORT).show();
        }
        else if(calenTwo.getTimeInMillis() <= calenOne.getTimeInMillis())
        {
            Toast.makeText(this, "Ngày thứ 2 phải lớn hơn ngày thứ 1 !", Toast.LENGTH_SHORT).show();
        }
        else
        {
            int kq = (int) ((calenTwo.getTimeInMillis() - calenOne.getTimeInMillis())/(1000*60*60*24));
            txtKQ.setText("Số ngày xa nhau là : " + kq);
        }

    }

    private void selectDateTwo() {
//        calenTwo = Calendar.getInstance();
        int ngay = calenTwo.get(Calendar.DATE);
        int thang = calenTwo.get(Calendar.MONTH);
        int nam = calenTwo.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calenTwo.set(year,month,dayOfMonth);
                edtNgay2.setText(simpleDateFormat.format(calenTwo.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }

    private void selectDateOne() {
//        calenOne = Calendar.getInstance();
        int ngay = calenOne.get(Calendar.DATE);
        int thang = calenOne.get(Calendar.MONTH);
        int nam = calenOne.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calenOne.set(year,month,dayOfMonth);
                edtNgay1.setText(simpleDateFormat.format(calenOne.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
}
