package lv.android.tsi.langbook.screens.notes.presenter;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import lv.android.tsi.langbook.model.Note;
import lv.android.tsi.langbook.interactions.CheckDeleteInteraction;

/**
 * Created by Natasa on 22.04.2017.
 */

public class NotesPresenterImpl implements NotesPresenter {

    private NotesScreen screen;

    private CheckDeleteInteraction checkDeleteInteraction;

    private List<Note> notes;

    public NotesPresenterImpl(NotesScreen screen) {
        this.screen = screen;
        this.checkDeleteInteraction = new CheckDeleteInteraction(screen);
        this.notes = getMockContent();
    }

    @Override
    public List<Note> getNotes() {
        return this.notes;
    }

    @Override
    public void performUpButtonClick() {
        this.screen.goUpToDictionaryList();
    }

    @Override
    public void performSelectNoteClick(int position) {
        this.screen.goToNoteContent(notes.get(position));
    }

    @Override
    public void performMenuCreateClick() {
        this.screen.showCreateDialog();
    }

    @Override
    public void deleteCheckedNote() {
        int position = this.checkDeleteInteraction.getLastSelectedPosition();
        if (position != -1) this.notes.remove(position);
        this.screen.refreshNotesList();
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
        notes.add(new Note(text));
    }

    private List<Note> getMockContent() {
        List<Note> noteMocks = new ArrayList<>();
        for (int i = 0; i < 10; i++) noteMocks.add(new Note(Integer.toString(i)));
        return noteMocks;
    }
}
