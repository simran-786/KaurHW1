package com.example.kaur1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{
    private ViewGroup svLayout;
    private EditText inputET;
    private TextView[] theTextViews = new TextView[1000];
    private int numTextViews = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.svLayout = (ViewGroup)this.findViewById(R.id.svLayout);
        this.inputET = (EditText)this.findViewById(R.id.inputET);
    }
    public void onRemoveNumberButtonPressed(View v)
    {
        if(this.svLayout.getChildCount() > 0)
        {
            this.svLayout.removeViewAt(0);
            this.numTextViews--;
            this.theTextViews[this.numTextViews] = null;
        }
        else
        {
            Toast.makeText(this, "Nothing To Remove", Toast.LENGTH_SHORT).show();
        }
    }
    private int charToInt(char c)
    {
        return "0123456789".indexOf(c);
    }

    //"123" -> 123
    private int stringToInt(String s)
    {
        int place = 1;
        int sum = 0;
        for(int i = s.length()-1; i >= 0; i--)
        {
            sum = sum + (this.charToInt(s.charAt(i)) * place);
            place = place * 10;
        }
        return sum;
    }
    private TextView findSmallest(TextView[] temp)
    {
        TextView winner = null;
        int winnerPos = -1;

        for(int i = 0; i < temp.length; i++)
        {
            if(temp[i] == null)
            {
                continue;
            }

            if(winner == null ||
                    Integer.parseInt(temp[i].getText().toString()) < Integer.parseInt(winner.getText().toString()))
            {
                winner = this.theTextViews[i];
                winnerPos = i;
            }
        }
        temp[winnerPos] = null;
        return winner;
    }
    public void onSortNumberButtonPressed(View v)
    {
        TextView[] temp = new TextView[this.numTextViews];
        for(int i = 0; i < temp.length; i++)
        {
            temp[i] = this.theTextViews[i];
        }

        this.svLayout.removeAllViews();
        for(int i = 0; i < this.numTextViews; i++)
        {
            this.svLayout.addView(this.findSmallest(temp));
        }
    }
    public void onAddNumberButtonPressed(View v)
    {
        if(this.inputET.getText().length() > 0)
        {
            TextView tv = new TextView(this);
            tv.setText(this.inputET.getText().toString());
            this.svLayout.addView(tv);
            //this.sum = this.sum + Integer.parseInt(this.inputET.getText().toString());
            this.theTextViews[this.numTextViews] = tv;
            this.numTextViews++;
            this.inputET.setText("");
        }
        else
        {
            //System.out.println("***** Need to enter a number!!!!!");
            Toast.makeText(this, "Please enter a number.", Toast.LENGTH_SHORT).show();
        }

    }
}