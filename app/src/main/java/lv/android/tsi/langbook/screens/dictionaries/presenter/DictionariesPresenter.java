package lv.android.tsi.langbook.screens.dictionaries.presenter;


import android.widget.ImageView;

import java.util.List;

import lv.android.tsi.langbook.domain.Dictionary;

/**
 * Created by Natasa on 22.04.2017.
 */

public interface DictionariesPresenter {

    void performMenuCreateClick();
    void preformMenuDeleteClick();
    void performSelectDictionaryClick(int position);

    void createDictionary(String itemName);

    void deleteCheckedDictionary();
    void toggleDictionaryCheck(int position, ImageView marker);
    void resetCheck();

    void initialize(DictionariesScreen screen);
    void detachScreen();
}
