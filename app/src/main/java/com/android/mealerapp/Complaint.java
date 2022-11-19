package com.android.mealerapp;


public class Complaint {
        private ChefAccount _cook;
        private String _complaint;
        private String id;

        public Complaint(){}

        public Complaint(String complaint, ChefAccount cook){
            this._complaint = complaint;
            this._cook = cook;
        }

    public String getId() {
            return id;
    }

    public void setId(String id) {
            this.id = id;
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
