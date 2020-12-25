

package shared.turboeditor.views;

import android.content.Context;
import androidx.drawerlayout.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.KeyEvent;

public class CustomDrawerLayout extends DrawerLayout{
    public CustomDrawerLayout(Context context) {
        super(context);
    }

    public CustomDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return false;
    }
}
