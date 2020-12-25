

package shared.turboeditor.home.texteditor;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import shared.turboeditor.R;

public class PageSystemButtons {

    private static final int TIME_TO_SHOW_FABS = 2000;
    final Handler handler = new Handler();
    final Runnable runnable = () -> {
        PageSystemButtons.this.next.setVisibility(View.GONE);
        PageSystemButtons.this.prev.setVisibility(View.GONE);
    };
    FloatingActionButton prev, next;
    PageButtonsInterface pageButtonsInterface;

    public PageSystemButtons(Context context, final PageButtonsInterface pageButtonsInterface, FloatingActionButton prev, FloatingActionButton next) {
        this.prev = prev;
        this.next = next;
        this.pageButtonsInterface = pageButtonsInterface;

        this.next.setBackgroundColor(context.getResources().getColor(R.color.fab_light));
        this.next.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_keyboard_arrow_right));

        this.prev.setBackgroundColor(context.getResources().getColor(R.color.fab_light));
        this.prev.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_keyboard_arrow_left));

        if (pageButtonsInterface.canReadNextPage())
            next.setVisibility(View.VISIBLE);

        if (pageButtonsInterface.canReadPrevPage())
            prev.setVisibility(View.VISIBLE);

        this.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageButtonsInterface.nextPageClicked();
            }
        });

        this.next.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pageButtonsInterface.pageSystemButtonLongClicked();
                return true;
            }
        });

        this.prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageButtonsInterface.prevPageClicked();
            }
        });

        this.prev.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pageButtonsInterface.pageSystemButtonLongClicked();
                return true;
            }
        });
    }

    public void updateVisibility(boolean autoHide) {

        if (pageButtonsInterface.canReadNextPage())
            PageSystemButtons.this.next.setVisibility(View.VISIBLE);
        else
            PageSystemButtons.this.next.setVisibility(View.GONE);

        if (pageButtonsInterface.canReadPrevPage())
            PageSystemButtons.this.prev.setVisibility(View.VISIBLE);
        else
            PageSystemButtons.this.prev.setVisibility(View.GONE);

        /*if(pageButtonsInterface.hasNext())
            next.showFab();
        else
            next.hideFab();

        if(pageButtonsInterface.hasPrev())
            prev.showFab();
        else
            prev.hideFab();*/

        if (autoHide) {
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, TIME_TO_SHOW_FABS);
        } else {
            handler.removeCallbacks(runnable);
        }
    }

    public interface PageButtonsInterface {
        public void nextPageClicked();

        public void prevPageClicked();

        public void pageSystemButtonLongClicked();

        public boolean canReadNextPage();

        public boolean canReadPrevPage();
    }

}
