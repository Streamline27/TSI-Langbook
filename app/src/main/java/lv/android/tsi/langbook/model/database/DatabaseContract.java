package lv.android.tsi.langbook.model.database;

import android.provider.BaseColumns;

/**
 * Created by Natasa on 23.04.2017.
 */

public class DatabaseContract {

    public static final class DictionaryEntry implements BaseColumns{

        public static final String TABLE_NAME = "dictionary";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "creation_date";

    }

    public static final class NoteEntry implements BaseColumns{

        public static final String TABLE_NAME = "note";
        public static final String COLUMN_CAPTION = "caption";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_DATE = "creation_date";
        public static final String COLUMN_DICTIONARY_ID_FK = "dictionary_id";

    }

}
