package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView winronde = findViewById(R.id.winnerrond);
        TextView wintim = findViewById(R.id.winnerteam);
        ImageView btnhome = findViewById(R.id.home);

        Bundle bundle=getIntent().getExtras();
        String swinronde=bundle.getString("rondee");
        winronde.setText("Round: "+swinronde);
        String swintim=bundle.getString("winner");
        wintim.setText(swintim);

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}