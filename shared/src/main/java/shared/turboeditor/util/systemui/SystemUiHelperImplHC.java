

package shared.turboeditor.util.systemui;

import android.app.Activity;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;

class SystemUiHelperImplHC extends SystemUiHelper.SystemUiHelperImpl
            implements View.OnSystemUiVisibilityChangeListener {

        final View mDecorView;

        SystemUiHelperImplHC(Activity activity, int level, int flags,
                             SystemUiHelper.OnVisibilityChangeListener onVisibilityChangeListener) {
            super(activity, level, flags, onVisibilityChangeListener);

            mDecorView = activity.getWindow().getDecorView();
            mDecorView.setOnSystemUiVisibilityChangeListener(this);
        }


        @Override
        void show() {
            mDecorView.setSystemUiVisibility(createShowFlags());
        }

        @Override
        void hide() {
            mDecorView.setSystemUiVisibility(createHideFlags());
        }

        @Override
        public final void onSystemUiVisibilityChange(int visibility) {
            if ((visibility & createTestFlags()) != 0) {
                onSystemUiHidden();
            } else {
                onSystemUiShown();
            }
        }

        protected void onSystemUiShown() {
            ActionBar ab = ((AppCompatActivity) mActivity).getSupportActionBar();
            if (ab != null) {
                ab.show();
            }

            mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setIsShowing(true);
        }

        protected void onSystemUiHidden() {
            ActionBar ab = ((AppCompatActivity) mActivity).getSupportActionBar();
            if (ab != null) {
                ab.hide();
            }

            mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setIsShowing(false);
        }

        protected int createShowFlags() {
            return View.STATUS_BAR_VISIBLE;
        }

        protected int createHideFlags() {
            return View.STATUS_BAR_HIDDEN;
        }

        protected int createTestFlags() {
            return View.STATUS_BAR_HIDDEN;
        }
    }