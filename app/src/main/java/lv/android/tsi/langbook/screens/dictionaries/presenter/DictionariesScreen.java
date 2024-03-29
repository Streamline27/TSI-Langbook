package lv.android.tsi.langbook.screens.dictionaries.presenter;

import java.util.List;

import lv.android.tsi.langbook.domain.Dictionary;
import lv.android.tsi.langbook.interactions.CheckDeleteScreen;

/**
 * Created by Natasa on 22.04.2017.
 */

public interface DictionariesScreen extends CheckDeleteScreen{

    void showCreateDialog();
    void showDeleteDialog();

    void refreshDictionariesList();

    void goToDictionaryNotes(Dictionary dictionary);

    void displayFetchedData(List<Dictionary> dictionaries);

    void changeListViewToSpinner();
    void changeSpinnerToListView();
}
