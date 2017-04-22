package lv.android.tsi.langbook.screens.dictionaries.presenter;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import lv.android.tsi.langbook.model.Dictionary;
import lv.android.tsi.langbook.interactions.CheckDeleteInteraction;

/**
 * Created by Natasa on 22.04.2017.
 */

public class DictionariesPresenterImpl implements DictionariesPresenter{

    private DictionariesScreen screen;

    private List<Dictionary> dictionaries ;

    private CheckDeleteInteraction checkDeleteInteraction;

    public DictionariesPresenterImpl(DictionariesScreen screen) {
        this.screen = screen;
        this.checkDeleteInteraction = new CheckDeleteInteraction(screen);

        this.dictionaries = getMockData();
    }

    @Override
    public void deleteCheckedDictionary() {

        int position = checkDeleteInteraction.getLastSelectedPosition();
        if (position != -1){
            dictionaries.remove(position);
            screen.refreshDictionariesList();
            checkDeleteInteraction.reset();
        }
    }

    @Override
    public void createDictionary(String itemName) {
        dictionaries.add(new Dictionary(itemName));
        screen.refreshDictionariesList();
    }

    @Override
    public void performMenuCreateClick() {
        screen.showCreateDialog();
    }

    @Override
    public void preformMenuDeleteClick() {
        screen.showDeleteDialog();
    }

    @Override
    public List<Dictionary> getDictionaries() {
        return dictionaries;
    }

    @Override
    public void performSelectDictionaryClick(int position) {
        screen.goToDictionaryNotes(dictionaries.get(position));
    }

    @Override
    public void toggleDictionaryCheck(int position, ImageView marker) {
        this.checkDeleteInteraction.toggleItemCheck(position, marker);
    }

    @Override
    public void resetCheck() {
        this.checkDeleteInteraction.reset();
    }

    /*
        Private helper methods
     */

    private List<Dictionary> getMockData(){
        List<Dictionary> dictionaries = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            dictionaries.add(new Dictionary(Integer.toString(i)));
        }
        return dictionaries;
    }
}
