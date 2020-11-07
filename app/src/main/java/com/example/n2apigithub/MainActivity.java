package com.example.n2apigithub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView nome;
    private TextView id;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DownloadPessoa download = new DownloadPessoa();

        nome = (TextView) findViewById(R.id.textView5);
        id = (TextView) findViewById(R.id.textView6);

        Button botaoNext = (Button) findViewById(R.id.button);
        botaoNext.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent( MainActivity.this, ActivityFollower.class );
                startActivity(intent);
                finish();
            }
        });


        //Chama Async Task
        download.execute();
    }

    private class DownloadPessoa extends AsyncTask<Void, Void, UserGit> {

        @Override
        protected void onPreExecute() {
            //inicia o dialog
            load = ProgressDialog.show(MainActivity.this,
                    "Aguarde ...", "Obtendo Informações...");
        }

        @Override
        protected UserGit doInBackground(Void... params) {
            Conversor util = new Conversor();
            return util.getInformacao("https://api.github.com/users/giselezrossi");
        }
        @Override
        protected void onPostExecute(UserGit userGit){
            nome.setText(userGit.getNome());
            id.setText(userGit.getId());
            load.dismiss();
        }

    }


}