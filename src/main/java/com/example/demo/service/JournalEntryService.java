package com.example.demo.service;

import com.example.demo.Repository.JournalEntryRepo;
import com.example.demo.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
       return journalEntryRepo.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId myId){
        return journalEntryRepo.findById(myId);
    }
    public void deleteById(ObjectId myId){
        journalEntryRepo.deleteById(myId);
    }

}
