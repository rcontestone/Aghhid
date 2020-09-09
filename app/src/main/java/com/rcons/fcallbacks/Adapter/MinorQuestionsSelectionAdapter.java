package com.rcons.fcallbacks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.QST_Helper.QuestionModel;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;


public class MinorQuestionsSelectionAdapter extends RecyclerView.Adapter<MinorQuestionsSelectionAdapter.MyViewHolder> {
    private Context context;
    ArrayList<QuestionModel> questionModels;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final TextView txtq1;
        private final TextView QuestionType;
        private final RadioGroup groupq1;
        private final RadioGroup groupAnswers;

        public MyViewHolder(View view) {
            super(view);
            txtq1 = view.findViewById(R.id.txtq1);
            QuestionType = view.findViewById(R.id.txt_question_type);
            groupq1 = view.findViewById(R.id.groupq1);
            groupAnswers = view.findViewById(R.id.groupAnswers);

        }
    }

    public MinorQuestionsSelectionAdapter(ArrayList<QuestionModel> horizontalList, Context context) {
        this.questionModels = horizontalList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_question_selection_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final QuestionModel model = questionModels.get(position);
        holder.txtq1.setText(" سوالنمبر  " + model.getQuestionNumber() + " : " + model.getQuestionName());
        holder.QuestionType.setText(model.getQuestionType());
        setRadioGroupData(holder.groupq1, model.getOptionName(), model.getAnswer());
        holder.groupAnswers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


            }
        });


    }


    @Override
    public int getItemCount() {
        return questionModels.size();
    }

    void setRadioGroupData(RadioGroup group, ArrayList<String> answers, int correctPosition) {
        //Typeface font = Typeface.createFromAsset(context.getAssets(), "urdu.ttf");
        //  Typeface typeface = context.getResources().getFont(R.font.urdu);
        final RadioButton[] rb = new RadioButton[answers.size()];
        for (int i = 0; i < answers.size(); i++) {
            rb[i] = new RadioButton(context);
            if (i == correctPosition) {
                rb[i].setChecked(true);
            }
            rb[i].setClickable(false);
            //   rb[i].setTypeface(font);
            rb[i].setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            rb[i].setPadding(8 , 8 , 8 , 8);
            rb[i].setLineSpacing(0, 1);
            rb[i].setTextSize(18);

            rb[i].setText(answers.get(i));
            rb[i].setId(i + 100);
            group.addView(rb[i]);
        }

    }

}