package id.putraprima.skorbola;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText ronde, namatim1, namatim2;
    private ImageView logotim1;
    private ImageView logotim2;
    private Uri uritim1, uritim2;

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int TEAM1_REQUEST_CODE = 1;
    private static final int TEAM2_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ronde=findViewById(R.id.rondke);
        namatim1=findViewById(R.id.team1);
        namatim2=findViewById(R.id.team2);
        logotim1=findViewById(R.id.team1_logo);
        logotim2=findViewById(R.id.team2_logo);
        Button btnnext = findViewById(R.id.btn_team);

        logotim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), TEAM1_REQUEST_CODE);
            }
        });

        logotim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), TEAM2_REQUEST_CODE);
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sronde = ronde.getText().toString();
                String snamatim1 = namatim1.getText().toString();
                String snamatim2 = namatim2.getText().toString();

                Intent intent = new Intent(MainActivity.this, MatchActivity.class);
                intent.putExtra("rondee", sronde);
                intent.putExtra("namatim1", snamatim1);
                intent.putExtra("namatim2", snamatim2);
                intent.putExtra("tim1logo", uritim1.toString());
                intent.putExtra("tim2logo", uritim2.toString());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            Log.d(TAG, "Pilih gambar dicancel");
        }
        else if(requestCode == TEAM1_REQUEST_CODE){
            if(data != null) {
                try {
                    uritim1 = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uritim1);
                    logotim1.setImageBitmap(bitmap);
                } catch (IOException error) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, Objects.requireNonNull(error.getMessage()));
                }
            }
        }
        else if(requestCode == TEAM2_REQUEST_CODE){
            if(data != null){
                try {
                    uritim2 = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uritim2);
                    logotim2.setImageBitmap(bitmap);
                }
                catch (IOException error){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, Objects.requireNonNull(error.getMessage()));
                }
            }
        }
    }
}
//TODO
//Fitur Main Activity
//1. Validasi Input Home Team
//2. Validasi Input Away Team
//3. Ganti Logo Home Team
//4. Ganti Logo Away Team
//5. Next Button Pindah Ke MatchActivity