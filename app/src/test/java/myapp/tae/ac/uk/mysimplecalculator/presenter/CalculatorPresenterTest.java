package myapp.tae.ac.uk.mysimplecalculator.presenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import myapp.tae.ac.uk.mysimplecalculator.R;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Karma on 18/04/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculatorPresenterTest {
    private CalculatorPresenter presenter;
    @Mock
    private CalculatorView view;

    @Before
    public void setUp() throws Exception {
        presenter = new CalculatorPresenter(view);
    }

    @Test
    public void testShowErrorOnEmptyEntry() throws Exception {
        when(view.getEntry()).thenReturn("");
        presenter.onClickCalculateButton();
        verify(view).showEmptyErrorMessage(R.string.error_empty_entry);
    }

    @Test
    public void testMathCalculation() throws Exception {
        Comparable c = mock(Comparable.class);
        when(view.getEntry()).thenReturn("2+3/6*2");
        presenter.onClickCalculateButton();

        when(view.getEntry()).thenReturn("4+5*2");
        presenter.onClickCalculateButton();

        when(view.getEntry()).thenReturn("4+5/2");
        presenter.onClickCalculateButton();

        when(view.getEntry()).thenReturn("4+5/2-1");
        presenter.onClickCalculateButton();

        verify(view).setResult("3.0");
        verify(view).setResult("14.0");
        verify(view).setResult("6.5");
        verify(view).setResult("5.5");
    }

    @Test
    public void testshowErrorWhenEntryFormatError() throws Exception {
        when(view.getEntry()).thenReturn("34+2/-.2");
        presenter.onClickCalculateButton();
        verify(view).showEntryFormatErrorMessage(R.string.error_format_text);

    }
}