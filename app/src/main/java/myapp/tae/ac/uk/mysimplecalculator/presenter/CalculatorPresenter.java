package myapp.tae.ac.uk.mysimplecalculator.presenter;

import myapp.tae.ac.uk.mysimplecalculator.R;
import myapp.tae.ac.uk.mysimplecalculator.utility.SimpleMaths;

/**
 * Created by Karma on 18/04/16.
 */
public class CalculatorPresenter {

    private final CalculatorView view;

    public CalculatorPresenter(CalculatorView view) {
        this.view = view;
    }

    public void onClickCalculateButton() {
        String tvEntry = view.getEntry();
        if (tvEntry.isEmpty()) {
            view.showEmptyErrorMessage(R.string.error_empty_entry);
            return;
        }

        if (!tvEntry.matches("\\d+(\\.\\d+)?([\\/\\*\\+-]\\d+(.\\d+)?)+")) {
            view.showEntryFormatErrorMessage(R.string.error_format_text);
            return;
        }
        String result = SimpleMaths.calculateSimpleMaths(tvEntry);
        view.setResult(result);
    }
}
