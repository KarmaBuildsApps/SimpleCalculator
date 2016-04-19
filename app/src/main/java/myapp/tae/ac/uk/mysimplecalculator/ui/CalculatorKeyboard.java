package myapp.tae.ac.uk.mysimplecalculator.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TableLayout;

/**
 * Created by Karma on 18/04/16.
 */
public class CalculatorKeyboard extends GridLayout {
    private Context context;
    private ButtonOnClickListener buttonClickListener;
    private KeyButton[] keyButtons = {KeyButton.CLEAR, KeyButton.DIVIDE, KeyButton.MULTIPLY, KeyButton.BACK,
            KeyButton.ONE, KeyButton.TWO, KeyButton.THREE, KeyButton.MINUS,
            KeyButton.FOUR, KeyButton.FIVE, KeyButton.SIX, KeyButton.PLUS,
            KeyButton.SEVEN, KeyButton.EIGHT, KeyButton.NINE,
            KeyButton.ZERO, KeyButton.DOT, KeyButton.CALCULATE};

    public CalculatorKeyboard(Context context) {
        super(context);
        this.context = context;
        initializeButtons();
    }

    public CalculatorKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeButtons();
    }

    public void setButtonClickListener(ButtonOnClickListener onClickListener) {
        this.buttonClickListener = onClickListener;
    }

    private void initializeButtons() {
        KeyButton keyButton;
        Button button;

        for (int i = 0, c = 0, r = 0; i < keyButtons.length; i++, c++) {
            if (c == 4) {
                c %= 4;
                r++;
            }

            keyButton = keyButtons[i];
            button = new Button(context);
            button.setText(keyButton.getTitle());
            button.setTag(keyButton);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonClickListener.onButtonClick(v);
                }
            });
            GridLayout.LayoutParams buttonLayout = new GridLayout.LayoutParams();
            if (keyButton == KeyButton.PLUS || keyButton == KeyButton.CALCULATE) {
                if (keyButton == KeyButton.PLUS) {
                    buttonLayout.rowSpec = GridLayout.spec(r, 2);
                    buttonLayout.columnSpec = GridLayout.spec(c);
                } else {
                    buttonLayout.columnSpec = GridLayout.spec(c, 2);
                    buttonLayout.rowSpec = GridLayout.spec(r);
                }
            } else {
                buttonLayout.columnSpec = GridLayout.spec(c);
                buttonLayout.rowSpec = GridLayout.spec(r);
            }

            if (keyButton == KeyButton.NINE)
                c++;

            buttonLayout.setGravity(Gravity.CENTER);
            button.setLayoutParams(buttonLayout);
            this.addView(button);
        }
    }

    public interface ButtonOnClickListener {
        public void onButtonClick(View view);
    }

    public enum KeyButton {
        ZERO("0", KeyButtonCategory.NUMBER), ONE("1", KeyButtonCategory.NUMBER),
        TWO("2", KeyButtonCategory.NUMBER), THREE("3", KeyButtonCategory.NUMBER),
        FOUR("4", KeyButtonCategory.NUMBER), FIVE("5", KeyButtonCategory.NUMBER),
        SIX("6", KeyButtonCategory.NUMBER), SEVEN("7", KeyButtonCategory.NUMBER),
        EIGHT("8", KeyButtonCategory.NUMBER), NINE("9", KeyButtonCategory.NUMBER),

        PLUS("+", KeyButtonCategory.OPERATOR), MINUS("-", KeyButtonCategory.OPERATOR),
        MULTIPLY("*", KeyButtonCategory.OPERATOR), DIVIDE("/", KeyButtonCategory.OPERATOR),

        BACK("<--", KeyButtonCategory.MEMO), CLEAR("CE", KeyButtonCategory.OTHERS),
        DOT(".", KeyButtonCategory.OTHERS), CALCULATE("=", KeyButtonCategory.OTHERS);

        private KeyButtonCategory keyCategory;
        private CharSequence title;

        KeyButton(CharSequence bTitle, KeyButtonCategory keyCategory) {
            this.title = bTitle;
            this.keyCategory = keyCategory;
        }

        public CharSequence getTitle() {
            return this.title;
        }

        public enum KeyButtonCategory {
            NUMBER, OPERATOR, MEMO, OTHERS;
        }
    }
}
