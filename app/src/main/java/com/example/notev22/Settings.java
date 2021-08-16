package com.example.notev22;

public class Settings {
    public static final String SHARED_FILE_NAME = "SHARED_PREFERENCE_NAME";

    public static final String IS_BACK_STACK_USED = "IS_BACK_STACK_USED";
    public static final String IS_ADD_FRAGMENT_USED = "IS_ADD_FRAGMENT_USED";
    public static final String IS_REPLACE_FRAGMENT_USED = "IS_REPLACE_FRAGMENT_USED";
    public static final String IS_BACK_IS_REMOVE_FRAGMENT = "IS_BACK_IS_REMOVE_FRAGMENT";
    public static final String IS_DELETE_FRAGMENT_BEFORE_ADD = "IS_DELETE_FRAGMENT_BEFORE_ADD";


    public boolean isBackStack = false;
    public boolean isAddFragment = false;
    public boolean isReplaceFragment = true;
    public boolean isBackIsRemove = false;
    public boolean isDeleteFragment = false;

    public boolean getBackStack() {
        return isBackStack;
    }

    public void setBackStack(boolean backStack) {
        isBackStack = backStack;
    }

    public boolean getAddFragment() {
        return isAddFragment;
    }

    public void setAddFragment(boolean addFragment) {
        isAddFragment = addFragment;
    }

    public boolean getReplaceFragment() {
        return isReplaceFragment;
    }

    public void setReplaceFragment(boolean replaceFragment) {
        isReplaceFragment = replaceFragment;
    }

    public boolean getBackIsRemove() {
        return isBackIsRemove;
    }

    public void setBackIsRemove(boolean backIsRemove) {
        isBackIsRemove = backIsRemove;
    }

    public boolean getDeleteFragment() {
        return isDeleteFragment;
    }

    public void setDeleteFragment(boolean deleteFragment) {
        isDeleteFragment = deleteFragment;
    }

}