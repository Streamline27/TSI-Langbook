package lv.android.tsi.langbook.model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import lv.android.tsi.langbook.domain.Dictionary;
import lv.android.tsi.langbook.domain.Note;

/**
 * Created by Strea on 26.04.2017.
 */

public class FirebaseModel implements Model{

    private FirebaseDatabase mFirebaseDatabase;

    public FirebaseModel() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
    }

    public void getNotes(Dictionary dictionary, Model.OnNotesFetchedCallback callback) {
        mFirebaseDatabase.getReference("dictionaries")
                .child(toStr(dictionary.getId()))
                .child("notes").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Note> notes = new ArrayList<Note>();
                for (DataSnapshot s : dataSnapshot.getChildren()){
                    notes.add(s.getValue(Note.class));
                }
                callback.apply(notes);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });


    }


    public void getDictionaries(Model.OnDictionariesFetchedCallback callback) {
        mFirebaseDatabase.getReference("dictionaries").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Dictionary> dictionaries = new ArrayList<Dictionary>();
                for (DataSnapshot s : dataSnapshot.getChildren()){
                    dictionaries.add(s.getValue(Dictionary.class));
                }
                callback.apply(dictionaries);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }

    public long addNote(Note note) {
        DatabaseReference dictionaryNotesFolder = mFirebaseDatabase.getReference("dictionaries")
                                                                   .child(toStr(note.getDictionaryId()))
                                                                   .child("notes");
        long id = dictionaryNotesFolder.push().getKey().hashCode();
        note.setId(id);

        dictionaryNotesFolder.child(toStr(id)).setValue(note);

        return id;
    }

    public long addDictionary(Dictionary dictionary) {
        DatabaseReference dictionariesFolder = mFirebaseDatabase.getReference("dictionaries");
        long id = dictionariesFolder.push().getKey().hashCode();
        dictionary.setId(id);

        dictionariesFolder.child(toStr(id)).setValue(dictionary);

        return id;
    }

    public void deleteNote(Note note) {

        mFirebaseDatabase.getReference("dictionaries")
                .child(toStr(note.getDictionaryId()))
                .child("notes")
                .child(toStr(note.getId()))
                .removeValue();

    }

    public void deleteDictionary(Dictionary dictionary) {

        mFirebaseDatabase.getReference("dictionaries").child(toStr(dictionary.getId())).removeValue();

    }

    public void updateNote(Note note) {
        mFirebaseDatabase.getReference("dictionaries")
                .child(toStr(note.getDictionaryId()))
                .child("notes")
                .child(toStr(note.getId()))
                .setValue(note);
    }


    private String toStr(long id) {
        return Long.toString(id);
    }

}
