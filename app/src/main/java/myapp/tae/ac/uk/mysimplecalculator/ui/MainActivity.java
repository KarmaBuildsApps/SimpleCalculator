package myapp.tae.ac.uk.mysimplecalculator.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myapp.tae.ac.uk.mysimplecalculator.R;
import myapp.tae.ac.uk.mysimplecalculator.presenter.CalculatorPresenter;
import myapp.tae.ac.uk.mysimplecalculator.presenter.CalculatorView;

public class MainActivity extends AppCompatActivity implements CalculatorView, CalculatorKeyboard.ButtonOnClickListener {
    @Bind(R.id.tvOperation)
    TextView tvOperation;
    @Bind(R.id.tvSolution)
    TextView tvSolution;
    @Bind(R.id.tvEntry)
    TextView tvEntry;
    @Bind(R.id.customKeyboard)
    CalculatorKeyboard keyboard;
//    @Bind(R.id.btCalculate)
//    Button btCalculate;

    CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new CalculatorPresenter(this);
        keyboard.setButtonClickListener(this);
    }

    @Override
    public void onClickCalculate(View view) {
        presenter.onClickCalculateButton();
    }

    @Override
    public String getEntry() {
        return tvEntry.getText().toString();
    }

    @Override
    public void showEmptyErrorMessage(int resId) {
        tvEntry.setError(getString(resId));
    }

    @Override
    public void setResult(String result) {
        tvOperation.setText(getEntry());
        tvSolution.setText(result);
        tvEntry.setText("");
    }

    @Override
    public String getSolution() {
        return tvSolution.getText().toString();
    }

    @Override
    public void showEntryFormatErrorMessage(int resId) {
        tvEntry.setError(getString(resId));
    }

    @Override
    public void onButtonClick(View v) {
        CalculatorKeyboard.KeyButton keyButton = (CalculatorKeyboard.KeyButton) v.getTag();
        if (keyButton == CalculatorKeyboard.KeyButton.CALCULATE) {
            onClickCalculate(v);
            return;
        }
        if (keyButton == CalculatorKeyboard.KeyButton.CLEAR) {
            tvEntry.setText("0");
            return;
        }
        String entryText = tvEntry.getText().toString();
        if (keyButton == CalculatorKeyboard.KeyButton.BACK) {
            if (!entryText.equals("0") && !entryText.isEmpty()) {
                if (entryText.length() > 1)
                    entryText = entryText.substring(0, entryText.length() - 1);
                else
                    entryText = "0";
            }
            tvEntry.setText(entryText);
            return;
        }

        if (entryText.equals("0"))
            entryText = "";
        entryText += keyButton.getTitle();
        tvEntry.setText(entryText);

    }
}
