package gustaventerprises.sudoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView g = (GridView)findViewById(R.id.g);
        final GridAdapter a = new GridAdapter(this);
        g.setAdapter(a);
        Button button_solve = (Button)findViewById(R.id.button_solve);
        Button button_clear = (Button)findViewById(R.id.button_clear);

        button_solve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                a.solve();
            }
        });
        button_clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                a.clear();
            }
        });

        //button_solve.setOnClickListener(e -> a.solve());
    }
}
