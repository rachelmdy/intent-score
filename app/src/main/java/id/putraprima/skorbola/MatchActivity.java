package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchActivity extends AppCompatActivity {
    private TextView skortim1;
    private TextView skortim2;
    private int skor1, skor2;
    private String winner, snamatim1, snamatim2, sarond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        TextView tvronde = findViewById(R.id.rondke);
        TextView tvnamatim1 = findViewById(R.id.nmtv1);
        TextView tvnamatim2 = findViewById(R.id.nmtv2);
        skortim1 = findViewById(R.id.skorteam1);
        skortim2 = findViewById(R.id.skorteam2);
        ImageView ivlogotim1 = findViewById(R.id.team1_logo);
        ImageView ivlogotim2 = findViewById(R.id.team2_logo);
        Button btncek = findViewById(R.id.btn_cek);

        Button add1tim1 = findViewById(R.id.tambah1);
        Button add2tim1 = findViewById(R.id.tambah2);
        Button add3tim1 = findViewById(R.id.tambah3);
        Button resettim1 = findViewById(R.id.reset);

        Button add1tim2 = findViewById(R.id.tambah1_2);
        Button add2tim2 = findViewById(R.id.tambah2_2);
        Button add3tim2 = findViewById(R.id.tambah3_2);
        Button resettim2 = findViewById(R.id.reset2);

        skor1 = 0;
        skor2 = 0;
        skortim1.setText(String.valueOf(skor1));
        skortim2.setText(String.valueOf(skor2));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            if (bundle.getString("tim1logo")!=null && bundle.getString("tim2logo")!=null ){
                ivlogotim1.setImageURI(Uri.parse(bundle.getString("tim1logo")));
                ivlogotim2.setImageURI(Uri.parse(bundle.getString("tim2logo")));
            }
            sarond = bundle.getString("rondee");
            snamatim1 = bundle.getString("namatim1");
            snamatim2 = bundle.getString("namatim2");
            tvronde.setText(sarond);
            tvnamatim1.setText(snamatim1);
            tvnamatim2.setText(snamatim2);
        }else {
            Toast.makeText(MatchActivity.this, "Intent is missing", Toast.LENGTH_SHORT).show();
            return;
        }


        add1tim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor1 += 1;
                skortim1.setText(String.valueOf(skor1));
            }
        });
        add2tim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor1 += 2;
                skortim1.setText(String.valueOf(skor1));
            }
        });
        add3tim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor1 += 3;
                skortim1.setText(String.valueOf(skor1));
            }
        });
        resettim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor1 = 0;
                skortim1.setText(String.valueOf(skor1));
            }
        });

        //---------------------

        add1tim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor2 += 1;
                skortim2.setText(String.valueOf(skor2));
            }
        });
        add2tim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor2 += 2;
                skortim2.setText(String.valueOf(skor2));
            }
        });
        add3tim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor2 += 3;
                skortim2.setText(String.valueOf(skor2));
            }
        });
        resettim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor2 = 0;
                skortim2.setText(String.valueOf(skor2));
            }
        });

        //---------------------------

        btncek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(skor1 > skor2){
                    winner = snamatim1;
                }
                else if (skor1 == skor2){
                    winner = "Seri";
                }
                else {
                    winner = snamatim2;
                }

                Intent intent = new Intent(MatchActivity.this, ResultActivity.class);
                intent.putExtra("winner", winner);
                intent.putExtra("rondee", sarond);
                startActivity(intent);
            }
        });
    }
}

//TODO
//1.Menampilkan detail match sesuai data dari main activity
//2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
//3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"