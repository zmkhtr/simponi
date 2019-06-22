package com.sipemandu.sipemandu.UI.ItemFragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sipemandu.sipemandu.Room.Model.DataAnak;
import com.sipemandu.sipemandu.Room.Model.DataAnakOrtu;
import com.sipemandu.sipemandu.Room.Repository;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<DataAnak>> allAnak;
    private LiveData<List<DataAnakOrtu>> allAnakOrtu;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allAnak = repository.getAllAnak();
        allAnakOrtu = repository.getAllAnakOrtu();
    }

    public void insertAnak(DataAnak dataAnak){
        repository.insertAnak(dataAnak);
    }

    public LiveData<List<DataAnak>> getAllAnak(){
        return allAnak;
    }

    public void insertAnakOrtu(DataAnakOrtu dataAnakOrtu){
        repository.insertAnakOrtu(dataAnakOrtu);
    }

    public LiveData<List<DataAnakOrtu>> getAllAnakOrtu(){
        return allAnakOrtu;
    }
}
