package myapp.tae.ac.uk.mysimplecalculator.presenter;

import android.view.View;

/**
 * Created by Karma on 18/04/16.
 */
public interface CalculatorView {
    public String getEntry();

    void onClickCalculate(View view);

    void showEmptyErrorMessage(int resId);

    void setResult(String result);

    String getSolution();

    void showEntryFormatErrorMessage(int resId);
}
