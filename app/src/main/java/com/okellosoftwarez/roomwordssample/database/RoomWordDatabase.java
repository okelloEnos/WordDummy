package com.okellosoftwarez.roomwordssample.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.okellosoftwarez.roomwordssample.DAO.WordDao;
import com.okellosoftwarez.roomwordssample.entity.Word;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class RoomWordDatabase extends RoomDatabase {
    private static RoomWordDatabase INSTANCE;

    public abstract WordDao wordDao();

    public static RoomWordDatabase getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (RoomWordDatabase.class){
                if (INSTANCE == null){
//                    create database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomWordDatabase.class, "word_database")
                            .fallbackToDestructiveMigration().addCallback(roomCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new populateDbAsync(INSTANCE).execute();
        }
    };

    private static class populateDbAsync extends AsyncTask<Void, Void, Void> {
        private WordDao dao;
        private String [] words = {"dolphin", "crocodile", "cobra"};

        public populateDbAsync(RoomWordDatabase instance) {
            dao = instance.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();

            for (int i = 0; i <= words.length - 1 ; i++){
                Word word = new Word(words[i]);
                dao.insert(word);
            }
            return null;
        }
    }
}
