package lv.android.tsi.langbook.screens.dictionaries.presenter;

import android.database.Cursor;
import android.widget.ImageView;

import java.util.List;

import lv.android.tsi.langbook.model.Model;
import lv.android.tsi.langbook.model.domain.Dictionary;
import lv.android.tsi.langbook.interactions.CheckDeleteInteraction;

/**
 * Created by Natasa on 22.04.2017.
 */

public class DictionariesPresenterImpl implements DictionariesPresenter{

    private DictionariesScreen screen;
    private Model model;

    private List<Dictionary> dictionaries ;

    private CheckDeleteInteraction checkDeleteInteraction;

    public DictionariesPresenterImpl(Model model) {
        this.checkDeleteInteraction = new CheckDeleteInteraction();
        this.dictionaries = model.getDictionaries();
        this.model = model;

    }

    @Override
    public void initialize(DictionariesScreen screen) {
        this.screen = screen;
        this.checkDeleteInteraction.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        this.screen = null;
        this.checkDeleteInteraction.detachScreen();
    }

    @Override
    public void deleteCheckedDictionary() {

        int position = checkDeleteInteraction.getLastSelectedPosition();
        if (position != -1){
            model.deleteDictionary(dictionaries.get(position));
            dictionaries.remove(position);

            screen.refreshDictionariesList();
            checkDeleteInteraction.reset();
        }
    }

    @Override
    public void createDictionary(String itemName) {
        Dictionary dictionary = new Dictionary(itemName);

        long id = model.addDictionary(dictionary);
        dictionary.setId(id);
        dictionaries.add(dictionary);

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
        this.checkDeleteInteraction.reset();
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

}
