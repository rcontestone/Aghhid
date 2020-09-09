package com.rcons.fcallbacks.QST_Helper;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;

public class Questions extends AppCompatActivity {

    RecyclerView   rvQuestion;
    TextView   txtNoDataFound;
    LinearLayoutManager manager;
    QuestionsAdapter adapter;

    DatabaseAdapter databaseAdapter;
    ArrayList<QuestionModel> MultiQuestions = new ArrayList<>();

    int counter= 0;
    Cursor cursor;

    String region = "";
    String cropID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        region = getIntent().getStringExtra("Region");
        cropID = getIntent().getStringExtra("Crop");



        txtNoDataFound = findViewById(R.id.txtNoDataFound);
        rvQuestion = findViewById(R.id.rvQuestion);

        manager = new LinearLayoutManager(Questions.this, RecyclerView.VERTICAL , false);
        rvQuestion.setLayoutManager(manager);

        databaseAdapter = new DatabaseAdapter(Questions.this);
        databaseAdapter.Open();



        cursor = databaseAdapter.getDataBaseOnRegionAndCrop(cropID,region);
        if(cursor!=null&&cursor.getCount()>0){
            if (cursor.moveToFirst()){

                do{
                    QuestionModel model = new QuestionModel();
                    ArrayList<String> options = new ArrayList<>();
                    String questionNo = cursor.getString(cursor.getColumnIndex("question_no_new"));
                    String questionName = cursor.getString(cursor.getColumnIndex("question_name"));
                    String questionType = cursor.getString(cursor.getColumnIndex("question_type"));
                    model.setQuestionName(questionName);
                    model.setQuestionNumber(questionNo);
                    model.setQuestionType(questionType);


                    Cursor answerCursor =  databaseAdapter.getAnswers(questionNo);
                    if(answerCursor!=null&&answerCursor.getCount()>0){
                        counter = 0;
                        if (answerCursor.moveToFirst()){
                            do{
                                String answer = answerCursor.getString(answerCursor.getColumnIndex("options_name"));
                                String correctAnswer = answerCursor.getString(answerCursor.getColumnIndex("answers"));
                                if(correctAnswer.equalsIgnoreCase("Yes")){
                                    model.setAnswer(counter);
                                }
                                options.add(answer);
                                counter ++;
                            }while(answerCursor.moveToNext());
                        }
                        answerCursor.close();
                    }
                    model.setOptionName(options);
                    MultiQuestions.add(model);


                }while(cursor.moveToNext());
            }
            cursor.close();

        }
        if(MultiQuestions!=null&& MultiQuestions.size()>0){
            txtNoDataFound.setVisibility(View.GONE);
            rvQuestion.setVisibility(View.VISIBLE);
            adapter = new QuestionsAdapter(MultiQuestions, Questions.this);
            rvQuestion.setAdapter(adapter);

        }else {
            rvQuestion.setVisibility(View.GONE);
            txtNoDataFound.setVisibility(View.VISIBLE);
        }



    }

}
