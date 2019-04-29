package com.example.x40apps.checklotterynumbers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivityCLN extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    //private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cln);

        //initImageBitmaps();

        RawData rawData = new RawData();
        final TextView txtV = (TextView) findViewById(R.id.textView);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText editText2 = (EditText) findViewById(R.id.editText2);
        final EditText editText3 = (EditText) findViewById(R.id.editText3);
        final EditText editText4 = (EditText) findViewById(R.id.editText4);
        final EditText editText5 = (EditText) findViewById(R.id.editText5);
        final EditText editMegaN = (EditText) findViewById(R.id.megaNumber);
        final CheckBox checkMegaOnly = (CheckBox) findViewById(R.id.checkBox);
        final EditText minMathces = (EditText) findViewById(R.id.editText6);



        final int nmbrOfDraws = rawData.getNmbrOfrawsRaw();



        //database
        final String fiveNbrs[][] = rawData.getRawfiveNbrs();
        final String date[] = rawData.getRawDate();
        final String megaNbrs[] = rawData.getRawmegaNbrs();




         int flagGlobal = 0;

         /* for (int i = 0; i < nmbrOfDraws; i++) {
                  date[i]=String.valueOf(i*111);
                  megaNbrs[i] = String.valueOf(i);
                  for (int j = 0; j < 5; j++) {
                      fiveNbrs[i][j] = String.valueOf(j+(i+6)*100);
                  }
          }*/




        final Button btn = (Button) findViewById(R.id.button);
         CheckNumbers checkNumbers = null;


         btn.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View view) {
                /*Runnable runnable = new Runnable() {
                    @Override
                    public void run() {*/

                int flag = 0;
                int minimumMatched;
                if (minMathces.getText().toString().length()==0) {

                    minMathces.setText("3");
                    minimumMatched = Integer.parseInt(minMathces.getText().toString());
                    // minimumMatched = 3;
                 } else { minimumMatched = Integer.parseInt(minMathces.getText().toString());}

                if ((minimumMatched > 5) || (minimumMatched < 1)) {
                         minimumMatched = 0;
                     } else {
                         minimumMatched = minimumMatched - 1;
                     }

                int allSelectedMatchedCount = 0;
                boolean megaFlag = false;
                int allMatches[] = new int[5];
                int allMatchesAndMega[] = new int[5];

                mNames.clear();

                String flagString[]=new String[nmbrOfDraws];

                //lOOP FOR CHECKING ALL HISTORY NUMBERS
                for (int i = 0; i < nmbrOfDraws; i++) {
                    flag = 0;
                    megaFlag = false;
                    flagString[i]="aaa";

                    //fiveNbrs[1][1]="2";
                     //flag = 0;


                    if (editMegaN.getText().toString().equals(megaNbrs[i])) {               //COMPARING MEGA NUMBER
                                megaFlag=true;
                        }
                    for (int j = 0; j < 5; j++) {
                        if (editText.getText().toString().equals(fiveNbrs[i][j])) {
                            flag++;
                            //Toast.makeText(getApplicationContext(),"y", Toast.LENGTH_SHORT).show();

                            //recyclerView.import
                        }}
                    for (int j = 0; j < 5; j++) {
                        if (editText2.getText().toString().equals(fiveNbrs[i][j])) {
                            flag++;
                        }}
                    for (int j = 0; j < 5; j++) {
                        if (editText3.getText().toString().equals(fiveNbrs[i][j])) {
                            flag++;
                        }}
                    for (int j = 0; j < 5; j++) {
                        if (editText4.getText().toString().equals(fiveNbrs[i][j])) {
                            flag++;
                        }}
                    for (int j = 0; j < 5; j++) {
                        if (editText5.getText().toString().equals(fiveNbrs[i][j])) {
                            flag++;
                        }}
                    //Saving breef info about matched results
                    if (flag>minimumMatched) {
                        if (megaFlag) {
                            allMatchesAndMega[(flag)-1]++;
                        } else
                            allMatches[(flag)-1]++;
                    }
                    //Saving matched results without MegaNumber
                    if (!checkMegaOnly.isChecked())
                        if (flag>minimumMatched) {
                            allSelectedMatchedCount++;
                            flagString[i] = String.valueOf(date[i]) + " test " + String.valueOf(flag) + " megaflag " + String.valueOf(megaFlag);
                            //nbrMatch[i]=flag;
                        }
                    //Saving matched results with MegaNumber
                    if (checkMegaOnly.isChecked())
                        if ((flag>minimumMatched) && (megaFlag)) {
                            allSelectedMatchedCount++;
                            flagString[i] = String.valueOf(date[i]) + " test " + String.valueOf(flag) + " megaflag " + String.valueOf(megaFlag);
                            //nbrMatch[i]=flag;
                        }
                        //txtV.setText();
                }

                 mNames.clear();
                 for (int i = minimumMatched; i < 5; i++){
                     initImageBitmaps((i+1)+" allMatches "+ allMatches[i]+ " times");
                     initImageBitmaps((i+1)+" allMatches and MegaBall "+ allMatchesAndMega[i]+ " times");
                 }

                 final String[] nbrMatch;
                 nbrMatch = new String[allSelectedMatchedCount];
                 flag=0;

                 for (int i = 0; i < nmbrOfDraws; i++) {
                     if (flagString[i] != "aaa") {
                         nbrMatch[flag] = flagString[i];
                         initImageBitmaps(nbrMatch[flag]);
                         flag++;
                    }
                 }
                 Toast.makeText(getApplicationContext(),"Done", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),megaNbrs[1], Toast.LENGTH_SHORT).show();
               // final String[] toast = {""};
               // nbr[0] = checkNumbers.NsCheck(0,2,3,4,5,6);

                    /*}
                };
                btn.postDelayed(runnable, 500); //Delay for 5 seconds to show the result
*/
            }

        });
    }


    private void initImageBitmaps(String putFlagString){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mNames.add(putFlagString);
/*
        //mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Havasu Falls");

       // mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");

        //mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");
*/
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
