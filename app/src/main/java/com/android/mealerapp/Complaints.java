package com.android.mealerapp;


public class Complaints {
        private ChefAccount _cook;
        private String _complaint;

        public Complaints(){}

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

        public ChefAccount get_cook(){
            return _cook;
        }
        public void set_cook(ChefAccount cook) {
            _cook = cook;
        }
}