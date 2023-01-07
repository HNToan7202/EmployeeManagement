package vn.iotstar.slide5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import vn.iotstar.slide5.SQLlite.DBHelper;
import vn.iotstar.slide5.SQLlite.EmployeeDAO;
import vn.iotstar.slide5.adapter.EmployeeAdapter;
import vn.iotstar.slide5.model.Employee;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EmployeeAdapter employeeAdapter;
    private ListView lvEmployees;
    private String emloyeeId;
    private List<Employee> list;

    @Override
    protected void onResume() {
        super.onResume();

        EmployeeDAO dao = new EmployeeDAO(this);
        List<Employee> listUpdate = dao.getAll();

        list.clear();
        listUpdate.forEach(item -> list.add(item)); //add trong list item

        employeeAdapter.notifyDataSetChanged(); //Cap nhat lai hien thi trong list view

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        DBHelper dbHelper = new DBHelper(this);
//        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
//        readableDatabase.close();

        findViewById(R.id.btnEdit).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
        findViewById(R.id.btnInsert).setOnClickListener(this);

        lvEmployees = findViewById(R.id.lvEmployee);
        EmployeeDAO dao = new EmployeeDAO(this);
        list = dao.getAll();

        employeeAdapter = new EmployeeAdapter(this,list);
        lvEmployees.setAdapter(employeeAdapter);
        lvEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Employee emp = list.get(i);
                emloyeeId = emp.getId();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,AddOrEditEmployeeActivity.class);
        Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.btnEdit:

                if(emloyeeId == null)
                {
                    Toast.makeText(this, "Employee Must Be Seclected !", Toast.LENGTH_LONG).show();
                    return;
                }

                bundle.putString("id", emloyeeId);
                intent.putExtra("data", bundle);
                startActivity(intent);
                break;
            case R.id.btnInsert:
                startActivity(intent);
            case R.id.btnDelete:
                if(emloyeeId == null)
                {
                    Toast.makeText(this, "Employee Must Be Seclected !", Toast.LENGTH_LONG).show();
                    return;
                }
                EmployeeDAO dao = new EmployeeDAO(this);
                dao.delete(emloyeeId);
                emloyeeId = null;
                onResume();

                Toast.makeText(this, "Emlpoyee was deleted !", Toast.LENGTH_LONG).show();



        }

    }

}