package com.android.mealerapp;

import static org.junit.Assert.*;

import android.app.Activity;
import android.telephony.mbms.MbmsErrors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DemandTest {

    @Test
    public void Demand_isCorrect(){
        ChefAccount name1 = new ChefAccount();
        UserAccount user1 = new UserAccount();
        Meal burger = new Meal();
        Demand test = new Demand(name1, user1, burger);
        ChefAccount expected = name1;
        ChefAccount actual = test.get_cook();

        assertEquals(expected,actual);
    }

    @Test
    public void Demand_isCorrect2(){
        ChefAccount name1 = new ChefAccount();
        UserAccount user1 = new UserAccount();
        Meal burger = new Meal();
        Demand test = new Demand(name1, user1, burger);
        UserAccount expected = user1;
        UserAccount actual = test.get_client();

        assertEquals(expected,actual);
    }

    @Test
    public void Demand_isCorrect3(){
        ChefAccount name1 = new ChefAccount();
        UserAccount user1 = new UserAccount();
        Meal burger = new Meal();
        Demand test = new Demand(name1, user1, burger);
        Meal expected = burger;
        Meal actual = test.get_item();

        assertEquals(expected,actual);
    }

/*
    @Test
    public void DemandList_isCorrect() {
        List<Demand> test = new List<Demand>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Demand> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] a) {
                return null;
            }

            @Override
            public boolean add(Demand demand) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Demand> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends Demand> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Demand get(int index) {
                return null;
            }

            @Override
            public Demand set(int index, Demand element) {
                return null;
            }

            @Override
            public void add(int index, Demand element) {

            }

            @Override
            public Demand remove(int index) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Demand> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Demand> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<Demand> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
                
        Activity order = new Activity();
        DemandList test1 = new DemandList(order,test);
         = test1;
        List<Demand> actual = test;
    }
    */


    /* we updated complaint methode so we had to make sure it still works*/
    @Test
    public void Complaint_isSuccessful(){
        ChefAccount name1 = new ChefAccount();
        Complaint test2 = new Complaint("This Chef sucks",name1);
        String expected = "This Chef sucks";
        String actual = test2.getComplaint();

        assertEquals(expected,actual);

    }
}