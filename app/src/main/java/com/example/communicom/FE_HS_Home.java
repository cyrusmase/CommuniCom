package com.example.communicom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FE_HS_Home extends AppCompatActivity
{
    private static final String FILE_NAME = "form_submitNews";
    String versionN_H, versionF_H, versionR_H, versionM_H;
    private List<String[]> elements;
    public int currentIndex = 0;

    TextView pageContentAView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fe_hs_home);

        pageContentAView = findViewById(R.id.pageContent_HomeA);
        CharSequence text = "Sending message...";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this /* MyActivity */, text, duration);

        elements = readFromFile(this, FILE_NAME);
    }
    public void onNewsClickH(View view){
        Intent intentN = new Intent(this, FE_HS_News.class);
        intentN.putExtra("version", versionN_H);
        startActivity(intentN);

        finish();
    }
    public void onForumsClickH(View view)
    {
        Intent intentF = new Intent(this, FE_HS_Forums.class);
        intentF.putExtra("version", versionF_H);
        startActivity(intentF);

        finish();
    }
    public void onResourcesClickH(View view)
    {
        Intent intentR = new Intent(this, FE_HS_Resources.class);
        intentR.putExtra("version", versionR_H);
        startActivity(intentR);

        finish();

    }
    public void onMapsClickH(View view)
    {
        Intent intentM = new Intent(this, FE_HS_Maps.class);
        intentM.putExtra("version", versionM_H);
        startActivity(intentM);

        finish();
    }
    public void onNextClickH(View view){
        currentIndex++;
        if (currentIndex < elements.size()) {
            // Retrieve the new currentElement based on the updated index
            String[] currentElement = elements.get(currentIndex);

            // Make sure the array has enough elements to avoid ArrayIndexOutOfBoundsException
            if (currentElement.length >= 5) {
                // Update TextViews with information from the new currentElement array
                pageContentAView.setText("Log Num: " + currentElement[0]);}
        } else {currentIndex = 0;}


    }

    public void onPrevClickH(View view){
        if (currentIndex > 0) {
            currentIndex--;
            String[] currentElement = elements.get(currentIndex);

            if (currentIndex < elements.size()) {
                // Retrieve the new currentElement based on the updated index

                // Make sure the array has enough elements to avoid ArrayIndexOutOfBoundsException
                if (currentElement.length >= 1) {
                    // Update TextViews with information from the new currentElement array
                    pageContentAView.setText("Log Num: " + currentElement[0]);
                }
            } else {
                currentIndex = 0;
            }
        }
    }

    public static List<String[]> readFromFile(Context context, String fileName) {
        List<String[]> elements = new ArrayList<>();
        try {
            // Open the file for reading
            FileInputStream fileInputStream = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // Read each line from the file
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line into an array of strings
                String[] lineArray = line.split(",");
                // Add each line (element) to the list
                elements.add(lineArray);
            }

            // Close the streams
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return elements;
    }
    public void onSubmitNewsClickH(View view){
        Intent intentSubmit = new Intent (this, FE_Form_SubmitNews.class);
        startActivity(intentSubmit);
    }
}