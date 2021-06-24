package com.example.vehiclepriceprediction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

String currentImagePath =null;
    private static final int IMAGE_REQUEST=1;
    Button clickme ;
    String Texthere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickme = (Button)findViewById(R.id.button);
        clickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Texthere = text.getText().toString();
                Intent intent = new Intent(MainActivity.this,
                        Screen2.class);
                intent.putExtra("Text",Texthere);
                startActivity(intent);
            }
        });

    }
    public void captureImage(View view)
    {
        Intent cameraIntent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(cameraIntent.resolveActivity(getPackageManager())!=null)
        {
            File imageFile =null;
            try {
                imageFile=getImageFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            if (imageFile!=null)
            {
                Uri imageUri = FileProvider.getUriForFile(this,"com.example.vehiclePricePrediction.fileprovider",imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(cameraIntent,IMAGE_REQUEST);
            }
        }
    }
    public void displayImage(View view)
    {
Intent intent =new Intent(this,DisplayImage.class);
intent.putExtra("image_path",currentImagePath);
startActivity(intent);
    }
    private File getImageFile()throws IOException
    {
        String timeStamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName="jpg "+timeStamp+"_";
        File strorageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile=File.createTempFile(imageName,".jpg",strorageDir);
        return imageFile;
    }

}