package com.android.mealerapp;


import android.util.CloseGuard;

public class Complaints {
        ChefAccount _cook;
        String _complaint;

        public Complaints(String complaint, ChefAccount cook){
            this._complaint = complaint;
            this._cook = cook;
        }

        public String getComplaint() {
            return _complaint;
        }

        public void setComplaint(String complaint) {
            this._complaint = complaint;
        }

        public String getCookName() {
            return _cook.getName();
        }
    }
