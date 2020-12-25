

package shared.turboeditor.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import shared.turboeditor.R;
import shared.turboeditor.preferences.PreferenceHelper;

/**
 * Created by mac on 15/02/15.
 */
public class AccessoryView extends LinearLayout {

    public IAccessoryView iAccessoryView;
    private TypedValue outValue;

    public AccessoryView(Context context) {
        super(context);
        init();
    }

    public AccessoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AccessoryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        createView();
    }

    public void setInterface(IAccessoryView iBreadcrumb) {
        this.iAccessoryView = iBreadcrumb;
    }


    public void createView() {
        removeAllViews();

        // If we're running on Honeycomb or newer, then we can use the Theme's
        // selectableItemBackground to ensure that the View has a pressed state
        outValue = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.selectableItemBackgroundBorderless, outValue, true);

        String[] characters = {
                ".", ",", ";", "?", "\"", "[", "]", "(", ")", "_", "@", ":", "&", "|", "#", "*", "+", "-", "!", "/", "=", "{", "}", "<", ">"
        };

        for (int i = 0; i < characters.length; i++)
                addAButton(characters[i]);

        updateTextColors();
    }

    private void addAButton(final String text) {
        int dimension = (int) PixelDipConverter.convertDpToPixel(50, getContext());
        //int padding = (int) PixelDipConverter.convertDpToPixel(10, getContext());
        final Button name = new Button(getContext());

        name.setLayoutParams(new LinearLayout.LayoutParams(dimension, dimension));


        name.setGravity(Gravity.CENTER);

        name.setText(text);
        name.setTextSize(15);
        name.setAllCaps(true);

        //name.setPadding(padding, padding, padding, padding);

        name.setClickable(true);

        name.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                iAccessoryView.onButtonAccessoryViewClicked(text);
            }
        });

        name.setBackgroundResource(outValue.resourceId);
        addView(name);
    }

    public void updateTextColors() {
        boolean isLightTheme = PreferenceHelper.getLightTheme(getContext());
        for (int i = 0; i < getChildCount(); i++) {
            ((Button) getChildAt(i)).setTextColor(isLightTheme ? getResources().getColor(android.R.color.background_dark) : getResources().getColor(android.R.color.white));
        }
    }

    public interface IAccessoryView {
        public void onButtonAccessoryViewClicked(String text);
    }
}
