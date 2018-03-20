package com.beta.giusseppe.canacoveracruz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.beta.giusseppe.canacoveracruz.R;

public class RegistroCorrectoActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_correcto);

        imageView = (ImageView)findViewById(R.id.imageViewCheckVerde);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //correcto
                Intent intent = new Intent(RegistroCorrectoActivity.this, MainActivity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
}
