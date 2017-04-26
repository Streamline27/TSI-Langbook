package lv.android.tsi.langbook.screens.notes.presenter;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import lv.android.tsi.langbook.model.Model;
import lv.android.tsi.langbook.domain.Dictionary;
import lv.android.tsi.langbook.domain.Note;
import lv.android.tsi.langbook.interactions.CheckDeleteInteraction;

/**
 * Created by Natasa on 22.04.2017.
 */

public class NotesPresenterImpl implements NotesPresenter {

    private NotesScreen screen;
    private Model model;

    private CheckDeleteInteraction checkDeleteInteraction;

    private List<Note> notes;
    private Dictionary dictionary;

    public NotesPresenterImpl(Model model) {

        this.checkDeleteInteraction = new CheckDeleteInteraction();
        this.model = model;
        this.notes = new ArrayList<>();
    }

    @Override
    public void initialize(NotesScreen screen, Dictionary dictionary) {
        this.screen = screen;
        this.checkDeleteInteraction.attachScreen(screen);
        this.dictionary = dictionary;

        this.model.getNotes(dictionary, notes -> {
            this.notes = notes;
            this.screen.displayFetchedData(notes);
        });
    }

    @Override
    public void detachScreen() {
        this.screen = null;
        this.checkDeleteInteraction.detachScreen();
        this.notes = null;
        this.dictionary = null;
    }

    @Override
    public void performUpButtonClick() {
        this.checkDeleteInteraction.reset();
        this.screen.goUpToDictionaryList();
    }

    @Override
    public void performSelectNoteClick(int position) {
        this.checkDeleteInteraction.reset();
        this.screen.goToNoteContent(notes.get(position));
    }

    @Override
    public void performMenuCreateClick() {
        this.screen.showCreateDialog();
    }

    @Override
    public void deleteCheckedNote() {
        int position = this.checkDeleteInteraction.getLastSelectedPosition();
        if (position != -1) {
            model.deleteNote(notes.get(position));
            notes.remove(position);
        }
        this.screen.refreshNotesAdapterList();
    }

    @Override
    public void performMenuDeleteClick() {
        this.screen.showDeleteDialog();
    }

    @Override
    public void toggleNoteCheck(int position, ImageView mark) {
        this.checkDeleteInteraction.toggleItemCheck(position, mark);
    }

    @Override
    public void resetCheck() {
        this.checkDeleteInteraction.reset();
    }

    @Override
    public void createNote(String text) {

        Note note = new Note(text, dictionary.getId());
        note.setDictionaryId(dictionary.getId());
        long id = model.addNote(note);
        note.setId(id);
        notes.add(note);

        this.screen.refreshNotesAdapterList();
    }

    @Override
    public void performBackButtonClick() {
        this.checkDeleteInteraction.reset();
    }
}
