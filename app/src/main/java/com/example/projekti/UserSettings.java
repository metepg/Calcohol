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
    private final static String AGEKEY = "ageValue";
    RadioGroup radiosexi;
    RadioButton radiofemale;
    RadioButton radiomale;

    SharedPreferences sharedPrefs;
    EditText ageText;
    EditText weightText;
    String gender;
    String weighted;
    String aged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
       sharedPrefs = getSharedPreferences(USER, MODE_PRIVATE);

        //Haetaan aikaisemmin syötetyt tiedot
        getGender();
        getWeight();
        getAge();
    }
    public void getGender(){
        String gender = sharedPrefs.getString("genderValue", "male");
        Log.d("TAG", gender);
        radiosexi = findViewById(R.id.radioSex);
        radiofemale = findViewById(R.id.radioFemale);
        radiomale = findViewById(R.id.radioMale);

        if(gender.equals("male")){
            radiomale.setChecked(true);
        } else {
            radiofemale.setChecked(true);
        }
    }
    public void getAge(){
        String age = sharedPrefs.getString("ageValue", "25");
        Log.d("TAG", age);
        ageText = findViewById(R.id.newAge);
        ageText.setText(age);
    }
    public void getWeight(){
        String weight = sharedPrefs.getString("weightValue", "70");
        Log.i("TAG", weight);
        weightText = findViewById(R.id.weightStart);
        weightText.setText(weight);
    }
    public String setAge(){
        ageText = findViewById(R.id.newAge);
        aged = ageText.getText().toString();
        return aged;
    }

public String setWeight(){
        weightText = findViewById(R.id.weightStart);
        weighted = weightText.getText().toString();
        return weighted;
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
        super.onBackPressed();
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("genderValue", setGender());
        editor.putString("weightValue", setWeight());
        editor.putString("ageValue", setAge());
        editor.commit();
        finish();
    }
    public void backToMain(View view){
        super.onBackPressed();
        finish();
    }
}