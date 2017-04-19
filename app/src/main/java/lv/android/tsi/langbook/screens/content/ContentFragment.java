package lv.android.tsi.langbook.screens.content;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import lv.android.tsi.langbook.R;

import static lv.android.tsi.langbook.utilities.functions.DelayUtilities.makeVisibleAfterDelay;
import static lv.android.tsi.langbook.utilities.functions.KeyboardUtilities.hideKeyboard;
import static lv.android.tsi.langbook.utilities.functions.KeyboardUtilities.showKeyBoard;

public class ContentFragment extends Fragment {

    @BindView(R.id.content_text)     EditText mEditTextContext;
    @BindView(R.id.content_edit_fab) FloatingActionButton mBtnEdit;

    private Unbinder unbinder;

    private Boolean editModeEnabled = false;


    public ContentFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        return view;
    }

    @OnClick(R.id.content_edit_fab)
    public void onEditButtonClick(View view){

        notifyActivityEditModeToggleOn();
        toggleEditModeOn();

        editModeEnabled = true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_content, menu);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();

        notifyActivityEditModeToggleOff();
        editModeEnabled = false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home && this.editModeEnabled){

            notifyActivityEditModeToggleOff();
            toggleEditModeOff();
            editModeEnabled = false;
        }
        else if (id == android.R.id.home) getActivity().getSupportFragmentManager().popBackStackImmediate();

        return true;

    }




    public interface OnEditButtonClickListener{
        void onContentEditModeToggledOn();
        void onContentEditModeToggledOff();
    }


    /*
       Private helpers
       */

    private void toggleEditModeOff() {

        hideKeyboard(getActivity());

        mEditTextContext.clearFocus();
        mEditTextContext.setFocusable(false);
        makeVisibleAfterDelay(mBtnEdit, 100);
    }

    private void toggleEditModeOn() {

        mBtnEdit.setVisibility(View.GONE);
        mEditTextContext.setFocusableInTouchMode(true);
        showKeyBoard(getActivity(), mEditTextContext);
    }



    private void notifyActivityEditModeToggleOff() {
        ((OnEditButtonClickListener) getActivity()).onContentEditModeToggledOff();
    }

    private void notifyActivityEditModeToggleOn() {
        ((OnEditButtonClickListener) getActivity()).onContentEditModeToggledOn();
    }



}
