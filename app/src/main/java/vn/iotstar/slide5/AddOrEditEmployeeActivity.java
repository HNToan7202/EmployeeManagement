package vn.iotstar.slide5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.iotstar.slide5.SQLlite.EmployeeDAO;
import vn.iotstar.slide5.model.Employee;

public class AddOrEditEmployeeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText employeeId, name, etsalary ;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit);

        employeeId = findViewById(R.id.etEmployeeId);
        name = findViewById(R.id.etName);
        etsalary = findViewById(R.id.etEmployeeSalary);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        findViewById(R.id.btnlistemp).setOnClickListener(this);

        readEmployee();
    }

    private void readEmployee(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if(bundle == null)
        {
            return;
        }
        String id = bundle.getString("id");
        EmployeeDAO dao = new EmployeeDAO(this);
        Employee employee = dao.getById(id);
        employeeId.setText(employee.getId());
        name.setText(employee.getName());
        etsalary.setText(""+employee.getSalary());
        btnSave.setText("Update");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                EmployeeDAO dao = new EmployeeDAO(this);
                Employee emp = new Employee();
                emp.setId(employeeId.getText().toString());
                emp.setName(name.getText().toString());
                emp.setSalary(Float.parseFloat(etsalary.getText().toString()));

                if(btnSave.getText().equals("Save")){
                    dao.insert(emp);
                }
                else {
                    dao.update(emp);
                }
                dao.insert(emp);
                Toast.makeText(this, "New Employee has been saved !",
                        Toast.LENGTH_LONG).show();

            case R.id.btnlistemp:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);


        }
    }
}