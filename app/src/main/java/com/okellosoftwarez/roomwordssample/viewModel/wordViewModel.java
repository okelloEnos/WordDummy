package com.okellosoftwarez.roomwordssample.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.okellosoftwarez.roomwordssample.entity.Word;
import com.okellosoftwarez.roomwordssample.repository.WordRepository;

import java.util.List;

public class wordViewModel extends AndroidViewModel {
    private WordRepository wordRepository;
    private LiveData<List<Word>> mAllWords;


    public wordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
        mAllWords = wordRepository.getAllWords();
    }

    public LiveData<List<Word>> getmAllWords(){
        return mAllWords;
    }

    public void insert(Word word){
        wordRepository.insert(word);
    }

}
