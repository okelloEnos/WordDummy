package com.okellosoftwarez.roomwordssample.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.okellosoftwarez.roomwordssample.DAO.WordDao;
import com.okellosoftwarez.roomwordssample.database.RoomWordDatabase;
import com.okellosoftwarez.roomwordssample.entity.Word;

import java.util.List;

public class WordRepository {
    private WordDao wordDao;
    private LiveData<List<Word>> allWords;

    public WordRepository(Application application) {
        RoomWordDatabase database = RoomWordDatabase.getDatabase(application);
        wordDao = database.wordDao();
        allWords = wordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords(){
        return allWords;
    }

    public void insert(Word word){
        new insertAsyncTask(wordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask <Word, Void, Void> {
        private WordDao wordDaoAsync;

        public insertAsyncTask(WordDao wordDao) {

            wordDaoAsync = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDaoAsync.insert(words[0]);
            return null;
        }
    }
}
