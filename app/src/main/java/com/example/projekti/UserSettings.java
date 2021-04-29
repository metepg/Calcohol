package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class UserSettings extends AppCompatActivity {
    private final static String USER = "properties";
    private static final String WEIGHTKEY = "weightValue";
    private static final String GENDERKEY = "genderValue";
    RadioGroup radiosexi;
    RadioButton radiofemale;
    RadioButton radiomale;

    SharedPreferences sharedPrefs;
    EditText weightText;
    String gender;
    String paino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
       sharedPrefs = getSharedPreferences(USER, MODE_PRIVATE);

        //Haetaan aikaisemmin syötetyt tiedot


        getGender();
        getWeight();
    }
    public void getGender(){
        String gender = sharedPrefs.getString("genderValue", "male");
        Log.d("TAG", gender);
        radiosexi = findViewById(R.id.radioSex);
        radiofemale = findViewById(R.id.radioFemale);
        radiomale = findViewById(R.id.radioMale);

        if(gender.equals("male")){
            radiomale.setChecked(true);
            Log.d("TAG", "paska");
        } else {
            radiofemale.setChecked(true);
        }

    }
    public void getWeight(){
        String weight = sharedPrefs.getString("weightValue", "70");
        System.out.println("meteg");
        Log.i("TAG", weight);
        weightText = findViewById(R.id.weightStart);
        weightText.setText(weight);
    }
public String setWeight(){
        weightText = findViewById(R.id.weightStart);
        paino = weightText.getText().toString();
        return paino;
}

public String setGender(){
    radiosexi = findViewById(R.id.radioSex);
    radiofemale = findViewById(R.id.radioFemale);
    radiomale = findViewById(R.id.radioMale);

    if(radiomale.isChecked()){
        gender = "male";
        radiomale.setChecked(true);
    } else {
        gender = "female";
        radiofemale.setChecked(true);
    }
    System.out.println(radiomale.isChecked());
    System.out.println(radiofemale.isChecked());
    return gender;

}

    public void saveNewValues(View view){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("genderValue", setGender());
        editor.putString("weightValue", setWeight());
        editor.commit();
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }
    public void backToMain(View view){
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }
}