package marand.drjames;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openAboutActivity(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void buttonClick(View view){
        TextView t =(TextView) findViewById(R.id.instructions_start);
        t.setText(R.string.instruction_start2);
        TextView t2 =(TextView) findViewById(R.id.instructions);
        t2.setText(R.string.instructions2);
        Button b=(Button) findViewById(R.id.button);
        b.animate();
    }

}
